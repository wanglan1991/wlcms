package com.ekt.cms.apiUser.entity;

import java.util.Date;

/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月22日 下午4:46:12 
* 程序的简单说明 
*/
public class ApiUser {
	

	private Integer id;
	private String username;
	private String password;
	private String realName;
	private String telephone;
	private String sex;
	private String headPicture;
	private String registerType;
	private String qqOpenId;
	private Date   registerDate;
	private String nickname;
	private String email;
	private Integer gradeNo;
	private int credits;
	private Integer status;
	private String grade;
	private String school;
	private Date birthday;
	private String signature;
	//微信开放平台openID
	private String wechatOpenId;
	//微信公众平台openID
	private String wechatGZOpenId;
	//用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
	private String wechatUnionId;
	//角色
	private Integer role;
	//cms账户ID
	private Integer accountId;
	//cms角色名称
	private String roleName;
	
	private int untreatedCount;//待处理事物个数
	
	private int treatedCount;//已处理事物个数
	
	private int isReal;//是否为真实用户
	
	
	public int getIsReal() {
		return isReal;
	}
	public void setIsReal(int isReal) {
		this.isReal = isReal;
	}
	public int getUntreatedCount() {
		return untreatedCount;
	}
	public void setUntreatedCount(int untreatedCount) {
		this.untreatedCount = untreatedCount;
	}
	public int getTreatedCount() {
		return treatedCount;
	}
	public void setTreatedCount(int treatedCount) {
		this.treatedCount = treatedCount;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
		
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getRegisterType() {
		return registerType;
	}
	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}
	public String getQqOpenId() {
		return qqOpenId;
	}
	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getWechatOpenId() {
		return wechatOpenId;
	}
	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}
	public String getWechatGZOpenId() {
		return wechatGZOpenId;
	}
	public void setWechatGZOpenId(String wechatGZOpenId) {
		this.wechatGZOpenId = wechatGZOpenId;
	}
	public String getWechatUnionId() {
		return wechatUnionId;
	}
	public void setWechatUnionId(String wechatUnionId) {
		this.wechatUnionId = wechatUnionId;
	}
	
	
	
}
