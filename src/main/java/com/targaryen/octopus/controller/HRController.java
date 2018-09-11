package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.InterviewEntity;
import com.targaryen.octopus.entity.PostEntity;
import com.targaryen.octopus.entity.ResumeModelEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.HRService;
import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.PostVo;
import com.targaryen.octopus.vo.ResumeModelVo;
import org.modelmapper.ModelMapper;
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

@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class HRController {
    private HRService hrService;

    @Autowired
    public HRController(ServiceFactory serviceFactory) {
        this.hrService = serviceFactory.getHRService();
    }

    @RequestMapping(value="/hr/index")
    public ModelAndView hrIndex(ModelMap map){
        ModelAndView result = new ModelAndView("hr-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return result;
    }

    @RequestMapping(value = "/hr/post/list", method = RequestMethod.GET)
    public String hrPostList(ModelMap map) {
        map.addAttribute("postList", hrService.listPosts());
        return "hr-post-list";
    }

    @RequestMapping(value = "/hr/post/{postId}", method = RequestMethod.GET)
    public String hrPostDetail(@PathVariable("postId") int postId, ModelMap map) {
        /* UI Settings */
        map.addAttribute("title", "Check/Edit post need detail");
        map.addAttribute("action", "edit");
        map.addAttribute("returnUrl", "list");
        map.addAttribute("swalTextSuccess", "You have successfully edited this post need!");
        map.addAttribute("swalTextFailure", "You have not successfully edited this post need.");

        map.addAttribute("post", hrService.findPostById(postId));
        return "dpt-hr-post-detail";
    }

    @RequestMapping(value = "/hr/post/{postId}/application/list", method = RequestMethod.GET)
    public String hrPostApplication(@PathVariable("postId") int postId, ModelMap map) {
        map.addAttribute("post", hrService.findPostById(postId));
        map.addAttribute("applicationList", hrService.findApplicationsByPostId(postId));
        return "hr-post-application-list";
    }

    @RequestMapping(value = "/hr/post/{postId}/resume/model", method = RequestMethod.GET)
    public String hrPostResumeModel(@PathVariable("postId") int postId, ModelMap map) {
        map.addAttribute("post", hrService.findPostById(postId));
        return "hr-post-resume-model";
    }

    @RequestMapping(value = "/hr/post/{postId}/resume/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResumeModelEntity hrPostResumeJson(@PathVariable("postId") int postId) {
        ModelMapper modelMapper = new ModelMapper();
        ResumeModelEntity resumeModelEntity = modelMapper.map(hrService.findResumeModelVoByPostId(postId), ResumeModelEntity.class);
        return resumeModelEntity;
    }

    @RequestMapping(value = "/hr/post/{postId}/application/{appliId}/timeline", method = RequestMethod.GET)
    public String hrApplicationTimeline(@PathVariable("postId") int postId, @PathVariable("appliId") int applicationId, ModelMap map) {
        map.addAttribute("appli", hrService.findApplicationResumeVoByApplicationId(applicationId));

        map.addAttribute("interviewerList", hrService.listInterviewers());

        List<InterviewVo> interviewVoList = hrService.findInterviewByApplicationId(applicationId);

        map.addAttribute("interviewList", interviewVoList);
        if (interviewVoList.size() != 0)
            map.addAttribute("lastInterviewIdOfList", interviewVoList.get(interviewVoList.size() - 1).getInterviewId());
        return "hr-application-timeline";
    }

    /* ***************************************************************************** */

    @RequestMapping(value = "/hr/post/publish", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostPublish(PostEntity postEntity) {
        return String.valueOf(hrService.publishPostById(postEntity.getPostId()));
    }

    @RequestMapping(value = {"/hr/post/unpublish", "/hr/post/reopen"}, method = RequestMethod.POST)
    @ResponseBody
    public String hrPostUnpublish(PostEntity postEntity) {
        return String.valueOf(hrService.closePostById(postEntity.getPostId()));
    }

    @RequestMapping(value = "/hr/post/finish", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostFinish(PostEntity postEntity) {
        return String.valueOf(hrService.finishPostById(postEntity.getPostId()));
    }

    @RequestMapping(value = "/hr/post/edit", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostEditPost(PostEntity postEntity) {
        PostVo postVo = new PostVo.Builder()
                .postId(postEntity.getPostId())
                .postName(postEntity.getPostName())
                .postType(postEntity.getPostType())
                .postLocale(postEntity.getPostLocale())
                .postDescription(postEntity.getPostDescription())
                .postRequirement(postEntity.getPostRequirement())
                .recruitNum(postEntity.getRecruitNum())
                .recruitDpt(postEntity.getRecruitDpt())
                .publishTime(postEntity.getPublishTime())
                .status(postEntity.getStatus())
                .build();

        return String.valueOf(hrService.updatePost(postVo));
    }

    @RequestMapping(value = "/hr/application/resume/pass", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationResumePass(@RequestParam(value="chkArray[]") int[] chkArray) {
        int overAllStatus = StatusCode.SUCCESS;
        if (chkArray.length != 0) {
            for (Integer applicationId: chkArray) {
                int status = hrService.filterPassApplicationById(applicationId);
                if (status != StatusCode.SUCCESS) overAllStatus = status;
            }
        }
        return String.valueOf(overAllStatus);
    }

    @RequestMapping(value = "/hr/application/resume/reject", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationResumeReject(@RequestParam(value="chkArray[]") int[] chkArray) {
        int overAllStatus = StatusCode.SUCCESS;
        if (chkArray.length != 0) {
            for (Integer applicationId: chkArray) {
                int status = hrService.filterFailApplicationById(applicationId);
                if (status != StatusCode.SUCCESS) overAllStatus = status;
            }
        }
        return String.valueOf(overAllStatus);
    }

    @RequestMapping(value = "/hr/application/timeline/interview/new", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineInterviewNew(InterviewEntity interviewEntity) {
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

    @RequestMapping(value = "/hr/application/timeline/interview/cancel", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineInterviewCancel(@RequestParam("interviewId") int interviewId) {
        return String.valueOf(hrService.deleteInterviewById(interviewId));
    }

    @RequestMapping(value = "/hr/application/timeline/reject", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineReject(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.interviewFailApplicationById(applicationId));
    }

    @RequestMapping(value = "/hr/application/timeline/pass", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelinePass(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.interviewPassApplicationById(applicationId));
    }

    @RequestMapping(value = "/hr/application/timeline/offer", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineOffer(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.sendOfferByApplicationId(applicationId));
    }

    @RequestMapping(value = "/hr/post/{postId}/resume/update", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostResumeJson(@PathVariable("postId") int postId, ResumeModelEntity resumeModelEntity) {
        ResumeModelVo resumeModelVo = new ResumeModelVo.Builder()
                .resumeModelId(resumeModelEntity.getResumeModelId())
                .applicantName(resumeModelEntity.isApplicantName())
                .applicantSex(resumeModelEntity.isApplicantSex())
                .applicantAge(resumeModelEntity.isApplicantAge())
                .applicantSchool(resumeModelEntity.isApplicantSchool())
                .applicantDegree(resumeModelEntity.isApplicantDegree())
                .applicantMajor(resumeModelEntity.isApplicantMajor())
                .applicantCity(resumeModelEntity.isApplicantCity())
                .applicantEmail(resumeModelEntity.isApplicantEmail())
                .applicantPhone(resumeModelEntity.isApplicantPhone())
                .applicantCV(resumeModelEntity.isApplicantCV())
                .applicantHometown(resumeModelEntity.isApplicantHometown())
                .applicantNation(resumeModelEntity.isApplicantNation())
                .applicantPoliticalStatus(resumeModelEntity.isApplicantPoliticalStatus())
                .applicantMaritalStatus(resumeModelEntity.isApplicantMaritalStatus())
                .applicantDateOfBirth(resumeModelEntity.isApplicantDateOfBirth())
                .applicantTimeToWork(resumeModelEntity.isApplicantTimeToWork())
                .applicantCurrentSalary(resumeModelEntity.isApplicantCurrentSalary())
                .applicantExpectSalary(resumeModelEntity.isApplicantExpectSalary())
                .applicantDutyTime(resumeModelEntity.isApplicantDutyTime())
                .recommenderName(resumeModelEntity.isRecommenderName())
                .applicantAddress(resumeModelEntity.isApplicantAddress())
                .applicantSelfIntro(resumeModelEntity.isApplicantSelfIntro())
                .applicantPhoto(resumeModelEntity.isApplicantPhoto())
                .applicantDegreePhoto(resumeModelEntity.isApplicantDegreePhoto())
                .familyContactRelation(resumeModelEntity.isFamilyContactRelation())
                .familyContactName(resumeModelEntity.isFamilyContactName())
                .familyContactCompany(resumeModelEntity.isFamilyContactCompany())
                .familyContactPhoneNum(resumeModelEntity.isFamilyContactPhoneNum())
                .build();

        return String.valueOf(hrService.updateResumeModelById(resumeModelVo));
    }
}
