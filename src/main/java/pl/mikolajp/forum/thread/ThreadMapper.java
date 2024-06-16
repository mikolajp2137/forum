package pl.mikolajp.forum.thread;

import pl.mikolajp.forum.reply.ReplyDao;

public class ThreadMapper {
    ThreadDao mapDtoToDao(ThreadDto threadDto){
        ThreadDao threadDao = new ThreadDao();
        threadDao.setCreatorId(threadDto.getCreatorId());
        threadDao.setCategoryId(threadDto.getCategoryId());
        threadDao.setLocked(threadDto.isLocked());
        threadDao.setTitle(threadDto.getTitle());

        return threadDao;
    }

    ThreadDao mapDtoToDao(ThreadDto threadDto, Integer id){
        ThreadDao threadDao = new ThreadDao();
        threadDao.setThreadId(id);
        threadDao.setCreatorId(threadDto.getCreatorId());
        threadDao.setCategoryId(threadDto.getCategoryId());
        threadDao.setLocked(threadDto.isLocked());
        threadDao.setTitle(threadDto.getTitle());

        return threadDao;
    }

    ThreadDto mapDaoToDto(ThreadDao threadDao){
        return new ThreadDto(
                threadDao.getThreadId(),
                threadDao.getCreatorId(),
                threadDao.getCategoryId(),
                threadDao.isLocked(),
                threadDao.getTitle());
    }

    public ThreadDto mapContentToThread(ThreadDao threadDao, ReplyDao replyDao) {
        ThreadDto threadDto = new ThreadDto();
        threadDto.setThreadId(threadDao.getThreadId());
        threadDto.setCreatorId(threadDao.getCreatorId());
        threadDto.setTitle(threadDao.getTitle());
        threadDto.setContents(replyDao.getContents());
        threadDto.setLocked(threadDao.isLocked());
        threadDto.setCategoryId(threadDao.getCategoryId());

        return threadDto;
    }
}
