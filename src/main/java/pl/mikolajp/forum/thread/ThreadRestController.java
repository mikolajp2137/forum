package pl.mikolajp.forum.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
class ThreadRestController {
    private final ThreadService threadService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ThreadDao> getAllThreadsOrByTitle(@RequestParam(required = false) Optional<String> title){
        if (title.isPresent()){
            return threadService.getThreadByTitle(title);
        }
        return threadService.getAllThreads();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ThreadDao getThread(@PathVariable Integer id){
        Optional<ThreadDao> result = threadService.getThread(id);
        return result.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addThread(@RequestBody ThreadDto threadDto){
        threadService.addThread(threadDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateThread(@RequestBody ThreadDto threadDto, @PathVariable Integer id){
        threadService.updateThread(threadDto, id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteThread(@PathVariable Integer id){
        threadService.deleteThread(id);
    }
}
