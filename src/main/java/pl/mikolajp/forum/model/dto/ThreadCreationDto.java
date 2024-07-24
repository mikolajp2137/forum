package pl.mikolajp.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadCreationDto {
    private String title;
    private String text;
    private String creator;
    private Long categoryId;
}
