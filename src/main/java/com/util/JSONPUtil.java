package com.util;

import java.util.List;
import java.util.Map;

import oa.service.DictionaryParam;

import org.codehaus.jackson.map.ObjectMapper;

import com.common.util.SystemHWUtil;
import com.entity.BBS;
import com.entity.BusinessInformation;
import com.entity.PaperNews;
import com.entity.TVVideo;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;

public class JSONPUtil extends HWJacksonUtils {
	
	
	/***
	 * 1,格式化日期,<br>
	 * 2,截断新闻主体
	 * @param paperNewsList
	 */
	public static void formatNews(List<PaperNews>paperNewsList){
		int size=paperNewsList.size();
		for (int i = 0; i <size; i++) {
			PaperNews paperNews=paperNewsList.get(i);
			paperNews.setReleaseTimeStr(TimeHWUtil.formatSecondTime(paperNews.getReleaseTime()));
			String content=paperNews.getContent();
			paperNews.setContent(SystemHWUtil.splitAndFilterString(content, DictionaryParam.getInt("news_settings", "content_max")));
			String picPath=paperNews.getPic();
			if(!ValueWidget.isNullOrEmpty(picPath)){
				paperNews.setPic(JSONPUtil.getPicPath(picPath));
			}
		}
	}
	
	public static void formatTVVideo(List<TVVideo>tVVideos){
		int size=tVVideos.size();
		for (int i = 0; i <size; i++) {
			TVVideo tVVideo=tVVideos.get(i);
			tVVideo.setReleaseTimeStr(TimeHWUtil.formatSecondTime(tVVideo.getReleaseTime()));
			String content=tVVideo.getContent();
			tVVideo.setTitlePic(getPicPath(tVVideo.getTitlePic()));
			tVVideo.setContent(SystemHWUtil.splitAndFilterString(content, DictionaryParam.getInt("broadcast_settings", "content_max")));
		}
	}
	public static void formatTimeBusinessInformation(List<BusinessInformation>businessInformations){
		int size=businessInformations.size();
		for (int i = 0; i <size; i++) {
			BusinessInformation businessInformation=businessInformations.get(i);
			businessInformation.setReleaseTimeStr(TimeHWUtil.formatSecondTime(businessInformation.getReleaseTime()));
		}
	}
	public static void formatBBS(List<BBS>businessInformations){
		int size=businessInformations.size();
		for (int i = 0; i <size; i++) {
			BBS businessInformation=businessInformations.get(i);
			businessInformation.setReleaseTimeStr(TimeHWUtil.formatSecondTime(businessInformation.getReleaseTime()));
			String content=businessInformation.getCardcontent();
			businessInformation.setCardcontent(SystemHWUtil.splitAndFilterString(content, DictionaryParam.getInt("bbs_settings", "content_max")));
		}
	}
	
	
	public static String getPicPath(String carPicPath){
		String prefix=DictionaryParam.get("global_settings", "pic_prefix");
		if(!carPicPath.startsWith("http://")){
			carPicPath=carPicPath.replaceAll("^\\.", "");
			if(!carPicPath.startsWith("/") && !prefix.endsWith("/")){
				carPicPath="/"+carPicPath;
			}
			carPicPath=prefix+carPicPath;
		}
		return carPicPath;
	}
	public static String getVideoPath(String carPicPath){
		if(!carPicPath.startsWith("http://")){
			carPicPath=carPicPath.replaceAll("^\\.", "");
			if(!carPicPath.startsWith("/")){
				carPicPath="/"+carPicPath;
			}
			carPicPath=DictionaryParam.get("global_settings", "video_prefix")+carPicPath;
		}
		return carPicPath;
	}
	public static Map getMapFromJson(String jsonInput){
		ObjectMapper mapper = getObjectMapper();
		Map map=null;
		try {
			map = mapper.readValue(jsonInput, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}

