package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***
 * 客户端接收到的推送消息
 * @author huangweii
 * 2015年6月20日
 */
@Entity
@Table(name = "t_received_push_message")
public class ReceivedPushMessage {
	/***
	 * 数据库ID
	 */
	private int id;
	/***
	 * 推送给哪个用户
	 */
	private User toUser;
	/***
	 * 用户的登录名,冗余字段.为了方便显示登录名,而不必重新再查数据库
	 */
	private String toUsername;
	/***
	 * 推送消息标题
	 */
	private String pushTitle;
	/***
	 * 推送消息内容
	 */
	private String pushContent;
	/***
	 * 接收到推送消息的日期,格式:yyyy-MM-dd HH:mm:ss
	 */
	private String receivedTime;
	
	/***
	 * 是谁发起推送的
	 */
	private int fromUid;
	private String  reserved;
	private PushDevice pushDevice;
	
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
	
	
	/*@Column(name="bulk_or_point")
	public String getBulkOrPoint() {
		return bulkOrPoint;
	}
	public void setBulkOrPoint(String bulkOrPoint) {
		this.bulkOrPoint = bulkOrPoint;
	}*/
	@Column(name="fromuid")
	public int getFromUid() {
		return fromUid;
	}
	@Column(name="received_time")
	public String getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
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
	@Column(name="to_username")
	public String getToUsername() {
		return toUsername;
	}
	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}
	@OneToOne
	@JoinColumn(name="push_device_id")
	public PushDevice getPushDevice() {
		return pushDevice;
	}
	public void setPushDevice(PushDevice pushDevice) {
		this.pushDevice = pushDevice;
	}
	
}
