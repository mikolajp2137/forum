package pl.mikolajp.forum.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CategoryDao> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CategoryDao getCategoryById(@PathVariable Integer id){
        Optional<CategoryDao> result = categoryService.getCategoryById(id);
        return result.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateThread(@RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }
}
