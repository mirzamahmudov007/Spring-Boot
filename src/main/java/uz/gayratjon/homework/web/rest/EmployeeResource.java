package uz.gayratjon.homework.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gayratjon.homework.entity.Employee;
import uz.gayratjon.homework.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity create (@RequestBody Employee employee){
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/employees")
    public ResponseEntity update(@RequestBody Employee employee){
        if (employee.getId() == null){
            return ResponseEntity.badRequest().body("Error no id");
        }

        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    // bitta qatorni db dan olish
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Long id){
        Employee result = employeeService.findById(id);
        return ResponseEntity.ok(result);

    }
/*
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll1(@RequestParam String name,
                                                 @RequestParam String lastName){
        List<Employee> employees = employeeService.findAll1(name, lastName);
        return ResponseEntity.ok(employees);
    }
*/
/*
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll2(@RequestParam String name,
                                                 @RequestParam String lastName,
                                                 @RequestParam String emailEnding){
        List<Employee> employees = employeeService.findAll2(name, lastName, emailEnding);
        return ResponseEntity.ok(employees);
    }

 */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll1(@RequestParam String name,
                                                  @RequestParam String lastName){
        List<Employee> employees = employeeService.findAll3(name, lastName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/get-search")
    public ResponseEntity<List<Employee>> getSearch(@RequestParam String jumla){
        List<Employee> employees = employeeService.getSearch(jumla);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }


}
