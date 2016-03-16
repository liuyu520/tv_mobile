package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***
 * 
 * @author huangweii
 * 2015年6月20日
 */
@Entity
@Table(name = "t_push_device")
public class PushDevice {
	private int id;
	/***
	 * 要推送的目标用户
	 */
	private User user;
	/***
	 * 设备标示（device token or clientid）
	 */
	private String deviceId;
	/***
	 * 操作系统版本
	 */
	private String osVersion;
	/***
	 * 注册时间
	 */
	private String deviceRegisterTime;
	/***
	 * 操作系统类型：android or ios
	 */
	private String osType;
	/***
	 * 移动设备的其他信息,比如uuid,mac地址,手机型号等
	 */
	private String deviceInfo;
	private String reserved;
	/***
	 * 用户的登录名,冗余字段.为了方便显示登录名,而不必重新再查数据库
	 */
	private String username;
	/***
	 * 客户端上次登录的时间
	 */
	private String loginedTime;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@OneToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/***
	 * 
	 * @return
	 */
	@Column(name="device_id")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name="device_register_time")
	public String getDeviceRegisterTime() {
		return deviceRegisterTime;
	}
	public void setDeviceRegisterTime(String deviceRegisterTime) {
		this.deviceRegisterTime = deviceRegisterTime;
	}
	@Column(name="os_type")
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	@Column(name="device_info")
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	@Column(name="os_version")
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	/***
	 * 用户的登录名,冗余字段.为了方便显示登录名,而不必重新再查数据库
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="logined_time")
	public String getLoginedTime() {
		return loginedTime;
	}
	public void setLoginedTime(String loginedTime) {
		this.loginedTime = loginedTime;
	}
	
	
	
	
}
