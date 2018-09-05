package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.dto.ResumeDto;
import com.targaryen.octopus.entity.ResumeEntity;
import com.targaryen.octopus.entity.UserEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = "/applicant/post/apply")
    ModelAndView applicationResumeApply(ModelMap map){
        ModelAndView result = new ModelAndView("applicant-post-apply");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());

        List<PostVo> posts = serviceFactory.getPulicService().listPostsByStatus(PostStatus.PUBLISHED);
        result.addObject("posts", posts);
        return result;
    }

    @RequestMapping(value = "/applicant/post/detail/{postId}")
    public ModelAndView postDetail(@PathVariable("postId") String postId, ModelMap map){
        ModelAndView result = new ModelAndView("applicant-post-detail");

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        PostVo getPost = serviceFactory.getPulicService().findPostById(Integer.parseInt(postId));
        if(getPost != null){
            result.getModel().put("post", getPost);
        }
        return result;
    }

    @RequestMapping(value = "/applicant/application/list")
    public ModelAndView applicationList(ModelMap map){
        ModelAndView result = new ModelAndView("applicant-application-list");

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());

        List<PostVo> posts = serviceFactory.getPulicService().listPostsByStatus(PostStatus.PUBLISHED);
        result.addObject("posts", posts);
        return result;
    }


    @RequestMapping(value = "/applicant/resume/add", method = RequestMethod.GET)
    public String resumeAddGet(ModelMap map) {
        /* UI Settings *//*
        map.addAttribute("title", "Add new post need");
        map.addAttribute("action", "add");
        map.addAttribute("returnUrl", "list");
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");*/

        map.addAttribute("resume", new ResumeEntity());

        return "applicant-resume-add";
    }

    @RequestMapping(value = "/applicant/post/list", method = RequestMethod.GET)
    public String resumePostList(ModelMap map) {
        /* UI Settings *//*
        map.addAttribute("title", "Add new post need");
        map.addAttribute("action", "add");
        map.addAttribute("returnUrl", "list");
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");*/

        //1. save resume information into database

        //2. jump to the post list
        map.addAttribute("postList", serviceFactory.getPulicService().listPostsByStatus(PostStatus.PUBLISHED));

        return "applicant-post-list";
    }

}
