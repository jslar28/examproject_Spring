package jsl28.exam.project.controller;

import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.service.SitterService;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jakob on 22-05-2018.
 */

@Controller
public class SitterUserController {

    @Autowired
    private SitterService sitterService;

    private SitterUserService sitterUserService;

    public SitterUserController(SitterUserService sitterUserService) {
        this.sitterUserService = sitterUserService;
    }

    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<SitterUser> getAllUsers() {
        return sitterUserService.getAllUser();
    }*/

    @RequestMapping(value = "/editProfile/{userId}", method = RequestMethod.GET)
    public String getEditProfilePage(@PathVariable("userId") int userId, Model model) {
        SitterUser sitterUser = sitterUserService.findById(userId);
        model.addAttribute("sitterToEdit", sitterUser.getSitter());
        return "editSitter";
    }

    @RequestMapping(value = "/editProfile/{userId}", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("sitterToEdit") Sitter sitterToEdit, @PathVariable("userId") int userId,
                              HttpSession session, Model model) {
        System.out.println("Called editProfile");
        SitterUser sitterUser = sitterUserService.findById(userId);
        int oldId = sitterUser.getSitter().getId(); // Work around for in-proper update
        sitterService.updateSitter(sitterToEdit);
        sitterUser.setSitter(sitterService.getSitter(sitterToEdit.getId()));
        sitterService.removeSitter(oldId); // Work around for in-proper update
        sitterUserService.updateSitterUser(sitterUser);
        session.setAttribute("loggedInUser", sitterUserService.findByUsername(sitterUser.getUsername()));
        model.addAttribute("status", "Profile updated");
        return "editSitter";
    }

    @RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
    public String viewProfile() {
        return "welcome";
    }
}