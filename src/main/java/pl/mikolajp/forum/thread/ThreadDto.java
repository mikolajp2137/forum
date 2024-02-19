package pl.mikolajp.forum.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDto {
    private Integer threadId;
    private Integer creatorId;
    private String contents;
    private Integer categoryId;
    private boolean isLocked;
    private String title;

    public ThreadDto(Integer threadId, Integer creatorId, Integer categoryId, boolean isLocked, String title) {
        this.threadId = threadId;
        this.creatorId = creatorId;
        this.categoryId = categoryId;
        this.isLocked = isLocked;
        this.title = title;
    }
}
