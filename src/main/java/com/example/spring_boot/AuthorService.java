package com.example.spring_boot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author update(Long id, Author authorData) {
        Author authorToUpdate = authorRepository.findById(id).orElse(null);
        authorToUpdate.setName(authorData.getName());
        return authorRepository.save(authorToUpdate);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public String deleteById() {
        Long id = null;
        authorRepository.deleteById(id);
        return null;
    }
}
