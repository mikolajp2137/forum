package pl.mikolajp.forum.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ThreadRepository {
    private final IThreadRepository iThreadRepository;

    List<ThreadDao> findAllThreads(){
        return iThreadRepository.findAll();
    }

    ThreadDao saveThread(ThreadDao threadDao){
        return iThreadRepository.save(threadDao);
    }

    Optional<ThreadDao> findById(Integer id){
        return iThreadRepository.findById(id);
    }

    List<ThreadDao> findByTitle(Optional<String> title){
        return iThreadRepository.findByTitleContains(title);
    }

    void deleteThread(Integer id){
        iThreadRepository.deleteById(id);
    }

    List<ThreadDao> findAllThreadsByCategory(Integer id) {
        return iThreadRepository.findByCategoryId(id);
    }
}
