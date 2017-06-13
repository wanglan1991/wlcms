package com.ekt.cms.textbook.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author wanglan 2016-05-02
 */
public class CmsTextbook {
	// 主键
	private Integer id;
	//学段字典
	private Integer phaseNo;
	//学段value
	private String phase;
	// 字典年级
	private Integer gradeNo;
	// 年级value
	private String grade;
	// 字典科目
	private Integer subjectNo;
	// 科目value
	private String subject;
	// 字典出版社
	private Integer publisherNo;
	// 出版社value
	private String publisher;
	// 字典教材
	private Integer textbookTypeNo;
	// 教材value
	private String textbookType;
	// 标题
	private String title;
	// 知识点数组
	private String knowledgePointArr;
	// 知识点集合
	private List<String> knowledgeList;
	// 摘要
	private String digest;
	//教师id
	private int authorId;
	// 作者
	private String author;
	// 图片URL
	private String imgUrl;
	// 状态
	private Integer status;
	//录入者
	private Integer inputAccountId; 
	//录入者名称
	private String inputAccountRealName;
	//创建时间
	private Date crateTime;
	
	private String knowledgePointArrVal;
	//折扣
	private Double discount;
	//价格
	private Double price;
	//是否免费
	private Integer isFree;
	//是否为热门
	private Integer isHot;
	//章数
	private Integer chapterCount;
	//节数
	private Integer sectionCount;
	//父级id
	private  Integer parentId;
	//是否为推荐视频
	private  Integer isRecommend;
	//单品数量
	private int childCount;
	
	
	
	
	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getChapterCount() {
		return chapterCount;
	}

	public void setChapterCount(Integer chapterCount) {
		this.chapterCount = chapterCount;
	}

	public Integer getSectionCount() {
		return sectionCount;
	}

	public void setSectionCount(Integer sectionCount) {
		this.sectionCount = sectionCount;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public String getInputAccountRealName() {
		return inputAccountRealName;
	}

	public void setInputAccountRealName(String inputAccountRealName) {
		this.inputAccountRealName = inputAccountRealName;
	}
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public Date getCrateTime() {
		return crateTime;
	}

	public String getKnowledgePointArrVal() {
		return knowledgePointArrVal;
	}

	public void setKnowledgePointArrVal(String knowledgePointArrVal) {
		this.knowledgePointArrVal = knowledgePointArrVal;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	

	public Integer getInputAccountId() {
		return inputAccountId;
	}

	public void setInputAccountId(Integer inputAccountId) {
		this.inputAccountId = inputAccountId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(Integer subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getPublisherNo() {
		return publisherNo;
	}

	public void setPublisherNo(Integer publisherNo) {
		this.publisherNo = publisherNo;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getTextbookTypeNo() {
		return textbookTypeNo;
	}

	public void setTextbookTypeNo(Integer textbookTypeNo) {
		this.textbookTypeNo = textbookTypeNo;
	}

	public String getTextbookType() {
		return textbookType;
	}

	public void setTextbookType(String textbookType) {
		this.textbookType = textbookType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKnowledgePointArr() {
		return knowledgePointArr;
	}

	public void setKnowledgePointArr(String knowledgePointArr) {
		this.knowledgePointArr = knowledgePointArr;
	}

	public List<String> getKnowledgeList() {
		return knowledgeList;
	}

	public void setKnowledgeList(List<String> knowledgeList) {
		this.knowledgeList = knowledgeList;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(Integer phaseNo) {
		this.phaseNo = phaseNo;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}
	

}
