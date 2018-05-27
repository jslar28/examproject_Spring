package jsl28.exam.project.controller;

import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.service.SitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jakob on 19-05-2018.
 */

@RestController
@RequestMapping(value = "/")
public class TestController {

    @Autowired
    private SitterUserService sitterUserService;

    @RequestMapping(value = "/mytest")
    public String testMethod() {
        return "Hello world!";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<SitterUser> getAllUsers() {
        return sitterUserService.getAllUser();
    }
}