package com.example.msusers.controller;

import com.example.msusers.domain.User;
import com.example.msusers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username={username}")
    public ResponseEntity<User> getUserByUsername(
            @PathVariable String username
    ) {
        try {
            return ResponseEntity.ok(
                    userService.getUserByUsername(username));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @GetMapping("/email={email}")
    public ResponseEntity<User> getUserByEmail(
            @PathVariable String email
    ) {
        try {
            return ResponseEntity.ok(
                    userService.getUserByEmail(email));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound()
                    .build();
        }
    }

}
