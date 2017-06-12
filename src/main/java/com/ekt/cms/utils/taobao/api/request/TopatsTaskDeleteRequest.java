package com.ekt.cms.utils.taobao.api.request;

import java.util.Map;

import com.ekt.cms.utils.taobao.api.ApiRuleException;
import com.ekt.cms.utils.taobao.api.BaseTaobaoRequest;
import com.ekt.cms.utils.taobao.api.internal.util.RequestCheckUtils;
import com.ekt.cms.utils.taobao.api.internal.util.TaobaoHashMap;
import com.ekt.cms.utils.taobao.api.response.TopatsTaskDeleteResponse;

/**
 * TOP API: taobao.topats.task.delete request
 * 
 * @author top auto create
 * @since 1.0, 2014.03.27
 */
public class TopatsTaskDeleteRequest extends BaseTaobaoRequest<TopatsTaskDeleteResponse> {
	
	

	/** 
	* 需要取消的任务ID
	 */
	private Long taskId;

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public String getApiMethodName() {
		return "taobao.topats.task.delete";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("task_id", this.taskId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<TopatsTaskDeleteResponse> getResponseClass() {
		return TopatsTaskDeleteResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(taskId, "taskId");
	}
	

}