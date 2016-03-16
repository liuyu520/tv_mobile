package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 管理员
 * @author huangwei
 * @since 2015年3月2日
 */
@Entity
@Table(name = "t_admin")
public class Admin {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private Integer create_id;
	private Integer createTime;
	private int status;
	private String email;
	private String potrait;
	/***
	 * 最后登录时间
	 */
	private Integer lastlogintime;
	/***
	 * 登录IP
	 */
	private String loginip;
	/***
	 * 管理员的级别,即权限
	 */
	private int type;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	@Column(name="create_time")
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPotrait() {
		return potrait;
	}
	public void setPotrait(String potrait) {
		this.potrait = potrait;
	}
	
	public Integer getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Integer lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	
}
