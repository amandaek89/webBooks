package repositorys;

import models.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepo extends JpaRepository<Authors, Long> {
    Authors findByName(String name);
}