package com.ekt.cms.promotion.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.CMSConstants;
import com.ekt.cms.utils.TextUtil;
import com.ekt.cms.utils.mail.SendmailUtil;
import com.ekt.cms.utils.taobao.api.ApiException;
import com.ekt.cms.utils.taobao.api.DefaultTaobaoClient;
import com.ekt.cms.utils.taobao.api.TaobaoClient;
import com.ekt.cms.utils.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.ekt.cms.utils.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 推广工具类
 * 
 * @Title: PromotionToolsController.java
 * @Package com.ekt.cms.promotion.controller
 * @Description: TODO(推广工具控制器)
 * @author wanglan
 * @date 2017年4月1日 下午4:09:20
 * @version V1.0
 */

@Controller
@RequestMapping("/promotion")
public class PromotionToolsController {

	// http://api.moming.me 莫名股份管理平台地址
	private final int USER_ID = 2065;// 莫名短信用户id
	private final String ACCOUNT = "15386055290";// 莫名短信平台账号
	private final String PASSWORD = "yunzhong605";// 莫名短信平台密码
	private static HttpClient httpClient;
	private static SendmailUtil sendmailUtil;// 邮件发送工具

	private static SendmailUtil getMailUtil() {
		if (sendmailUtil == null) {
			return sendmailUtil = new SendmailUtil();
		}
		return sendmailUtil;
	}

	private static HttpClient getHttpClient() {
		if (httpClient == null) {
			return httpClient = new HttpClient();
		}
		return httpClient;
	}

	/**
	 * 返回权限首页
	 * 
	 * @return
	 */
	@RequestMapping("/manage")
	public String permission() {
		return "main/promotion/promotionManage";
	}

	@RequestMapping(value = "/sendMessage")
	@ResponseBody
	public Result promotionAction(HttpServletRequest request, String messageContent) {
		Result result = Result.getResults();
		// 方便地得到文件名和文件内容
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("fileData");
		// 获取原始文件名
		String sourceName = file.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		String phoneNumberArr = "";
		try {
			boolean flag = false;
			InputStream input = file.getInputStream();
			byte[] b = new byte[1048576];
			int length = input.read(b);
			path += "\\" + sourceName;
			// 文件流写到服务器端
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(b, 0, length);
			input.close();
			outputStream.close();
			String msg = "";
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(0);
			// 定义 row、cell
			XSSFRow row;
			String cell;
			if (sheet.getPhysicalNumberOfRows() < 3) {
				return new Result(-1, "读取文件失败 原因（不正确的模板规范！）");
			}
			for (int i = sheet.getFirstRowNum() + 2; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					if (j == 1) {
						DecimalFormat format = new DecimalFormat("#");
						String phone = format.format(row.getCell(j).getNumericCellValue());
						if (phone.replace(" ", "").equals("") || !TextUtil.isPhoneNum(phone)) {
							flag = true;
							msg += "序号为【" + (i - 2) + "】 的"
									+ (phone.replace(" ", "").equals("") ? "号码为空！" : "非手机号码格式！\n");
							break;
						} else {
							System.out.println(phone);
							phoneNumberArr += phone + ",";
						}
					}
				}
				if (flag) {
					continue;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return new Result(-1, "【E】上传文件异常，请仔细检查文档是否按照规范录入，如依然还存在该问题 。请与管理员联系！\n");
		}
		int phoneNumberArrLength = phoneNumberArr.split(",").length;
		if (phoneNumberArrLength > 0) {
			sendMessage(phoneNumberArr, messageContent);// 发送短信
		}

		return new Result(1, "您已经成功发送了" + phoneNumberArrLength + "条短信！");
	}

	public void sendMessage(String phoneNumArr, String MessageContent) {
		String momingMsmInteface = "http://api.moming.me/sms.aspx?action=send" + "&userid=" + USER_ID + "&account="
				+ ACCOUNT + "&password=" + PASSWORD + "&mobile=" + phoneNumArr + "&content=" + MessageContent
				+ "&sendTime=&extno=";

		HttpClient client = getHttpClient();
		HttpMethod method = new GetMethod(momingMsmInteface);
		try {
			client.executeMethod(method);
			if (method.getStatusLine().getStatusCode() == 200) {
				System.out.println("短信 发送成功了！");
			} else {
				System.out.println("短信 发送失败了！");
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			// 释放链接
			method.releaseConnection();
		}

	}

	// @RequestMapping(value = "/toWanglan")
	// @ResponseBody
	// public Result sendTowanglan(String messageContent) {
	// return Result.getResults(this.sendMessageByTempleCode("18684551056",
	// CMSConstants.MESSAGE_TEMPLET_CODE));
	//
	// }

	@RequestMapping(value = "/sendMaill")
	@ResponseBody
	public void sendMaill(@RequestParam("title") String title, @RequestParam("content") String content,
			String[] mallAddress) {
		if (mallAddress.length > 0) {
			SendmailUtil su = getMailUtil();
			try {
				for (String address : mallAddress) {
					su.doSendHtmlEmail(title, content, address);
				}
			} catch (Exception e) {
				System.out.println("---------------------------邮件发送失败！");
			}

		}

		
	}

}
