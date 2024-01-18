package pl.mikolajp.forum.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper = new ReplyMapper();

    List<ReplyDao> getAllRepliesByThreadId(Integer id){
        return replyRepository.findAllRepliesByThreadId(id);
    }

    Optional<ReplyDao> getReplyById(Integer id){
        return replyRepository.findReplyById(id);
    }

    void addReply(ReplyDto replyDto){
        replyRepository.saveReply(replyMapper.mapDtoToDao(replyDto));
    }

    void updateReply(ReplyDto replyDto, Integer id){
        replyRepository.saveReply(replyMapper.mapDtoToDao(replyDto, id));
    }

    void deleteReply(Integer id){
        replyRepository.deleteReply(id);
    }
}
