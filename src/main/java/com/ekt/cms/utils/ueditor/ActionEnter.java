package com.ekt.cms.utils.ueditor;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;
import com.ekt.cms.common.controller.OssUploadSample;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.ueditor.define.Config;
import com.google.gson.Gson;

public class ActionEnter {

	private HttpServletRequest request = null;

	private String rootPath = null;
	private String contextPath = null;

	private String actionType = null;

	private ConfigManager configManager = null;

	public ActionEnter(HttpServletRequest request, String rootPath) {

		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter("action");
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());

	}

	public String exec() {

		String callbackName = this.request.getParameter("callback");
		String path = request.getSession().getServletContext().getRealPath("/");// 获取当前容器跟目录

		if (callbackName != null) {

			if (!validCallbackName(callbackName)) {
				return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
			}

			return callbackName + "(" + this.invoke(path) + ");";

		} else {
			return this.invoke(path);
		}

	}

	public String invoke(String path) {

		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}

		if (this.configManager == null || !this.configManager.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		State state = null;

		int actionCode = ActionMap.getType(this.actionType);

		Map<String, Object> conf = null;

		switch (actionCode) {

		case ActionMap.CONFIG:
			return this.configManager.getAllConfig().toString();

		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = this.configManager.getConfig(actionCode);
			state = new Uploader(request, conf).doExec();
			break;

		case ActionMap.CATCH_IMAGE:
			conf = configManager.getConfig(actionCode);
			String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;

		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = configManager.getConfig(actionCode);
			int start = this.getStartIndex();
			state = new FileManager(conf).listFile(start);
			break;

		}

		String a = state.toJSONString().replace("/WEB-INF", "");//获取UED上传文件路径已经相关配置
		Config config = new Gson().fromJson(a, Config.class);//转成配置对象
		String url = path.substring(0, path.length() - 1) + config.getUrl();//文件完整路径
		String realKey = "images/" + config.getTitle();//上传所需的 文件夹路径
		File file = new File(url);//h
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
			if (file.exists()) {
				OSSClient client = new OSSClient(Constants.DEFAULT_OSS_ENDPOINT, Constants.DEFAULT_OSS_ACCESS_KEY_ID,
						Constants.DEFAULT_OSS_ACCESS_KEY_SECRET);
				client.putObject(new PutObjectRequest(OssUploadSample.bucketName, realKey, fs));
				config.setUrl(config.getTitle());
			}
			file.delete();//执行删除操作
			fs.close();//关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject(config).toString();

	}

	public int getStartIndex() {

		String start = this.request.getParameter("start");

		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * callback参数验证
	 */
	public boolean validCallbackName(String name) {

		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}

		return false;

	}

}