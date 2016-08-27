/**   
* @Title: QuintessenceService.java 
* @Package com.ekt.cms.quintessence.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月25日 下午4:10:19 
* @version V1.0   
*/
package com.ekt.cms.quintessence.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.quintessence.dao.CmsQuintessenceMapper;
import com.ekt.cms.quintessence.entity.CmsQuintessence;

/** 
* @ClassName: QuintessenceService 
* @Description: TODO(精选接口实现类) 
* @author wanglan
* @date 2016年8月25日 下午4:10:19 
*  
*/

@Service("quintessenceService")
public class CmsQuintessenceService implements ICmsQuintessenceService {
	
	@Resource
	private CmsQuintessenceMapper quintessenceMapper;

	@Override
	public List<CmsQuintessence> listPage(CmsQuintessence quintessence) {
		return quintessenceMapper.listPage(quintessence);
	}

	@Override
	public int delQuintessenceById(int id) {
		return quintessenceMapper.delQuintessenceById(id);
	}

	@Override
	public List<Map<String, Object>> getQuintessenceTextbook() {
		return quintessenceMapper.getQuintessenceTextbook();
	}

	@Override
	public List<Map<String, Object>> getQuintessenceTestpaper() {
		return quintessenceMapper.getQuintessenceTestpaper();
	}

	@Override
	public int addQuintessence(CmsQuintessence cmsQuintessence) {
		return quintessenceMapper.addQuintessence(cmsQuintessence);
	}

	@Override
	public List<CmsQuintessence> getQuintessence(CmsQuintessence cmsQuintessence) {
		return quintessenceMapper.getQuintessence(cmsQuintessence);
	}

}
