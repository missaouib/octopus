package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.ApplicantCommentEntity;
import com.targaryen.octopus.entity.ApplicationEntity;
import com.targaryen.octopus.entity.ResumeEntity;
import com.targaryen.octopus.entity.UserEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.status.ApplicantStatus;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
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

        int userId = AuthInfo.getUserId();

        List<ApplicantInterviewVo> interviewVos =  serviceFactory.getApplicantService().findUnreplyedInterviewDetailsByUserId(userId);
        List<ApplicantInterviewVo> interviewVosAccpted = serviceFactory.getApplicantService().findAcceptedInterviewDetailsByUserId(userId);

        /*InterviewVo interviewVo = new InterviewVo.Builder().interviewPlace("Shanghai").interviewStartTime(Calendar.getInstance().getTime()).build();
        interviewVos.add(interviewVo);
        interviewVosAccpted.add(interviewVo);*/

        //System.out.println("[msg]: " + interviewVos.get(0).getInterviewPlace());
        result.addObject ("unreplyMsg", interviewVos);

        map.addAttribute("acceptedMsg", interviewVosAccpted);
        return result;
    }

    @RequestMapping(value = "/applicant/agree/{interviewId}")
    ModelAndView applicantAgree(@PathVariable("interviewId") String interviewId, ModelMap map){

        //Change interview status
        serviceFactory.getApplicantService().updateApplicantStatusOfInterview(Integer.parseInt(interviewId), ApplicantStatus.ACCEPTED, null);

        ModelAndView result = new ModelAndView("applicant-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());

        int userId = AuthInfo.getUserId();
        List<InterviewVo> interviewVos =  serviceFactory.getApplicantService().findUnreplyedInterviewsByUserId(userId);
        List<InterviewVo> interviewVosAccpted = serviceFactory.getApplicantService().findAcceptedInterviewsByUserId(userId);

        result.addObject ("unreplyMsg", interviewVos);
        map.addAttribute("acceptedMsg", interviewVosAccpted);
        return result;
    }

    @RequestMapping(value = "/applicant/refuse")
    ModelAndView applicantRefuse(ApplicantCommentEntity applicantCommentEntity, ModelMap map){

        //Change interview status
        serviceFactory.getApplicantService().updateApplicantStatusOfInterview(applicantCommentEntity.getInterviewId(), ApplicantStatus.REJECTED, applicantCommentEntity.getApplicantComment());

        ModelAndView result = new ModelAndView("applicant-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());

        int userId = AuthInfo.getUserId();

        List<InterviewVo> interviewVos =  serviceFactory.getApplicantService().findUnreplyedInterviewsByUserId(userId);
        List<InterviewVo> interviewVosAccpted = serviceFactory.getApplicantService().findAcceptedInterviewsByUserId(userId);
        map.addAttribute("unreplyMsg", interviewVos);
        map.addAttribute("acceptedMsg", interviewVosAccpted);
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
            ResumeEntity resumeEntity = new ResumeEntity();
            resumeEntity.setHasPostId(applicationEntity.getPostId());
            map.addAttribute("resume", resumeEntity);
            result = new ModelAndView("applicant-resume-add");
            return result;
        }
        // get the relative application record
        if(applicationEntity.getPostId() != 0){

            ApplicationVo applicationVo = new ApplicationVo.Builder().applicantId(applicationEntity.getApplicantId()).postId(applicationEntity.getPostId()).build();
            serviceFactory.getApplicantService().CreateNewApplication(applicationVo);
        }
        result = new ModelAndView("applicant-application-list");

        List<ApplicantApplicationVo> applicantApplicationVos = serviceFactory.getApplicantService().findApplicationsByUserId(AuthInfo.getUserId());
        result.addObject("applicationVos", applicantApplicationVos);
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

        System.out.println("[msg：msg]: " + resumeEntity.getHasPostId());
        if(resumeEntity.getHasPostId() != 0){
            System.out.println("[msg：msg2]: " + resumeEntity.getHasPostId());
            ApplicationVo applicationVo = new ApplicationVo.Builder().applicantId(applicantId).postId(resumeEntity.getHasPostId()).build();
            serviceFactory.getApplicantService().CreateNewApplication(applicationVo);
        }
        List<ApplicantApplicationVo> applicantApplicationVos = serviceFactory.getApplicantService().findApplicationsByUserId(AuthInfo.getUserId());
        map.addAttribute("applicationVos", applicantApplicationVos);

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
