package pl.mikolajp.forum.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private String fileName;
    private String filePath;
    private Integer replyId;
}
