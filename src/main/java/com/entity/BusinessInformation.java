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
/***
 * 
 * @author huangwei
 * @since 2015年3月5日
 */
@Entity
@Table(name = "t_business_information")
public class BusinessInformation {
	private int id;
	/***
	 * 栏目
	 */
	private Integer type;
	/***
	 * 商讯标题
	 */
	private String title;
	/***
	 * 标题图片
	 */
	private String pic;
	/***
	 * 跳转到其他网站的外链
	 */
	private String link;
	/***
	 * 外链网站名称
	 */
	private String fromWebsit;
	/***
	 * 0:审核中;1:激活, 2,审核不通过,3:失效(不显示)
	 */
	private int status;
	private Admin releaseAdmin;
	private Admin deleteAdmin;
	private Long releaseTime;
	private Long deleteTime;
	private String reserved;
	private String releaseTimeStr;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Column(name="from_websit")
	public String getFromWebsit() {
		return fromWebsit;
	}
	public void setFromWebsit(String fromWebsit) {
		this.fromWebsit = fromWebsit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "releaseId")
	public Admin getReleaseAdmin() {
		return releaseAdmin;
	}
	public void setReleaseAdmin(Admin releaseAdmin) {
		this.releaseAdmin = releaseAdmin;
	}
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "deleteId")
	public Admin getDeleteAdmin() {
		return deleteAdmin;
	}
	public void setDeleteAdmin(Admin deleteAdmin) {
		this.deleteAdmin = deleteAdmin;
	}
	@Column(name="release_time")
	public Long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Long releaseTime) {
		this.releaseTime = releaseTime;
	}
	@Column(name="delete_time")
	public Long getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Long deleteTime) {
		this.deleteTime = deleteTime;
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
	
}
