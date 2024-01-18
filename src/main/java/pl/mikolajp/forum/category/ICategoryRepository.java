package pl.mikolajp.forum.category;

import org.springframework.data.jpa.repository.JpaRepository;

interface ICategoryRepository extends JpaRepository<CategoryDao, Integer> {
}
