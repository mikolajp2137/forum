package pl.mikolajp.forum.attachment;

public class AttachmentMapper {
    AttachmentDao mapDtoToDao(AttachmentDto attachmentDto){
        AttachmentDao attachmentDao = new AttachmentDao();
        attachmentDao.setFileName(attachmentDto.getFileName());
        attachmentDao.setFilePath(attachmentDto.getFilePath());
        attachmentDao.setReplyId(attachmentDto.getReplyId());

        return attachmentDao;
    }

    AttachmentDao mapDtoToDao(AttachmentDto attachmentDto, Integer id){
        AttachmentDao attachmentDao = new AttachmentDao();
        attachmentDao.setId(id);
        attachmentDao.setFileName(attachmentDto.getFileName());
        attachmentDao.setFilePath(attachmentDto.getFilePath());
        attachmentDao.setReplyId(attachmentDto.getReplyId());

        return attachmentDao;
    }

    AttachmentDto mapDaoToDto(AttachmentDao attachmentDao){
        return new AttachmentDto(
                attachmentDao.getFileName(),
                attachmentDao.getFilePath(),
                attachmentDao.getReplyId()
        );
    }
}
