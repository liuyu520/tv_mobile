package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "t_vote")
public class Vote {
	private int id;
	/***
	 * 1:最宜居<br>
	 * 2:最优户<br>
	 * 3:最佳物业
	 */
	private int type;
	private HouseBuilding houseBuilding;
	/***
	 * 投票数
	 */
	private long voteCount;
	/***
	 * 最近更新时间
	 */
	private String updateTime;
	/***
	 * 预留
	 */
	private String reserved;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Column(name="vote_count")
	public long getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}
	@Transient
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	@Column(name="update_time")
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
