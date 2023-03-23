package com.example.spring_boot;

public class Book {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String Isbn;
    private String Description;

    public Book(Long id, String title, String author, Double price, String Isbn, String Description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.Isbn = Isbn;
        this.Description = Description;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Double getPrice() {
        return price;
    }

    public String getIsbn() {
        return Isbn;
    }

    public String getDescription() {
        return Description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
