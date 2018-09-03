package com.targaryen.octopus.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhouy on 2018/9/3.
 */
@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class UserController {

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView result = new ModelAndView("login");
        return result;
    }

    @RequestMapping(value = "/register")
    public ModelAndView register(Model model) {
        ModelAndView result = new ModelAndView("register");
        return result;
    }

    @RequestMapping("fd")
    @ResponseBody
    public String test(){
        return "fda";
    }
}
