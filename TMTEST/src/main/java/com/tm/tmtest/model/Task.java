package com.tm.tmtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
	
	
	public Task(String taskId, String skill) {
		super();
		this.taskId = taskId;
		this.skill = skill;
	}


	@Id
	@Column(name="task_id")
	private String taskId;
	
	
	@Column(name="skill")
	private String skill;


	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}


	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}


	/**
	 * @param skill the skill to set
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	


}
