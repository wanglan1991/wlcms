package com.ekt.cms.teacher.entity;
/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月20日 上午10:49:08 
* 程序的简单说明 
*/
public class CmsTeacher {
	
//	主键id
	private int id; 
//	名称
	private String name;
//	性别
	private String sex; 
//	头像
	private String headPicture;
//	学校id
	private int schoolId;
//	学校名称
	private String schoolName;
//	字典学科
	private int subjectNo;
//	字典学科值
	private String subject;
//	字典年级
	private int gradeNo; 
//	字典年级值
	private String grade;
//	用户id
	private int userId; 
//	用户用户名
	private String userName;
//	格言
	private String motto;
//	名师信息
	private String info;
//	字典学段
	private int phaseNo;
//	字典学段值
	private String phase;
//	是否为名师
	private int isFamous;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeadPicture() {
		return headPicture;
	}
	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getPhaseNo() {
		return phaseNo;
	}
	public void setPhaseNo(int phaseNo) {
		this.phaseNo = phaseNo;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public int getIsFamous() {
		return isFamous;
	}
	public void setIsFamous(int isFamous) {
		this.isFamous = isFamous;
	}

	
	

}
