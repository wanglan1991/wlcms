package com.ekt.cms.utils.taobao.api.internal.cluster;

import java.util.HashMap;
import java.util.Map;

import com.ekt.cms.utils.taobao.api.ApiRuleException;
import com.ekt.cms.utils.taobao.api.BaseTaobaoRequest;



public class HttpdnsGetRequest extends BaseTaobaoRequest<HttpdnsGetResponse> {

	public String getApiMethodName() {
		return "taobao.httpdns.get";
	}

	public Map<String, String> getTextParams() {
		return new HashMap<String, String>();
	}

	public Class<HttpdnsGetResponse> getResponseClass() {
		return HttpdnsGetResponse.class;
	}

	public void check() throws ApiRuleException {
	}

}
