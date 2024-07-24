package pl.mikolajp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajp.forum.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
