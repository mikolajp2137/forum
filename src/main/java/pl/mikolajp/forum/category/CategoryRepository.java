package pl.mikolajp.forum.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class CategoryRepository {
    private final ICategoryRepository iCategoryRepository;

    List<CategoryDao> findAllCategories(){
        return iCategoryRepository.findAll();
    }

    Optional<CategoryDao> findCategoryById(Integer id){
        return iCategoryRepository.findById(id);
    }

    void saveCategory(CategoryDao categoryDao){
        iCategoryRepository.save(categoryDao);
    }

    void deleteCategory(Integer id){
        iCategoryRepository.deleteById(id);
    }
}
