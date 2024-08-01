package pl.mikolajp.forum.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long threadId;

    @Size(min = 2, message = "The comment is too short (at least 2 characters)")
    @Size(max = 2222, message = "The comment is too long")
    private String text;
}
