package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 轮播图
 * @author huangwei
 * @since 2014年9月13日
 */
@Entity
@Table(name = "t_carousel_diagram")
public class CarouselDiagram  implements Cloneable,Serializable{
	private static final long serialVersionUID = -5241102799643317754L;
	private int id;
	/***
	 * 图片的路径
	 */
	private String picPath;
	/***
	 * 上传日期
	 */
	private java.sql.Timestamp uploadTime;
	private String desc;
	/***
	 * 
	 */
	private int status;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="pic_path")
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public CarouselDiagram clone()throws CloneNotSupportedException{
		return (CarouselDiagram)super.clone();
	}
	@Column(name="upload_time")
	public java.sql.Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(java.sql.Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(name="desc2")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
