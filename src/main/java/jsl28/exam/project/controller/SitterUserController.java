package jsl28.exam.project.controller;

import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jakob on 22-05-2018.
 */

@RestController
public class SitterUserController {

    private SitterUserService sitterUserService;

    public SitterUserController(SitterUserService sitterUserService) {
        this.sitterUserService = sitterUserService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<SitterUser> getAllUsers() {
        return sitterUserService.getAllUser();
    }
}