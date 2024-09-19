package controllers;


import lombok.RequiredArgsConstructor;
import models.Authors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AuthorService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final AuthorService authorService;

    // Hämta alla författare
    @GetMapping
    public ResponseEntity<List<Authors>> getAllAuthors() {
        List<Authors> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // Hämta en författare med specifikt ID
    @GetMapping("/{id}")
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
    public ResponseEntity<Authors> saveAuthor(@RequestBody Authors author) {
        Authors savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    // Uppdatera en befintlig författare
    @PatchMapping("/{id}")
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
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}