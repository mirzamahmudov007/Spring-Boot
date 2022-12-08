package uz.gayratjon.homework.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gayratjon.homework.model.Course;
import uz.gayratjon.homework.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {

/*
    @GetMapping("/students")
    public ResponseEntity hello() {
        return ResponseEntity.ok("Hello Spring !!!");
    }
*/


    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }


    @PostMapping("/students/list")
    public ResponseEntity createAll(@RequestBody List<Student> students){
        return ResponseEntity.ok(students);
    }


    @PutMapping("/students")
    public ResponseEntity update(@RequestBody Student student){
        student.setLastName("Test uchun");
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity updateSecond(@PathVariable Long id,
                                       @RequestBody Student student){
        student.setLastName("Test uchun");  // LastNameni "Test uchun" ga o'zgartirib qo'yish
        student.setId(id);  // pathvariable da kelgan id ni o'zlashtirish
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable Long id){   // ResponseEntity<Student>  ---> methoddan aniq Student ning objecti qaytadi
        Student student = new Student();
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity getAll(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam String lastName,
                                 @RequestParam Integer age){
        List<Student> students = new ArrayList<>();
        Course course1 = new Course();
        course1.setId(21L);
        course1.setName("Mechanics ");
        students.add(new Student(id, name, lastName, age, course1));
        students.add(new Student(24L, "John", "Giova", 27, course1));
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/students/{id}/{id1}")
    public ResponseEntity delete(@PathVariable Long id,
                                 @PathVariable Long id1){
        return ResponseEntity.ok(id + " and " + id1 + " id data are deleted successfully !!!");
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity patchUpdate(@PathVariable Long id,
                                      @RequestBody Student student){
        return ResponseEntity.ok(student);
    }


}
