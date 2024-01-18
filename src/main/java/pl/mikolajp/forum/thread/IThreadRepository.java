package pl.mikolajp.forum.thread;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface IThreadRepository extends JpaRepository<ThreadDao, Integer> {
    List<ThreadDao> findByTitle(Optional<String> title);
}
