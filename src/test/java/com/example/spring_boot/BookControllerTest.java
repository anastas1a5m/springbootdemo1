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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,  "Lisova Pisnya", new Date(1998, 12, 11), 700.0, new Author(1L, "Lesya Ukrainka")));
        books.add(new Book( 2L, "Mavka",  new Date(2023, 10, 13), 300.0, new Author(2L, "Ivan Franko")));
        books.add(new Book(3L, "Eneida",  new Date(1997, 10, 9), 200.0, new Author(3L, "Ivan Kot")));
        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Lisova Pisnya"))
                // Pohuy for now
//                .andExpect(jsonPath("$[0].publishedDate").value(books.get(0).getPublishedDate().toLocalDate().toEpochSecond(LocalTime.now(), ZoneOffset.UTC)))
                .andExpect(jsonPath("$[0].price").value("700.0"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Mavka"))

//                .andExpect(jsonPath("$[0].publishedDate").value("2023-10-13"))
                .andExpect(jsonPath("$[1].price").value("300.0"))
                .andExpect(jsonPath("$[2].id").value(3L))
                .andExpect(jsonPath("$[2].title").value("Eneida"))
//                .andExpect(jsonPath("$[0].publishedDate").value("1997-10-9"))
                .andExpect(jsonPath("$[2].price").value("200.0"));

        verify(bookService, times(1)).findAll();
    }

    @Test
    public void testGetBookById() throws Exception {
        long id = 3;
        Book book = new Book(id, "Zahar Berkut",  new Date(1988, 12, 11), 700.0, new Author (id, "Ivan Franko"));
        when(bookService.findById(anyLong())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/books/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Zahar Berkut"))
//               .andExpect(jsonPath("$.publishedDate").value("1988-12-11"))
                .andExpect(jsonPath("$.price").value("700.0"));

        verify(bookService,times(1)).findById(eq(id));
    }

    @Test
    public void shouldCreateBook() throws Exception {
        Long id = 5L;
        Book book = new Book(id, "Kobzar", new Date(1978, 12, 11), 1000.0, new Author(id, "Taras Shevchenko"));
        when(bookService.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Kobzar"))
  //              .andExpect(jsonPath("$.publishedDate").value("1978-12-11"))
                .andExpect(jsonPath("$.price").value("1000.0"));

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookService, times(1)).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .isEqualTo(book);


    }

    @Test
    public void testUpdateBook() throws Exception {
        Long id = 10L;
        Book book = new Book(id, "Tugrolovu", new Date(1978, 12, 11), 500.0, new Author(id, "Ivan Bagryanuy"));
        when(bookService.update(eq(id), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/books/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Tugrolovu"))
  //              .andExpect(jsonPath("$.publishedDate").value("1978-12-11"))
                .andExpect(jsonPath("$.price").value("500.0"));

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookService, times(1)).update(eq(id), bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .isEqualTo(book);
    }

    @Test
    public void testDeleteBook() throws Exception {
     Long id = 20L;
        Book book = new Book(id, "Pchilka",  new Date(1989, 12, 11), 400.0, new Author (id, "Olena Pchilka"));
        doNothing().when(bookService).deleteById(anyLong());

        mockMvc.perform(delete("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService,times(1)).deleteById(eq(id));
    }

}