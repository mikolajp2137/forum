package pl.mikolajp.forum.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ThreadDto {
    private Integer creatorId;
    private String contents;
    private Integer categoryId;
    private Integer attachmentId;
    private boolean isLocked;
    private String title;
}
