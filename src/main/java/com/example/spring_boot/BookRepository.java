package com.example.spring_boot;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(Long id);
    Book save (Book book);
    void update(Book book);
    void deleteById(Long id);

    @Repository
    public class BookRepositoryImp implements BookRepository {
        private final List<Book>  books = new ArrayList<>();

        @Override
        public List<Book> findAll() {
            return books;
        }

        @Override
        public Book findById(Long id){
            return books.stream()
                    .filter(book -> book.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Book save(Book book){
            books.add(book);
            return book;
        }

        @Override
        public void update(Book book){
            Book oldBook = findById(book.getId());
            if (oldBook != null){
                oldBook.setTitle(book.getTitle());
                oldBook.setAuthor(book.getAuthor());
                oldBook.setPrice(book.getPrice());
            }
        }

        @Override
        public void deleteById(Long id){
            books.removeIf(book -> book.getId().equals(id));
        }

    }

}
