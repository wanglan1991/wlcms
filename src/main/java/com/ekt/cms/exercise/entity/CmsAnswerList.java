package com.ekt.cms.exercise.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author wanglan
 *
 */
public class CmsAnswerList {
	
	private List<Map<String,Object>> answerList=new ArrayList<Map<String,Object>>();

	public List<Map<String,Object>> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Map<String,Object>> answerList) {
		this.answerList = answerList;
	}

}
