package com.ekt.cms.common;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.JSONUtil;
import com.ekt.cms.utils.JSONUtils;
import com.ekt.cms.utils.page.Pagination;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class BaseController {
	/**
	 * 根据前台生成的list数据，生成json格式数据传递到前台
	 * @param list：集合数据
	 * @param pagination：分页空间
	 * @param response
	 * @throws Exception
	 */
	    @Autowired
	    public HttpServletRequest request;

	    @Autowired
	    public HttpServletResponse response;

	    @Autowired
	    public HttpSession session;
	    
	    
	    public JSONUtil jsonUtil;
	    
	  
	
	
	
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
		curAccount.logout();
		
	}
}
