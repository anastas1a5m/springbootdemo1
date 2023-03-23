package com.example.spring_boot;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public record BookController(BookRepository bookRepository) {

    @GetMapping
    public List<Book> getAllBooks() {                                                                                   //отримати всі книги
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);                                                                            //отримати конкретну книгу за індефікатором
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);                                                                              //створити нову книгу
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookData) {
        Book bookToUpdate = bookRepository.findById(id);                                                                //оновити наявну книгу
        bookToUpdate.setTitle(bookData.getTitle());
        bookToUpdate.setAuthor(bookData.getAuthor());
        bookToUpdate.setDescription(bookData.getDescription());
        bookToUpdate.setIsbn(bookData.getIsbn());
        bookToUpdate.setPrice(bookData.getPrice());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
