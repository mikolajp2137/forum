package pl.mikolajp.forum.thread;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "threads")
@Data
class ThreadDao {
    @Id
    @Column(name = "thread_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer threadId;
    @Column(name = "creator_id")
    private Integer creatorId;
    private String contents;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "attachment_id")
    private Integer attachmentId;
    @Column(name = "is_locked")
    private boolean isLocked;
    private String title;
}
