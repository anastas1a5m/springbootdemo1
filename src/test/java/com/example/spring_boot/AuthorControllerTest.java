package com.example.spring_boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;
    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void testGetAuthorById() throws Exception {
        long id = 102L;
        Author author = new Author(id, "Ivan Franko");
        when(authorService.findById(anyLong())).thenReturn(author);

        mockMvc.perform(get("/authors/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Ivan Franko"));

        verify(authorService,times(1)).findById(eq(id));
    }

    @Test
    public void shouldCreateAuthor() throws Exception {
        Author author = new Author(1L, "Taras Shevchenko");
        when(authorService.save(any(Author.class))).thenReturn(new Author(1L, "Taras Shevchenko"));

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(author)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Taras Shevchenko"));

        ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
        verify(authorService, times(1)).save(authorArgumentCaptor.capture());

        assertThat(authorArgumentCaptor.getValue())
                .isEqualTo(author);

    }

    @Test
    public void testUpdateAuthor() throws Exception {
        Author author = new Author(152L, "Lesya Ukrainka");
        when(authorService.update(eq(152L), any(Author.class))).thenReturn(author);

        mockMvc.perform(put("/authors/152")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(author)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(152L))
                .andExpect(jsonPath("$.name").value("Lesya Ukrainka"));
        ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
        verify(authorService, times(1)).update(eq(152L), authorArgumentCaptor.capture());

        assertThat(authorArgumentCaptor.getValue())
                .isEqualTo(author);

    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Long id = 203L;
        Author author = new Author(id, "Olena Pchilka");
        doNothing().when(authorService).deleteById(anyLong());

        mockMvc.perform(delete("/authors/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(authorService,times(1)).deleteById(eq(id));
    }

}


