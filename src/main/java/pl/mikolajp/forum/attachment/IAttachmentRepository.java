package pl.mikolajp.forum.attachment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttachmentRepository extends JpaRepository<AttachmentDao, Integer> {
    List<AttachmentDao> findAllByReplyId(Integer id);
}
