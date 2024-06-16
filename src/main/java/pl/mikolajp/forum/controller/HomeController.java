package pl.mikolajp.forum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mikolajp.forum.reply.ReplyService;
import pl.mikolajp.forum.thread.ThreadDto;
import pl.mikolajp.forum.thread.ThreadMapper;
import pl.mikolajp.forum.thread.ThreadService;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final ThreadService threadService;
    private final ReplyService replyService;
    private final ThreadMapper threadMapper = new ThreadMapper();

    @GetMapping({"/", "/home", "/index"})
    public String listExistingThreads(Model model) {
        List<ThreadDto> threads = threadService.getAllThreads();
        model.addAttribute("threads", threads);

        return "home";
    }
}
