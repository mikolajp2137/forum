package pl.mikolajp.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadCreationDto {
    private String title;
    private String text;
    private String creator;
    private Long categoryId;
    private List<MultipartFile> imageFiles;
}
