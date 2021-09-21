package employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import employee.entity.Employee;
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

    @GetMapping("/guests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    public String getAllEmployees(Model model) {
       List<Employee> guests = empService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests-view.html";
    }
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