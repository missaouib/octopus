package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactoryImpl;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhouy on 2018/9/18.
 */
@Controller
@RequestMapping(value = "/octopus")
public class PdfFileController {

    private final ServiceFactoryImpl serviceFactory;

    @Autowired
    public PdfFileController(ServiceFactoryImpl serviceFactory){
        this.serviceFactory = serviceFactory;
    }

    @RequestMapping(value = "/applicant/pdf")
    public String pdf(){
        return "applicant-pdf";
    }

    @RequestMapping(value="/applicant/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("[msg]: " + "MultipartFile");
        int ret = serviceFactory.getFileStorageService().storeCVByApplicantId(1, file);
        return "OK";
    }

}
