package com.example.spring_boot;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public record UserController(UserService userService) {

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
    }

    @PostMapping("/favorites/books")
    public User addFavoriteBook(@RequestBody Book book, Principal principal) {
        return userService.addFavoriteBook(principal.getName(), book);
    }

    @PostMapping("/favorites/authors")
    public User addFavoriteAuthor(@RequestBody Author author, Principal principal) {
        return userService.addFavoriteAuthor(principal.getName(), author);
    }

    @GetMapping("/info")
    public User getUserInfo(Principal principal) {
        return userService.getUserInfo(principal.getName());
    }


}
