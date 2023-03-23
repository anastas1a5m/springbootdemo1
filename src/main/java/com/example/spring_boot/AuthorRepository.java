package com.example.spring_boot;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface AuthorRepository{
    List<Author> findAll();
    Author findById(Long id);
    Author save (Author Author);
    void update(Author author);
    void deleteById(Long id);

    @Repository
    public class AuthorRepositoryImp implements AuthorRepository {
        private final List<Author>  author = new ArrayList<>();

        @Override
        public List<Author> findAll() {
            return author;
        }

        @Override
        public Author findById(Long id) {
            return author.stream()
                    .filter(book -> book.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Author save(Author Author) {
            author.add(Author);
            return Author;
        }

        @Override
        public void update(Author author) {
            Author author1 = findById(author.getId());
            if (author != null){
                author.setName(author.getName());
                author.setYearOB(author.getYearOB());
                author.setBooks(author.getBooks());
        }

        }

        @Override
        public void deleteById(Long id){
            author.removeIf(book -> book.getId().equals(id));
        }

        }

    }
