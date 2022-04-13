package mk.ukim.finki.test.repository;

import mk.ukim.finki.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String name);
    void deleteByName(String name);
    Optional<Category> findById(Long id);
}
