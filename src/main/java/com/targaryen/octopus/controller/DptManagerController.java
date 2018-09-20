package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.MessageDto;
import com.targaryen.octopus.entity.PostEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.DptManagerService;
import com.targaryen.octopus.service.MessageService;
import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.HRMessage;
import com.targaryen.octopus.util.status.RecruitTypeStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DptManagerController {
    private DptManagerService dptManagerService;
    private MessageService messageService;

    @Autowired
    public DptManagerController(ServiceFactory serviceFactory) {
        this.dptManagerService = serviceFactory.getDptManagerService();
        this.messageService = serviceFactory.getMessageService();
    }

    @RequestMapping(value="/dpt/index")
    public ModelAndView dptIndex(ModelMap map){
        ModelAndView result = new ModelAndView("dpt-index");
        map.addAttribute("roleName", Role.getRoleNameByAuthority());
        map.addAttribute("userName", AuthInfo.getUserName());
        return result;
    }

    @RequestMapping("/dpt/post/list")
    public String postList(ModelMap map, @RequestParam("type") int type) {
        if (type == RecruitTypeStatus.CAMPUS) {
            map.addAttribute("title", "Campus Posts");
        } else if (type == RecruitTypeStatus.SOCIETY) {
            map.addAttribute("title", "Society Posts");
        }

        map.addAttribute("recruitType", type);

        List<PostVo> postVoList = dptManagerService.findPostsByUserId(AuthInfo.getUserId());
        postVoList = postVoList.stream().filter(s -> s.getRecruitType() == type).collect(Collectors.toList());
        map.addAttribute("postList",postVoList);
        return "dpt-post-list";
    }

    @RequestMapping(value = "/dpt/post/{postId}/application/list", method = RequestMethod.GET)
    public String postApplicationList(@PathVariable("postId") int postId, ModelMap map) {
        map.addAttribute("post", dptManagerService.findPostById(postId));
        map.addAttribute("applicationList", dptManagerService.findInterviewPassedApplicationsByPostId(postId));
        return "dpt-post-application-list";
    }

    @RequestMapping(value = "/dpt/post/add", method = RequestMethod.GET)
    public String dptPostAddGet(ModelMap map, @RequestParam("type") int type) {
        map.addAttribute("title", "New Post");
        map.addAttribute("recruitType", type == RecruitTypeStatus.SOCIETY ? true : false);
        map.addAttribute("roleName", "Department Manager");
        map.addAttribute("action", "add");
        map.addAttribute("returnUrl", "list?type=" + type);
        map.addAttribute("swalTextSuccess", "You have successfully added a new post need!");
        map.addAttribute("swalTextFailure", "You have not successfully added a new post need.");

        map.addAttribute("post", new PostVo.Builder()
                .departmentName(dptManagerService.findDptNameByUserId(AuthInfo.getUserId()))
                .recruitNum(1)
                .build());
        return "dpt-hr-post-detail";
    }

    @RequestMapping(value = "/dpt/post/{postId}", method = RequestMethod.GET)
    public String dptPostDetail(@PathVariable("postId") int postId, ModelMap map) {
        PostVo postVo = dptManagerService.findPostById(postId);
        map.addAttribute("title", "Post Detail");
        map.addAttribute("recruitType", postVo.getRecruitType() == RecruitTypeStatus.SOCIETY ? true : false);
        map.addAttribute("roleName", "Department Manager");
        map.addAttribute("action", "edit");
        map.addAttribute("returnUrl", "list?type=" + postVo.getRecruitType());
        map.addAttribute("swalTextSuccess", "You have successfully edited this post need!");
        map.addAttribute("swalTextFailure", "You have not successfully edited this post need.");

        map.addAttribute("announcementType", postVo);
        map.addAttribute("post", postVo);
        return "dpt-hr-post-detail";
    }

    /* ***************************************************************************** */

    @RequestMapping(value = "/dpt/post/add", method = RequestMethod.POST)
    @ResponseBody
    public String dptPostAddPost(PostEntity postEntity) {
        PostVo postVo = new PostVo.Builder()
                .recruitType(postEntity.isRecruitType() ? RecruitTypeStatus.SOCIETY : RecruitTypeStatus.CAMPUS)
                .postName(postEntity.getPostName())
                .postType(postEntity.getPostType())
                .postLocale(postEntity.getPostLocale())
                .recruitNum(postEntity.getRecruitNum())
                .postDescription(postEntity.getPostDescription())
                .postRequirement(postEntity.getPostRequirement())
                .build();

        int postId = dptManagerService.createNewPost(postVo, AuthInfo.getUserId());

        if (postId > 0) {
            // Notification
            MessageDto messageDto = new MessageDto();
            messageDto.setSubject(AuthInfo.getUserName());
            messageDto.setText("created a new " + (postEntity.isRecruitType() ? "society" : "campus") + " post need: ");
            messageDto.setObject(postEntity.getPostName());
            messageDto.setLink("hr/post/" + postId);
            messageDto.setMessageType(HRMessage.DPT_CREATE_POST);
            messageDto.setCreateTime(Calendar.getInstance().getTime());
            messageDto.setChannel("hr");

            messageService.broadcastAndSave("hr", messageDto, true);
        }

        return String.valueOf(postId);
    }

    @RequestMapping(value = "/dpt/post/edit", method = RequestMethod.POST)
    @ResponseBody
    public String dptPostEditPost(PostEntity postEntity) {
        PostVo postVo = new PostVo.Builder()
                .recruitType(postEntity.isRecruitType() ? RecruitTypeStatus.SOCIETY : RecruitTypeStatus.CAMPUS)
                .postId(postEntity.getPostId())
                .postName(postEntity.getPostName())
                .postType(postEntity.getPostType())
                .postLocale(postEntity.getPostLocale())
                .postDescription(postEntity.getPostDescription())
                .postRequirement(postEntity.getPostRequirement())
                .recruitNum(postEntity.getRecruitNum())
                .publishTime(postEntity.getPublishTime())
                .status(postEntity.getStatus())
                .build();

        int postId = dptManagerService.updatePost(postVo);

        if (postId > 0) {
            // Notification
            MessageDto messageDto = new MessageDto();
            messageDto.setSubject(AuthInfo.getUserName());
            messageDto.setText("updated the " + (postEntity.isRecruitType() ? "society" : "campus") + " post need: ");
            messageDto.setObject(postEntity.getPostName());
            messageDto.setLink("hr/post/" + postId);
            messageDto.setMessageType(HRMessage.DPT_UPDATE_POST);
            messageDto.setCreateTime(Calendar.getInstance().getTime());
            messageDto.setChannel("hr");

            messageService.broadcastAndSave("hr", messageDto, true);
        }

        return String.valueOf(postId);
    }

    @RequestMapping(value = "/dpt/post/{postId}/application/pass", method = RequestMethod.POST)
    @ResponseBody
    public String dptPostApplicationPassPost(@RequestParam(value="chkArray[]") int[] chkArray,
                                             @PathVariable("postId") int postId) {
        int overAllStatus = StatusCode.SUCCESS;
        if (chkArray.length != 0) {
            for (Integer applicationId: chkArray) {
                int status = dptManagerService.dptApprovePassApplicationById(applicationId);
                if (status != StatusCode.SUCCESS) overAllStatus = status;
            }
        }
        if (overAllStatus == StatusCode.SUCCESS) {
            PostVo postVo = dptManagerService.findPostById(postId);
            // Notification
            MessageDto messageDto = new MessageDto();
            messageDto.setSubject(AuthInfo.getUserName());
            messageDto.setText("passed applicants of " + (postVo.getRecruitType() == RecruitTypeStatus.SOCIETY ? "society" : "campus") + " post : ");
            messageDto.setObject(postVo.getPostName());
            messageDto.setLink("hr/post/" + postId + "/application/list");
            messageDto.setMessageType(HRMessage.DPT_PASS_APPS);
            messageDto.setCreateTime(Calendar.getInstance().getTime());
            messageDto.setChannel("hr");

            messageService.broadcastAndSave("hr", messageDto, true);
        }
        return String.valueOf(overAllStatus);
    }

    @RequestMapping(value = "/dpt/post/{postId}/application/reject", method = RequestMethod.POST)
    @ResponseBody
    public String dptPostApplicationRejectPost(@RequestParam(value="chkArray[]") int[] chkArray,
                                               @PathVariable("postId") int postId) {
        int overAllStatus = StatusCode.SUCCESS;
        if (chkArray.length != 0) {
            for (Integer applicationId: chkArray) {
                int status = dptManagerService.dptApproveFailApplicationById(applicationId);
                if (status != StatusCode.SUCCESS) overAllStatus = status;
            }
        }
        if (overAllStatus == StatusCode.SUCCESS) {
            PostVo postVo = dptManagerService.findPostById(postId);
            // Notification
            MessageDto messageDto = new MessageDto();
            messageDto.setSubject(AuthInfo.getUserName());
            messageDto.setText("rejected applicants of " + (postVo.getRecruitType() == RecruitTypeStatus.SOCIETY ? "society" : "campus") + " post : ");
            messageDto.setObject(postVo.getPostName());
            messageDto.setLink("hr/post/" + postId + "/application/list");
            messageDto.setMessageType(HRMessage.DPT_REJECT_APPS);
            messageDto.setCreateTime(Calendar.getInstance().getTime());
            messageDto.setChannel("hr");

            messageService.broadcastAndSave("hr", messageDto, true);
        }
        return String.valueOf(overAllStatus);
    }

    @RequestMapping(value = "/dpt/post/revoke", method = RequestMethod.POST)
    @ResponseBody
    public String dptPostRevokePost(PostEntity postEntity) {
        int postId = dptManagerService.revokePost(postEntity.getPostId());

        if (postId > 0) {
            // Notification
            MessageDto messageDto = new MessageDto();
            messageDto.setSubject(AuthInfo.getUserName());
            messageDto.setText("revoked the " + (postEntity.isRecruitType() ? "society" : "campus") + " post need: ");
            messageDto.setObject(postEntity.getPostName());
            messageDto.setLink("hr/post/" + postId);
            messageDto.setMessageType(HRMessage.DPT_REVOKE_POST);
            messageDto.setCreateTime(Calendar.getInstance().getTime());
            messageDto.setChannel("hr");

            messageService.broadcastAndSave("hr", messageDto, true);
        }

        return String.valueOf(postId);
    }
}
