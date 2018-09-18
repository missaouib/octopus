package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.MessageDto;
import com.targaryen.octopus.entity.*;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.*;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.InterviewResultStatus;
import com.targaryen.octopus.util.status.RecruitTypeStatus;
import com.targaryen.octopus.util.status.ReservationStatus;
import com.targaryen.octopus.vo.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class HRController {
    private HRService hrService;
    private ApplicantService applicantService;
    private MessageService messageService;
    private AnnouncementService announcementService;

    @Autowired
    public HRController(ServiceFactory serviceFactory) {
        this.hrService = serviceFactory.getHRService();
        this.applicantService = serviceFactory.getApplicantService();
        this.messageService = serviceFactory.getMessageService();
        this.announcementService = serviceFactory.getAnnouncementService();
    }

    @RequestMapping(value="/hr/index")
    public ModelAndView hrIndex(ModelMap map){
        ModelAndView result = new ModelAndView("hr-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return result;
    }

    @RequestMapping(value = "/hr/post/list", method = RequestMethod.GET)
    public String hrPostList(ModelMap map, @RequestParam("type") int type) {
        if (type == RecruitTypeStatus.CAMPUS) {
            map.addAttribute("title", "Campus Recruitment");
        } else if (type == RecruitTypeStatus.SOCIETY) {
            map.addAttribute("title", "Society Recruitment");
        }

        map.addAttribute("recruitType", type);

        List<PostVo> postVoList = hrService.listPosts();
        postVoList = postVoList.stream().filter(s -> s.getRecruitType() == type).collect(Collectors.toList());
        map.addAttribute("postList",postVoList);
        return "hr-post-list";
    }

    /******************************* billboard start zhou_me **************************************/
    @RequestMapping(value = "/hr/billboard/list", method = RequestMethod.GET)
    public String hrAnnouncementList(ModelMap map) {
        /*
        if (type == RecruitTypeStatus.CAMPUS) {
            map.addAttribute("title", "Campus Recruitment");
        } else if (type == RecruitTypeStatus.SOCIETY) {
            map.addAttribute("title", "Society Recruitment");
        }

        map.addAttribute("recruitType", type);*/

        /*List<PostVo> postVoList = hrService.listPosts();
        postVoList = postVoList.stream().filter(s -> s.getRecruitType() == type).collect(Collectors.toList());
        map.addAttribute("postList",postVoList);*/

        List<AnnouncementVo> announcementVos = announcementService.listHRAnnouncement();
        map.addAttribute("announcementList", announcementVos);
        return "hr-billboard-list";
    }

    @RequestMapping(value = "/hr/billboard/add", method = RequestMethod.GET)
    public String hrBillboardAddGet(ModelMap map) {

        /*
        map.addAttribute("title", "New Post");
        map.addAttribute("roleName", "Department Manager");
        map.addAttribute("action", "add");
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");

        map.addAttribute("post", new PostVo.Builder()
                .departmentName(dptManagerService.findDptNameByUserId(AuthInfo.getUserId()))
                .recruitNum(1)
                .build());
        */

        map.addAttribute("returnUrl", "list");
        map.addAttribute("action", "add");
        map.addAttribute("swalTextSuccess", "You have successfully added a new announcement!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new announcement.");

        map.addAttribute("announcement", new AnnouncementVo.Builder().build());
        return "hr-billboard-detail";
    }

    @RequestMapping(value = "/hr/billboard/add", method = RequestMethod.POST)
    @ResponseBody
    public String hrBillboardAddPost(AnnouncementEntity announcementEntity) {

        AnnouncementVo announcementVo = new AnnouncementVo.Builder()
                .announcementTitle(announcementEntity.getAnnouncementTitle())
                .announcementDetail(announcementEntity.getAnnouncementDetail())
                .announcementType(announcementEntity.getAnnouncementType()).build();

        return String.valueOf(announcementService.createNewAnnouncement(announcementVo));
    }

    @RequestMapping(value = "/hr/billboard/publish", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostPublish(AnnouncementEntity announcementEntity) {
        return String.valueOf(announcementService.publishAnnouncementById(announcementEntity.getAnnouncementId()));
    }

    @RequestMapping(value = "/hr/billboard/revoke", method = RequestMethod.POST)
    @ResponseBody
    public String hrBillboardRevoke(AnnouncementEntity announcementEntity) {
        return String.valueOf(announcementService.revokeAnnouncementById(announcementEntity.getAnnouncementId()));
    }
    /************************* end of billboard ********************************************/

    @RequestMapping(value = "/hr/post/{postId}", method = RequestMethod.GET)
    public String hrPostDetail(@PathVariable("postId") int postId, ModelMap map) {
        PostVo postVo = hrService.findPostById(postId);
        map.addAttribute("title", "Check/Edit Post Detail");
        map.addAttribute("recruitType", postVo.getRecruitType() == RecruitTypeStatus.SOCIETY ? true : false);
        map.addAttribute("roleName", "HR");
        map.addAttribute("action", "edit");
        map.addAttribute("returnUrl", "list?type=" + postVo.getRecruitType());
        map.addAttribute("swalTextSuccess", "You have successfully edited this post need!");
        map.addAttribute("swalTextFailure", "You have not successfully edited this post need.");

        map.addAttribute("post", postVo);

        // Test
        MessageDto messageDto = new MessageDto();
        messageDto.setText("Hello World");
        messageService.broadcastAndSave("/octopus/ws/hr", messageDto, false);

        return "dpt-hr-post-detail";
    }

    @RequestMapping(value = "/hr/post/{postId}/application/list", method = RequestMethod.GET)
    public String hrPostApplication(@PathVariable("postId") int postId, ModelMap map) {
        map.addAttribute("post", hrService.findPostById(postId));
        map.addAttribute("applicationList", hrService.findApplicationsByPostId(postId));

        /* Attach lastest interview to applications */
        List<InterviewVo> interviewVoList = hrService.findLatestAppInterviewByPostId(postId);
        Map<Integer, String> interviewVoMap = new HashMap<>();
        for (InterviewVo interviewVo: interviewVoList) {
            if (interviewVo != null) {
                interviewVoMap.put(interviewVo.getApplicationId(), "Round " + interviewVo.getInterviewRound() + " "
                        + InterviewResultStatus.toString(interviewVo.getInterviewResultStatus()));
            } else {
                interviewVoMap.put(interviewVo.getApplicationId(), "Resume Passed");
            }
        }
        map.addAttribute("applicationLastestInterview", interviewVoMap);

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

        map.addAttribute("interviewerList", hrService.listInterviewersByPostId(postId));

        List<InterviewVo> interviewVoList = hrService.findInterviewByApplicationId(applicationId);

        map.addAttribute("interviewList", interviewVoList);
        if (interviewVoList.size() != 0)
            map.addAttribute("lastInterviewIdOfList", interviewVoList.get(interviewVoList.size() - 1).getInterviewId());
        return "hr-application-timeline";
    }


    @RequestMapping(value = "/hr/post/{postId}/schedule/model", method = RequestMethod.GET)
    public String hrPostScheduleModel(@PathVariable("postId") int postId, ModelMap map) {
        map.addAttribute("post", hrService.findPostById(postId));
        map.addAttribute("applicationCount", hrService.findApplicationsByPostId(postId).size());
        map.addAttribute("applicantList", hrService.findApplicationsByPostId(postId));
        map.addAttribute("interviewerList", hrService.listInterviewersByPostId(postId));
        return "hr-post-schedule-model";
    }

    @RequestMapping(value = "/hr/post/{postId}/schedule/round/{interviewRound}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PostScheduleEntity hrPostCalendarInterviewRound(@PathVariable("postId") int postId, @PathVariable("interviewRound") int interviewRound, ModelMap map) {
        List<InterviewVo> interviewVoList = hrService.findInterviewByPostIdAndRound(postId, interviewRound);
        PostScheduleEntity postScheduleEntity = new PostScheduleEntity();

        /* Collect unique dates */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);

        Set<String> uniqueDates = new HashSet<>();
        Map<String, Map<String, List<InterviewVo>>> interviewVoHashMap = new HashMap<>();

        for (InterviewVo interviewVo: interviewVoList) {
            String yyyyMMdd = dateFormat.format(interviewVo.getInterviewStartTime());
            String hhMM = timeFormat.format(interviewVo.getInterviewStartTime());
            uniqueDates.add(yyyyMMdd);

            Map<String, List<InterviewVo>> interviewVoListGroupByDate = interviewVoHashMap.getOrDefault(yyyyMMdd, new HashMap<>());
            List<InterviewVo> interviewVoListGroupByTime = interviewVoListGroupByDate.getOrDefault(hhMM, new ArrayList<>());

            interviewVoListGroupByTime.add(interviewVo);
            interviewVoListGroupByDate.put(hhMM, interviewVoListGroupByTime);
            interviewVoHashMap.put(yyyyMMdd, interviewVoListGroupByDate);
        }

        // Prepare Json
        List<String> uniqueDatesList = new ArrayList<>(uniqueDates);
        Collections.sort(uniqueDatesList);
        postScheduleEntity.setUniqueDates(uniqueDatesList);
        postScheduleEntity.setInterviewMapThisRound(interviewVoHashMap);
        postScheduleEntity.setInterviewOverallCount(interviewVoList.size());
        if (uniqueDatesList.size() > 0) {
            postScheduleEntity.setOverallStartDate(uniqueDatesList.get(0));
            postScheduleEntity.setOverallFinalDate(uniqueDatesList.get(uniqueDatesList.size() - 1));
        } else {
            postScheduleEntity.setOverallStartDate("N/A");
            postScheduleEntity.setOverallFinalDate("N/A");
        }
        return postScheduleEntity;
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
                .recruitType(postEntity.isRecruitType() ? RecruitTypeStatus.SOCIETY: RecruitTypeStatus.CAMPUS)
                .postName(postEntity.getPostName())
                .postType(postEntity.getPostType())
                .postLocale(postEntity.getPostLocale())
                .postDescription(postEntity.getPostDescription())
                .postRequirement(postEntity.getPostRequirement())
                .recruitNum(postEntity.getRecruitNum())
                .publishTime(postEntity.getPublishTime())
                .status(postEntity.getStatus())
                .build();

        return String.valueOf(hrService.updatePost(postVo));
    }

    @RequestMapping(value = "/hr/post/{postId}/application/resume/pass", method = RequestMethod.POST)
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

    @RequestMapping(value = "/hr/post/{postId}/application/resume/reject", method = RequestMethod.POST)
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

    @RequestMapping(value = "/hr/post/{postId}/application/hr/pass", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationHrPass(@RequestParam(value="chkArray[]") int[] chkArray) {
        int overAllStatus = StatusCode.SUCCESS;
        if (chkArray.length != 0) {
            for (Integer applicationId: chkArray) {
                int status = hrService.interviewPassApplicationById(applicationId);
                if (status != StatusCode.SUCCESS) overAllStatus = status;
            }
        }
        return String.valueOf(overAllStatus);
    }

    @RequestMapping(value = "/hr/post/{postId}/application/hr/reject", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationHrReject(@RequestParam(value="chkArray[]") int[] chkArray) {
        int overAllStatus = StatusCode.SUCCESS;
        if (chkArray.length != 0) {
            for (Integer applicationId: chkArray) {
                int status = hrService.interviewFailApplicationById(applicationId);
                if (status != StatusCode.SUCCESS) overAllStatus = status;
            }
        }
        return String.valueOf(overAllStatus);
    }

    @RequestMapping(value = "/hr/post/{postId}/application/{appliId}/interview/new", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineInterviewNew(@PathVariable("postId") int postId, InterviewEntity interviewEntity) {
        // Locale is a MUST?
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date startTime = null;
        try {
            startTime = dateFormat.parse(interviewEntity.getInterviewStartTime());
            InterviewVo interviewVo = new InterviewVo.Builder()
                    .applicationId(interviewEntity.getApplicationId())
                    .interviewerId(interviewEntity.getInterviewerId())
                    .interviewStartTime(startTime)
                    .postId(postId)
                    .interviewPlace(interviewEntity.getInterviewPlace())
                    .build();
            return String.valueOf(hrService.createInterview(interviewVo));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(StatusCode.FAILURE);
    }

    @RequestMapping(value = "/hr/post/{postId}/application/{appliId}/interview/cancel", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineInterviewCancel(@RequestParam("interviewId") int interviewId) {
        return String.valueOf(hrService.deleteInterviewById(interviewId));
    }

    @RequestMapping(value = "/hr/post/{postId}/application/{appliId}/reject", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelineReject(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.interviewFailApplicationById(applicationId));
    }

    @RequestMapping(value = "/hr/post/{postId}/application/{appliId}/pass", method = RequestMethod.POST)
    @ResponseBody
    public String hrApplicationTimelinePass(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(hrService.interviewPassApplicationById(applicationId));
    }

    @RequestMapping(value = "/hr/post/{postId}/application/{appliId}/offer", method = RequestMethod.POST)
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

    @RequestMapping(value = "/hr/post/{postId}/schedule/round/new", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostScheduleModelRoundNew(@PathVariable("postId") int postId) {
        return String.valueOf(hrService.addNewInterviewRoundByPostId(postId));
    }

    @RequestMapping(value = "/hr/post/{postId}/schedule/interview/new", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostScheduleModel(@PathVariable("postId") int postId,
                                      @RequestParam(value="dates[]") String[] dates,
                                      @RequestParam(value="times[]") String[] times,
                                      @RequestParam("interviewPlace") String interviewPlace,
                                      @RequestParam("count") String count,
                                      @RequestParam("interviewRound") String interviewRound) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

        List<InterviewVo> interviewVoList = new ArrayList<>();

        for (int i = 0; i < Integer.valueOf(count); i++) {
            for (String dateString : dates) {
                for (String startTimeString : times) {

                    Date startTime = null;
                    try {
                        startTime = dateFormat.parse(dateString + " " + startTimeString);
                        InterviewVo interviewVo = new InterviewVo.Builder()
                                .interviewStartTime(startTime)
                                .postId(postId)
                                .interviewPlace(interviewPlace)
                                .interviewRound(Integer.valueOf(interviewRound))
                                .reservationStatus(ReservationStatus.SUCCESS)
                                .build();
                        interviewVoList.add(interviewVo);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Batch create by hibernate
        hrService.createListOfInterviews(interviewVoList);

        return "";
    }

    @RequestMapping(value = "/hr/post/{postId}/schedule/edit/applicant", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostScheduleModelEditApplicant(@PathVariable("postId") int postId,
                                                   @RequestParam("interviewId") int interviewId,
                                                   @RequestParam("whoId") int whoId) {
        hrService.updateApplicationOfInterview(interviewId, whoId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return dateFormat.format(hrService.findInterviewById(interviewId).getInterviewStartTime());
    }

    @RequestMapping(value = "/hr/post/{postId}/schedule/edit/interviewer", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostScheduleModelEditInterviewer(@PathVariable("postId") int postId,
                                                     @RequestParam("interviewId") int interviewId,
                                                     @RequestParam("whoId") int whoId) {
        hrService.updateInterviewerOfInterview(interviewId, whoId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return dateFormat.format(hrService.findInterviewById(interviewId).getInterviewStartTime());
    }

    @RequestMapping(value = "/hr/post/{postId}/schedule/round/{interviewRound}/auto", method = RequestMethod.POST)
    @ResponseBody
    public String hrPostScheduleAutoGenerator(@PathVariable("postId") int postId,
                                              @PathVariable("interviewRound") int interviewRound ){
        List<InterviewVo> lastestInterviewVoList = hrService.findLatestAppInterviewByPostId(postId);
        List<InterviewVo> interviewVoList = hrService.findInterviewByPostIdAndRound(postId, interviewRound);
        List<InterviewerVo> interviewerVoList = hrService.listInterviewersByPostId(postId);

        int overallApplicants = hrService.findApplicationsByPostId(postId).size();

        int passedApplicants = 0;
        for (InterviewVo interviewVo: lastestInterviewVoList) {
            if (interviewVo.getInterviewResultStatus() != InterviewResultStatus.INIT &&
                    interviewVo.getInterviewResultStatus() != InterviewResultStatus.NOT_HIRE) {
                passedApplicants += 1;
            }
        }

        // If this is the first round
        if (interviewRound == 1) passedApplicants = overallApplicants;

        int interviewCreated = 0;
        if (passedApplicants > 0) {
            // If there are not enough interviews for passed applicants
            if (passedApplicants > interviewVoList.size()) {
                return String.valueOf(interviewCreated);
            }

            // Let's do this
            Iterator<InterviewerVo> interviewerPointer = interviewerVoList.iterator();

            for (int i = 0; i != passedApplicants; i++) {
                if (!interviewerPointer.hasNext()) {
                    interviewerPointer = interviewerVoList.iterator(); // Reset to the start
                }
                hrService.updateInterviewerOfInterview(interviewVoList.get(i).getInterviewId(), interviewerPointer.next().getInterviewerId());
                interviewCreated += 1;
            }
        }
        return String.valueOf(interviewCreated);
    }

    /******************************/
    /**
     * applicant accept offer
     * @param applicationId
     * @return
     */
    @RequestMapping(value = "/hr/post/{postId}/application/acceptOffer", method = RequestMethod.POST)
    @ResponseBody
    public String applicantAcceptOffer(@PathVariable("postId") int postId, @RequestParam("applicationId") int applicationId) {
        return String.valueOf(applicantService.acceptOfferByApplicationId(applicationId));
    }

    /**
     * applicant reject offer
     * @param applicationId
     * @return
     */
    @RequestMapping(value = "/hr/post/{postId}/rejectOffer", method = RequestMethod.POST)
    @ResponseBody
    public String applicantRejectOffer(@PathVariable("postId") int postId, @RequestParam("applicationId") int applicationId) {
        return String.valueOf(applicantService.rejectOfferByApplicationId(applicationId));
    }
    /******************************/
}
