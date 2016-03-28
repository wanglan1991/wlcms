package com.ekt.cms.region.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.region.entity.Region;
import com.ekt.cms.utils.page.Pagination;

public interface RegionMapper {
	
    public int deleteByPrimaryKey(Integer id);

    public int insert(Region region);

    public Region selectByPrimaryKey(Integer id);

    public int updateByPrimaryKey(Region region);
    
    public List<Region> queryByCondition(@Param("region")Region region);
    
    /**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Region> listPage(@Param("region") Region region,
			@Param("pagination") Pagination pagination);
}