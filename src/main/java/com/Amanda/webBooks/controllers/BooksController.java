package com.Amanda.webBooks.controllers;


import com.Amanda.webBooks.models.Books;
import com.Amanda.webBooks.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {

    /**
     * The service for managing books.
     */

    private final BookService bookService;

    /**
     * Retrieves all books.
     *
     * @return a ResponseEntity containing the list of books
     */
    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book to retrieve
     * @return a ResponseEntity containing the book if found,
     * otherwise not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get one book", description = "Retrieves a specific book by ID")
    public ResponseEntity<Books> getOneBook(
            @PathVariable final Long id) {
        Optional<Books> book = bookService.getOneBook(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Saves a new book.
     *
     * @param book the book to save
     * @return a ResponseEntity containing the saved book
     */
    @PostMapping
    @Operation(summary = "Save a book", description = "Saves a new book")
    public ResponseEntity<Books> saveBook(
            @RequestBody final Books book) {
        Books savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * Updates an existing book.
     *
     * @param book the book data to update
     * @param id   the ID of the book to update
     * @return a ResponseEntity containing the updated book
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Patch a book", description = "Updates an existing book")
    public ResponseEntity<Books> patchBook(
            @RequestBody final Books book,
            @PathVariable final Long id) {
        Books patchedBook = bookService.patchBook(book, id);
        return ResponseEntity.ok(patchedBook);
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id the ID of the book to delete
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book")
    public ResponseEntity<Void> deleteBook(
            @PathVariable final Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}


