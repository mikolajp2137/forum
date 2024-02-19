package pl.mikolajp.forum.reply;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyDto {
    private Integer creatorId;
    private Integer threadId;
    private String contents;
}
