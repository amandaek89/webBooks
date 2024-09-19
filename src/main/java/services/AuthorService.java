package services;


import lombok.RequiredArgsConstructor;
import models.Authors;
import org.springframework.stereotype.Service;
import repositorys.AuthorsRepo;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorsRepo authorsRepo;

    public List<Authors> getAllAuthors() {
        return authorsRepo.findAll();
    }

    public Optional<Authors> getOneAuthor(Long id) {
        return Optional.of(authorsRepo.findById(id).orElse(new Authors()));
    }

    public Authors getOneAuthorByName(String name) {
        return authorsRepo.findByName(name);
    }

    public Authors saveAuthor(Authors author) {
        return authorsRepo.save(author);
    }

    public Authors patchAuthor(Authors author, Long id) {
        Optional<Authors> currentAuthor = authorsRepo.findById(id);

        if (currentAuthor.isPresent()) {
            Authors existingAuthor = currentAuthor.get();

            // Kontrollera om namn är olika och uppdatera om de inte är lika
            if (author.getName() != null && !author.getName().equals(existingAuthor.getName())) {
                existingAuthor.setName(author.getName());
            }

            // Eftersom age är en int, jämför vi direkt med !=
            if (author.getAge() != 0 && author.getAge() != existingAuthor.getAge()) {
                existingAuthor.setAge(author.getAge());
            }

            // Spara och returnera den uppdaterade författaren
            return authorsRepo.save(existingAuthor);
        } else {
            throw new RuntimeException("Author with ID " + id + " not found");
        }
    }

    public void deleteAuthor(Long id) {
        authorsRepo.deleteById(id);
    }
}