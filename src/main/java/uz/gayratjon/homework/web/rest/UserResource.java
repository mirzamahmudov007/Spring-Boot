package uz.gayratjon.homework.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gayratjon.homework.entity.User;
import uz.gayratjon.homework.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if (userService.existByLogin(user.getLogin())){
            return new ResponseEntity("Bu login mavjud", HttpStatus.BAD_REQUEST);
        }

        if (checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Password uzunligi 4 dan kam", HttpStatus.BAD_REQUEST);
        }

        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    private Boolean checkPasswordLength(String password){
        return password.length() <= 4;   // password uzunligi 4 tadan kam yoki teng bo'lsa true qaytaradi
    }

}
