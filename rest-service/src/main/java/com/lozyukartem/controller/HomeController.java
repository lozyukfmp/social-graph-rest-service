package com.lozyukartem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "get-some-string")
    public String getSomeString() {
        return "Some string";
    }
}