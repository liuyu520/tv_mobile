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
 * 
 * @author huangwei
 * @since 2015年3月13日
 */
@Entity
@Table(name="t_bbs")
@JsonIgnoreProperties(value = { "releaseUser" })
public class BBS {
	private int id;
	/***
	 * 发帖区
	 */
	private int type;
	/***
	 * 标题
	 */
	private String title;
	private String cardcontent;
	private int click;
	/***
	 * 0:审核中;1:激活, 2,审核不通过,3:失效(不显示)
	 */
	private int status;
	private User releaseUser;
	private String releaseName;
	private long releaseTime;
	private String reserved;
	private String releaseTimeStr;
	/***
	 * 发帖区名称
	 */
	private String typeStr;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCardcontent() {
		return cardcontent;
	}
	public void setCardcontent(String cardcontent) {
		this.cardcontent = cardcontent;
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
	@Column(name="release_name")
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}
	@Column(name="release_time")
	public long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	@Transient
	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}
	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}
	@Transient
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
}
