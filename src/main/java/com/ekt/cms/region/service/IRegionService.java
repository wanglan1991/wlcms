package com.ekt.cms.region.service;

import java.util.List;

import com.ekt.cms.region.entity.Region;
import com.ekt.cms.utils.page.Pagination;

public interface IRegionService {
	//add
	public int addRegion(Region region) throws Exception;
	
	//query by primary key
	public Region queryByKey(int key) throws Exception;
	
	//delete by primary key
	public int deleteByKey(int key) throws Exception;
	
	//update by primary key
	public int updateByKey(Region region) throws Exception;
	
	//query by condition
	public List<Region> queryByCondition(Region region);
	
	//query pagination list by condition
	public List<Region> listPage(Region region, Pagination pagination);
}
