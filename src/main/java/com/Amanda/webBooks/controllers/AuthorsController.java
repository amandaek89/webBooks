package com.Amanda.webBooks.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.Amanda.webBooks.models.Authors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Amanda.webBooks.services.AuthorService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
@Tag(name = "Authors", description = "Endpoints for managing authors")
public class AuthorsController {

    private final AuthorService authorService;

    // Hämta alla författare
    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors")
    public ResponseEntity<List<Authors>> getAllAuthors() {
        List<Authors> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // Hämta en författare med specifikt ID
    @GetMapping("/{id}")
    @Operation(summary = "Get one author", description = "Retrieves a specific author by ID")
    public ResponseEntity<Authors> getOneAuthor(@PathVariable Long id) {
        Optional<Authors> author = authorService.getOneAuthor(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Hämta en författare med specifikt namn
    @GetMapping("/name/{name}")
    @Operation(summary = "Get one author by name", description = "Retrieves a specific author by name")
    public ResponseEntity<Authors> getOneAuthorByName(@PathVariable String name) {
        Authors author = authorService.getOneAuthorByName(name);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Spara en ny författare
    @PostMapping
    @Operation(summary = "Save an author", description = "Saves a new author")
    public ResponseEntity<Authors> saveAuthor(@RequestBody Authors author) {
        Authors savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    // Uppdatera en befintlig författare
    @PatchMapping("/{id}")
    @Operation(summary = "Patch an author", description = "Updates an existing author")
    public ResponseEntity<Authors> patchAuthor(@RequestBody Authors author, @PathVariable Long id) {
        try {
            Authors patchedAuthor = authorService.patchAuthor(author, id);
            return ResponseEntity.ok(patchedAuthor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Ta bort en författare
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}