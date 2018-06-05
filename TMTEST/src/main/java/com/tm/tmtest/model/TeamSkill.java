package com.tm.tmtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team_skill")
public class TeamSkill {

	
	@Id
	private int id;
	
	@Column(name="team_id")
	private String teamId;
	
	
	@Column(name="skill")
	private String skill;


	public TeamSkill(String teamId, String skill) {
		super();
		this.teamId = teamId;
		this.skill = skill;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the teamId
	 */
	public String getTeamId() {
		return teamId;
	}


	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
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
