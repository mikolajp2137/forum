package pl.mikolajp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajp.forum.model.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByThreadIdOrderByCreationDateAsc(Long threadId);
}
