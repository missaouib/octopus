package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactoryImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;



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
        int ret = serviceFactory.getFileStorageService().storeCVByApplicantId(1, file);

        Resource resource = serviceFactory.getFileStorageService().loadCVResourceByApplicantId(1, file.getOriginalFilename());
        /*try {
            System.out.println("[msg]: " + resource.getFile().getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        File file1 = new File("C:/test/file1.pdf");

        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        try {
            FileCopyUtils.copy(resource.getFile(),
                    new File("C://file2.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "OK";
    }

}
