package com.targaryen.octopus.applicationRunner;

import com.targaryen.octopus.dto.ApplicantDto;
import com.targaryen.octopus.dto.DptManagerDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.service.ApplicantService;
import com.targaryen.octopus.service.DptManagerService;
import com.targaryen.octopus.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
