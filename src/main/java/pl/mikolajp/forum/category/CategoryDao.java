package pl.mikolajp.forum.category;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
class CategoryDao {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
}
