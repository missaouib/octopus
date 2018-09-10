package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.InterviewEntity;
import com.targaryen.octopus.entity.InterviewerCommentEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.InterviewerStatus;
import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.InterviewerInterviewVo;
import com.targaryen.octopus.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //map.addAttribute("postList", serviceFactory.getDptManagerService().findPostsByUserId(AuthInfo.getUserId()));
        int interviewerID = serviceFactory.getIDService().userIdToInterviewerId(AuthInfo.getUserId());

        System.out.println("[msg]: " + interviewerID);
        List<InterviewerInterviewVo> interviewVoList =  serviceFactory.getInterviewerService().listInterviewerInterviewsByInterviewerId(interviewerID);

        map.addAttribute("interviewList", interviewVoList);
        return "interviewer-interview-list";
    }
    @RequestMapping(value="/interviewer/interview/agree/{interviewId}", method = RequestMethod.POST)
    public String agreeInterview(@PathVariable("interviewId") int interviewId){
        System.out.println("[msg]: " + "fd" + interviewId);
        int status = serviceFactory.getInterviewerService().setInterviewerStatus(InterviewerStatus.ACCEPTED, interviewId, "");
        return "redirect:../list";
    }

    @RequestMapping(value="/interviewer/interview/reject/{interviewId}", method = RequestMethod.POST)
    public  String rejectInterview(@PathVariable("interviewId") int interviewId){
        int status = serviceFactory.getInterviewerService().setInterviewerStatus(InterviewerStatus.REJECTED, interviewId, "");
        return "redirect:../list";
    }

    @RequestMapping(value = "/interviewer/applicant/detail/{applicantId}", method = RequestMethod.GET)
    public String applicantDetail(@PathVariable("applicantId") int applicantId, ModelMap map) {
        /* UI Settings */
/*
        map.addAttribute("title", "Check/Edit post need detail");
        map.addAttribute("action", "../edit");
        map.addAttribute("returnUrl", "../list");
        map.addAttribute("swalTextSuccess", "You have successfully edited this post need!");
        map.addAttribute("swalTextFailure", "You have not successfully edited this post need.");
*/

        map.addAttribute("returnUrl", "../../interview/list");
        ResumeVo resumeVo = serviceFactory.getInterviewerService().findResumeByApplicationId(applicantId);
        map.addAttribute("resume", resumeVo);
        return "interviewer-applicant-detail";
    }

    @RequestMapping(value = "/interviewer/interview/timeline/{applicationId}", method = RequestMethod.GET)
    public String interviewerInterviewTimeline(@PathVariable("applicationId") int applicationId, ModelMap map) {

        map.addAttribute("interviewList", serviceFactory.getInterviewerService().findInterviewerInterviewsByApplicationId(applicationId));
        //map.addAttribute("interviewComment", new InterviewerCommentEntity());
        map.put("interviewComment", new InterviewerCommentEntity());
        return "interviewer-interview-timeline";
    }



    @RequestMapping(value = "/interviewer/interview/comment", method = RequestMethod.POST)
    public String interviewInterviewerComment(InterviewerCommentEntity commentEntity, ModelMap map) {

        int interviewId = commentEntity.getInterviewId();
        int applicationId = commentEntity.getApplicationId();

        serviceFactory.getInterviewerService().setInterviewResultStatus(interviewId, commentEntity.getInterviewResultStatus());
        serviceFactory.getInterviewerService().setInterviewResultComment(interviewId, commentEntity.getInterviewerComment());

        map.addAttribute("interviewList", serviceFactory.getInterviewerService().findInterviewerInterviewsByApplicationId(applicationId));
        //map.addAttribute("interviewComment", new InterviewerCommentEntity());
        map.put("interviewComment", new InterviewerCommentEntity());
        return "interviewer-interview-timeline";
    }
/*
    @RequestMapping(value = "/interviewer/interview/timeline/interview/new", method = RequestMethod.POST)
    @ResponseBody
    public String interviewerInterviewTimelineInterviewNew(InterviewEntity interviewEntity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        Date startTime = null;
        try {
            startTime = dateFormat.parse(interviewEntity.getInterviewStartTime());
            InterviewVo interviewVo = new InterviewVo.Builder()
                    .applicationId(interviewEntity.getApplicationId())
                    .interviewerId(interviewEntity.getInterviewerId())
                    .interviewStartTime(startTime)
                    .interviewPlace(interviewEntity.getInterviewPlace())
                    .build();
            return String.valueOf(hrService.createInterview(interviewVo));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(StatusCode.FAILURE);
    }

    @RequestMapping(value = "/interviewer/interview/timeline/interview/cancel", method = RequestMethod.POST)
    @ResponseBody
    public String interviewerInterviewTimelineInterviewCancel(@RequestParam("interviewId") int interviewId) {
        return String.valueOf(hrService.deleteInterviewById(interviewId));
    }

    @RequestMapping(value = "/interviewer/interview/timeline/reject", method = RequestMethod.POST)
    @ResponseBody
    public String interviewerInterviewTimelineReject(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.interviewFailApplicationById(applicationId));
    }

    @RequestMapping(value = "/interviewer/interview/timeline/pass", method = RequestMethod.POST)
    @ResponseBody
    public String interviewerInterviewTimelinePass(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.interviewPassApplicationById(applicationId));
    }

    @RequestMapping(value = "/interviewer/interview/timeline/offer", method = RequestMethod.POST)
    @ResponseBody
    public String interviewerInterviewTimelineOffer(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.sendOfferByApplicationId(applicationId));
    }*/

}
