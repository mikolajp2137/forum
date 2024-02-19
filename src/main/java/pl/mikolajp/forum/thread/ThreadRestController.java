package pl.mikolajp.forum.thread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mikolajp.forum.reply.ReplyDao;
import pl.mikolajp.forum.reply.ReplyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
@Slf4j
class ThreadRestController {
    private final ThreadService threadService;
    private final ReplyService replyService;
    private final ThreadMapper threadMapper = new ThreadMapper();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ThreadDto> getAllThreadsOrByTitle(@RequestParam(required = false) Optional<String> title){
        if (title.isPresent()){
            return threadService.getThreadByTitle(title);
        }
        return threadService.getAllThreads();
    }

    //TODO: move logic to ThreadService
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ThreadDto getThread(@PathVariable Integer id){
        Optional<ThreadDao> threadSearch = threadService.getThread(id);
        ThreadDao thread = threadSearch.get();
        log.info(String.valueOf(thread));
        List<ReplyDao> replies = replyService.getAllRepliesByThreadId(thread.getThreadId());
        ThreadDto result = threadMapper.mapContentToThread(thread, replies.get(0));
        log.info(String.valueOf(result));

        return result;
    }

    @GetMapping("/byCategory/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<ThreadDto> getAllThreadsFromCategory(@PathVariable Integer id){
        return threadService.getAllThreadsByCategory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addThread(@RequestBody ThreadDto threadDto){
        threadService.addThread(threadDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateThread(@RequestBody ThreadDto threadDto, @PathVariable Integer id){
        threadService.updateThread(threadDto, id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteThread(@PathVariable Integer id){
        threadService.deleteThread(id);
    }
}
