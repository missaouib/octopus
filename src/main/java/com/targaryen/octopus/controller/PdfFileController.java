package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.ServiceFactoryImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


/**
 * Created by zhouy on 2018/9/18.
 */
@Controller
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

    @RequestMapping(value="/applicant/uploadFile/{applicantId}", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@PathVariable("applicantId") String applicantId,  @RequestParam("file") MultipartFile file) {
        int ret = serviceFactory.getFileStorageService().storeCVByApplicantId(Integer.parseInt(applicantId), file);

        //File file1 = new File(System.getProperty("user.dir") + "/src/main/resources/static/pdf/files/file2.pdf");
        Resource resource = serviceFactory.getFileStorageService().loadCVResourceByApplicantId(Integer.parseInt(applicantId), file.getOriginalFilename());
        String path_2 = System.getProperty("user.dir");
        File localFile = new File(path_2 + "/src/main/resources/static/pdf/files/" + file.getOriginalFilename());
        //File localFile = new File(path + "static/pdf/files/" + fileName);

        try {
            FileUtils.copyInputStreamToFile(resource.getInputStream(), localFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @RequestMapping(value="/applicant/uploadPhoto/{applicantId}", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPhoto(@PathVariable("applicantId") String applicantId,  @RequestParam("file") MultipartFile file) {
        int ret = serviceFactory.getFileStorageService().storePhotoByApplicantId(Integer.parseInt(applicantId), file);

        //File file1 = new File(System.getProperty("user.dir") + "/src/main/resources/static/pdf/files/file2.pdf");
        Resource resource = serviceFactory.getFileStorageService().loadPhotoResourceByApplicantId(Integer.parseInt(applicantId), file.getOriginalFilename());
        String path_2 = System.getProperty("user.dir");
        File localFile = new File(path_2 + "/src/main/resources/static/pdf/files/" + file.getOriginalFilename());
        //File localFile = new File(path + "static/pdf/files/" + fileName);

        try {
            FileUtils.copyInputStreamToFile(resource.getInputStream(), localFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @RequestMapping(value = "/applicant/resume/pdf/{applicantId}")
    public String resumePdf(@PathVariable("applicantId") String applicantId, Model map){

        int id = Integer.parseInt(applicantId);
        List<String> cvs = serviceFactory.getFileStorageService().listCVFilenamesByApplicantId(id);
        if(cvs.size()>0){

            String path = null;
            try {
                path = ResourceUtils.getURL("classpath:").getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String path_2 = System.getProperty("user.dir");
            Resource resource = serviceFactory.getFileStorageService().loadCVResourceByApplicantId(id, cvs.get(0));
            System.out.println("[msg]: " + id + ", " + cvs.size() +", " + path_2 + ", " + cvs.get(0));
            //String fileName = path + "static/pdf/files/" + cvs.get(0);
            String fileName =  cvs.get(0);

            File localFile = new File(path_2 + "/src/main/resources/static/pdf/files/" + fileName);
            //File localFile = new File(path + "static/pdf/files/" + fileName);
            try {
                FileUtils.copyInputStreamToFile(resource.getInputStream(), localFile);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            map.addAttribute("fileName", fileName);
        }else{
            map.addAttribute("istrue", true);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "applicant-resume-pdf";
    }

    @RequestMapping(value = "/applicant/resume/photo")
    public String resumePhoto(){
        return "applicant-resume-photo";
    }

}
