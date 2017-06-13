package com.ekt.cms.video.entity;

import com.ekt.cms.textbook.entity.CmsTextbook;

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
	//原文件名
	private String oldFileName;
	//URL
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
	//选课Id
	private int textbookId;
	//试看视频video key
	private String subVideoKey;
	//习题个数
	private int exerciseCount;
	//是否存在题库组卷
	private int hasTestpaper;
	//试看版videoKey
	private String subUrl;
	
	
	
	
	
	
	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public String getSubUrl() {
		return subUrl;
	}

	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}

	public int getHasTestpaper() {
		return hasTestpaper;
	}

	public void setHasTestpaper(int hasTestpaper) {
		this.hasTestpaper = hasTestpaper;
	}

	public int getExerciseCount() {
		return exerciseCount;
	}

	public void setExerciseCount(int exerciseCount) {
		this.exerciseCount = exerciseCount;
	}

	public String getSubVideoKey() {
		return subVideoKey;
	}

	public void setSubVideoKey(String subVideoKey) {
		this.subVideoKey = subVideoKey;
	}

	public int getTextbookId() {
		return textbookId;
	}

	public void setTextbookId(int textbookId) {
		this.textbookId = textbookId;
	}

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

	
	//转换选课
	public CmsTextbook getTextbook(){
		CmsTextbook  book=new CmsTextbook();
		book.setTitle(this.videoName);
		book.setDigest(this.digest);
		book.setGradeNo(this.gradeNo);
		book.setSubjectNo(this.subjectNo);
		book.setPhaseNo(this.gradeNo==19||this.gradeNo==20||this.gradeNo==21?61:60);
		book.setTextbookTypeNo(46);
		book.setImgUrl(this.imageUrl);
		if(this.knowledgeId!=null&&this.knowledge!=null){
			book.setKnowledgePointArr(this.knowledgeId.substring(1, this.knowledgeId.length()));
			book.setKnowledgePointArrVal(this.knowledge.substring(1, this.knowledge.length()));
		}
		book.setPrice(this.price);
		book.setDiscount(this.discount);
		book.setIsFree(this.isFree);
		return book;
	}

	
	


}
