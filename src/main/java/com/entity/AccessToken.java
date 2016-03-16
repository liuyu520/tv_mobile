package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 
 * @author huangweii
 * 2015年11月3日
 */
@Entity
@Table(name = "t_access_token")
public class AccessToken {
	private int id;
	private String access_token;
	/***
	 * 登录名
	 */
	private String auth_username;
	private String password;
	/***
	 * 登录时间,格式:yyyy-MM-dd HH:mm:ss
	 */
	private String loginTime;
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
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAuth_username() {
		return auth_username;
	}
	public void setAuth_username(String auth_username) {
		this.auth_username = auth_username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
}
