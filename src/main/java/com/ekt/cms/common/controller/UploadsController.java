package com.ekt.cms.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.ekt.cms.utils.ueditor.ActionEnter;



/**
 * 
 * @author Administrator
 *
 */


@Controller
@RequestMapping("/uploads")
public class UploadsController {

	
    @RequestMapping(value="/img")
    public void config(HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");// 允许任意域名发起的跨域请求
		response.setHeader("Access-Control-Allow-Methods", "*");
//		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,x_requested_with");
		response.setHeader("P3P", "CP=CAO PSA OUR");
    	 try {
//       response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
        
            String exec =new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("出现了个一个小异常！");
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
