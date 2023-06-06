package com.example.spring_boot;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public record BookController(BookService bookService) {

    @GetMapping("/")
    public List<Book> getAllBooks() {                                                                                   //отримати всі книги
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id);                                                                            //отримати конкретну книгу за індефікатором
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);                                                                              //створити нову книгу
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookData) {
        return bookService.update(id, bookData);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
