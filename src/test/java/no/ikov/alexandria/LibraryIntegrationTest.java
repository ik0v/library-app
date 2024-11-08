package no.ikov.alexandria;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ikov.alexandria.application.author.Author;
import no.ikov.alexandria.application.book.Book;
import no.ikov.alexandria.application.book.Status;
import no.ikov.alexandria.application.location.Location;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class LibraryIntegrationTest {

    @Container
    @ServiceConnection

    PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

   private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @Order(1)
    void shouldReturnListOfAllBooks() throws Exception {
        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(50)));
    }

    @Test
    @Order(2)
    void shouldReturnListOfAvailableBooks() throws Exception {
        this.mockMvc.perform(get("/api/books/available"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", lessThanOrEqualTo(50)));
    }

    @Test
    @Order(3)
    void shouldReturnBookById() throws Exception {
        this.mockMvc.perform(get("/api/books/27"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookId").value("27"));
    }

//    @Test
//    @Order(4)
//    void shouldCreateNewBook() throws Exception {
//        Book testBook = new Book(
//                101,
//                "Tapping the Source",
//                List.of(new Author("Kem", "Nun")),
//                "St Martins Press",
//                Status.AVAILABLE,
//                new Location("Shelf 947")
//        );
//
//        this.mockMvc.perform(post("/api/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(testBook)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @Order(5)
//    void shouldFailToCreateBookWithInvalidData() throws Exception {
//        Book invalidBook = new Book(0, "", null, "", new Status(true), new Location("Section A", "Shelf 1"));
//
//        this.mockMvc.perform(post("/api/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(invalidBook)))
//                .andExpect(status().isBadRequest());
//    }
}
