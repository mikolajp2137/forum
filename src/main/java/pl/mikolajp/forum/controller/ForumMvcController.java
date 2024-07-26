package pl.mikolajp.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mikolajp.forum.model.dto.ThreadCreationDto;
import pl.mikolajp.forum.model.dto.ThreadMainPageDto;
import pl.mikolajp.forum.model.entity.Thread;
import pl.mikolajp.forum.service.CategoryService;
import pl.mikolajp.forum.service.ThreadService;

import java.util.List;

@Controller
public class ForumMvcController {
    @Autowired
    private ThreadService threadService;
    @Autowired
    private CategoryService categoryService;

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

        return "thread/thread";
    }

    @GetMapping({"/new/thread"})
    public String showThreadCreationPage(Model model){
        ThreadCreationDto threadCreationDto = new ThreadCreationDto();
        model.addAttribute("threadCreationDto", threadCreationDto);
        model.addAttribute("allCategories", categoryService.getAllCategories());

        return "thread/new-thread";
    }

    @RequestMapping(value = "/new/thread", method = RequestMethod.POST)
    public String createNewThread(@ModelAttribute ThreadCreationDto threadCreationDto, Authentication authentication){
        threadService.saveThread(threadCreationDto, authentication);

        return "redirect:/forum";
    }

    @GetMapping("/edit/thread/{id}")
    public String editExistingThread(Model model, @PathVariable("id") Long id){
        try{
            model.addAttribute("allCategories", categoryService.getAllCategories());

            Thread thread = threadService.fetchEditedThread(id);
            model.addAttribute("thread", thread);

            ThreadCreationDto threadCreationDto = new ThreadCreationDto();
            threadCreationDto.setText(thread.getComments().get(0).getText());
            threadCreationDto.setTitle(thread.getTitle());
            threadCreationDto.setCategoryId(thread.getCategory().getId());
            model.addAttribute("threadCreationDto", threadCreationDto);
        }catch (Exception e){
            return "redirect:/forum";
        }


        return "thread/edit";
    }

    @PostMapping ("/edit/thread/{id}")
    public String updateThread(Model model, @PathVariable("id") Long id, @ModelAttribute ThreadCreationDto threadCreationDto){
        try{
            Thread thread = threadService.fetchEditedThread(id);
            threadService.updateThread(threadCreationDto, thread);
        }catch (Exception e){
            return "redirect:/forum";
        }

        return "redirect:/forum";
    }

    @GetMapping("/delete/thread/{id}")
    public String deleteThread(@PathVariable("id") Long id){
        try{
            threadService.deleteThread(id);
        }catch (Exception e){
            return "redirect:/forum";
        }
        return "redirect:/forum";
    }
}
