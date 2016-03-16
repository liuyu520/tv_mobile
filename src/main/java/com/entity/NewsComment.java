package com.entity;

import java.io.Serializable;

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
 * @since 2015年3月10日
 */
@Entity
@Table(name = "t_comment")
@JsonIgnoreProperties(value = { "paperNews" })
public class NewsComment implements Serializable{
	private static final long serialVersionUID = 5524132356666940620L;
	private int id;
	/***
	 * 评论作者
	 */
	private String fromUsername;
	private long releaseTime;
	private String releaseTimeStr;
	private String comments;
	/***
	 * 1:新闻;2:报料;
	 */
	private int targetType;
	/***
	 * 评论的对象(新闻或报料)
	 */
	private int targetId;
//	private PaperNews paperNews;
	private int status;
	private User fromUser;
//	private int deleteId;
	private long deleteTime;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="from_username")
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	@Column(name="release_time")
	public long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(name="target_type")
	public int getTargetType() {
		return targetType;
	}
	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "from_userId")
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	@Column(name="delete_time")
	public long getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(long deleteTime) {
		this.deleteTime = deleteTime;
	}
	/*@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "newsId")
	public PaperNews getPaperNews() {
		return paperNews;
	}
	public void setPaperNews(PaperNews news) {
		this.paperNews = news;
	}*/
	
	@Transient
	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}
	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}
}
