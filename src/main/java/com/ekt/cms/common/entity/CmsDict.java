package com.ekt.cms.common.entity;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */
public class CmsDict {
	//主键
    private Integer id;
    //字典值
    private String value;
    //父级ID，如果存在
    private Integer parentId;
    //字典类型编码
    private String typeEncoding;
    //状态 0 不可用  1 可用
    private Integer status;

    private String remark;
    
    private String typeName;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
        return parentId;
    }

    public void setParentid(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeEncoding() {
		return typeEncoding;
	}

	public void setTypeEncoding(String typeEncoding) {
		this.typeEncoding = typeEncoding;
	}
    
}