package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "")
    public ModelAndView index(){
        ModelAndView result = new ModelAndView("default");
        List<PostVo> posts = serviceFactory.getPulicService().listPostsByStatus(PostStatus.PUBLISHED);
        /*for(PostVo tmp : posts){
            System.out.println("[msg]: " + tmp.getPostName());
        }*/
        result.addObject("posts", posts);
        return result;
    }

    @RequestMapping(value = "/postDetail/{postId}")
    public ModelAndView postDetail(@PathVariable("postId") String postId){
        ModelAndView result = new ModelAndView("pub-post-detail");
        PostVo getPost = serviceFactory.getPulicService().findPostById(Integer.parseInt(postId));
        if(getPost != null){

            result.getModel().put("post", getPost);
            System.out.println("[msg]: " + getPost.getRecruitDpt() + ", " + getPost.getPublishTime());
        }
        return result;
    }

}
