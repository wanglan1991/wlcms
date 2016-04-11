package com.ekt.cms.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ekt.cms.user.entity.CmsUser;
import com.ekt.cms.utils.page.Pagination;
/**
 * notice： 这是一个接口文件，mybatis3 只需要定义dao接口，不需要实现类， 会自动匹配，名字和mapper文件夹下面的 xml文件名字要一致
 **/
public interface CmsUserMapper {
	
	// 按主键删除    	
    int deleteByPrimaryKey(Integer id);
    
    // 插入 返回新插入数据的ID    
    int insert(CmsUser cmsUser);
    
    // 按主键查询   
    CmsUser selectByPrimaryKey(Integer id);
    
    // 更新   
    int updateByPrimaryKey(CmsUser cmsUser);
    /**
     * 按条件查询
     */
    List<CmsUser> queryByCondition(@Param("cmsUser")CmsUser cmsUser);
    /**
	 * 分页查找
	 * 
	 * @param 传入带查询条件的对象参数
	 * @param 传入分页对象参数
	 * @return 返回包含对象的List
	 */
	List<CmsUser> listPage(@Param("cmsUser") CmsUser cmsUser, @Param("pagination") Pagination pagination);
}
    
