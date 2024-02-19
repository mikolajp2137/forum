package pl.mikolajp.forum.reply;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "replies")
@Data
public class ReplyDao {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyId;
    @Column(name = "creator_id")
    private Integer creatorId;
    @Column(name = "thread_id")
    private Integer threadId;
    private String contents;
}
