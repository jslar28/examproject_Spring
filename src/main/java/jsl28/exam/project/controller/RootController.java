package jsl28.exam.project.controller;

import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.service.SitterService;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jakob on 20-05-2018.
 */

@Controller
public class RootController {

    private SitterUserService sitterUserService;
    private SitterService sitterService;

    public RootController(SitterUserService sitterUserService, SitterService sitterService) {
        this.sitterUserService = sitterUserService;
        this.sitterService = sitterService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFrontPage(HttpSession session) {
        sitterService.createDummySitters();
        sitterUserService.createDummyUsers();
        System.out.println("Root session attr: " + session.getAttribute("loggedInUser"));
        return "frontpage";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSitterByZipCode(@RequestParam String zipCode, Model model) {
        List<Sitter> sitters = sitterService.getAllSittersByZipCode(Integer.parseInt(zipCode));
        model.addAttribute("sitters", sitters);
        return "sitters";
    }
}