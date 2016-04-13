package com.ekt.cms.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.JSONUtils;
import com.ekt.cms.utils.page.Pagination;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.net.httpserver.HttpServer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseController<T>  {
	/**
	 * 根据前台生成的list数据，生成json格式数据传递到前台
	 * @param list：集合数据
	 * @param pagination：分页空间
	 * @param response
	 * @throws Exception
	 */
	    @Autowired
	    protected HttpServletRequest request;

	    @Autowired
	    protected HttpServletResponse response;

	    @Autowired
	    protected HttpSession session;
	    
	
	
	public void printStr(List<T> list, Pagination pagination, HttpServletResponse response,String sEcho) throws Exception{
		JSONArray jsonArray = JSONArray.fromObject(list);
		JSONObject json = new JSONObject();
		json.put("sEcho", sEcho);
		json.put("recordsTotal", pagination.getTotal());
		json.put("recordsFiltered", pagination.getTotal());
		if(list.size() >0 ){
			json.put("data", jsonArray);
		}else{
			json.put("data", "[]");
		}
		String jsonStr = json.toString();
		System.out.println("jsonStr--> " + jsonStr);
		JSONUtils.printStr(jsonStr, response);
	}
	
	/**
	 * list转成json字符串
	 * @param list
	 * @param response
	 * @throws Exception
	 */
	public void printStr(List<T> list, HttpServletResponse response) throws Exception{
		JSONArray jsonArray = JSONArray.fromObject(list);
		String jsonStr = jsonArray.toString();
		JSONUtils.printStr(jsonStr, response);
	}
	/**
	 * 获取当前session用户
	 * @return
	 */
	public CmsAccount getCurrentAccount(){
		Subject curAccount=SecurityUtils.getSubject();
		Session session=curAccount.getSession();
		return (CmsAccount)session.getAttribute(Constants.DEFAULT_SESSION_ACCOUNT);
	}
	/**
	 * 销毁session
	 */
	public void destroySession(){
		Subject curAccount=SecurityUtils.getSubject();
		curAccount.getSession().removeAttribute(Constants.DEFAULT_SESSION_ACCOUNT);
	}
	
	
}
