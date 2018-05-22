package jsl28.exam.project.controller;

import jsl28.exam.project.model.Baby;
import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.service.BabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob on 19-05-2018.
 */

@RestController
@RequestMapping(value = ("/babies"))
public class BabyController {

    @Autowired
    private BabyService babyService;

    @RequestMapping
    public String babies(){
        babyService.createDummyBabies();
        return "Default page";
    }

    @RequestMapping(value = "/sitter/{id}")
    public List<Baby> getAllBabiesForSitter(@PathVariable String id) {
        return babyService.getAllBabiesForSitter(id);
    }

    @RequestMapping(value = "/getall")
    public ArrayList<Baby> getAllBabies() {
        //TODO: Replace with database functionality
        return babyService.getAllBabies();
    }

    @RequestMapping(value = "/get/{id}")
    public Baby getBabyById(@PathVariable("id") String id) {
        return babyService.getBaby(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addbaby")
    public void addBaby(@RequestBody Baby baby, @PathVariable String sitterId) {
        baby.setSitter(new Sitter("", 0));
        babyService.addBaby(baby);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateBaby(@PathVariable String id, @RequestBody Baby baby) {
        babyService.updateBaby(baby);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void removeSitter(@PathVariable String id) {
        babyService.removeBaby(id);
    }
}