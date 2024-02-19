package pl.mikolajp.forum.reply;

import pl.mikolajp.forum.thread.ThreadDto;

public class ReplyMapper {
    ReplyDao mapDtoToDao(ReplyDto replyDto){
        ReplyDao replyDao = new ReplyDao();
        replyDao.setCreatorId(replyDto.getCreatorId());
        replyDao.setThreadId(replyDto.getThreadId());
        replyDao.setContents(replyDto.getContents());

        return replyDao;
    }

    ReplyDao mapDtoToDao(ReplyDto replyDto, Integer id){
        ReplyDao replyDao = new ReplyDao();
        replyDao.setReplyId(id);
        replyDao.setCreatorId(replyDto.getCreatorId());
        replyDao.setThreadId(replyDto.getThreadId());
        replyDao.setContents(replyDto.getContents());

        return replyDao;
    }

    ReplyDto mapDaoToDto(ReplyDao replyDao){
        return new ReplyDto(
                replyDao.getCreatorId(),
                replyDao.getThreadId(),
                replyDao.getContents()
        );
    }

    public ReplyDto mapReplyFromThreadDao(ThreadDto threadDto, Integer threadId){
        return new ReplyDto(
                threadDto.getCreatorId(),
                threadId,
                threadDto.getContents()
        );
    }
}
