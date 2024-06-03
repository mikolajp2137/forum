package pl.mikolajp.forum.thread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mikolajp.forum.attachment.AttachmentDto;
import pl.mikolajp.forum.attachment.AttachmentService;
import pl.mikolajp.forum.reply.ReplyDao;
import pl.mikolajp.forum.reply.ReplyDto;
import pl.mikolajp.forum.reply.ReplyMapper;
import pl.mikolajp.forum.reply.ReplyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class ThreadService {
    private final ThreadRepository threadRepository;
    private final ReplyService replyService;
    private final AttachmentService attachmentService;
    private final ThreadMapper threadMapper = new ThreadMapper();
    private final ReplyMapper replyMapper = new ReplyMapper();

    List<ThreadDto> getAllThreads() {
        List<ThreadDao> threadDaoList = threadRepository.findAllThreads();
        List<ThreadDto> results = new ArrayList<>();

        for (ThreadDao thread : threadDaoList){
            //TODO: create method to find first reply
            List<ReplyDao> replies = replyService.getAllRepliesByThreadId(thread.getThreadId());
            ThreadDto result = threadMapper.mapContentToThread(thread, replies.get(0));
            results.add(result);
        }

        return results;
    }

    Optional<ThreadDao> getThread(Integer id) {
        return threadRepository.findById(id);
    }

    List<ThreadDto> getThreadByTitle(Optional<String> title) {
        List<ThreadDao> threadDaoList = threadRepository.findByTitle(title);
        List<ThreadDto> results = new ArrayList<>();

        for (ThreadDao thread : threadDaoList){
            //TODO: create method to find first reply
            List<ReplyDao> replies = replyService.getAllRepliesByThreadId(thread.getThreadId());
            ThreadDto result = threadMapper.mapContentToThread(thread, replies.get(0));
            results.add(result);
        }

        return results;
    }

    void addThread(ThreadDto threadDto) {
        ThreadDao savedThread = threadRepository.saveThread(threadMapper.mapDtoToDao(threadDto));
        log.info("CREATED THREAD ID: "+String.valueOf(savedThread.getThreadId()));
        //ReplyDao reply = replyMapper.mapDtoToDao(replyMapper.mapReplyFromThreadDao(threadDto, savedThread.getThreadId()));
        ReplyDto reply = replyMapper.mapReplyFromThreadDao(threadDto, savedThread.getThreadId());
        replyService.addReply(reply);

//        if (attachment != null) {
//            AttachmentDto attachmentDto = new AttachmentDto();
//            attachmentDto.setFileName(attachment.getOriginalFilename());
//            attachmentDto.setFilePath("C:\\Users\\Mikolaj\\Documents\\forumAttachments" + attachment.getOriginalFilename());
//            attachmentDto.setReplyId(reply.getReplyId());
//
//            attachmentService.saveAttachment(attachmentDto);
//        }
    }

    void updateThread(ThreadDto threadDto, Integer id) {
        //TODO: create method for finding first reply
        List<ReplyDao> replies = replyService.getAllRepliesByThreadId(id);
        Integer firstReplyId = replies.get(0).getReplyId();
        ReplyDto updatedContent = replyMapper.mapReplyFromThreadDao(threadDto, id);
        replyService.updateReply(updatedContent, firstReplyId);

        threadRepository.saveThread(threadMapper.mapDtoToDao(threadDto, id));
    }

    void deleteThread(Integer id) {
        replyService.deleteAllRepliesByThreadId(id);
        threadRepository.deleteThread(id);
    }

    List<ThreadDto> getAllThreadsByCategory(Integer id) {
        List<ThreadDao> threadDaoList = threadRepository.findAllThreadsByCategory(id);
        List<ThreadDto> results = new ArrayList<>();

        for (ThreadDao thread : threadDaoList){
            //TODO: create method to find first reply
            List<ReplyDao> replies = replyService.getAllRepliesByThreadId(thread.getThreadId());
            ThreadDto result = threadMapper.mapContentToThread(thread, replies.get(0));
            results.add(result);
        }

        return results;
    }
}
