package pl.mikolajp.forum.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    private String categoryName;
}
