package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.dto.ResumeDto;
import com.targaryen.octopus.entity.ApplicationEntity;
import com.targaryen.octopus.entity.ResumeEntity;
import com.targaryen.octopus.entity.UserEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.PostVo;
import com.targaryen.octopus.vo.ResumeVo;
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

        ApplicationEntity application = new ApplicationEntity();
        application.setPostId(Integer.parseInt(postId));
        application.setApplicantId(serviceFactory.getIDService().userIdToApplicantId(AuthInfo.getUserId()));

        map.addAttribute("application", application);

        PostVo getPost = serviceFactory.getPulicService().findPostById(Integer.parseInt(postId));
        if(getPost != null){
            result.getModel().put("post", getPost);
        }
        return result;
    }

    @RequestMapping(value = "/applicant/application/list")
    public ModelAndView applicationList(ApplicationEntity applicationEntity, ModelMap map){

        //product application record
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        ModelAndView result = null;

        System.out.println("[msg]: " + applicationEntity.getPostId() + ", " + applicationEntity.getApplicantId());

        ResumeVo resumeVo =  serviceFactory.getApplicantService().findResumeByUserId(AuthInfo.getUserId());
        if(resumeVo == null){

            System.out.println("[msg]: " + "applicant register");
            map.addAttribute("resume", new ResumeEntity());
            result = new ModelAndView("applicant-resume-add");
            return result;
        }
        // get the relative application record
        if(applicationEntity.getPostId() != 0){

            ApplicationVo applicationVo = new ApplicationVo.Builder().applicantId(applicationEntity.getApplicantId()).postId(applicationEntity.getPostId()).build();
            serviceFactory.getApplicantService().CreateNewApplication(applicationVo);
        }
        result = new ModelAndView("applicant-application-list");

        List<ApplicationVo> applicationVos = serviceFactory.getApplicantService().findApplicationsByUserId(AuthInfo.getUserId());
        result.addObject("applicationVos", applicationVos);
        return result;

    }


    @RequestMapping(value = "/applicant/resume/add", method = RequestMethod.POST)
    public String resumeAdd(ResumeEntity resumeEntity, ModelMap map) {
        /* UI Settings *//*
        map.addAttribute("title", "Add new post need");
        map.addAttribute("action", "add");
        map.addAttribute("returnUrl", "list");
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");*/

        System.out.println("[msg]: " + "applicant addd");
        System.out.println("[msg]: " + resumeEntity.getApplicantName() + ", " + resumeEntity.getApplicantPhone());

        int applicantId = serviceFactory.getIDService().userIdToApplicantId(AuthInfo.getUserId());
        ResumeVo res = new ResumeVo.Builder().applicantId(applicantId)
                .applicantName(resumeEntity.getApplicantName())
                .applicantSex(resumeEntity.getApplicantSex())
                .applicantAge(resumeEntity.getApplicantAge())
                .applicantSchool(resumeEntity.getApplicantSchool())
                .applicantDegree(resumeEntity.getApplicantDegree())
                .applicantMajor(resumeEntity.getApplicantMajor())
                .applicantCity(resumeEntity.getApplicantCity())
                .applicantEmail(resumeEntity.getApplicantEmail())
                .applicantPhone(resumeEntity.getApplicantPhone()).build();
        serviceFactory.getApplicantService().SaveResume(AuthInfo.getUserId(), res);

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());

        List<ApplicationVo> applicationVos = serviceFactory.getApplicantService().findApplicationsByUserId(AuthInfo.getUserId());
        map.addAttribute("applicationVos", applicationVos);

        return "applicant-application-list";
}

    @RequestMapping(value = "/applicant/resume/update", method = RequestMethod.POST)
    public String resumeUpdate(ResumeEntity resumeEntity, ModelMap map){
        //search database and pass it to front end
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByUserId(AuthInfo.getUserId());
        //get update information from front end

        //save it to backend and redirect to applicant-resume-magm

        map.addAttribute("resume", resumeVo);

        return "applicant-resume-add";

    }
    @RequestMapping(value = "/applicant/resume/magm", method = RequestMethod.GET)
    public String resumeMagm(ModelMap map) {
        /* UI Settings *//*
        map.addAttribute("title", "Add new post need");
        map.addAttribute("action", "add");
        map.addAttribute("returnUrl", "list");
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");*/

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByUserId(AuthInfo.getUserId());
        map.addAttribute("resume", resumeVo);
        return "applicant-resume-magm";
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

    @RequestMapping(value = "/applicant/interview/magm", method = RequestMethod.GET)
    public String interviewMagm(ModelMap map){
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return "applicant-interview-magm";
    }

    @RequestMapping(value = "/applicant/interview/detail", method = RequestMethod.GET)
    public String interviewDetail(ModelMap map){
        return "applicant-interview-detail";
    }

}
