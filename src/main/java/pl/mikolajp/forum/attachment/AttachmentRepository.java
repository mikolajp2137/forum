package pl.mikolajp.forum.attachment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttachmentRepository {
    private final IAttachmentRepository iAttachmentRepository;

    List<AttachmentDao> finAllAttachmentsById(Integer id){
        return iAttachmentRepository.findAllByReplyId(id);
    }

    void saveAttachment(AttachmentDao attachmentDao){
        iAttachmentRepository.save(attachmentDao);
    }
}
