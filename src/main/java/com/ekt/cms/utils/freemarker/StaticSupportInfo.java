package com.ekt.cms.utils.freemarker;

/**
 * 封装静态化需要的属性，如果需要得知静态化处理结果，需要实现StatusCallBack回调接口
 * @author mili
 *
 */
public class StaticSupportInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 295085193429020250L;
	private String targetHtml;

	private StatusCallBack statusCallBack;
	
	/**
	 * 静态化成功，由StaticSupportFreeMarkerView类调用
	 */
	public void staticHtmlSuccess() {
		if (statusCallBack == null) {
			return;
		}
		//回调
		statusCallBack.success();
	}
	
	/**
	 * 静态化失败，由StaticSupportFreeMarkerView类调用
	 */
	public void staticHtmlFail() {
		if (statusCallBack == null) {
			return;
		}
		//回调
		statusCallBack.fail();
	}
	
	/**
	 * 目标html文件，除根目录外的其他路径和名字
	 * 如：category/app.html
	 * @return
	 */
	public String getTargetHtml() {
		return targetHtml;
	}

	/**
	 * 设置静态页面的文件名
	 * @param targetHtml	目标html文件，除根目录外的其他路径和名字，如：category/app.html
	 */
	public void setTargetHtml(String targetHtml) {
		this.targetHtml = targetHtml;
	}

	/**
	 * @return the statusCallBack
	 */
	public StatusCallBack getStatusCallBack() {
		return statusCallBack;
	}

	/**
	 * @param statusCallBack the statusCallBack to set
	 */
	public void setStatusCallBack(StatusCallBack statusCallBack) {
		this.statusCallBack = statusCallBack;
	}
	
	/**
	 * 静态化处理结果状态回调接口
	 * @author mili
	 *
	 */
	public interface StatusCallBack {
		/**
		 * 成功
		 */
		public void success();
		
		/**
		 * 失败
		 */
		public void fail();
	}

}