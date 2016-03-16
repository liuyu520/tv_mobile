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

import org.codehaus.jackson.map.annotate.JsonFilter;

import com.dict.Constant2;
@Entity
@Table(name = "t_news")
@JsonFilter(Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS)/*json序列化时要排除指定属性*/
public class PaperNews {
	private int id;
	/***
	 * 新闻还是报料
	 */
	private int type;
	/***
	 * 栏目
	 */
	private Integer sort;
	/***
	 * 新闻标题
	 */
	private String title;
	/***
	 * 标题图片
	 */
	private String pic;
	private String content;
	private String keyword;
	/***
	 * 0:不置顶,1:置顶,2:推荐(1的优先级大于2),默认值为0
	 */
	private int sticktop;
	/***\
	 * 0:审核中;1:激活,2:失效(不显示)
	 */
	private int status;
	private Admin releaseAdmin;
	private Admin deleteAdmin;
	private Long releaseTime;
	private Long deleteTime;
	private String reserved;
//	private Set<NewsComment> newsCommentSet;
	private long stickTime;
	/**
	 * 添加报料的用户
	 */
	private Integer userId;
	/***
	 * 联系人
	 */
	private String contacts;
	/***
	 * 联系方式
	 */
	private String contactWay;
	/***
	 * 评论的总数(status==1)
	 */
	private long commentSum;
	private String releaseTimeStr;
	private String splitAndFilterString;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getSticktop() {
		return sticktop;
	}
	public void setSticktop(int sticktop) {
		this.sticktop = sticktop;
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
	/*@OneToMany(mappedBy="paperNews",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@Where(clause = "status="+Constant2.STATUS_ACTIVE)
	public Set<NewsComment> getNewsCommentSet() {
		return newsCommentSet;
	}
	public void setNewsCommentSet(Set<NewsComment> newsCommentSet) {
		this.newsCommentSet = newsCommentSet;
	}*/
	@Column(name="stick_time")
	public long getStickTime() {
		return stickTime;
	}
	public void setStickTime(long stickTime) {
		this.stickTime = stickTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(name="contact_way")
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	@Transient
	public long getCommentSum() {
		return commentSum;
	}
	public void setCommentSum(long commentSum) {
		this.commentSum = commentSum;
	}
	
	@Transient
	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}
	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}
	@Transient
	public String getSplitAndFilterString() {
		return splitAndFilterString;
	}
	public void setSplitAndFilterString(String splitAndFilterString) {
		this.splitAndFilterString = splitAndFilterString;
	}
	
}
