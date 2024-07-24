package pl.mikolajp.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadMainPageDto {
    private Long id;
    private String title;
    private String creator;
    private String shortenedDescription;
}
