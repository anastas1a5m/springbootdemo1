package com.example.spring_boot;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public record UserController(UserService userService) {

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
     @GetMapping  ("/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService. findByUsername(username);
    }
    @PostMapping ("/")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
    }

}
