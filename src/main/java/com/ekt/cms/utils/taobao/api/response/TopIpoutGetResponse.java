package com.ekt.cms.utils.taobao.api.response;

import java.util.List;

import com.ekt.cms.utils.taobao.api.TaobaoResponse;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiField;
import com.ekt.cms.utils.taobao.api.internal.mapping.ApiListField;

/**
 * TOP API: taobao.top.ipout.get response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class TopIpoutGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5125292152311546162L;

	/** 
	 * 出口IP段列表
	 */
	@ApiListField("ip_sections")
	@ApiField("string")
	private List<String> ipSections;


	public void setIpSections(List<String> ipSections) {
		this.ipSections = ipSections;
	}
	public List<String> getIpSections( ) {
		return this.ipSections;
	}
	


}
