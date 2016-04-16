package com.ekt.cms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class ApplicationListener extends ContextLoaderListener
		implements ServletContextListener, ServletRequestListener {

	private ServletContext context;
	private WebApplicationContext webApplicationContext;

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	/**
	 * 服务器启动时加载,初始化一些信息
	 */
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent sce) {
		this.context = sce.getServletContext();
//		this.webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//		webApplicationContext.getBean("beanName");

		try {
			//这里可以读取一些配置文件的信息，或者初始化一些数据
			System.out.println("启动监听，初始化一些数据");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.gc();
		}

	}

	public void requestInitialized(ServletRequestEvent sre) {
		String appContext = sre.getServletContext().getContextPath();
		String url = sre.getServletRequest().getScheme() + "://" + sre.getServletRequest().getServerName() + ":" + sre.getServletRequest().getServerPort()
				+ appContext;
		String basePath = System.getProperty("web.root");
		// 设置webroot路径
		if (basePath == null) {
			System.setProperty("web.root", url);
		}
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		String appContext = sre.getServletContext().getContextPath();
		String url = sre.getServletRequest().getScheme() + "://" + sre.getServletRequest().getServerName() + ":" + sre.getServletRequest().getServerPort()
				+ appContext;
		String basePath = System.getProperty("web.root");
		// 设置webroot路径
		if (basePath == null) {
			System.setProperty("web.root", url);
		}
	}
}
