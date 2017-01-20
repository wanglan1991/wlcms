package com.ekt.cms.video.entity;

public class ExerciseDetail {
	
	private int testpaperId; 
	private int exerciseId; 
	private int userId; 
	
	public ExerciseDetail(int testpaperId, int exerciseId, int userId) {
		this.testpaperId = testpaperId;
		this.exerciseId = exerciseId;
		this.userId = userId;
	}
	public int getTestpaperId() {
		return testpaperId;
	}
	public void setTestpaperId(int testpaperId) {
		this.testpaperId = testpaperId;
	}
	public int getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
