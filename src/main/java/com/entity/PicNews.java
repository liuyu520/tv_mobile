package com.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.dict.Constant2;

/***
 * 图文,对应多个PicNewsOne
 * @author huangwei
 * @since 2015年3月12日
 */
@Entity
@Table(name = "t_pic_news")
@JsonIgnoreProperties(value = { "picNewsOnes" })
public class PicNews {
	private int id;
	/***
	 * 标题
	 */
	private String title;
	private long uploadTime;
	private Set<PicNewsOne> picNewsOnes;
	private List<PicNewsOne> picNewsOneList;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="upload_time")
	public long getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(long uploadTime) {
		this.uploadTime = uploadTime;
	}
	@OneToMany(mappedBy="picNews",
			/*cascade={CascadeType.ALL},*/
			fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Where(clause = "status="+Constant2.STATUS_ACTIVE)
	public Set<PicNewsOne> getPicNewsOnes() {
		return picNewsOnes;
	}
	public void setPicNewsOnes(Set<PicNewsOne> picNewsOnes) {
		this.picNewsOnes = picNewsOnes;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Transient
	public List<PicNewsOne> getPicNewsOneList() {
		return picNewsOneList;
	}
	public void setPicNewsOneList(List<PicNewsOne> picNewsOneList) {
		this.picNewsOneList = picNewsOneList;
	}
	
}
