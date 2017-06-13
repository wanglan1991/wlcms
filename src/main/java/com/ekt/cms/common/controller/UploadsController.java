package com.ekt.cms.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.ueditor.ActionEnter;



/**
 * 
 * @author Administrator
 *
 */


@Controller
@RequestMapping("/uploads")
public class UploadsController {

	private String BUCKETNAME ="images";
	
    @RequestMapping(value="/img")
    public void config(HttpServletRequest request, HttpServletResponse response) {
    	OSSClient client = null;
    	 try {
//        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
        
        
		        client = new OSSClient(Constants.DEFAULT_OSS_ENDPOINT, 
						   Constants.DEFAULT_OSS_ACCESS_KEY_ID, 
						   Constants.DEFAULT_OSS_ACCESS_KEY_SECRET);
		    
//		        PutObjectResult putObjectResult = client.putObject(new PutObjectRequest(BUCKETNAME, realKey, file.getInputStream()));
       
            String exec =new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
    @RequestMapping  
    public String index(){  
        System.out.println(1);  
        return "/ueditor/index";  
    }  
      
    @RequestMapping(value = "/upload", method = RequestMethod.GET)    
    @ResponseBody    
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {    
        String action = request.getParameter("action");    
        if ("config".equals(action)) {    
            OutputStream os = response.getOutputStream();    
//            IOUtils.copy(UeditorController.class.getClassLoader().getResourceAsStream("config.json"), os);    
        }  
    }  
      
    @RequestMapping(value = "/upload", method = RequestMethod.POST)    
    @ResponseBody    
    public Map<String, String> upload(HttpServletRequest request,@RequestParam CommonsMultipartFile upfile) throws IOException {    
//        Map<String, String> result = Maps.newHashMap();  
//        System.out.println(upfile.getFileItem().getFieldName());  
//        String path = getFilePath(upfile);  
//        File file = new File(path);  
//        System.out.println(path);  
//        String state = "SUCCESS";  
//        //返回类型    
//        String rootPath = request.getContextPath();  
//        result.put("url", rootPath + "/ueditor/show?filePath=" + path);  
//        result.put("size", String.valueOf(file.length()));    
//        result.put("type", file.getName().substring(file.getName().lastIndexOf(".")));    
//        result.put("state", state);    
//        return result; 
    	return null;
    }  
      
    @RequestMapping(value = "/show", method = RequestMethod.GET)  
    public void show(String filePath, HttpServletResponse response) throws IOException {  
          
        File file = getFile(filePath);  
  
        response.setDateHeader("Expires", System.currentTimeMillis() + 1000 * 60 * 60 * 24);  
        response.setHeader("Cache-Control", "max-age=60");  
        OutputStream os = response.getOutputStream();  
  
        FileInputStream is = null;  
        try {  
            is = new FileInputStream(file);  
            IOUtils.copy(is, os);  
        } catch (FileNotFoundException e) {  
            response.setStatus(404);  
            return;  
        } finally {  
            if (null != is) {  
                is.close();  
            }  
            if (null != os) {  
                os.flush();  
                os.close();  
            }  
        }  
    }  
      
    protected String getFilePath(CommonsMultipartFile uploadFile){  
        String absolutePath = "D:/upload";  
        File folder = new File(absolutePath);  
        if (!folder.exists()) {  
            folder.mkdirs();  
        }  
        String rawName = uploadFile.getFileItem().getName();  
        String fileExt = rawName.substring(rawName.lastIndexOf("."));  
        String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;  
        File saveFile = new File(absolutePath + File.separator + newName);  
        try {  
            uploadFile.getFileItem().write(saveFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
        return absolutePath + "/" + newName;  
    }  
      
    protected File getFile(String path){  
        File file = new File(path);  
        return file;  
          
    }  
}
