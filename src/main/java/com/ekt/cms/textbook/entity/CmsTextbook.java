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
	// 作者
	private String author;
	// 图片URL
	private String imgUrl;
	// 状态
	private Integer status;
	//录入者
	private String pushPerson; 
	//创建时间
	private Date crateTime;
	
	private String knowledgePointArrVal;

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

	public String getPushPerson() {
		return pushPerson;
	}

	public void setPushPerson(String pushPerson) {
		this.pushPerson = pushPerson;
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

}
