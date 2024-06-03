package pl.mikolajp.forum.attachment;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "attachments")
@Data
public class AttachmentDao {
    @Id
    @Column(name = "attachment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "reply_id")
    private Integer replyId;
}
