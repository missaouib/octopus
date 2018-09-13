package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.UserEntity;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.vo.DepartmentVo;
import com.targaryen.octopus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /*@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView result = new ModelAndView("default");
        return result;
    }*/

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
            result = new ModelAndView("login");
            result.getModel().put("istrue", 1);
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
        ModelAndView result = new ModelAndView("login");
        result.getModel().put("istrue", 1);
        return result;
    }

    @RequestMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView result = new ModelAndView("register");
        result.getModel().put("user", new UserEntity());
        List<DepartmentVo> departmentVos  = serviceFactory.getPublicService().findAllDepartments();
        System.out.println("[msg]: " + departmentVos.get(0).getDepartmentName());
        result.getModel().put("dptList", departmentVos);
        return result;
    }

    @PostMapping("/userRegister")
    public ModelAndView userRegister(UserEntity user){
        UserVo userVo = new UserVo.Builder().userName(user.getUserName()).userPassword(user.getUserPassword()).userRole(user.getUserRole()).departmentId(user.getDpt()).build();
        int istrue = serviceFactory.getUserService().saveUser(userVo);
        ModelAndView result = null;
        if(istrue == StatusCode.SUCCESS){
            result = new ModelAndView("login");
            result.getModel().put("isRegister", 1);
        }else {
            result = new ModelAndView("register");
            result.getModel().put("user", new UserEntity());
            result.getModel().put("istrue", 1);
        }
        return result;
    }
}
