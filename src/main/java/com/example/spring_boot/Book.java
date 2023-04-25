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

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "published_date")
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

    public void setAuthorId(int author_id) {
        this.authorId = author_id;
    }

    public void setPublishedDate(Date published_date) {
        this.publishedDate = published_date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
