package com.bvr.h2project;

import com.bvr.h2project.entity.Book;
import com.bvr.h2project.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class H2projectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookRepository bookRepository;



	@Test
	void contextLoads()  throws Exception{
		//Keep the application context running (to launch h2-console while running the test case)
		Thread.sleep(Long.MAX_VALUE);
	}

	@Test
	void testCreateBook() throws Exception {
		mockMvc.perform(post("/api/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"title\": \"Test Book\", \"author\": \"Test Author\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("Test Book")))
				.andExpect(jsonPath("$.author", is("Test Author")));
	}


	@Test
	void testGetAllBooks() throws Exception {
		Book book = new Book();
		book.setTitle("Test Book");
		book.setAuthor("bharath");
		bookRepository.save(book);

		mockMvc.perform(get("/api/books"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title", is("Test Book")))
				.andExpect(jsonPath("$[0].author", is("Test Author")));
	}

	@Test
	void testGetBookById() throws Exception {
		Book book = new Book();
		book.setTitle("Test Book");
		book.setAuthor("bharath");
		book = bookRepository.save(book);

		mockMvc.perform(get("/api/books/" + book.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("Test Book")))
				.andExpect(jsonPath("$.author", is("Test Author")));
	}

	@Test
	void testUpdateBook() throws Exception {
		Book book = new Book();
		book.setTitle("Old Title");
		book.setAuthor("Old Author");
		book = bookRepository.save(book);

		mockMvc.perform(put("/api/books/" + book.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"title\": \"New Title\", \"author\": \"New Author\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("New Title")))
				.andExpect(jsonPath("$.author", is("New Author")));
	}

	@Test
	void testDeleteBook() throws Exception {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Test Book");
		book.setAuthor("Test Author");
		book = bookRepository.save(book);

		mockMvc.perform(delete("/api/books/" + 2))
				.andExpect(status().isNoContent());

		Optional<Book> deletedBook = bookRepository.findById(book.getId());
		assert !deletedBook.isPresent();
	}


}
