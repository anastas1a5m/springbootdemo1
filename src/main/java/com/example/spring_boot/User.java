package com.example.spring_boot;


import jakarta.persistence.*;

import java.util.*;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "user_name")
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> favoriteBooks = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_authors",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> favoriteAuthors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFavoriteBook(Book book) {
        this.favoriteBooks.add(book);

    }

    public void addFavoriteAuthor(Author author) {
        this.favoriteAuthors.add(author);
    }

    public List<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public List<Author> getFavoriteAuthors() {
        return favoriteAuthors;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
