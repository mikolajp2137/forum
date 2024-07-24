package pl.mikolajp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajp.forum.model.entity.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
