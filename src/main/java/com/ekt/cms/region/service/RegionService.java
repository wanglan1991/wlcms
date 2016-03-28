package com.ekt.cms.region.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.region.dao.RegionMapper;
import com.ekt.cms.region.entity.Region;
import com.ekt.cms.utils.page.Pagination;

@Service("regionService")
public class RegionService implements IRegionService {
	
	//inject dao
	@Resource
	private RegionMapper regionMapper;

	public int addRegion(Region region) throws Exception {
		return regionMapper.insert(region);
	}

	public Region queryByKey(int key) throws Exception {
		return regionMapper.selectByPrimaryKey(key);
	}

	public int deleteByKey(int key) throws Exception {
		return regionMapper.deleteByPrimaryKey(key);
		
	}

	public int updateByKey(Region region) throws Exception {
		return regionMapper.updateByPrimaryKey(region);
	}
	
	public List<Region> queryByCondition(Region region) {
		return regionMapper.queryByCondition(region);
	}

	public List<Region> listPage(Region region, Pagination pagination) {
		return regionMapper.listPage(region, pagination);
	}


}
