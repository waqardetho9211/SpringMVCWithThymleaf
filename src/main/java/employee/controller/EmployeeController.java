package employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import employee.controller.input.NewEmpInput;
import employee.dto.EmployeeDto;
import employee.service.EmpService;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ObjectMapper objectMapper;

//    @PostMapping
//    public ResponseEntity<EmployeeDto> addEmp(@RequestBody NewEmpInput emp) {
//        EmployeeDto employeeDto = empService.addEmp(objectMapper.convertValue(emp, EmployeeDto.class));
////        LOG.info("Add Employee: " + employeeDto);
//        return ResponseEntity.ok(employeeDto);
//    }


//    @RequestMapping(value = "/executesampleservice", method = RequestMethod.POST,
//            consumes = {"multipart/form-data"})
//    @ResponseBody
//    public boolean executeSampleService(
//            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
//
//        List<Employee> emp = objectMapper.readValue(file.getInputStream(), new TypeReference<List<Employee>>(){});
//
//        JsonNode jsonNode = objectMapper.readTree(file.getInputStream());
//        System.out.println(jsonNode.get("foo").get("bar"));
//        System.out.println("Successful" + file.getSize());
//        return false;
//    }

    @GetMapping(value={"/", "/index"})
    public String getHomePage(Model model){

        return "index.html";
    }

    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        return "login.html";
    }

    @GetMapping(value="/logout-success")
    public String getLogoutPage(Model model){
        return "logout.html";
    }

//    @GetMapping("/employees")
//    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
////        LOG.info("Get All Employees: " + empService.getAllEmployees());
////        System.out.println(getPrincipal());
//        return ResponseEntity.ok(empService.getAllEmployees());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDto> getEmp(@PathVariable("id") Long id) {
////        LOG.info("Get Employee: " + empService.getEmpById(id));
//        return ResponseEntity.ok(empService.getEmpById(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> removeEmp(@PathVariable("id") Long id) {
////        LOG.info("Remove Employee: " + empService.getEmpById(id));
//        empService.removeEmp(id);
//        return ResponseEntity.ok(String.format("Employee with ID %s removed", id));
//    }

}