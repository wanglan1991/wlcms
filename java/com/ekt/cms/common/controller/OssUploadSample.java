package com.ekt.cms.common.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.Constants;

/**
 * This sample demonstrates how to upload/download an object to/from 
 * Aliyun OSS using the OSS SDK for Java.
 */
@Controller
@RequestMapping("/upload")
public class OssUploadSample {
    
    private static String endpoint = Constants.DEFAULT_OSS_ENDPOINT;
    private static String accessKeyId = Constants.DEFAULT_OSS_ACCESS_KEY_ID;
    private static String accessKeySecret = Constants.DEFAULT_OSS_ACCESS_KEY_SECRET;
    private static String bucketName = "ekt";
  //参数key是指ObjectName。例如文件名为123.jpg，那么ObjectName设置为123.jpg即可   如果123.jgp要放在abc下显示，key即ObjectName参数设置为：abc/123.jpg 即可
//    private static String key = "textBook/";     
    @RequestMapping("/imageUpload")
    @ResponseBody
    public  Result uploadImage(HttpServletRequest request,String key,String fileName) throws IOException {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
    	Result result=Result.getResults();
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
        Map<String, Object> data = new HashMap<String, Object>();
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile  file=mulRequest.getFile(fileName);
        
        try {
            
            /**
             * Note that there are two ways of uploading an object to your bucket, the one 
             * by specifying an input stream as content source, the other by specifying a file.
             */
            
            /*	
             * Upload an object to your bucket from an input stream
             */
        	 String realKey = key + file.getOriginalFilename();
        	PutObjectResult putObjectResult= client.putObject(new PutObjectRequest(bucketName, realKey, file.getInputStream()));
        	
        	if(putObjectResult !=null){
        	}

            
            /*
             * Download an object from your bucket
             */
//            System.out.println("Downloading an object------下载文件");
//            OSSObject object = client.getObject(new GetObjectRequest(bucketName, realKey));
//            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
//           System.out.println(object.getObjectContent());
//            displayTextInputStream(object.getObjectContent());
        	data.put("status", 0);
        	data.put("msg", "上传成功");
            result.setValue(data);
        } catch (OSSException oe) {
            data.put("status", 1);
        	data.put("msg", "访问对象存储服务出异常");
        	result.setValue(data);
        } catch (ClientException ce) {
            data.put("status", 1);
        	data.put("msg", "访问阿里云出异常");
        	result.setValue(data);
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
        return result;
    }
    
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("\t" + line);
        }
        System.out.println();

        reader.close();
    }
    
}