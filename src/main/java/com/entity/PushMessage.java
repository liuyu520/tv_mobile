package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * 
 * @author huangweii
 * 2015年6月20日
 */
@Entity
@Table(name = "t_push_message")
public class PushMessage {
	/***
	 * 数据库ID
	 */
	private int id;
	/***
	 * 推送给哪个用户
	 */
	private User toUser;
	/***
	 * 推送消息标题
	 */
	private String pushTitle;
	/***
	 * 推送消息内容
	 */
	private String pushContent;
	/***
	 * 推送日期,格式:yyyy-MM-dd HH:mm:ss
	 */
	private String pushTime;
	/***
	 * 是否是定点推送,取值bulk,point
	 */
	private String bulkOrPoint;
	/***
	 * 是谁发起推送的
	 */
	private int fromUid;
	private String  reserved;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="to_userId")
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	@Column(name="push_title")
	public String getPushTitle() {
		return pushTitle;
	}
	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}
	@Column(name="push_content")
	public String getPushContent() {
		return pushContent;
	}
	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}
	@Column(name="push_time")
	public String getPushTime() {
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	@Column(name="bulk_or_point")
	public String getBulkOrPoint() {
		return bulkOrPoint;
	}
	public void setBulkOrPoint(String bulkOrPoint) {
		this.bulkOrPoint = bulkOrPoint;
	}
	@Column(name="fromuid")
	public int getFromUid() {
		return fromUid;
	}
	public void setFromUid(int fromUid) {
		this.fromUid = fromUid;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
}
