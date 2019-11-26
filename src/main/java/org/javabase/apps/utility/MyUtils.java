/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author raj.shah
 */
public class MyUtils {

    private final static String TEMP_UPLOAD_DIRECTORY_NAME = "TEMP_UPLOAD";
    private final static String PERMANENT_UPLOAD_DIRECTORY_NAME = "FINAL_UPLOAD";

    public enum FileType {
        Temporary, Permanent
    }

    public static boolean isNullOrEmpty(String str) {
        boolean isNullOrEmpty = false;
        if (str == null || str.equals("") || str.length() == 0) {
            isNullOrEmpty = true;
        }
        return isNullOrEmpty;
    }

    public static String getCommaSeparatedStringFromList(List<String> lst) {
        String str = "";
        if (lst != null && lst.size() > 0) {
            str = String.join(",", lst);
        }
        return str;
    }

    public static String getRootPath() {
//         String rootPath = System.getProperty("catalina.home"); // C:\Program Files\Apache Software Foundation\apache-tomcat-9.0.10
        String rootPath = System.getProperty("user.home");   //   C:\Users\raj.shah
//              String rootPath = System.getProperty("user.dir");    //   C:\Program Files\Apache Software Foundation\apache-tomcat-9.0.10\bin
        return rootPath;
    }

    public static Map<String, String> uploadFile(MultipartFile file,FileType fileType,String uploadedFileNewName) {
        Map<String,String> myMap= new HashMap();
        String newlyUploadedFileFullPath="";
        boolean isUploadSuccess=false;
        String message="";
        String newFileName= "";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String uploadDirectoryName= fileType == FileType.Temporary ? TEMP_UPLOAD_DIRECTORY_NAME : PERMANENT_UPLOAD_DIRECTORY_NAME;
                newFileName= isNullOrEmpty(uploadedFileNewName) ? file.getOriginalFilename() : uploadedFileNewName ;
                // Creating the directory to store file
                File dir = new File(getRootPath() + File.separator + uploadDirectoryName);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + newFileName);
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(bytes);
                }
                
                newlyUploadedFileFullPath=serverFile.getAbsolutePath();
                isUploadSuccess=true;
                message="File "+ uploadedFileNewName +" Uploaded Successfully";
            } catch (Exception e) {
                System.out.println("ERROR in File Upload : "+e.getMessage());
                message="File Upload Failed.";
            }
        } else {
            message="File Upload Failed because the file was empty.";
        }
        myMap.put("message", message);
        myMap.put("success", String.valueOf(isUploadSuccess));
        myMap.put("uploaded_file_full_path", newlyUploadedFileFullPath);
        myMap.put("uploaded_file_name", newFileName);
        return myMap;
    }
}
