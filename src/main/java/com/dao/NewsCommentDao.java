package com.dao;

import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.entity.NewsComment;
@Component
public class NewsCommentDao extends GenericDao<NewsComment>{
	/***
	 * 
	 * @param targetType : 1:新闻;2:报料;3:点播
	 * @param targetId
	 * @param status
	 * @return
	 */
	public long getCount(Integer targetType,Integer targetId,Integer status){
		NewsComment newsComment=new NewsComment();
		newsComment.setTargetId(targetId);
		if(targetType!=null){
			newsComment.setTargetType(targetType);
		}
		if(status!=null){
			newsComment.setStatus(status);
		}
		return count(newsComment, false, false);
	}
}
