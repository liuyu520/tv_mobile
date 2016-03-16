package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/***
 * 
 * @author huangwei
 * @since 2015年3月12日
 */
@Entity
@Table(name = "t_pic_news_one")
@JsonIgnoreProperties(value = { "picNews" })
public class PicNewsOne {
	private int id;
	/***
	 * 图片的路径:不要包含项目名
	 */
	private String picPath;
	private String desc2 ;
	/***
	 * 图片上传时间
	 */
	private long uploadTime;
	/***
	 * 属于哪个图文
	 */
	private PicNews picNews;
	/***\
	 * 0:审核中;1:激活,2:失效(不显示)
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
	
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "pic_news_id")
	public PicNews getPicNews() {
		return picNews;
	}
	public void setPicNews(PicNews picNews) {
		this.picNews = picNews;
	}
	@Column(name="pic_path")
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Column(name="upload_time")
	public long getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(long uploadTime) {
		this.uploadTime = uploadTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PicNewsOne [id=" + id + ", picPath=" + picPath + ", desc2="
				+ desc2 + "]";
	}
	
}
