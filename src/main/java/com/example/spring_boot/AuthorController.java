package com.example.spring_boot;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public record AuthorController(AuthorService authorService) {

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }


    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        return authorService.update(id, authorDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
       authorService.deleteById(id);
    }
}
