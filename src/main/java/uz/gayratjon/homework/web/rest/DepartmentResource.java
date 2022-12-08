package uz.gayratjon.homework.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gayratjon.homework.entity.Department;
import uz.gayratjon.homework.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentResource {
    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity save(@RequestBody Department department){
        Department result = departmentService.save(department);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Department result = departmentService.getOne(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/departments")
    public ResponseEntity getAll(){
        List<Department> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        String result = departmentService.delete(id);
        return ResponseEntity.ok(result);
    }



}
