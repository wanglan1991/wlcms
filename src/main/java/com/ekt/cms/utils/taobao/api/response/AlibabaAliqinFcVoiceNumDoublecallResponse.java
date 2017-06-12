package com.ekt.cms.utils.taobao.api.response;

import com.ekt.cms.utils.taobao.api.TaobaoResponse;
import com.ekt.cms.utils.taobao.api.domain.BizResult;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiField;

/**
 * TOP API: alibaba.aliqin.fc.voice.num.doublecall response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class AlibabaAliqinFcVoiceNumDoublecallResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5199365812178453555L;

	/** 
	 * 接口返回参数
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
