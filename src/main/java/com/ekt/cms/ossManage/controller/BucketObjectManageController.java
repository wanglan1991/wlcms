/**   
* @Title: BucketObjectManageController.java 
* @Package com.ekt.cms.ossManage.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月23日 下午3:25:21 
* @version V1.0   
*/
package com.ekt.cms.ossManage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.ekt.cms.ossManage.entity.OssConfiguration;

/** 
* @ClassName: BucketObjectManageController 
* @Description: TODO(阿里oss object管理控制器) 
* @author wanglan
* @date 2016年8月23日 下午3:25:21 
*  
*/



@Controller
@RequestMapping("/manage")
public class BucketObjectManageController {
	
	
	@RequestMapping("/object")
	@ResponseBody
	public void getObject(HttpServletRequest request,HttpServletResponse response,@RequestParam("KeyPrifex")String KeyPrifex){	
		OSSClient ossClient = new OSSClient(OssConfiguration.ENDPOINT,
											OssConfiguration.ACCESS_KEY_ID,
											OssConfiguration.ACCESS_KEY_SECRET);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		// 列举Object
		ObjectListing objectListing = ossClient.listObjects(OssConfiguration.BUCKET_NAME, KeyPrifex);
		List<OSSObjectSummary> sums = objectListing.getObjectSummaries();				
		StringBuffer sb =new StringBuffer();
			sb.append("<table border='1' cellspacing='1' cellpadding='1' style='margin-left:26%;text-align:center;'>");			
				sb.append("</tr>"
						+ "<th>序号</th>");
				sb.append("<th>图片地址</th>");
				sb.append("<th>图片</th>"
						+ "</tr>");				
			for(int i=0;i<sums.size();i++){
				
				sb.append("<tr>"
						  + "<td>"+(i+1)+"</td>");
				  sb.append("<td>"+OssConfiguration.ENDPOINT+"/"+sums.get(i).getKey()+"</td>");
				  sb.append("<td> <img style='max-width:100px' src='http://ekt.oss-cn-shenzhen.aliyuncs.com/"+sums.get(i).getKey()+"'></td></tr>");
		}
			sb.append("</table>");
			try {
				response.getWriter().print(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		// 关闭client
		ossClient.shutdown();

		
	}

		
}
	
	


