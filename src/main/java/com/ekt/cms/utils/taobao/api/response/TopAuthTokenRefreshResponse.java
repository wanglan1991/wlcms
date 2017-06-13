package com.ekt.cms.utils.taobao.api.response;

import com.ekt.cms.utils.taobao.api.TaobaoResponse;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiField;

/**
 * TOP API: taobao.top.auth.token.refresh response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class TopAuthTokenRefreshResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8234649363837415459L;

	/** 
	 * 返回的是json信息
	 */
	@ApiField("token_result")
	private String tokenResult;


	public void setTokenResult(String tokenResult) {
		this.tokenResult = tokenResult;
	}
	public String getTokenResult( ) {
		return this.tokenResult;
	}
	


}
