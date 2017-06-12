package com.ekt.cms.utils.taobao.api.request;

import java.util.Map;

import com.ekt.cms.utils.taobao.api.ApiRuleException;
import com.ekt.cms.utils.taobao.api.BaseTaobaoRequest;
import com.ekt.cms.utils.taobao.api.internal.util.RequestCheckUtils;
import com.ekt.cms.utils.taobao.api.internal.util.TaobaoHashMap;
import com.ekt.cms.utils.taobao.api.response.AlibabaAliqinFcFlowChargeProvinceResponse;

/**
 * TOP API: alibaba.aliqin.fc.flow.charge.province request
 * 
 * @author top auto create
 * @since 1.0, 2016.03.30
 */
public class AlibabaAliqinFcFlowChargeProvinceRequest extends BaseTaobaoRequest<AlibabaAliqinFcFlowChargeProvinceResponse> {
	
	

	/** 
	* 需要充值的流量
	 */
	private String grade;

	/** 
	* 唯一流水号
	 */
	private String outRechargeId;

	/** 
	* 手机号
	 */
	private String phoneNum;

	/** 
	* 充值原因
	 */
	private String reason;

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setOutRechargeId(String outRechargeId) {
		this.outRechargeId = outRechargeId;
	}

	public String getOutRechargeId() {
		return this.outRechargeId;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	public String getApiMethodName() {
		return "alibaba.aliqin.fc.flow.charge.province";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("grade", this.grade);
		txtParams.put("out_recharge_id", this.outRechargeId);
		txtParams.put("phone_num", this.phoneNum);
		txtParams.put("reason", this.reason);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<AlibabaAliqinFcFlowChargeProvinceResponse> getResponseClass() {
		return AlibabaAliqinFcFlowChargeProvinceResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(grade, "grade");
		RequestCheckUtils.checkNotEmpty(outRechargeId, "outRechargeId");
		RequestCheckUtils.checkNotEmpty(phoneNum, "phoneNum");
	}
	

}