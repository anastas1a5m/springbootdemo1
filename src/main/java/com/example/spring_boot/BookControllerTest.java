package com.example.spring_boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void TestAddBook() {
        final Book book = new Book(
                555L,
                "ZaharBerkut",
                "IvanFranko",
                500.00,
                "1234566789",
                "AboutZaharBerkut"
        );
        ResponseEntity<Book> response = restTemplate.postForEntity(
                "/books", book, Book.class
        );
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isEqualTo(book);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "/books", List.class
        );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isNotNull();
    }

    @Test
    public void testGetBookById() throws Exception {
        final Book book = new Book(
                567L,
                "Kobzar",
                "TarasShevchenko",
                800.00,
                "2345877789",
                "ZbirkaVirshiv"
        );
        restTemplate.postForEntity(
                "/books", book, Book.class
        );

        ResponseEntity<Book> response = restTemplate.getForEntity(
                "/books/" + book.getId(), Book.class
        );
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isNotNull();
    }

    @Test
    public void testUpdateBook() throws Exception {
        final Book book = new Book(
                765L,
                "LisovaPisnya",
                "lesyaUkrainka",
                300.00,
                "2342347789",
                "LisovaPisnya"
        );

        restTemplate.postForEntity(
                "/books", book, Book.class
        );

        book.setTitle("Updated Test Book");

        restTemplate.put(
                "/books/" + 765, book
        );

        ResponseEntity<Book> response = restTemplate.getForEntity(
                "/books/765", Book.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isEqualTo(book);
    }

    @Test
    public void testDeleteBook() throws Exception {
        final Book book = new Book(
                444L,
                "MarusyaChuray",
                "LinaKostenko",
                200.00,
                "2342987789",
                "MarusyaChuray"
        );

        book.setTitle("Delete Test Book");

        restTemplate.delete("/books/444");

        ResponseEntity<Book> response = restTemplate.getForEntity(
                "/books/444", Book.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isNull();
    }
}
