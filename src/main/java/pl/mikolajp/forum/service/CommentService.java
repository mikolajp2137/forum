package pl.mikolajp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mikolajp.forum.model.dto.CommentDto;
import pl.mikolajp.forum.model.entity.Comment;
import pl.mikolajp.forum.model.entity.Thread;
import pl.mikolajp.forum.repository.CommentRepository;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ThreadService threadService;

    public void addComment(CommentDto commentDto, String username){
        Date createdAtTimestamp = new Date();
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setCreator(userService.findByUsername(username));
        comment.setCreationDate(createdAtTimestamp);

        Thread thread = threadService.getThreadById(commentDto.getThreadId());
        comment.setThread(thread);

        commentRepository.save(comment);
    }

    public Long deleteComment(Long id){
        Comment comment = commentRepository.findById(id).get();
        Long redirectId = comment.getThread().getId();
        commentRepository.deleteById(id);

        return redirectId;
    }
}
