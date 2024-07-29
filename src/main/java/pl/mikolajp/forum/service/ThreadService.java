package pl.mikolajp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
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
            String shortDescription = commentRepository.findAllByThreadIdOrderByCreationDateAsc(thread.getId()).stream()
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

    public void saveThread(ThreadCreationDto newThread, Authentication authentication){
        Date createdAtTimestamp = new Date();
        User creator = userService.findByUsername(authentication.getName());

        Thread thread = new Thread();
        thread.setTitle(newThread.getTitle());
        thread.setCreator(creator);
        thread.setCreationDate(createdAtTimestamp);
        thread.setCategory(categoryRepository.findById(newThread.getCategoryId()).get());

        Comment comment = new Comment();
        comment.setCreationDate(createdAtTimestamp);
        comment.setText(newThread.getText());
        comment.setCreator(creator);
        comment.setThread(thread);

        thread.setComments(List.of(comment));

        threadRepository.save(thread);
    }

    public Thread fetchEditedThread(Long id) {
        Thread thread = threadRepository.findById(id).get();
        return thread;
    }

    public void updateThread(ThreadCreationDto threadCreationDto, Thread thread){
        thread.setTitle(threadCreationDto.getTitle());
        thread.setCategory(categoryRepository.findById(threadCreationDto.getCategoryId()).get());

        Comment comment = thread.getComments().get(0);
        comment.setText(threadCreationDto.getText());

        threadRepository.save(thread);
        commentRepository.save(comment);
    }

    public void deleteThread(Long id) {
        Thread thread = threadRepository.findById(id).get();
        threadRepository.delete(thread);
    }
}
