package com.ekt.cms.textbook.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ekt.cms.textbook.dao.CmsTextbookMapper;
import com.ekt.cms.textbook.entity.CmsTextbook;
/**
 * 
 * @author wanglan
 * 2016-05-02
 *教材接口
 */
@Service("cmsTextbookService")
public class CmsTextbookService implements ICmsTextbookService {

	@Resource
	private CmsTextbookMapper cmsTextbookMapper;

	@Override
	public List<CmsTextbook> listPage(CmsTextbook cmsTextbook) {
		return cmsTextbookMapper.listPage(cmsTextbook);
	}

	@Override
	public Integer addTextbook(CmsTextbook cmsTextbook) {
		return cmsTextbookMapper.insertTextbook(cmsTextbook);
	}

	@Override
	public Integer deleteTextbook(Integer id) {
		return cmsTextbookMapper.deleteTextbook(id);
	}

	@Override
	public Integer confine(Integer id, Integer status) {
		return cmsTextbookMapper.confine(id,status);
	}

	@Override
	public Integer update(CmsTextbook cmsTextbook) {
		return cmsTextbookMapper.updateTextbook(cmsTextbook);
	}

}