package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/***
 * 论坛评论表
 * @author huangwei
 * @since 2015年3月13日
 */
@Entity
@Table(name="t_user_sendcard")
@JsonIgnoreProperties(value = { "releaseUser" })
public class UserSendcard {
	private int id;
	private String followcardcontent;
	/***
	 * 帖子ID
	 */
	private int cardid;
	private int click;
	private int status;
	private User releaseUser;
	private String from_username;
	private long releaseTime;
	private String releaseTimeStr;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFollowcardcontent() {
		return followcardcontent;
	}
	public void setFollowcardcontent(String followcardcontent) {
		this.followcardcontent = followcardcontent;
	}
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "releaseId")
	public User getReleaseUser() {
		return releaseUser;
	}
	public void setReleaseUser(User releaseUser) {
		this.releaseUser = releaseUser;
	}
	public String getFrom_username() {
		return from_username;
	}
	public void setFrom_username(String from_username) {
		this.from_username = from_username;
	}
	@Column(name="release_time")
	public long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(long release_time) {
		this.releaseTime = release_time;
	}
	
	@Transient
	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}
	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}
	
}
