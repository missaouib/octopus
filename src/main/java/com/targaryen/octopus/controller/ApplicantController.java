package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.*;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.ApplicantStatus;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

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

        //List<ApplicantInterviewVo> interviewVos =  serviceFactory.getApplicantService().findUnreplyedInterviewDetailsByUserId(userId);
        List<ApplicantInterviewVo> interviewVosAccpted = serviceFactory.getApplicantService().findAcceptedInterviewDetailsByUserId(userId);

        List<InterviewVo> interviewVos = serviceFactory.getApplicantService().findUnreplyedInterviewsByUserId(userId);
        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        List<InterviewEntity> interviewEntities = new ArrayList<>();
        for(InterviewVo temp : interviewVos){
            InterviewEntity interviewEntity = new InterviewEntity();
            interviewEntity.setApplicationId(temp.getApplicationId());
            interviewEntity.setInterviewId(temp.getInterviewId());
            interviewEntity.setPostId(temp.getPostId());
            interviewEntity.setInterviewStartTime(fmt.format(temp.getInterviewStartTime()));
            interviewEntity.setInterviewPlace(temp.getInterviewPlace());
            interviewEntity.setPostName(serviceFactory.getPublicService().findPostById(temp.getPostId()).getPostName());
            interviewEntities.add(interviewEntity);
        }
        //mock data
        /*ApplicantInterviewVo interviewVo = new ApplicantInterviewVo.Builder()
                .interviewPlace("Shanghai")
                .interviewStartTime(Calendar.getInstance().getTime())
                .postName("Java").build();
        interviewVos.add(interviewVo);
        interviewVosAccpted.add(interviewVo);*/

        //System.out.println("[msg]: " + interviewVos.get(0).getInterviewPlace());
        result.addObject ("unreplyMsg", interviewEntities);

        map.addAttribute("acceptedMsg", interviewVosAccpted);

        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);

        return result;
    }

    @RequestMapping(value="/applicant/user/setting")
    ModelAndView applicantSetting(ModelMap map){

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        UserEntity userEntity = new UserEntity();
        int userId = AuthInfo.getUserId();
        userEntity.setUserId(userId);
        ModelAndView result = new ModelAndView("applicant-user-setting", "userEntity",userEntity);

        return result;
    }

    @RequestMapping(value="/applicant/user/setting/update")
    ModelAndView applicantSettingUpdate(UserEntity userEntity, ModelMap map, HttpServletRequest request){

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        ModelAndView result = new ModelAndView("applicant-user-setting");

        System.out.println("[msg]: " + userEntity.getNewUserPassword1() + ", " + userEntity.getUserId());
        boolean ret = true; //status of return
        String userName = AuthInfo.getUserName();
        UserVo userVo1 = new UserVo.Builder().userId(userEntity.getUserId()).userName(userName).userPassword(userEntity.getUserPassword()).build();
        ret =  serviceFactory.getUserService().checkPassword(userVo1);

        if(ret && userEntity.getNewUserPassword1().equals(userEntity.getNewUserPassword2())){

            UserVo userVo2 = new UserVo.Builder().userId(userEntity.getUserId()).userName(userName).userPassword(userEntity.getNewUserPassword1()).build();
            serviceFactory.getUserService().editPassword(userVo2);
            request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
            result = new ModelAndView("redirect:/octopus/logout");
        }
        else {
            map.addAttribute("ret", !ret);
        }
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
        /*List<InterviewVo> interviewVos =  serviceFactory.getApplicantService().findUnreplyedInterviewsByUserId(userId);
        List<InterviewVo> interviewVosAccpted = serviceFactory.getApplicantService().findAcceptedInterviewsByUserId(userId);*/
        List<ApplicantInterviewVo> interviewVos =  serviceFactory.getApplicantService().findUnreplyedInterviewDetailsByUserId(userId);
        List<ApplicantInterviewVo> interviewVosAccpted = serviceFactory.getApplicantService().findAcceptedInterviewDetailsByUserId(userId);
        map.addAttribute("unreplyMsg", interviewVos);
        map.addAttribute("acceptedMsg", interviewVosAccpted);
        return result;
    }

    /**
     * old function, refuse doesn't exist
     * @param applicantCommentEntity
     * @param map
     * @return
     */
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

        List<PostVo> posts = serviceFactory.getPublicService().listPostsByStatus(PostStatus.PUBLISHED);
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

        PostVo getPost = serviceFactory.getPublicService().findPostById(Integer.parseInt(postId));
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
        int userId = AuthInfo.getUserId();
        int postId = applicationEntity.getPostId();
        map.addAttribute("postId", postId);
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);
        ResumeModelVo resumeModelVo = serviceFactory.getApplicantService().findResumeModelByPostId(postId);

        int resumeId = resumeVo.getResumeId();
        int resumeModelId = -1;
        boolean isComplete = false;
        if(resumeModelVo != null){
            resumeModelId= resumeModelVo.getResumeModelId();
            isComplete = serviceFactory.getApplicantService().isResumeComplete(resumeId, resumeModelId);
        }

        if(resumeModelId != -1 && (resumeVo == null || isComplete==false)){

            System.out.println("[msg]: " + "applicant register");

            if(resumeVo == null){
                serviceFactory.getApplicantService().createResume(applicantId);
            }
            result = new ModelAndView("applicant-resume-add-magm");
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

    /**
     * applicant add new resume
     * @param map
     * @return
     */
    @RequestMapping(value = "/applicant/resume/add/{postId}", method = RequestMethod.GET)
    public String resumeAdd(@PathVariable("postId") String postId, ModelMap map) {

        int userId = AuthInfo.getUserId();
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);
        int resumeId = serviceFactory.getApplicantService().findResumeByApplicantId(applicantId).getResumeId();

        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setResumeId(resumeId);
        resumeEntity.setApplicantId(applicantId);
        resumeEntity.setPostId(Integer.parseInt(postId));

        map.addAttribute("resumeEntity", resumeEntity);
        //result = new ModelAndView("applicant-resume-add");
        ResumeModelVo resumeModelVo = serviceFactory.getApplicantService().findResumeModelByPostId(Integer.parseInt(postId));
        map.addAttribute("listResumeModel", resumeModelVo);

        return "applicant-resume-add";
  /*      ResumeVo res = new ResumeVo.Builder().applicantId(applicantId)
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
        map.addAttribute("userName", AuthInfo.getUserName());*/

        /*System.out.println("[msg：msg]: " + resumeEntity.getHasPostId());
        if(resumeEntity.getHasPostId() != 0){
            System.out.println("[msg：msg2]: " + resumeEntity.getHasPostId());
            ApplicationVo applicationVo = new ApplicationVo.Builder().applicantId(applicantId).postId(resumeEntity.getHasPostId()).build();
            serviceFactory.getApplicantService().CreateNewApplication(applicationVo);
        }
        List<ApplicantApplicationVo> applicantApplicationVos = serviceFactory.getApplicantService().findApplicationsByUserId(AuthInfo.getUserId());
        map.addAttribute("applicationVos", applicantApplicationVos);*/

    }

    //@RequestMapping(value = "/new/add", method = RequestMethod.GET)
    @RequestMapping(value = "/applicant/resume/new/add", method = RequestMethod.GET)
    public ModelAndView newResumeAddGet(ModelMap map){

        ModelAndView result = null;
        /*resumeRequiredEntity.setApplicantPoliticalStatus(false);
        resumeRequiredEntity.setApplicantMajor(false);*/
        int userId = AuthInfo.getUserId();
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);
        int resumeStatus = serviceFactory.getApplicantService().createResume(applicantId);

        if(resumeStatus == StatusCode.SUCCESS) {

            ResumeRequiredEntity resumeRequiredEntity1 = new ResumeRequiredEntity();

            //resumeRequiredEntity1.setApplicantAddress(false);

            map.addAttribute("listResumeModel", resumeRequiredEntity1);

            ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByApplicantId(applicantId);
            //System.out.println("[msg]: " + resumeVo);
            int resumeId = resumeVo.getResumeId();
            List<WorkExperienceVo> workExperienceVos = serviceFactory.getApplicantService().listWorkExperiencesByResumeId(resumeId);
            map.addAttribute("workExperienceList", workExperienceVos);

            ResumeEntity resumeEntity = new ResumeEntity();
            resumeEntity.setApplicantId(applicantId);
            resumeEntity.setResumeId(resumeId);
            map.addAttribute("resumeEntity",resumeEntity );

            result = new ModelAndView("applicant-resume-new-add");
        }else {
            result = new ModelAndView("redirect:/octopus/applicant/index");
        }
        return result;
    }

    @RequestMapping(value = "/applicant/resume/new/add/fill", method = RequestMethod.POST)
    public String newResumeAddPost(ResumeEntity resumeEntity, ModelMap map){

        System.out.println("[msg]: " + "newResumeAddPost");

        int userId = AuthInfo.getUserId();
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);


        int reumseId = serviceFactory.getApplicantService().findResumeByUserId(AuthInfo.getUserId()).getResumeId();
        System.out.println("[msg]: " + "newResumeAddPost, " + resumeEntity.getResumeId() + " ," + resumeEntity.getApplicantId());

        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        Date dateOfBirth = null;
        Date timeToWork = null;
        Date dutyTime = null;
        try {
            if(!resumeEntity.getApplicantDateOfBirth().equals("")){

                dateOfBirth= fmt.parse(resumeEntity.getApplicantDateOfBirth());
                System.out.println("[msg]: " + "getApplicantDateOfBirth, " + dateOfBirth);
            }
            if(!resumeEntity.getApplicantTimeToWork().equals("")) {
                timeToWork = fmt.parse(resumeEntity.getApplicantTimeToWork());
                System.out.println("[msg]: " + "getApplicantTimeToWork, " + timeToWork);
            }
            if(!resumeEntity.getApplicantDutyTime().equals("")) {
                dutyTime = fmt.parse(resumeEntity.getApplicantDutyTime());
                System.out.println("[msg]: " + "getApplicantDutyTime, " + dutyTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("[msg]: " + "ApplicantName, " + resumeEntity.getApplicantName());
        ResumeVo resumeVo = new ResumeVo.Builder()
                .resumeId(resumeEntity.getResumeId())
                .applicantId(resumeEntity.getApplicantId())
                .applicantName(resumeEntity.getApplicantName())
                .applicantSex(resumeEntity.getApplicantSex())
                .applicantAge(resumeEntity.getApplicantAge())
                .applicantSchool(resumeEntity.getApplicantSchool())
                .applicantDegree(resumeEntity.getApplicantDegree())
                .applicantMajor(resumeEntity.getApplicantMajor())
                .applicantCity(resumeEntity.getApplicantCity())
                .applicantEmail(resumeEntity.getApplicantEmail())
                .applicantPhone(resumeEntity.getApplicantPhone())
                .applicantCV(resumeEntity.getApplicantCV())
                .applicantHometown(resumeEntity.getApplicantHometown())
                .applicantNation(resumeEntity.getApplicantNation())
                .applicantPoliticalStatus(resumeEntity.getApplicantPoliticalStatus())
                .applicantMaritalStatus(resumeEntity.getApplicantMaritalStatus())
                .applicantDateOfBirth(dateOfBirth)
                .applicantTimeToWork(timeToWork)
                .applicantCurrentSalary(resumeEntity.getApplicantCurrentSalary())
                .applicantExpectSalary(resumeEntity.getApplicantExpectSalary())
                .applicantDutyTime(dutyTime)
                .recommenderName(resumeEntity.getRecommenderName())
                .applicantAddress(resumeEntity.getApplicantAddress())
                .applicantSelfIntro(resumeEntity.getApplicantSelfIntro())
                .applicantPhoto(resumeEntity.getApplicantPhoto())
                .applicantDegreePhoto(resumeEntity.getApplicantDegreePhoto())
                .familyContactRelation(resumeEntity.getFamilyContactRelation())
                .familyContactName(resumeEntity.getFamilyContactName())
                .familyContactCompany(resumeEntity.getFamilyContactCompany())
                .familyContactPhoneNum(resumeEntity.getFamilyContactPhoneNum())
                .build();
        serviceFactory.getApplicantService().saveResume(resumeVo);
        ApplicationVo applicationVo = new ApplicationVo.Builder().applicantId(applicantId).postId(resumeEntity.getPostId()).build();
        serviceFactory.getApplicantService().CreateNewApplication(applicationVo);


        return "redirect:/octopus/applicant/resume/basic";
    }

    /**
     * update applicant resume info
     * @param resumeEntity
     * @param map
     * @return
     */
    @RequestMapping(value = "/applicant/resume/update", method = RequestMethod.POST)
    public String resumeUpdate(ResumeEntity resumeEntity, ModelMap map){
        //search database and pass it to front end
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByUserId(AuthInfo.getUserId());
        //get update information from front end

        //save it to backend and redirect to applicant-resume-magm
        ResumeEntity newResumeEntity = new ResumeEntity();
        newResumeEntity.setResumeId(resumeVo.getResumeId());
        newResumeEntity.setApplicantName(resumeVo.getApplicantName());
        newResumeEntity.setApplicantSex(resumeVo.getApplicantSex());
        newResumeEntity.setApplicantAge(resumeVo.getApplicantAge());
        newResumeEntity.setApplicantSchool(resumeVo.getApplicantSchool());
        newResumeEntity.setApplicantDegree(resumeVo.getApplicantDegree());
        newResumeEntity.setApplicantMajor(resumeVo.getApplicantMajor());
        newResumeEntity.setApplicantCity(resumeVo.getApplicantCity());
        newResumeEntity.setApplicantEmail(resumeVo.getApplicantEmail());
        newResumeEntity.setApplicantPhone(resumeVo.getApplicantPhone());
        newResumeEntity.setApplicantCV(resumeVo.getApplicantCV());
        //newResumeEntity.setHasPostId(0);

        map.addAttribute("resume", newResumeEntity);

        return "applicant-resume-add";

    }

    @RequestMapping(value = "/applicant/resume/magm", method = RequestMethod.GET)
    public String resumeMagm(ModelMap map) {

        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByUserId(AuthInfo.getUserId());
        map.addAttribute("resume", resumeVo);
        return "applicant-resume-magm";
    }

    /**
     * post detail list table
     * @param map
     * @return
     */
    @RequestMapping(value = "/applicant/post/list", method = RequestMethod.GET)
    public String resumePostList(ModelMap map) {

        map.addAttribute("postList", serviceFactory.getPublicService().listPostsByStatus(PostStatus.PUBLISHED));

        return "applicant-post-list";
    }

    @RequestMapping(value = "/applicant/interview/magm/{applicationId}", method = RequestMethod.GET)
    public String interviewMagm(@PathVariable("applicationId") int applicationId, ModelMap map){
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return "applicant-interview-magm";
    }

    /**
     * applicant interview detail
     * @param applicationId
     * @param map
     * @return
     */
    @RequestMapping(value = "/applicant/interview/detail/{applicationId}", method = RequestMethod.GET)
    public String interviewDetail(@PathVariable("applicationId") int applicationId, ModelMap map){

        map.addAttribute("interviewList", serviceFactory.getApplicantService().findApplicantInterviewsByApplicationId(applicationId));
        map.addAttribute("InterviewFinal", serviceFactory.getApplicantService().findApplicationByApplicationId(applicationId));
        return "applicant-interview-detail";
    }

    /**
     * applicant accept offer
     * @param applicationId
     * @return
     */
    @RequestMapping(value = "/applicant/interview/acceptOffer", method = RequestMethod.POST)
    @ResponseBody
    public String applicantAcceptOffer(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(serviceFactory.getApplicantService().acceptOfferByApplicationId(applicationId));
    }

    /**
     * applicant reject offer
     * @param applicationId
     * @return
     */
    @RequestMapping(value = "/applicant/interview/rejectOffer", method = RequestMethod.POST)
    @ResponseBody
    public String applicantRejectOffer(@RequestParam("applicationId") int applicationId) {
        return String.valueOf(serviceFactory.getApplicantService().rejectOfferByApplicationId(applicationId));
    }


    @RequestMapping(value = "/applicant/resume/basic/magm", method = RequestMethod.GET)
    public String applicantResumeBasicmagm(Model map){
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return "applicant-resume-basic-magm";
    }

    @RequestMapping(value = "/applicant/resume/basic", method = RequestMethod.GET)
    public ModelAndView applicantResumeBasic(Model map){

        ModelAndView result = null;
        int userId = AuthInfo.getUserId();
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);
        int resumeStatus = serviceFactory.getApplicantService().createResume(applicantId);

        ResumeRequiredEntity resumeRequiredEntity1 = new ResumeRequiredEntity();
        //resumeRequiredEntity1.setApplicantAddress(false);
        //resumeRequiredEntity1.setApplicantMajor(false);
        map.addAttribute("listResumeModel", resumeRequiredEntity1);
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByApplicantId(applicantId);
        int resumeId = resumeVo.getResumeId();

        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setApplicantId(applicantId);
        resumeEntity.setResumeId(resumeId);

        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");

        if(resumeStatus == StatusCode.SUCCESS) {

            //System.out.println("[msg]: " + resumeVo);
            map.addAttribute("resumeEntity",resumeEntity );

            result = new ModelAndView("applicant-resume-basic");
        }else{
            resumeEntity.setApplicantName(resumeVo.getApplicantName());
            resumeEntity.setApplicantSex(resumeVo.getApplicantSex());
            resumeEntity.setApplicantAge(resumeVo.getApplicantAge());
            resumeEntity.setApplicantSchool(resumeVo.getApplicantSchool());
            resumeEntity.setApplicantDegree(resumeVo.getApplicantDegree());
            resumeEntity.setApplicantMajor(resumeVo.getApplicantMajor());
            resumeEntity.setApplicantCity(resumeVo.getApplicantCity());
            resumeEntity.setApplicantEmail(resumeVo.getApplicantEmail());
            resumeEntity.setApplicantPhone(resumeVo.getApplicantPhone());
            resumeEntity.setApplicantCV(resumeVo.getApplicantCV());
            resumeEntity.setApplicantHometown(resumeVo.getApplicantHometown());
            resumeEntity.setApplicantNation(resumeVo.getApplicantNation());
            resumeEntity.setApplicantPoliticalStatus(resumeVo.getApplicantPoliticalStatus());
            resumeEntity.setApplicantMaritalStatus(resumeVo.getApplicantMaritalStatus());
            //resumeEntity.setApplicantDateOfBirth(fmt.format(resumeVo.getApplicantDateOfBirth()));
            //resumeEntity.setApplicantTimeToWork(fmt.format(resumeVo.getApplicantTimeToWork()));
            resumeEntity.setApplicantCurrentSalary(resumeVo.getApplicantCurrentSalary());
            resumeEntity.setApplicantExpectSalary(resumeVo.getApplicantCurrentSalary());
            //resumeEntity.setApplicantDutyTime(fmt.format(resumeVo.getApplicantDutyTime()));
            resumeEntity.setRecommenderName(resumeVo.getRecommenderName());
            resumeEntity.setApplicantAddress(resumeVo.getApplicantAddress());
            resumeEntity.setApplicantSelfIntro(resumeVo.getApplicantSelfIntro());
            resumeEntity.setApplicantPhone(resumeVo.getApplicantPhoto());
            resumeEntity.setApplicantDegreePhoto(resumeVo.getApplicantDegreePhoto());
            resumeEntity.setFamilyContactRelation(resumeVo.getFamilyContactRelation());
            resumeEntity.setFamilyContactName(resumeVo.getFamilyContactName());
            resumeEntity.setFamilyContactCompany(resumeVo.getFamilyContactCompany());
            resumeEntity.setFamilyContactPhoneNum(resumeVo.getFamilyContactPhoneNum());

            map.addAttribute("resumeEntity", resumeEntity);
            result = new ModelAndView("applicant-resume-basic-info");
        }
        return result;
    }

    @RequestMapping(value = "/applicant/resume/work/magm", method = RequestMethod.GET)
    public String applicantResumeWorkmagm(Model map){
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return "applicant-resume-work-magm";
    }

    @RequestMapping(value="/applicant/resume/work", method = RequestMethod.GET)
    public ModelAndView applicantResumeWork(Model map){
        int userId = AuthInfo.getUserId();
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByApplicantId(applicantId);

        int resumeId = resumeVo.getResumeId();
        List<WorkExperienceVo> workExperienceVos = serviceFactory.getApplicantService().listWorkExperiencesByResumeId(resumeId);
        List<WorkExperienceEntity> workExperienceEntities = new ArrayList<>();
        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        for(WorkExperienceVo tmp : workExperienceVos){
            WorkExperienceEntity ans = new WorkExperienceEntity();
            ans.setResumeId(tmp.getResumeId());
            ans.setWorkExperienceId(tmp.getWorkExperienceId());
            ans.setStartTime(fmt.format(tmp.getStartTime()));
            ans.setEndTime(fmt.format(tmp.getEndTime()));
            ans.setCompany(tmp.getCompany());
            ans.setPost(tmp.getPost());
            ans.setCity(tmp.getCity());
            ans.setReferenceName(tmp.getReferenceName());
            ans.setReferencePhoneNum(tmp.getReferencePhoneNum());
            ans.setWorkDescription(tmp.getWorkDescription());
            ans.setAchievement(tmp.getAchievement());

            workExperienceEntities.add(ans);
        }

        ModelAndView result = new ModelAndView("applicant-resume-work");
        result.addObject("workExperienceList", workExperienceEntities);

        WorkExperienceEntity workExperienceEntity =  new WorkExperienceEntity();
        workExperienceEntity.setResumeId(resumeId);
        result.getModel().put("workExperience", workExperienceEntity);
        return result;
    }

    @RequestMapping(value="/applicant/resume/work/add", method = RequestMethod.POST)
    public ModelAndView applicantResumeWorkAdd(WorkExperienceEntity workExperienceEntity, Model map){

        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime= fmt.parse(workExperienceEntity.getStartTime());
            endTime= fmt.parse(workExperienceEntity.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        WorkExperienceVo experienceVo = new WorkExperienceVo.Builder()
                .resumeId(workExperienceEntity.getResumeId())
                .startTime(startTime)
                .endTime(endTime)
                .company(workExperienceEntity.getCompany())
                .city(workExperienceEntity.getCity())
                .post(workExperienceEntity.getPost())
                .workDescription(workExperienceEntity.getWorkDescription())
                .achievement(workExperienceEntity.getAchievement())
                .referenceName(workExperienceEntity.getReferenceName())
                .referencePhoneNum(workExperienceEntity.getReferencePhoneNum()).build();

        serviceFactory.getApplicantService().createWorkExperience(experienceVo);
        ModelAndView result = new ModelAndView("redirect:/octopus/applicant/resume/work");
        return result;
    }

    @RequestMapping(value="/applicant/resume/work/update", method = RequestMethod.POST)
    public ModelAndView applicantResumeWorkUpdate(WorkExperienceEntity work) {

        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        System.out.println("[msg]: "+ work.getStartTime() + ", " + work.getCity());
        try {
            startTime= fmt.parse(work.getStartTime());
            endTime= fmt.parse(work.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        WorkExperienceVo experienceVo = new WorkExperienceVo.Builder()
                .resumeId(work.getResumeId())
                .workExperienceId(work.getWorkExperienceId())
                .startTime(startTime)
                .endTime(endTime)
                .company(work.getCompany())
                .city(work.getCity())
                .post(work.getPost())
                .workDescription(work.getWorkDescription())
                .achievement(work.getAchievement())
                .referenceName(work.getReferenceName())
                .referencePhoneNum(work.getReferencePhoneNum()).build();

        serviceFactory.getApplicantService().updateWorkExperience(experienceVo);
        ModelAndView result = new ModelAndView("redirect:/octopus/applicant/resume/work");
        return result;
    }

    @RequestMapping(value="/applicant/resume/work/delete/{workExperienceId}", method = RequestMethod.GET)
    public ModelAndView applicantResumeWorkDelete(@PathVariable("workExperienceId") int workExperienceId) {

        serviceFactory.getApplicantService().deleteWorkExperienceByWorkExperienceId(workExperienceId);
        ModelAndView result = new ModelAndView("redirect:/octopus/applicant/resume/work");
        return result;
    }

    @RequestMapping(value = "/applicant/resume/education/magm", method = RequestMethod.GET)
    public String applicantResumeEducationmagm(Model map){
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return "applicant-resume-education-magm";
    }

    @RequestMapping(value="/applicant/resume/education", method = RequestMethod.GET)
    public ModelAndView applicantResumeEducation(Model map){
        int userId = AuthInfo.getUserId();
        int applicantId = serviceFactory.getIDService().userIdToApplicantId(userId);
        ResumeVo resumeVo = serviceFactory.getApplicantService().findResumeByApplicantId(applicantId);

        int resumeId = resumeVo.getResumeId();
        ModelAndView result = new ModelAndView("applicant-resume-education");

        List<EducationExperienceVo> educationExperienceVos = serviceFactory.getApplicantService().listEducationExperiencesByResumeId(resumeId);
        map.addAttribute("educationExperienceList", educationExperienceVos);

        EducationExperienceEntity educationExperienceEntity =  new EducationExperienceEntity();
        educationExperienceEntity.setResumeId(resumeId);
        result.getModel().put("educationExperience", educationExperienceEntity);

        return result;
    }


    @RequestMapping(value="/applicant/resume/education/add", method = RequestMethod.POST)
    public ModelAndView applicantResumeEducationAdd( EducationExperienceEntity educationExperienceEntity, Model map) {
        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime= fmt.parse(educationExperienceEntity.getStartTime());
            endTime= fmt.parse(educationExperienceEntity.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EducationExperienceVo educationExperienceVo = new EducationExperienceVo.Builder()
                .resumeId(educationExperienceEntity.getResumeId())
                .startTime(startTime)
                .endTime(endTime)
                .school(educationExperienceEntity.getSchool())
                .major(educationExperienceEntity.getMajor())
                .typeOfStudy(educationExperienceEntity.getTypeOfStudy())
                .degree(educationExperienceEntity.getDegree()).build();

        serviceFactory.getApplicantService().createEducationExperience(educationExperienceVo);
        ModelAndView result = new ModelAndView("redirect:/octopus/applicant/resume/education");
        return result;

    }

    @RequestMapping(value="/applicant/resume/education/update", method = RequestMethod.POST)
    public ModelAndView applicantResumeEducationUpdate(EducationExperienceEntity edu) {

        SimpleDateFormat fmt =new SimpleDateFormat ("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime= fmt.parse(edu.getStartTime());
            endTime= fmt.parse(edu.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EducationExperienceVo educationExperienceVo = new EducationExperienceVo.Builder()
                .educationExperienceId(edu.getEducationExperienceId())
                .resumeId(edu.getResumeId())
                .startTime(startTime)
                .endTime(endTime)
                .school(edu.getSchool())
                .major(edu.getMajor())
                .typeOfStudy(edu.getTypeOfStudy())
                .degree(edu.getDegree()).build();
        serviceFactory.getApplicantService().updateEducationExperience(educationExperienceVo);
        ModelAndView result = new ModelAndView("redirect:/octopus/applicant/resume/education");
        return result;
    }

    @RequestMapping(value="/applicant/resume/education/delete/{educationExperienceId}", method = RequestMethod.GET)
    public ModelAndView applicantResumeEducationDelete(@PathVariable("educationExperienceId") int educationExperienceId) {
        serviceFactory.getApplicantService().deleteEducationExperienceByEducationExperienceId(educationExperienceId);
        ModelAndView result = new ModelAndView("redirect:/octopus/applicant/resume/education");
        return result;
    }
}
