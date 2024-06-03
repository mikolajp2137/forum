package pl.mikolajp.forum.attachment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper = new AttachmentMapper();

    public void saveAttachment(AttachmentDto attachmentDto){
        attachmentRepository.saveAttachment(attachmentMapper.mapDtoToDao(attachmentDto));
    }

    public List<AttachmentDao> getAllAttachments(Integer id){
        return attachmentRepository.finAllAttachmentsById(id);
    }
}
