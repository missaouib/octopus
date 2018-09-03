package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zhouy on 2018/9/3.
 */
@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class UserController {

    private final ServiceFactoryImpl serviceFactory;

    @Autowired
    public UserController(ServiceFactoryImpl serviceFactory){
        this.serviceFactory = serviceFactory;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView result = new ModelAndView("login");
        return result;
    }

    @RequestMapping(value="/loginCheck")
    ModelAndView userLoginSuccess(UserVo user){
        ModelAndView result = new ModelAndView("index");
        return result;
    }

    @RequestMapping(value="/loginError")
    ModelAndView userLoginError(UserVo user){
        ModelAndView result = new ModelAndView("error");
        return result;
    }

    @RequestMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView result = new ModelAndView("register");
        result.getModel().put("user", new UserVo());
        return result;
    }

    @PostMapping("/userRegister")
    ModelAndView userRegister(UserVo user){

        //System.out.println("[msg]: " + user.getUserName() + ", " + user.getUserPassword());
        serviceFactory.getUserService().saveUser(user);
        ModelAndView result = new ModelAndView("login");
        return result;
        /*if(userService.userRegister(user)){
            ModelAndView res1 = new ModelAndView("default", "user", user);
            List<String> departments = workshopService.getAllDepartment();
            res1.getModel().put("departments", departments);
            return res1;
        }else {
            ModelAndView res2 = new ModelAndView("register");
            res2.getModel().put("istrue", true);
            res2.getModel().put("user", new UserEntity());
            return res2;
        }*/
    }

    @RequestMapping("fd")
    @ResponseBody
    public String test(){
        return "fda";
    }
}
