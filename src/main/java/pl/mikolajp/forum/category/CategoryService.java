package pl.mikolajp.forum.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = new CategoryMapper();

    List<CategoryDao> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

    Optional<CategoryDao> getCategoryById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    void addCategory(CategoryDto categoryDto) {
        categoryRepository.saveCategory(categoryMapper.mapDtoToDao(categoryDto));
    }

    void updateCategory(CategoryDto categoryDto, Integer id) {
        categoryRepository.saveCategory(categoryMapper.mapDtoToDao(categoryDto, id));
    }

    void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }
}
