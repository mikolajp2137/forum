package pl.mikolajp.forum.reply;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper = new ReplyMapper();

    public List<ReplyDao> getAllRepliesByThreadId(Integer id){
        return replyRepository.findAllRepliesByThreadId(id);
    }

    Optional<ReplyDao> getReplyById(Integer id){
        return replyRepository.findReplyById(id);
    }

    public void addReply(ReplyDto replyDto){
        replyRepository.saveReply(replyMapper.mapDtoToDao(replyDto));
    }

    public void addReply(ReplyDao replyDao){
        replyRepository.saveReply(replyDao);
    }

    public void updateReply(ReplyDto replyDto, Integer id){
        replyRepository.saveReply(replyMapper.mapDtoToDao(replyDto, id));
    }

    void deleteReply(Integer id){
        replyRepository.deleteReply(id);
    }

    @Transactional
    public void deleteAllRepliesByThreadId(Integer id){
        replyRepository.deleteAllRepliesByThreadId(id);
    }
}
