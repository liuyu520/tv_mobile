package com.dao.mob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.common.util.SystemHWUtil;
import com.dict.Constant2;
import com.entity.mob.Blog;
import com.util.TVUtils;
@Component
public class BlogDao extends GenericDao<Blog> {
	public List<Blog> getFrontPaperNews(Map conditonObj,int front){
		List<Blog> paperNewsList=super.find(conditonObj, false, 0, front, "title", TVUtils.getListOrderedMap());
		return paperNewsList;
	}
	public List<Blog> getRecommendNews(int front){
		Map conditionNews=new HashMap();
		conditionNews.put("status", Constant2.STATUS_ACTIVE);
		conditionNews.put("type", Constant2.TYPE_BLOG);//Constant2.TYPE_NEWS
		return getFrontPaperNews(conditionNews, front);
	}

	public List<Blog> getNewsBySort(int type,int sort,int frontNum){
		Map conditionNews=new HashMap();
		conditionNews.put("status", Constant2.STATUS_ACTIVE);
		conditionNews.put("type", type);//Constant2.TYPE_NEWS
		if(sort!=SystemHWUtil.NEGATIVE_ONE){
			conditionNews.put("sort", sort);
		}
		List<Blog> paperNewsList=getFrontPaperNews(conditionNews, frontNum);
		return paperNewsList;
	}
}
