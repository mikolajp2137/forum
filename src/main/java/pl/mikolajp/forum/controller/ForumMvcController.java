package pl.mikolajp.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mikolajp.forum.model.dto.ThreadCreationDto;
import pl.mikolajp.forum.model.dto.ThreadMainPageDto;
import pl.mikolajp.forum.model.entity.Thread;
import pl.mikolajp.forum.repository.ThreadRepository;
import pl.mikolajp.forum.service.ThreadService;

import java.util.List;

@Controller
public class ForumMvcController {
    @Autowired
    private ThreadService threadService;

    @GetMapping({"/forum"})
    public String showHome(Model model){
        List<ThreadMainPageDto> threads = threadService.showThreadCards();
        model.addAttribute("threads", threads);

        return "forum";
    }

    @GetMapping({"/","/home","/index"})
    public String showHome(){
        return "home";
    }

    @GetMapping({"/thread/{id}"})
    public String showThread(Model model, @PathVariable("id") Long id){
        model.addAttribute(threadService.getThreadById(id));

        return "thread";
    }

    @GetMapping({"/new/thread"})
    public String showThreadCreationPage(Model model){
        ThreadCreationDto threadCreationDto = new ThreadCreationDto();
        model.addAttribute("threadCreationDto", threadCreationDto);

        return "new-thread";
    }

    @RequestMapping(value = "/new/thread", method = RequestMethod.POST)
    public String createNewThread(@ModelAttribute ThreadCreationDto threadCreationDto){
        threadService.saveThread(threadCreationDto);

        return "redirect:/forum";
    }
}
