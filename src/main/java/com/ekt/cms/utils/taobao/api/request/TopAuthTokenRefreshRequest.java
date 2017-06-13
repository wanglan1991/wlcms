package com.ekt.cms.utils.taobao.api.request;

import java.util.Map;

import com.ekt.cms.utils.taobao.api.ApiRuleException;
import com.ekt.cms.utils.taobao.api.BaseTaobaoRequest;
import com.ekt.cms.utils.taobao.api.internal.util.RequestCheckUtils;
import com.ekt.cms.utils.taobao.api.internal.util.TaobaoHashMap;
import com.ekt.cms.utils.taobao.api.response.TopAuthTokenRefreshResponse;

/**
 * TOP API: taobao.top.auth.token.refresh request
 * 
 * @author top auto create
 * @since 1.0, 2015.08.20
 */
public class TopAuthTokenRefreshRequest extends BaseTaobaoRequest<TopAuthTokenRefreshResponse> {
	
	

	/** 
	* grantType==refresh_token 时需要
	 */
	private String refreshToken;

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public String getApiMethodName() {
		return "taobao.top.auth.token.refresh";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("refresh_token", this.refreshToken);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<TopAuthTokenRefreshResponse> getResponseClass() {
		return TopAuthTokenRefreshResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(refreshToken, "refreshToken");
	}
	

}