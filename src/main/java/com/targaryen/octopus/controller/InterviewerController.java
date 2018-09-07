package com.targaryen.octopus.controller;

import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.vo.InterviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zhouy on 2018/9/6.
 */
@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class InterviewerController {

    private final ServiceFactoryImpl serviceFactory;

    @Autowired
    public InterviewerController(ServiceFactoryImpl serviceFactory){
        this.serviceFactory = serviceFactory;
    }

    @RequestMapping(value="/interviewer/index")
    ModelAndView interviewerIndex(ModelMap map){
        ModelAndView result = new ModelAndView("interviewer-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return result;
    }

    @RequestMapping("/interviewer/interview/list")
    public String interviewList(ModelMap map) {
        map.addAttribute("postList", serviceFactory.getDptManagerService().findPostsByUserId(AuthInfo.getUserId()));
        int interviewID = serviceFactory.getIDService().userIdToInterviewerId(AuthInfo.getUserId());
        List<InterviewVo> interviewVoList =  serviceFactory.getInterviewerService().listInterviewsByInterviewerId(interviewID);
        map.addAttribute("interviewList", interviewVoList);
        return "interviewer-interview-list";
    }
}
