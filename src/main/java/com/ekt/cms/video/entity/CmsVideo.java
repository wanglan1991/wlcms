package com.ekt.cms.video.entity;

/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */
public class CmsVideo {
	private int id;
	private String videoName;
	// 视频文件名
	private String fileName;
	private String url;
	// 备用URL
	private String urlBak;
	// 运营商
	private String isp;
	// 知识点ID
	private String knowledgeId;
	private String knowledge;
	// 年级ID
	private int gradeNo;
	private String grade;
	// 科目ID
	private int subjectNo;
	private String subject;
	// 视频时长
	private int duration;
	// 视频截图URL
	private String imageUrl;
	// 简介
	private String digest;
	// 视频讲师
	private int authorId;
	private String author;
	// 状态
	private int status;
	// 视频上传后获取到的ID
	private String  videoKey;
	//转码状态 0:未转码 1:已转码
	private int transcodeStatus;
	//配套习题念年级
	private int exerciseGradeNo;
	//配套习题科目
	private int exerciseSubjectNo;
	//配套习题知识点
	private int exerciseKnoeledgeId;
	//文件Id;
	private String fileId;
	//价格
	private Double price;
	//折扣
	private Double discount;
	//是否免费
	private Integer isFree;
	
	
	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getExerciseGradeNo() {
		return exerciseGradeNo;
	}

	public void setExerciseGradeNo(int exerciseGradeNo) {
		this.exerciseGradeNo = exerciseGradeNo;
	}

	public int getExerciseSubjectNo() {
		return exerciseSubjectNo;
	}

	public void setExerciseSubjectNo(int exerciseSubjectNo) {
		this.exerciseSubjectNo = exerciseSubjectNo;
	}

	public int getExerciseKnoeledgeId() {
		return exerciseKnoeledgeId;
	}

	public void setExerciseKnoeledgeId(int exerciseKnoeledgeId) {
		this.exerciseKnoeledgeId = exerciseKnoeledgeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlBak() {
		return urlBak;
	}

	public void setUrlBak(String urlBak) {
		this.urlBak = urlBak;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}


	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public int getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getVideoKey() {
		return videoKey;
	}

	public void setVideoKey(String videoKey) {
		this.videoKey = videoKey;
	}

	public int getTranscodeStatus() {
		return transcodeStatus;
	}

	public void setTranscodeStatus(int transcodeStatus) {
		this.transcodeStatus = transcodeStatus;
	}

	



}
