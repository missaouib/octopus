package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.dto.ResumeDto;
import com.targaryen.octopus.entity.UserEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.vo.ResumeVo;
import com.targaryen.octopus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhouy on 2018/9/4.
 */
@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class ApplicantController {

    private final ServiceFactoryImpl serviceFactory;

    @Autowired
    public ApplicantController(ServiceFactoryImpl serviceFactory){
        this.serviceFactory = serviceFactory;
    }


    @RequestMapping(value="/applicant/index")
    ModelAndView applicantLogin(UserEntity user, ModelMap map){
        // judge here, non-existed jump to applicant-index fill the information, existed jump to applicant-resume-magn to update the information

        ModelAndView result = new ModelAndView("applicant-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return result;
    }

    @RequestMapping(value = "/applicant/resume/add", method = RequestMethod.GET)
    public String resumeAddGet(ModelMap map) {
        /* UI Settings */
        map.addAttribute("title", "Add new post need");
        map.addAttribute("action", "add");
        map.addAttribute("returnUrl", "list");
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");

        map.addAttribute("resume", new ResumeDto());

        return "applicant-resume-magm";
    }

}
