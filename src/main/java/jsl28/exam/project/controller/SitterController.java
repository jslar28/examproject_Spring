package jsl28.exam.project.controller;

import jsl28.exam.project.service.SitterService;
import jsl28.exam.project.model.Sitter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Jakob on 19-05-2018.
 */

@RestController
public class SitterController {

    private SitterService sitterService;

    public SitterController(SitterService sitterService) {
        this.sitterService = sitterService;
    }

    @RequestMapping(value = "/sitters", method = RequestMethod.GET)
    public ArrayList<Sitter> getAllSitters() {
        return sitterService.getAllSitters();
    }

    @RequestMapping(value = "/sitters/{id}", method = RequestMethod.GET)
    public Sitter getSitterById(@PathVariable("id") int id) {
        return sitterService.getSitter(id);
    }

    @RequestMapping(value = "/sitters", method = RequestMethod.POST)
    public void addSitter(@RequestBody Sitter sitter) {
        sitterService.addSitter(sitter);
    }

    @RequestMapping(value = "/sitters", method = RequestMethod.PUT)
    public void updateSitter(@RequestBody Sitter sitter) {
        sitterService.updateSitter(sitter);
    }

    @RequestMapping(value = "/sitters/{id}", method = RequestMethod.DELETE)
    public void removeSitter(@PathVariable int id) {
        sitterService.removeSitter(id);
    }
}