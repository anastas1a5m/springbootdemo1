package com.example.spring_boot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
                this.bookRepository = bookRepository;
    }

    @Transactional
    public Book update(Long id, Book bookData) {
        Book bookToUpdate = bookRepository.findById(id).get();                                                                //оновити наявну книгу
        bookToUpdate.setTitle(bookData.getTitle());
        bookToUpdate.setAuthor(bookData.getAuthor());
        bookToUpdate.setPublishedDate(bookData.getPublishedDate());
        bookToUpdate.setPrice(bookData.getPrice());
        return bookRepository.save(bookToUpdate);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
