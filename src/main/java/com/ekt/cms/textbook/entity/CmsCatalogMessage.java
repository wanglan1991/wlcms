package com.ekt.cms.textbook.entity;

/**
 * 
 * @author wanglan 2016-10-09
 *
 */
public class CmsCatalogMessage {

	private Integer id;

	private String catalogName;

	private Integer textbookId;

	private Integer gradeNo;

	private Integer subjectNo;

	private Integer phaseNo;

	private Integer isHot;

	private Integer status;

	private Integer textbookTypeNo;

	private String knowledgePointArr;

	private Double discount;

	private Integer isfree;

	private String imageUrl;

	private String videoFileName;

	private Double price;
	
	private String introduction;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getTextbookId() {
		return textbookId;
	}

	public void setTextbookId(Integer textbookId) {
		this.textbookId = textbookId;
	}

	public String getKnowledgePointArr() {
		return knowledgePointArr;
	}

	public void setKnowledgePointArr(String knowledgePointArr) {
		this.knowledgePointArr = knowledgePointArr;
	}

	public Integer getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}

	public Integer getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(Integer subjectNo) {
		this.subjectNo = subjectNo;
	}

	public Integer getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(Integer phaseNo) {
		this.phaseNo = phaseNo;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTextbookTypeNo() {
		return textbookTypeNo;
	}

	public void setTextbookTypeNo(Integer textbookTypeNo) {
		this.textbookTypeNo = textbookTypeNo;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getIsfree() {
		return isfree;
	}

	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public CmsTextbook getTextbook(){
		CmsTextbook book = new CmsTextbook();
		book.setGradeNo(this.getGradeNo());
		book.setSubjectNo(this.subjectNo);
		book.setPhaseNo(this.phaseNo);
		book.setTextbookTypeNo(this.textbookTypeNo);
		book.setTitle(this.getCatalogName());
		book.setDigest(this.introduction);
		book.setImgUrl(this.imageUrl);
		if (this.knowledgePointArr != null) {
			book.setKnowledgePointArr(this.knowledgePointArr);
			book.setKnowledgePointArrVal(this.catalogName);
		}
		book.setIsHot(this.isHot);
		book.setPrice(this.price);
		book.setDiscount(this.discount);
		book.setIsFree(this.isfree);
		book.setParentId(this.textbookId);
		return book;
	}
	
	public CmsCatalog getCatalog(int textbookId,int catalogLevel,int orderNo,int parentId){
		CmsCatalog catalog =new CmsCatalog();
		catalog.setTextbookId(textbookId);
		catalog.setCatalogName(this.catalogName);
		catalog.setCatalogLevel(catalogLevel);
		catalog.setOrderNo(orderNo);
		catalog.setStatus(1);
		catalog.setParentId(parentId);
		if(parentId!=0){
			catalog.setVideoFileName(this.videoFileName);
		}
		return catalog;
	}
}