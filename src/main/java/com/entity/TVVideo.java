package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonFilter;

import com.dict.Constant2;
/***
 * 
 * @author huangwei
 * @since 2015年3月2日
 */
@Entity
@Table(name = "t_video")
@JsonFilter(Constant2.SIMPLEFILTER_JACKSON_BROADCAST)/*json序列化时要排除指定属性*/
public class TVVideo {
	private int id;
	/***
	 * 栏目
	 */
	private Integer columnType;
	/***
	 * 视频标题
	 */
	private String title;
	/***
	 * 标题图片
	 */
	private String titlePic;
	private String url;
	private String path;
	private String videoformat;
	private String content;
	/***
	 * 1:点播;2:直播TV,3:直播广播
	 */
	private int type;
	private int status;
	private Admin releaseAdmin;
	private Admin deleteAdmin;
	private Long releaseTime;
	private Long deleteTime;
	private String reserved;
	private String releaseTimeStr;
	/***
	 * 点播视频的大小
	 */
	private String broadcastSize;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="column_type")
	public Integer getColumnType() {
		return columnType;
	}
	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVideoformat() {
		return videoformat;
	}
	public void setVideoformat(String videoformat) {
		this.videoformat = videoformat;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	
	@Column(name="title_pic")
	public String getTitlePic() {
		return titlePic;
	}
	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}
	@Transient
	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}
	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}
	@Transient
	public String getBroadcastSize() {
		return broadcastSize;
	}
	public void setBroadcastSize(String broadcastSize) {
		this.broadcastSize = broadcastSize;
	}
	
}
