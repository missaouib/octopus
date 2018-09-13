package com.targaryen.octopus.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

 interface FileStorageService {
     List<String> listDegreeFilenamesByApplicantId(int id);
     List<String> listPhotoFilenamesByApplicantId(int id);
     List<String> listCVFilenamesByApplicantId(int id);
     int storeCVByApplicantId(int id, MultipartFile file);
     int storePhotoByApplicantId(int id, MultipartFile file);
     int storeDegreeByApplicantId(int id, MultipartFile file);
     int deleteDegreeByApplicantId(int id, String filename);
     int deletePhotoByApplicantId(int id, String filename);
     int deleteCVByApplicantId(int id, String filename);
     Resource loadDegreeResourceByApplicantId(int id, String filename);
     Resource loadCVResourceByApplicantId(int id, String filename);
     Resource loadPhotoResourceByApplicantId(int id, String filename);
     List<Resource> loadDegreeResourcesByApplicantId(int id);
     List<Resource> loadPhotoResourcesByApplicantId(int id);
     List<Resource> loadCVResourcesByApplicantId(int id);
}
