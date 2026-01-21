
package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    private final UserService service;

    public UserControllerV1(@Qualifier("userServiceV1") UserService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User savedUser = service.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(user); // 200 OK
    }


    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAllUsers());
    }



    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @Valid @RequestBody User user) {

        User updatedUser = service.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully with id " + id);
    }
}
