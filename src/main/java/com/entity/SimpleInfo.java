package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 
 * @author huangweii
 * 2015年6月16日
 */
@Entity
@Table(name = "t_simple_info")
public class SimpleInfo {
	private int id;
	private Long createTime;
	/***
	 * 删除该记录的时间
	 */
	private Long deleteTime;
	private String info;
	/***
	 * 1:可用;2:不可用
	 */
	private int status;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	/***
	 * 1:可用;2:不可用
	 * @return
	 */
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name="delete_time")
	public Long getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Long deleteTime) {
		this.deleteTime = deleteTime;
	}
	
}
