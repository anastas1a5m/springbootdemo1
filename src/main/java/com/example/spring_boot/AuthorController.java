package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController{
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    private AuthorRepository authorRepository;
@GetMapping
    public List<Author> getAllAuthors(){
    return authorRepository.findAll();
}

@GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
    return authorRepository.findById(id);
}
@PostMapping
    public Author createAuthor(@RequestBody Author author){
    return authorRepository.save(author);
}

@PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id,  @RequestBody Author authorDetails){
    Author authorToUpdate = authorRepository.findById(id);                                                                //оновити наявну книгу
    authorToUpdate.setName(authorDetails.getName());
    authorToUpdate.setYearOB(authorDetails.getYearOB());
    authorToUpdate.setBooks(authorDetails.getBooks());
    return authorRepository.save(authorToUpdate);
}

@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
    Author author = authorRepository.findById(id);

    authorRepository.deleteById(id);
    return ResponseEntity.ok().build();
    }
}
