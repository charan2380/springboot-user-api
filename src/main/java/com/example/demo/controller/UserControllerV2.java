
package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
    private final UserService service;
    public UserControllerV2(@Qualifier("userServiceV2") UserService service){ this.service = service; }


    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = service.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
