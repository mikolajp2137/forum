package pl.mikolajp.forum.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ThreadService {
    private final ThreadRepository threadRepository;
    private final ThreadMapper threadMapper = new ThreadMapper();

    List<ThreadDao> getAllThreads() {
        return threadRepository.findAllThreads();
    }

    Optional<ThreadDao> getThread(Integer id) {
        return threadRepository.findById(id);
    }

    List<ThreadDao> getThreadByTitle(Optional<String> title) {
        return threadRepository.findByTitle(title);
    }

    void addThread(ThreadDto threadDto) {
        threadRepository.saveThread(threadMapper.mapDtoToDao(threadDto));
    }

    void updateThread(ThreadDto threadDto, Integer id) {
        threadRepository.saveThread(threadMapper.mapDtoToDao(threadDto, id));
    }

    void deleteThread(Integer id) {
        threadRepository.deleteThread(id);
    }

    List<ThreadDao> getAllThreadsByCategory(Integer id) {
        return threadRepository.findAllThreadsByCategory(id);
    }
}
