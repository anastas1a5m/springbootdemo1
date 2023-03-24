package com.example.spring_boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSaveAuthor() {
        final Author author = new Author(
                1L,
                "IvanFranko",
                1856.0,
                "ZaharBerkut"
        );

        ResponseEntity<Author> response = restTemplate.postForEntity(
                "/authors", author, Author.class
        );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isEqualTo(author);
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "/authors", List.class
        );
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isNotNull();
    }

    @Test
    public void testGetAuthorById() throws Exception {
        final Author author = new Author(
                222L,
                "TarasShevchenko",
                1814.0,
                "Kobzar"
        );

        restTemplate.postForEntity(
                "/authors", author, Author.class
        );

        ResponseEntity<Author> response = restTemplate.getForEntity(
                "/authors/" + author.getId(), Author.class
        );
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isNotNull();
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        final Author author = new Author(
                123L,
                "LinaKostenko",
                1930.0,
                "MarusyaChuray"
        );
        restTemplate.postForEntity(
                "/authors", author, Author.class
        );

        author.setName("Updated Test Author");

        restTemplate.put(
                "/authors/" + 123, author
        );

        ResponseEntity<Author> response = restTemplate.getForEntity(
                "/authors/123", Author.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isEqualTo(author);
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        final Author author = new Author(
                231L,
                "lesyaUkrainka",
                1871.0,
                "LisovaPisnya"
        );
        author.setName("Delete Test Author");

        restTemplate.delete("/authors/231");

        ResponseEntity<Author> response = restTemplate.getForEntity(
                "/authors/231", Author.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody())
                .isNull();
    }
}
