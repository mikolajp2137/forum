package pl.mikolajp.forum.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
class ReplyRestController {
    private final ReplyService replyService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<ReplyDao> getAllRepliesByThreadId(@PathVariable Integer id){
        return replyService.getAllRepliesByThreadId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addReply(@RequestBody ReplyDto replyDto){
        replyService.addReply(replyDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateReply(@RequestBody ReplyDto replyDto, @PathVariable Integer id){
        replyService.updateReply(replyDto, id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReply(@PathVariable Integer id){
        replyService.deleteReply(id);
    }
}
