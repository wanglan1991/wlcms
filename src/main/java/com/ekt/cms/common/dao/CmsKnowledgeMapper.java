package com.ekt.cms.common.dao;

<<<<<<< HEAD
public class CmsKnowledgeMapper {

}
=======
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsKnowledge;

public interface CmsKnowledgeMapper {
	/**
	 * 分页查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	List<CmsKnowledge> listPage(@Param("CmsKnowledge")CmsKnowledge cmsKnowledge);}
>>>>>>> upstream/master
