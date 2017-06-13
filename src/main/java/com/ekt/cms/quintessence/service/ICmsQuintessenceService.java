/**   
* @Title: IQuintessenceService.java 
* @Package com.ekt.cms.quintessence.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月25日 下午4:10:09 
* @version V1.0   
*/
package com.ekt.cms.quintessence.service;

import java.util.List;
import java.util.Map;

import com.ekt.cms.quintessence.entity.CmsQuintessence;

/** 
* @ClassName: IQuintessenceService 
* @Description: TODO(每日精选接口) 
* @author wanglan
* @date 2016年8月25日 下午4:10:09 
*  
*/
public interface ICmsQuintessenceService {
	
	/**
	 * 精选列表 带分页
	 * @return
	 */
	List<CmsQuintessence> listPage(CmsQuintessence quintessence);

	/**
	 * 获取teacher列表
	 * @return
	 */
	List<Map<String,Object>> getTeacherList();
	
	/**
	 * app端显示或隐藏每日精选习题、题库组卷
	 * @return
	 */
	int confine(int id);
	
	
}
