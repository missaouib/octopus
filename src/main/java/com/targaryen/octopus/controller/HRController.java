package com.targaryen.octopus.controller;

import com.targaryen.octopus.entity.PostEntity;
import com.targaryen.octopus.security.AuthInfo;
import com.targaryen.octopus.service.HRService;
import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.util.Role;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.ApplicationStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/hr/post/detail/{postId}", method = RequestMethod.GET)
    public String hrPostDetail(@PathVariable("postId") int postId, ModelMap map) {
        /* UI Settings */
        map.addAttribute("title", "Check/Edit post need detail");
        map.addAttribute("action", "../edit");
        map.addAttribute("returnUrl", "../list");
        map.addAttribute("swalTextSuccess", "You have successfully edited this post need!");
        map.addAttribute("swalTextFailure", "You have not successfully edited this post need.");

        map.addAttribute("post", hrService.findPostById(postId));
        return "dpt-hr-post-detail";
    }

    @RequestMapping(value = "/hr/post/publish/{postId}", method = RequestMethod.POST)
    public String hrPostPublish(@PathVariable("postId") int postId) {
        int status = hrService.publishPostById(postId);
        return "redirect:../list";
    }

    @RequestMapping(value = "/hr/post/close/{postId}", method = RequestMethod.POST)
    public String hrPostClose(@PathVariable("postId") int postId) {
        int status = hrService.closePostById(postId);
        return "redirect:../list";
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

    /* Post-Applicant */
    @RequestMapping(value = "/hr/post/application/{postId}", method = RequestMethod.GET)
    public String hrPostApplication(@PathVariable("postId") int postId, ModelMap map) {
        map.addAttribute("post", hrService.findPostById(postId));
        map.addAttribute("applicationList", hrService.findApplicationsByPostId(postId));
        return "hr-post-application-list";
    }
}
