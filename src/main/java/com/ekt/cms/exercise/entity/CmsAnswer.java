package com.ekt.cms.exercise.entity;

/**
 * 2016-04-25 15:44
 * 
 * @author wanglan
 *
 */
public class CmsAnswer {
	// 主键
	private int id;
	// 习题Id
	private int exerciseId;
	// 选项
	private String option;
	
	public CmsAnswer(){
		
	}
	/**
	 * 
	 * @param exerciseId
	 * @param option
	 * @param contents
	 * @param isTrue
	 * @param status
	 */
	public CmsAnswer( String option, String contents, int isTrue, int status) {
		this.option = option;
		this.contents = contents;
		this.isTrue = isTrue;
		this.status = status;
	}

	// 内容
	private String contents;
	// 内容URL
	private String contentsUrl;
	// 是否是正确答案
	private int isTrue;
	// 解析
	private String explain;
	// 解析URL
	private String explainUrl;
	// 状态
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContentsUrl() {
		return contentsUrl;
	}

	public void setContentsUrl(String contentsUrl) {
		this.contentsUrl = contentsUrl;
	}

	public int getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(int isTrue) {
		this.isTrue = isTrue;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExplainUrl() {
		return explainUrl;
	}

	public void setExplainUrl(String explainUrl) {
		this.explainUrl = explainUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

}
