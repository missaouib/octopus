package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactoryImpl;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        List<PostVo> posts = serviceFactory.getPulicService().listPostsByStatus(PostStatus.POSTED);
        for(PostVo tmp : posts){
            System.out.println("[msg]: " + tmp.getPostName());
        }
        result.addObject("posts", posts);
        return result;
    }

}
