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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("sitterUser");
        session.removeAttribute("sitter");
        session.removeAttribute("loggedInUser");
        return "frontpage";
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
            Sitter sitter = sitterUserService.findSitterUserByUsernameAndPassword(sitterUser.getUsername(), sitterUser.getPassword()).getSitter();
            System.out.println("Sitter: " + sitter.toString());
            session.setAttribute("loggedInUser", sitterUserService.findByUsername(sitterUser.getUsername()));
            session.setAttribute("sitterUser", sitterUser.getUsername());
            session.setAttribute("sitter", sitter);
            return "welcome";
        }
        model.addAttribute("error", "Wrong login.");
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerSitterUser(@ModelAttribute("sitterUser") SitterUser sitterUser,
                                 HttpSession session, Model model) {
        // Check if fields are filled out.
        if (!sitterUser.getUsername().equals("") && !sitterUser.getPassword().equals("")) {
            // Check if username is valid.
            if (sitterUserRepository.findByUsername(sitterUser.getUsername()) == null) {
                session.setAttribute("sitterUser", sitterUser);
                sitterUserService.addSitterUser(sitterUser);
                return "registerSitter";
            } else {
                model.addAttribute("error", "Username already in use.");
            }
        } else {
            model.addAttribute("error", "Fill all fields.");
        }
        return "register";
    }

    @RequestMapping(value = "registerSitter", method = RequestMethod.POST)
    public String registerSitter(@ModelAttribute("sitter") Sitter sitter,
                                 HttpSession session,
                                 Model model) {
        if (!sitter.getName().equals("") && !sitter.getEmail().equals("")) {

            SitterUser sitterUser = (SitterUser)session.getAttribute("sitterUser");
            sitterService.addSitter(sitter);
            SitterUser updatedSitterUser = sitterUserService.findByUsername(sitterUser.getUsername());
            updatedSitterUser.setSitter(sitterRepository.findById(sitter.getId()).orElse(null));
            sitterUserService.updateSitterUser(updatedSitterUser);
            session.setAttribute("sitter", sitterRepository.findByName(sitter.getName()));
            session.setAttribute("sitterUser", sitterUserService.findByUsername(updatedSitterUser.getUsername()));

            return "/login";
        }
        model.addAttribute("error", "Fill all fields.");
        return "registerSitter";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeUser() {
        return "welcome";
    }
}