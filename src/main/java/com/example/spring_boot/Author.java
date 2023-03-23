package com.example.spring_boot;

public class Author {
    private Long id;
    private String name;
    private Double yearOB;
    private String books;

    public Author(Long id, String name, Double yearOB, String books) {
        this.id = id;
        this.name = name;
        this.yearOB = yearOB;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public Double getYearOB() {
        return yearOB;
    }

    public String getBooks() {
        return books;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOB(Double yearOB) {
        this.yearOB = yearOB;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
