package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.AnnouncementVo;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class PublicController {

    private final ServiceFactoryImpl serviceFactory;

    @Autowired
    public PublicController(ServiceFactoryImpl serviceFactory){
        this.serviceFactory = serviceFactory;
    }

    @RequestMapping(value = "/")
    public ModelAndView index(){
        ModelAndView result = new ModelAndView("default");
        List<PostVo> posts = serviceFactory.getPublicService().listPostsByStatus(PostStatus.PUBLISHED);
        /*for(PostVo tmp : posts){
            System.out.println("[msg]: " + tmp.getPostName());
        }*/
        result.addObject("posts", posts);

        List<AnnouncementVo> announcementVos = serviceFactory.getAnnouncementService().listPublicAnnouncement();
        result.addObject("billboardList", announcementVos);

        return result;
    }

    @RequestMapping(value = "/postDetail/{postId}")
    public ModelAndView postDetail(@PathVariable("postId") String postId){
        ModelAndView result = new ModelAndView("pub-post-detail");
        PostVo getPost = serviceFactory.getPublicService().findPostById(Integer.parseInt(postId));
        if(getPost != null){

            result.getModel().put("post", getPost);
        }
        return result;
    }


    /************************ start of biilboard *********************************/
    @RequestMapping(value="/pub/billboard/list", method = RequestMethod.GET)
    public ModelAndView pubBillboardList(Model map){
        List<AnnouncementVo> announcementVos = serviceFactory.getAnnouncementService().listPublicAnnouncement();
        map.addAttribute("billboardList", announcementVos);
        ModelAndView result = new ModelAndView("pub-billboard-list");
        return result;
    }

    @RequestMapping(value="/pub/billboard/{announcementId}", method = RequestMethod.GET)
    public ModelAndView pubBillboardfindById(@PathVariable("announcementId") String announcementId, Model map){
        AnnouncementVo announcementVo = serviceFactory.getAnnouncementService().findAnnouncementById(Integer.parseInt(announcementId));
        map.addAttribute("announcement", announcementVo);
        ModelAndView result = new ModelAndView("pub-billboard-detail");
        return result;
    }

    /*********************** end of biilboard ********************************/
}
