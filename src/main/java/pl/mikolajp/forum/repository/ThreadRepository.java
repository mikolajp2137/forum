package pl.mikolajp.forum.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajp.forum.model.entity.Thread;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    public List<Thread> findAllByCategoryId(Long id, Sort sort);
}
