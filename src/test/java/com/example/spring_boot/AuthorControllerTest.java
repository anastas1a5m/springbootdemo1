package com.example.spring_boot;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void testGetAuthorById() throws Exception {
        Author author = new Author(102L, "Ivan Franko");
        when(authorService.findById(102L)).thenReturn(author);

        mockMvc.perform(get("/authors/102"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(102L))
                .andExpect(jsonPath("$.name").value("Ivan Franko"));
    }

    @Test
    public void shouldSaveAuthor() throws Exception {
        Author author = new Author(0L, "Taras Shevchenko");
        when(authorService.save(author)).thenReturn(new Author(1L, "Taras Shevchenko"));

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Taras Sevshenko"));
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        Author author = new Author(0L, "Lesya Ukrainka");
        when(authorService.update(152L, author)).thenReturn(
                new Author(152L, author.getName())
        );

        mockMvc.perform(get("/authors/152"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(152L))
                .andExpect(jsonPath("$.name").value("Lesya Ukrainka"));
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Author author = new Author(203L, "Olena Pchilka");
        Long id = null;
        when(authorService.deleteById());

        mockMvc.perform(get("/authors/203"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(203L))
                .andExpect(jsonPath("$.name").value("Olena Pchilka"));
    }

}


