package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_vote_log")
public class VoteLog {
	private int id;
	private User user;
	/***
	 * 1:最宜居<br>
	 * 2:最优户<br>
	 * 3:最佳物业
	 */
	private int type;
	private HouseBuilding houseBuilding;
	private String voteTime;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name = "house_building_id")
	public HouseBuilding getHouseBuilding() {
		return houseBuilding;
	}
	public void setHouseBuilding(HouseBuilding houseBuilding) {
		this.houseBuilding = houseBuilding;
	}
	@Column(name="vote_time")
	public String getVoteTime() {
		return voteTime;
	}
	public void setVoteTime(String voteTime) {
		this.voteTime = voteTime;
	}

	
}
