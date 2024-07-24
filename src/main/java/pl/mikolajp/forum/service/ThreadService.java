package pl.mikolajp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mikolajp.forum.model.dto.ThreadCreationDto;
import pl.mikolajp.forum.model.dto.ThreadMainPageDto;
import pl.mikolajp.forum.model.entity.Thread;
import pl.mikolajp.forum.model.entity.Comment;
import pl.mikolajp.forum.model.entity.User;
import pl.mikolajp.forum.repository.CategoryRepository;
import pl.mikolajp.forum.repository.CommentRepository;
import pl.mikolajp.forum.repository.ThreadRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {
    @Autowired
    private ThreadRepository threadRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    public List<ThreadMainPageDto> showThreadCards(){
        List<ThreadMainPageDto> mainPageCards = new ArrayList<>();
        List<Thread> threads = threadRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
        for(Thread thread : threads){
            String shortDescription = commentRepository.findAllByThreadId(thread.getId()).stream()
                    .findFirst()
                    .map(Comment::getText)
                    .map(text -> text.length() > 97 ? text.substring(0, 97) + "..." : text)
                    .orElse(null);
            mainPageCards.add(new ThreadMainPageDto(thread.getId(), thread.getTitle(), thread.getCreator().getUsername(),shortDescription));
        }

        return mainPageCards;
    }

    public Thread getThreadById(Long id){
        Optional<Thread> thread = threadRepository.findById(id);

        return thread.orElse(null);
    }

    public void saveThread(ThreadCreationDto newThread){
        Date createdAtTimestamp = new Date();
        User creator = userService.findByUserName(newThread.getCreator());

        Thread thread = new Thread();
        thread.setTitle(newThread.getTitle());
        thread.setCreator(creator);
        thread.setCreationDate(createdAtTimestamp);
        thread.setCategory(categoryRepository.findById(newThread.getCategoryId()).orElse(null));

        Comment comment = new Comment();
        comment.setCreationDate(createdAtTimestamp);
        comment.setText(newThread.getText());
        comment.setCreator(creator);
        comment.setThread(thread);

        thread.setComments(List.of(comment));

        threadRepository.save(thread);
    }
}
