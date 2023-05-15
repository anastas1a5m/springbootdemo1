package com.example.spring_boot;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "authorId")
    private int authorId;

    @Column(name = "publishedDate")
    private Date publishedDate;

    @Column(name = "price")
    private Double price;

    public Book(Long id, String title, int authorId, Date publishedDate, Double price) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publishedDate = publishedDate;
        this.price = price;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
