package com.ekt.cms.utils.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 鎵╁睍FreeMarker锛屾敮鎸侀潤鎬佸寲
 * @author zhoujun
 *
 */
public class StaticSupportFreeMarkerView extends FreeMarkerView {

	
	@Override
	protected void doRender(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Expose model to JSP tags (as request attributes).
		exposeModelAsRequestAttributes(model, request);
		// Expose all standard FreeMarker hash models.
		SimpleHash fmModel = buildTemplateModel(model, request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl()
					+ "] in FreeMarkerView '" + getBeanName() + "'");
		}
		// Grab the locale-specific version of the template.
		Locale locale = RequestContextUtils.getLocale(request);
		
		/*
		 * 濡傛灉attribute涓湁key涓衡�渟taticSupportInfo鈥濈殑StaticSupportInfo瀵硅薄灏辫〃绀鸿鐢熸垚闈欐�侀〉
		 */
		StaticSupportInfo staticSupportInfo = (StaticSupportInfo)request.getAttribute("staticSupportInfo");
		if (null == staticSupportInfo) {
			processTemplate(getTemplate(locale), fmModel, response);
		} else {
			try {
				createHTML(getTemplate(locale), fmModel, request, response);
			} catch (Exception e) {
				staticSupportInfo.staticHtmlFail();
			}
		}
	}

	
	/**
	 * 鐢熸垚闈欐�侀〉
	 * @param template
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws TemplateException
	 * @throws ServletException
	 */
	public void createHTML(Template template, SimpleHash model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException, ServletException {
		Writer writer = response.getWriter();
		//鑾峰彇閰嶇疆鏂囦欢涓殑闈欐�侀〉瀛樻斁璺緞
		String basePath = "E:\\myWorkspaces\\staticHtml\\";
		StaticSupportInfo staticSupportInfo = (StaticSupportInfo)request.getAttribute("staticSupportInfo");
		
		if(staticSupportInfo == null || staticSupportInfo.getTargetHtml() == null) {
			writer.write("fail");
			staticSupportInfo.staticHtmlFail();
			return;
		}
		
		//闈欐�侀〉闈㈢粷瀵硅矾寰�
		String fullHtmlName = basePath + staticSupportInfo.getTargetHtml();

		File htmlFile = new File(fullHtmlName);
		if (!htmlFile.getParentFile().exists()) {
			htmlFile.getParentFile().mkdirs();
		}
		if (!htmlFile.exists()) {
			htmlFile.createNewFile();
		}
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlFile), "UTF-8"));
		
		//澶勭悊妯＄増  
		template.process(model, out);
		out.flush();
		out.close();
		writer.write("success");
		staticSupportInfo.staticHtmlSuccess();
	}
}

