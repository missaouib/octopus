package com.targaryen.octopus.applicationRunner;

import com.targaryen.octopus.dto.DptManagerDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.service.DptManagerService;
import com.targaryen.octopus.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private DptManagerService dptManagerService;

    @Autowired
    public MyApplicationRunner(ServiceFactory serviceFactory) {
        this.dptManagerService = serviceFactory.getDptManagerService();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*
        DptManagerDto dptManager = new DptManagerDto();
        dptManager.setDptManagerId(1);
        dptManagerService.saveDptManager(dptManager);

        PostDto post = new PostDto();
        post.setPostId(1);
        post.setPostName("Java Developer");
        post.setPostType("Engineer");
        post.setPostLocale("Tokyo");
        post.setPostDescription("Are you passionate about technology in its many forms? Are you excited by interacting with people throughout the day, in person, on the phone, via email, and chat?");
        post.setPostRequirement("Unfortunately you are not.");
        post.setStatus(1);
        post.setDptManager(dptManager);
        dptManagerService.createNewPost(post);
        */
    }
}
