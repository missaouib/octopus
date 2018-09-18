package com.targaryen.octopus.service;

import com.targaryen.octopus.config.FtpProperties;
import com.targaryen.octopus.util.StatusCode;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FileStorageServiceFtpImpl implements FileStorageService {
    private FTPClient ftpClient;
    private String FTP_ADDRESS;
    private String LOGIN_NAME;
    private String PASSWORD;
    private String FTP_ROOT;
    private String CV = "CV";
    private String PHOTO = "PHOTO";
    private String DEGREE = "DEGREE";

    private final Path rootLocation;

    @Autowired
    public FileStorageServiceFtpImpl(FtpProperties ftpProperties) {
        this.ftpClient = new FTPClient();
        this.FTP_ADDRESS = ftpProperties.getFTP_ADDRESS();
        this.LOGIN_NAME = ftpProperties.getLOGIN_NAME();
        this.PASSWORD = ftpProperties.getPASSWORD();
        this.FTP_ROOT = ftpProperties.getFTP_ROOT();
        this.rootLocation = Paths.get(FTP_ROOT);
    }

    private boolean ftpLogin() {
        try {
            ftpClient.connect(FTP_ADDRESS);
            ftpClient.login(LOGIN_NAME, PASSWORD);
        } catch (IOException e) {
            System.out.println("ftp login failed.");
            return false;
        }

        System.out.println("ftp login success.");
        return true;
    }

    private boolean ftpLogout() {
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            System.out.println("ftp logout failed.");
            return false;
        }

        System.out.println("ftp logout success.");
        return true;
    }

    private void init() {
        if(!ftpLogin())
            return;
        try {
            ftpClient.enterLocalPassiveMode();
            System.out.println(ftpClient.printWorkingDirectory());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        ftpLogout();
    }

    private List<String> listFilenames(Path dir) {
        List<String> filenames;

        if(!ftpLogin())
            return new ArrayList<>();

        try {
            ftpClient.enterLocalPassiveMode();
            if(!ftpClient.changeWorkingDirectory(FilenameUtils.separatorsToUnix(dir.toString())))
                return new ArrayList<>();
            filenames = Arrays.asList(ftpClient.listNames());

        } catch (IOException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }

        ftpLogout();
        return filenames;
    }

    public List<String> listDegreeFilenamesByApplicantId(int id) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.DEGREE);
        return listFilenames(path);
    }

    public List<String> listPhotoFilenamesByApplicantId(int id) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.PHOTO);
        return listFilenames(path);
    }

    public List<String> listCVFilenamesByApplicantId(int id) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.CV);
        return listFilenames(path);
    }

    private int store(Path storePath, MultipartFile file, boolean single) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        InputStream is;
        List<String> prestored = listFilenames(storePath);

        if(!ftpLogin())
            return StatusCode.FAILURE;

        try {
            ftpClient.enterLocalPassiveMode();

            if(!ftpClient.changeWorkingDirectory(FilenameUtils.separatorsToUnix(storePath.toString()))) {
                ftpClient.makeDirectory(FilenameUtils.separatorsToUnix(storePath.toString()));
                ftpClient.changeWorkingDirectory(FilenameUtils.separatorsToUnix(storePath.toString()));
            }

            if(single) {
                for(String s: prestored) {
                    ftpClient.deleteFile(s);
                }
            }

            is = file.getInputStream();
            ftpClient.storeFile(filename, is);

        } catch (IOException e) {
            System.out.println(e.toString());
            return StatusCode.FAILURE;
        }

        ftpLogout();
        return StatusCode.SUCCESS;
    }

    public int storeCVByApplicantId(int id, MultipartFile file) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.CV);
        return store(path, file, true);
    }

    public int storePhotoByApplicantId(int id, MultipartFile file) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.PHOTO);
        return store(path, file, true);
    }

    public int storeDegreeByApplicantId(int id, MultipartFile file) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.DEGREE);
        return store(path, file, true);
    }

    private int delete(Path filePath) {
        if(!ftpLogin())
            return StatusCode.FAILURE;

        try {
            ftpClient.enterLocalPassiveMode();
            if(!ftpClient.deleteFile(FilenameUtils.separatorsToUnix(filePath.toString())))
                return StatusCode.FAILURE;
        } catch (IOException e) {
            System.out.println(e.toString());
            return StatusCode.FAILURE;
        }

        ftpLogout();
        return StatusCode.SUCCESS;
    }
    
    public int deleteDegreeByApplicantId(int id, String filename) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.DEGREE).resolve(filename);
        return delete(path);
    }

    public int deletePhotoByApplicantId(int id, String filename) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.PHOTO).resolve(filename);
        return delete(path);
    }

    public int deleteCVByApplicantId(int id, String filename) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.CV).resolve(filename);
        return delete(path);
    }

    private Resource loadResource(Path filePath) {
        Resource resource;
        InputStream is;

        if(!ftpLogin())
            return null;

        try {
            ftpClient.enterLocalPassiveMode();
            is = ftpClient.retrieveFileStream(FilenameUtils.separatorsToUnix(filePath.toString()));
            resource = new InputStreamResource(is);
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }

        ftpLogout();
        return resource;
    }

    public Resource loadDegreeResourceByApplicantId(int id, String filename) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.DEGREE).resolve(filename);
        return loadResource(path);
    }

    public Resource loadCVResourceByApplicantId(int id, String filename) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.CV).resolve(filename);
        return loadResource(path);
    }

    public Resource loadPhotoResourceByApplicantId(int id, String filename) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.PHOTO).resolve(filename);
        return loadResource(path);
    }

    public List<Resource> loadDegreeResourcesByApplicantId(int id) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.DEGREE);
        List<String> filenames = listFilenames(path);
        List<Resource> resources = new ArrayList<>();

        for(String s: filenames) {
            Resource resource = loadDegreeResourceByApplicantId(id, s);
            if(resource != null)
                resources.add(resource);
        }
        return resources;
    }

    public List<Resource> loadPhotoResourcesByApplicantId(int id) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.PHOTO);
        List<String> filenames = listFilenames(path);
        List<Resource> resources = new ArrayList<>();

        for(String s: filenames) {
            Resource resource = loadPhotoResourceByApplicantId(id, s);
            if(resource != null)
                resources.add(resource);
        }
        return resources;
    }

    public List<Resource> loadCVResourcesByApplicantId(int id) {
        Path path = rootLocation.resolve(Integer.toString(id)).resolve(this.CV);
        List<String> filenames = listFilenames(path);
        List<Resource> resources = new ArrayList<>();

        for(String s: filenames) {
            Resource resource = loadCVResourceByApplicantId(id, s);
            if(resource != null)
                resources.add(resource);
        }
        return resources;
    }
}
