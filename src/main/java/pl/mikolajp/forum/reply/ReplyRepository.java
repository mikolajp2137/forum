package pl.mikolajp.forum.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ReplyRepository {
    private final IReplyRepository iReplyRepository;

    List<ReplyDao> findAllRepliesByThreadId(Integer id){
        return iReplyRepository.findByThreadId(id);
    }

    Optional<ReplyDao> findReplyById(Integer id){
        return iReplyRepository.findById(id);
    }

    void saveReply(ReplyDao replyDao){
        iReplyRepository.save(replyDao);
    }

    void deleteReply(Integer id){
        iReplyRepository.deleteById(id);
    }

    void deleteAllRepliesByThreadId(Integer id){
        iReplyRepository.deleteAllByThreadId(id);
    }
}
