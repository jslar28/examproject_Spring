package jsl28.exam.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jakob on 19-05-2018.
 */

@RestController
@RequestMapping(value = "/")
public class TestController {

    @RequestMapping(value = "/mytest")
    public String testMethod() {
        return "Hello world!";
    }
}