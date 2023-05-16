package com.example.spring_boot;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return authorId == book.authorId && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorId, publishedDate, price);
    }
}
