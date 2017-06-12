package com.ekt.cms.utils.taobao.api.response;

import com.ekt.cms.utils.taobao.api.TaobaoResponse;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiField;

/**
 * TOP API: taobao.appip.get response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class AppipGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 3733636587414175661L;

	/** 
	 * ISV发起请求服务器IP
	 */
	@ApiField("ip")
	private String ip;


	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp( ) {
		return this.ip;
	}
	


}
