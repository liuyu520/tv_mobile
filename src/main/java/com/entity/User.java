package com.entity;

import com.common.entity.user.interf.GenericUser;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * 
 * @author huangwei
 * @since 2015年3月5日
 */
@Entity
@Table(name = "t_user")
public class User extends GenericUser implements Serializable{
	private static final long serialVersionUID = -7507235006701466342L;
	private int id;
	/***
	 * 登录名
	 */
	private String username;
	private String password;
	/***
	 * 称呼或真实姓名
	 */
	private String nickname;
	/***
	 * 0:审核中;1:激活,2:失效(不能登录)
	 */
	private int status;
	private Admin releaseAdmin;
	/***
	 * 添加时间
	 */
	private Long createTime;
	/***
	 * 最后修改时间(不区分谁修改)
	 */
	private long updateTime;
	private String email;
	/***
	 * 个人头像(图片的url地址,不包括项目名)
	 */
	private String potrait;
	/***
	 * 用户级别(预留)
	 */
	private Integer level;
	/***
	 * 预留信息，暂时没有用
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "create_id")
	public Admin getReleaseAdmin() {
		return releaseAdmin;
	}
	public void setReleaseAdmin(Admin releaseAdmin) {
		this.releaseAdmin = releaseAdmin;
	}
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Column(name="update_time")
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	
}
