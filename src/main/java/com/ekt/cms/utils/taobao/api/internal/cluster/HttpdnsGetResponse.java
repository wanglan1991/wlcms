package com.ekt.cms.utils.taobao.api.internal.cluster;

import com.ekt.cms.utils.taobao.api.TaobaoResponse;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiField;

public class HttpdnsGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7048494816614445538L;

	@ApiField("result")
	private String result;

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
