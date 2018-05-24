package jsl28.exam.project.controller;

import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.model.SitterMessage;
import jsl28.exam.project.repository.SitterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jakob on 22-05-2018.
 */

@Controller
public class ContactController {

    private SitterRepository sitterRepository;

    public ContactController(SitterRepository sitterRepository) {
        this.sitterRepository = sitterRepository;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactSitter(@ModelAttribute String sitter) {
        System.out.println("Called: contactSitter");
        System.out.println(sitter);
        //System.out.println(sitterRepository.findByName(sitter.getName()));
        return "sitters";
    }

    @RequestMapping(value = "/contact/{sitterName}", method = RequestMethod.GET)
    public String contactASitter(@PathVariable String sitterName, ModelMap modelMap) {
        Sitter sitter = sitterRepository.findByName(sitterName);
        modelMap.put("sitter", sitter);
        return "contactSitter";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute SitterMessage sitterMessage) {
        if (sitterRepository.findById(sitterMessage.getSitter().getId()).orElse(null) != null) {
            System.out.println("inSendMessage - message: " + sitterMessage.getMessage());
            System.out.println("inSendMessage - sitter: " + sitterMessage.getSitter().toString());
            sitterRepository.findById(sitterMessage.getSitter().getId()).
                    orElse(null).getInbox().add(sitterMessage.getMessage());
            return "contactSitter";
        }
        System.out.println("Invalid sitter: " + sitterMessage.getSitter().toString());
        return "error";
    }
}