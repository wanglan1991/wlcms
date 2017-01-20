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

import org.apache.ibatis.annotations.Param;

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
	 * 获取teacher列表
	 * @return
	 */
	List<Map<String,Object>> getTeacherList();
	
	/**
	 * app端显示或隐藏每日精选习题、题库组卷
	 * @return
	 */
	int confine(@Param("id")int id);
	

}
