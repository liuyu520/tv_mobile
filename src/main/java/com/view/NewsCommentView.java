package com.view;

import com.common.web.view.PageView;
/***
 * 
 * @author huangwei
 * @since 2015年3月10日
 */
public class NewsCommentView extends PageView {
	private int id;
	/***
	 * 1:新闻;2:评论;
	 */
	private int targetType;
	/***
	 * 评论的对象(新闻或评论)
	 */
	private int targetId;
	private String comments;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
