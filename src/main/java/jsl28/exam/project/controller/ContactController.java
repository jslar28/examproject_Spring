package jsl28.exam.project.controller;

import jsl28.exam.project.model.Message;
import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.repository.SitterRepository;
import jsl28.exam.project.service.SitterService;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Jakob on 22-05-2018.
 */

@Controller
public class ContactController {

    private SitterRepository sitterRepository;
    private SitterService sitterService;
    private SitterUserService sitterUserService;

    public ContactController(SitterRepository sitterRepository, SitterUserService sitterUserService, SitterService sitterService) {
        this.sitterRepository = sitterRepository;
        this.sitterUserService = sitterUserService;
        this.sitterService = sitterService;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactSitter(@ModelAttribute String sitter) {
        return "sitters";
    }

    @RequestMapping(value = "/contact/{sitterId}", method = RequestMethod.GET)
    public String contactASitter(@PathVariable int sitterId, HttpSession session, ModelMap modelMap) {
        Sitter sitter = sitterRepository.findById(sitterId).orElse(null);
        session.setAttribute("sitterToContact", sitter);
        modelMap.put("sitter", sitter);
        return "contactSitter";
    }

    @RequestMapping(value = "/contact/send", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute("message") Message message, HttpSession session, Model model) {

        Sitter recipient = sitterRepository.findById(((Sitter) session.getAttribute("sitterToContact")).getId()).orElse(null);
        if (recipient != null) {
            recipient.addToInbox(message);
            System.out.println("Recipient : " + recipient.toString());
            try {
                sitterService.updateSitter(recipient);
            } catch (org.springframework.dao.DataIntegrityViolationException err) {
                err.printStackTrace();
                model.addAttribute("sitter", recipient);
                model.addAttribute("messageStatus", "Message was unable to be sent.");
                model.addAttribute("message", message);
                return "contactSitter";
            }

            // Refresh session attribute - better way of doing this?
            if (session.getAttribute("loggedInUser") != null) {
                int id = ((SitterUser)session.getAttribute("loggedInUser")).getId();
                session.setAttribute("loggedInUser", sitterUserService.findById(id));
            }

            System.out.println("inSendMessage: inbox: " + sitterRepository.findById(((Sitter) session.getAttribute("sitterToContact")).getId()).orElse(null).getInbox());
            model.addAttribute("sitter", recipient);
            model.addAttribute("messageStatus", "Message was sent to " + recipient.getName());
            model.addAttribute("message", message);
            return "contactSitter";
        } else {
            System.out.println("Tried to add message to sitter that does not exist...");
        }
        return "contactSitter";
    }
}