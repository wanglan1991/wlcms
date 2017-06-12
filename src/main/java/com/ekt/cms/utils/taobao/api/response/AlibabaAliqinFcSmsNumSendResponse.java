package com.ekt.cms.utils.taobao.api.response;

import com.ekt.cms.utils.taobao.api.TaobaoResponse;
import com.ekt.cms.utils.taobao.api.domain.BizResult;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiField;

/**
 * TOP API: alibaba.aliqin.fc.sms.num.send response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class AlibabaAliqinFcSmsNumSendResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8193754799493547528L;

	/** 
	 * 返回值
	 */
	@ApiField("result")
	private BizResult result;


	public void setResult(BizResult result) {
		this.result = result;
	}
	public BizResult getResult( ) {
		return this.result;
	}
	


}
