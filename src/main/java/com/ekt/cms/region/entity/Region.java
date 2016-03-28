package com.ekt.cms.region.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Region implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6195767170033590551L;

	private Integer id;
	
	@NotBlank(message = "地域名称不能为空")
    private String regionName;
	
	@NotNull(message = "父级ID不能为空")
    private Integer parentId;
    
    @NotBlank(message = "地域级别不能为空")
    private String level;
    
    private String parentName;

	private String levelName;
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",")
				.append("regionName=").append(getRegionName()).append(",")
				.append("parentId=").append(getParentId()).append(",")
				.append("level=").append(getLevel()).append(",")
				.toString();
	}
}