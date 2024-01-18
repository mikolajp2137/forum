package pl.mikolajp.forum.thread;

class ThreadMapper {
    ThreadDao mapDtoToDao(ThreadDto threadDto){
        ThreadDao threadDao = new ThreadDao();
        threadDao.setCreatorId(threadDto.getCreatorId());
        threadDao.setContents(threadDto.getContents());
        threadDao.setCategoryId(threadDto.getCategoryId());
        threadDao.setAttachmentId(threadDto.getAttachmentId());
        threadDao.setLocked(threadDto.isLocked());
        threadDao.setTitle(threadDto.getTitle());

        return threadDao;
    }

    ThreadDao mapDtoToDao(ThreadDto threadDto, Integer id){
        ThreadDao threadDao = new ThreadDao();
        threadDao.setThreadId(id);
        threadDao.setCreatorId(threadDto.getCreatorId());
        threadDao.setContents(threadDto.getContents());
        threadDao.setCategoryId(threadDto.getCategoryId());
        threadDao.setAttachmentId(threadDto.getAttachmentId());
        threadDao.setLocked(threadDto.isLocked());
        threadDao.setTitle(threadDto.getTitle());

        return threadDao;
    }

    ThreadDto mapDaoToDto(ThreadDao threadDao){
        return new ThreadDto(
                threadDao.getCreatorId(),
                threadDao.getContents(),
                threadDao.getCategoryId(),
                threadDao.getAttachmentId(),
                threadDao.isLocked(),
                threadDao.getTitle());
    }
}
