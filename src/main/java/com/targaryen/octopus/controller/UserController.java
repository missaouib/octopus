package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.UserEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        return login();
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView result = new ModelAndView("login");
        return result;
    }

    @RequestMapping(value="/loginCheck")
    public ModelAndView userLoginSuccess(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        Collection<? extends GrantedAuthority> grantedAuthorityList = userDetails.getAuthorities();
        List<String> tmp = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
            tmp.add(grantedAuthority.getAuthority());
        }
        ModelAndView result = null;
        if (tmp.size() != 1){
            result = new ModelAndView("error");

        } else {
            if (tmp.get(0).equals(Role.APPLICANT)){
                result = new ModelAndView("redirect:/octopus/applicant/index");
            } else if(tmp.get(0).equals(Role.DPT)){
                result = new ModelAndView("redirect:/octopus/dpt/index");
            } else if(tmp.get(0).equals(Role.HR)){
                result = new ModelAndView("redirect:/octopus/hr/index");
            }  if(tmp.get(0).equals(Role.INTERVIEWER)){
                result = new ModelAndView("redirect:/octopus/interviewer/index");
            }
        }
        return result;
    }

    @RequestMapping(value="/loginError")
    public ModelAndView userLoginError(){
        ModelAndView result = new ModelAndView("error");

        return result;
    }

    @RequestMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView result = new ModelAndView("register");
        result.getModel().put("user", new UserEntity());
        return result;
    }

    @PostMapping("/userRegister")
    public ModelAndView userRegister(UserEntity user){
        UserVo userVo = new UserVo.Builder().userName(user.getUserName()).userPassword(user.getUserPassword()).userRole(user.getUserRole()).build();
        serviceFactory.getUserService().saveUser(userVo);
        ModelAndView result = new ModelAndView("login");
        return result;
    }
}
