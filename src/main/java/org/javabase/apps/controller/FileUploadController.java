package org.javabase.apps.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import org.javabase.apps.utility.MyUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application file upload requests
 * 
 * https://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files
 * 
 * https://www.baeldung.com/spring-file-upload
 */
@Controller
@RequestMapping(value = "dashboard/uploadfilepage",method = {RequestMethod.POST, RequestMethod.GET})
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public String getFileUploadPage() {
        return "uploadfile";
    }
    
    @RequestMapping(value = "uploadFile",method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        String response="";
        boolean success=false;
        String message="";
        String uploadedFileFullPath="";
        Map<String,String> uploadMap=MyUtils.uploadFile(file, MyUtils.FileType.Temporary, name);
        if(uploadMap != null && uploadMap.size() >0){
            success=Boolean.parseBoolean(uploadMap.get("success"));
            message=uploadMap.get("message");
            uploadedFileFullPath=uploadMap.get("uploaded_file_full_path");
        }
        return message;
    }

    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "uploadMultipleFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadMultipleFileHandler(@RequestParam("name") String[] names,@RequestParam("file") MultipartFile[] files) {

        if (files.length != names.length) {
            return "Mandatory information missing";
        }

        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                message = message + "You successfully uploaded file=" + name
                        + "<br />";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return message;
    }
}
