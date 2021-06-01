package employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import employee.dto.EmployeeDto;
import employee.repository.EmployeeRepository;
import employee.entity.Employee;

@Service
public class EmpService {

    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public EmpService(final EmployeeRepository employeeRepository,
                      final ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    public EmployeeDto addEmp(EmployeeDto employeeDto) {
        Employee employee = objectMapper.convertValue(employeeDto, Employee.class);
        employee = employeeRepository.add(employee);
        return objectMapper.convertValue(employee, EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmp(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId());
        employee.setName(employeeDto.getName());

        employeeRepository.update(employee);
        return employeeDto;
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allEmployee = employeeRepository.findAll();
        return objectMapper.convertValue(allEmployee, new TypeReference<List<EmployeeDto>>() {
        });
    }

    public EmployeeDto getEmpById(Long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            return objectMapper.convertValue(employee, EmployeeDto.class);
        }
        throw new RuntimeException(String.format("There isn't an employee having ID = %s", id));
    }

    public void removeEmp(Long id) {
        employeeRepository.delete(id);
    }

    public boolean executeSampleService(final MultipartFile file) {
        System.out.println("File Received");
        return true;
    }
}
