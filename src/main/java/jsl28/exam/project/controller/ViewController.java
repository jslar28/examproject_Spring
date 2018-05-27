package jsl28.exam.project.controller;

import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.service.SitterService;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jakob on 20-05-2018.
 */

@Controller
@RequestMapping("/svc")
public class ViewController {
    
    private SitterService sitterService;
    private SitterUserService sitterUserService;

    public ViewController(SitterService sitterService, SitterUserService sitterUserService) {
        this.sitterService = sitterService;
        this.sitterUserService = sitterUserService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getSitterForm() {
        return "sitterForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSitter(HttpServletRequest request) {
        Sitter newSitter = new Sitter(
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("age")));
        sitterService.addSitter(newSitter);
        return "sitters";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getSitter(HttpServletRequest request, Model model) {
        Sitter sitter = sitterService.getSitter(Integer.parseInt(request.getParameter("id")));
        if (sitter == null) {
            return "error";
        }
        model.addAttribute("id", sitter.getId());
        model.addAttribute("name", sitter.getName());
        model.addAttribute("age", sitter.getAge());
        return "sitters";
    }
}