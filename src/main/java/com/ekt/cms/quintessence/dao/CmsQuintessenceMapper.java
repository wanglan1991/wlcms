/**   
* @Title: QuintessenceMapper.java 
* @Package com.ekt.cms.quintessence.mapper 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月25日 下午4:29:57 
* @version V1.0   
*/
package com.ekt.cms.quintessence.dao;

import java.util.List;
import java.util.Map;

import com.ekt.cms.quintessence.entity.CmsQuintessence;

/** 
* @ClassName: QuintessenceMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author wanglan
* @date 2016年8月25日 下午4:29:57 
*  
*/
public interface CmsQuintessenceMapper {
	
	
	/**
	 * 精选列表 带分页
	 * @return
	 */
	List<CmsQuintessence> listPage(CmsQuintessence quintessence);
	
	
	
	/**
	 * 根据Id删除精选
	 * @param id
	 * @return
	 */
	int delQuintessenceById(int id);
	
	/**
	 * 获取可选的教材
	 * @return
	 */
	List<Map<String,Object>> getQuintessenceTextbook();
	
	/**
	 * 获取可选的组卷
	 * @return
	 */
	List<Map<String,Object>> getQuintessenceTestpaper();
	
	/**
	 * 添加精选
	 * @param cmsQuintessence
	 * @return
	 */
	int addQuintessence(CmsQuintessence cmsQuintessence);
		
	/**
	 * 根据条件查询
	 * @param cmsQuintessence
	 * @return
	 */
	List<CmsQuintessence> getQuintessence(CmsQuintessence cmsQuintessence);
	
	

}
