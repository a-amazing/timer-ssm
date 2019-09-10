package com.how2java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public List<String> hello(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        return strings;
    }
}
