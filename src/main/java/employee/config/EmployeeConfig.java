package employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import employee.repository.EmployeeRepository;
import employee.service.EmpService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "employee")
public class EmployeeConfig {

    @Bean
    public EmployeeRepository empRepository() {
        return new EmployeeRepository();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public EmpService empService(final EmployeeRepository employeeRepository,
                                 final ObjectMapper objectMapper) {
        return new EmpService(employeeRepository, objectMapper);
    }
}
