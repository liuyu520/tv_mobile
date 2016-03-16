package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonFilter;

import com.dict.Constant2;
/***
 * 报料
 * @author huangwei
 * @since 2015年3月12日
 */
//@Entity
//@Table(name = "t_tip_off")
//@JsonFilter(Constant2.SIMPLEFILTER_JACKSON_TIPOFF)/*json序列化时要排除指定属性*/
public class TipOff {
	private int id;
	private String title;
	private String pic;
	private String content;
	private int status;
	private Admin releaseAdmin;
	private Admin deleteAdmin;
	private Long releaseTime;
	private Long deleteTime;
	private String reserved;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
}
