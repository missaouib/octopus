package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.MessageDto;
import com.targaryen.octopus.entity.InterviewerCommentEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.status.HRMessage;
import com.targaryen.octopus.util.status.InterviewerStatus;
import com.targaryen.octopus.util.status.RecruitTypeStatus;
import com.targaryen.octopus.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
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

        InterviewVo interviewVo = serviceFactory.getHRService().findInterviewById(interviewId);
        ApplicationVo applicationVo = serviceFactory.getInterviewerService().findApplicationByApplicationId(interviewVo.getApplicationId());

        // Notification
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(AuthInfo.getUserName());
        messageDto.setText("accepted interview of applicant: ");
        messageDto.setObject(interviewVo.getApplicantName());
        messageDto.setLink("hr/post/" + applicationVo.getPostId() + "/application/" + applicationVo.getApplicationId() + "/timeline");
        messageDto.setMessageType(HRMessage.WER_ACCEPT_INTERVIEW);
        messageDto.setCreateTime(Calendar.getInstance().getTime());
        messageDto.setChannel("hr");

        serviceFactory.getMessageService().broadcastAndSave("hr", messageDto, true);

        return "redirect:../list";
    }

    @RequestMapping(value="/interviewer/interview/reject/{interviewId}", method = RequestMethod.POST)
    public  String rejectInterview(@PathVariable("interviewId") int interviewId){
        int status = serviceFactory.getInterviewerService().setInterviewerStatus(InterviewerStatus.REJECTED, interviewId, "");

        InterviewVo interviewVo = serviceFactory.getHRService().findInterviewById(interviewId);
        ApplicationVo applicationVo = serviceFactory.getInterviewerService().findApplicationByApplicationId(interviewVo.getApplicationId());

        // Notification
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(AuthInfo.getUserName());
        messageDto.setText("rejected interview of applicant: ");
        messageDto.setObject(interviewVo.getApplicantName());
        messageDto.setLink("hr/post/" + applicationVo.getPostId() + "/application/" + applicationVo.getApplicationId() + "/timeline");
        messageDto.setMessageType(HRMessage.WER_REJECT_INTERVIEW);
        messageDto.setCreateTime(Calendar.getInstance().getTime());
        messageDto.setChannel("hr");

        serviceFactory.getMessageService().broadcastAndSave("hr", messageDto, true);

        return "redirect:../list";
    }

    @RequestMapping(value = "/interviewer/applicant/detail/{applicantId}", method = RequestMethod.GET)
    public String applicantDetail(@PathVariable("applicantId") int applicantId, ModelMap map) {

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
        ApplicationVo applicationVo =  serviceFactory.getInterviewerService().findApplicationByApplicationId(applicationId);
        map.addAttribute("InterviewFinal",applicationVo );
        return "interviewer-interview-timeline";
    }



    @RequestMapping(value = "/interviewer/interview/comment", method = RequestMethod.POST)
    public String interviewInterviewerComment(InterviewerCommentEntity commentEntity, ModelMap map) {

        int interviewId = commentEntity.getInterviewId();
        int applicationId = commentEntity.getApplicationId();

        serviceFactory.getInterviewerService().setInterviewResultStatus(interviewId, commentEntity.getInterviewResultStatus());
        serviceFactory.getInterviewerService().setInterviewResultComment(interviewId, commentEntity.getInterviewerComment());


        InterviewVo interviewVo = serviceFactory.getHRService().findInterviewById(interviewId);
        ApplicationVo applicationVo = serviceFactory.getInterviewerService().findApplicationByApplicationId(applicationId);

        // Notification
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(AuthInfo.getUserName());
        messageDto.setText("commented on the interview of applicant: ");
        messageDto.setObject(interviewVo.getApplicantName());
        messageDto.setLink("hr/post/" + applicationVo.getPostId() + "/application/" + applicationVo.getApplicationId() + "/timeline");
        messageDto.setMessageType(HRMessage.WER_COMMENT_INTERVIEW);
        messageDto.setCreateTime(Calendar.getInstance().getTime());
        messageDto.setChannel("hr");

        serviceFactory.getMessageService().broadcastAndSave("hr", messageDto, true);



        map.addAttribute("interviewList", serviceFactory.getInterviewerService().findInterviewerInterviewsByApplicationId(applicationId));
        //map.addAttribute("interviewComment", new InterviewerCommentEntity());
        map.put("interviewComment", new InterviewerCommentEntity());

        map.addAttribute("InterviewFinal", applicationVo);
        return "interviewer-interview-timeline";
    }

}
