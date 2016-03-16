package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.common.util.SystemHWUtil;
import com.dict.Constant2;
import com.entity.PaperNews;
import com.util.TVUtils;
@Component
public class PaperNewsDao extends GenericDao<PaperNews> {
	public List<PaperNews> getFrontPaperNews(Map conditonObj,int front){
		List<PaperNews> paperNewsList=super.find(conditonObj, false, 0, front, "title", TVUtils.getListOrderedMap());
		return paperNewsList;
	}
	public List<PaperNews> getRecommendNews(int front){
		Map conditionNews=new HashMap();
		conditionNews.put("status", Constant2.STATUS_ACTIVE);
		conditionNews.put("type", Constant2.TYPE_NEWS);//Constant2.TYPE_NEWS
		return getFrontPaperNews(conditionNews, front);
	}

	public List<PaperNews> getNewsBySort(int type,int sort,int frontNum){
		Map conditionNews=new HashMap();
		conditionNews.put("status", Constant2.STATUS_ACTIVE);
		conditionNews.put("type", type);//Constant2.TYPE_NEWS
		if(sort!=SystemHWUtil.NEGATIVE_ONE){
			conditionNews.put("sort", sort);
		}
		List<PaperNews> paperNewsList=getFrontPaperNews(conditionNews, frontNum);
		return paperNewsList;
	}
}
