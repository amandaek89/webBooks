package com.Amanda.webBooks.services;


import lombok.RequiredArgsConstructor;
import com.Amanda.webBooks.models.Books;
import org.springframework.stereotype.Service;
import com.Amanda.webBooks.repositorys.BooksRepo;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BooksRepo booksRepo;

    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }

    public Optional<Books> getOneBook(Long id) {
        return Optional.of(booksRepo.findById(id).orElse(new Books()));
    }

    public Books saveBook(Books book) {
        return booksRepo.save(book);
    }

    public Books patchBook(Books book, Long id) {
        Optional<Books> currentBook = booksRepo.findById(id);

        if (currentBook.isPresent()) {
            Books existingBook = currentBook.get();

            if (book.getTitle() != null && !book.getTitle().equals(existingBook.getTitle())) {
                existingBook.setTitle(book.getTitle());
            }

            if (book.getISBN() != null && !book.getISBN().equals(existingBook.getISBN())) {
                existingBook.setISBN(book.getISBN());
            }

            return booksRepo.save(existingBook);
        } else {
            throw new RuntimeException("Book with ID " + id + " not found");
        }
    }
    public void deleteBook(Long id) {
        booksRepo.deleteById(id);
    }
}
