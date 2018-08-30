package com.targaryen.octopus.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class HelloWorld {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    String sayHello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/thymeleaf")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/greeting");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }
}
