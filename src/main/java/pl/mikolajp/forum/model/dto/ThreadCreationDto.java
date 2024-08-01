package pl.mikolajp.forum.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadCreationDto {
    @Size(min = 2, message = "The title is too short (at least 2 characters)")
    @Size(max = 250, message = "The title is too long (at most 250 characters)")
    private String title;
    @Size(min = 2, message = "The text is too short (at least 2 characters)")
    @Size(max = 2137, message = "The text is too long")
    private String text;
    private String creator;
    @NotNull(message = "Category cannot be empty!")
    private Long categoryId;
    private List<MultipartFile> imageFiles;
}
