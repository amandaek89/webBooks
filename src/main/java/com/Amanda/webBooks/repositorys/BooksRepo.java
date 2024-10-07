package com.Amanda.webBooks.repositorys;

import com.Amanda.webBooks.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {

}
