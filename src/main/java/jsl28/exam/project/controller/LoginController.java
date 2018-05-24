package jsl28.exam.project.controller;

import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.repository.SitterRepository;
import jsl28.exam.project.repository.SitterUserRepository;
import jsl28.exam.project.service.SitterService;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Jakob on 22-05-2018.
 */

@Controller
public class LoginController {

    private SitterUserService sitterUserService;
    private SitterService sitterService;
    private SitterRepository sitterRepository;
    private SitterUserRepository sitterUserRepository;

    public LoginController(SitterUserService sitterUserService,
                           SitterService sitterService,
                           SitterRepository sitterRepository,
                           SitterUserRepository sitterUserRepository) {
        this.sitterUserService = sitterUserService;
        this.sitterService = sitterService;
        this.sitterRepository = sitterRepository;
        this.sitterUserRepository = sitterUserRepository;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@ModelAttribute("sitterUser") SitterUser sitterUser,
                        HttpSession session,
                        Model model) {
        if (sitterUserService.findSitterUserByUsernameAndPassword(
                sitterUser.getUsername(),
                sitterUser.getPassword()) != null) {
            sitterUser = sitterUserRepository.findByUsername(sitterUser.getUsername());
            session.setAttribute("username", sitterUser.getUsername());
            session.setAttribute("sitter", sitterUser.getSitter());
            return "welcome";
        }
        model.addAttribute("error", "Wrong login.");
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerSitterUser(@ModelAttribute("sitterUser") SitterUser sitterUser,
                                 HttpSession session,
                                 Model model) {
        if (!sitterUser.getUsername().equals("") && !sitterUser.getPassword().equals("")) {
            session.setAttribute("sitterUser", sitterUser.getUsername());
            sitterUserService.addSitterUser(sitterUser);
            return "registerSitter";
        }
        model.addAttribute("error", "Fill all fields.");
        return "register";
    }

    @RequestMapping(value = "registerSitter", method = RequestMethod.POST)
    public String registerSitter(@ModelAttribute("sitter") Sitter sitter,
                                 HttpSession session,
                                 Model model) {
        if (!sitter.getName().equals("") && !sitter.getEmail().equals("")) {
            sitterUserService.findByUsername(session.getAttribute("sitterUser").toString()).setSitter(sitter);
            sitterService.addSitter(sitter);
            return "frontpage";
        }
        model.addAttribute("error", "Fill all fields.");
        return "registerSitter";
    }
}