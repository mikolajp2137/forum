package pl.mikolajp.forum.reply;

class ReplyMapper {
    ReplyDao mapDtoToDao(ReplyDto replyDto){
        ReplyDao replyDao = new ReplyDao();
        replyDao.setCreatorId(replyDto.getCreatorId());
        replyDao.setThreadId(replyDto.getThreadId());
        replyDao.setContents(replyDto.getContents());
        replyDao.setAttachmentId(replyDto.getAttachmentId());

        return replyDao;
    }

    ReplyDao mapDtoToDao(ReplyDto replyDto, Integer id){
        ReplyDao replyDao = new ReplyDao();
        replyDao.setReplyId(id);
        replyDao.setCreatorId(replyDto.getCreatorId());
        replyDao.setThreadId(replyDto.getThreadId());
        replyDao.setContents(replyDto.getContents());
        replyDao.setAttachmentId(replyDto.getAttachmentId());

        return replyDao;
    }

    ReplyDto mapDaoToDto(ReplyDao replyDao){
        return new ReplyDto(
                replyDao.getCreatorId(),
                replyDao.getThreadId(),
                replyDao.getContents(),
                replyDao.getAttachmentId()
        );
    }
}
