package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_feedback")
public class UserFeedback {
	private int id;
	/**
	 * 1:bug;2:心得;3:需求;4:建议
	 */
	private int type;
	/***
	 * 反馈的内容
	 */
	private String content;
	
	private User user;
	private long releaseTime;
	private int status;
	private Admin deleteAdmin;
	private long deleteTime;
	/***
	 * 联系人
	 */
	private String contacts;
	/***
	 * 联系方式
	 */
	private String contactWay;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="release_time")
	public long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "deleteId")
	public Admin getDeleteAdmin() {
		return deleteAdmin;
	}
	public void setDeleteAdmin(Admin deleteAdmin) {
		this.deleteAdmin = deleteAdmin;
	}
	
	@Column(name="delete_time")
	public long getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(long deleteTime) {
		this.deleteTime = deleteTime;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(name="contact_way")
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
}
