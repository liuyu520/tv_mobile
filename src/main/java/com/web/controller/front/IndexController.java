package com.web.controller.front;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oa.entity.common.CommonDictionary;
import oa.service.DictionaryParam;
import oa.web.controller.base.BaseController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.dao.CarouselDiagramDao;
import com.dao.PaperNewsDao;
import com.dao.PicNewsOneDao;
import com.dao.TVVideoDao;
import com.dict.Constant2;
import com.entity.CarouselDiagram;
import com.entity.PaperNews;
import com.entity.TVVideo;
import com.io.hw.file.util.FileUtils;
import com.string.widget.util.ValueWidget;
import com.util.JSONPUtil;
import com.util.TVUtils;
/***
 * 
 * @author huangwei
 * @since 2015年3月17日
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController{
	private CarouselDiagramDao carouselDiagramDao;
	private PaperNewsDao paperNewsDao;
	private TVVideoDao tVVideoDao;
	private PicNewsOneDao picNewsOneDao;
	/***
	 * 首页
	 * @param model
	 * @param request
	 * @param session
	 * @param targetView
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(Model model,HttpServletRequest request,HttpSession session,String targetView){
		
		//查询轮播图
		List<CarouselDiagram> carouselDiagrams=this.carouselDiagramDao.getAll();
		int carouselDiagramSize=carouselDiagrams.size();
		for(int i=0;i<carouselDiagramSize;i++){
			CarouselDiagram carouselDiagram=carouselDiagrams.get(i);
			String carPicPath=carouselDiagram.getPicPath();
			carouselDiagram.setPicPath(JSONPUtil.getPicPath(carPicPath));
		}
		model.addAttribute("carouselDiagrams", carouselDiagrams);
		
		//获取前n个点播
		Map conditionTV=new HashMap();
		conditionTV.put("status", Constant2.STATUS_ACTIVE);
		conditionTV.put("type", Constant2.VIDEO_TYPE_BROADCAST);//点播
		List<TVVideo>tVVideos=this.tVVideoDao.getFrontRecords(conditionTV, 4);
		JSONPUtil.formatTVVideo(tVVideos);
		model.addAttribute("tVVideos", tVVideos);
		
		//获取前4个图文
		List<Object[]> picNewsOnes=this.picNewsOneDao.getFrontPicNewsOnes(4);
		int picNewsSize=picNewsOnes.size();
		for(int i=0;i<picNewsSize;i++){
			Object[] objArr=picNewsOnes.get(i);
			objArr[0]=JSONPUtil.getPicPath((String)objArr[0]);
		}
		model.addAttribute("picNewsOnes", picNewsOnes);
		
		//头条关注
		List<PaperNews> paperNewsList_Recommend=this.paperNewsDao.getRecommendNews(5);
		JSONPUtil.formatNews(paperNewsList_Recommend);
		model.addAttribute("paperNewsList_Recommend", paperNewsList_Recommend);
		
		//专题报道
		List<PaperNews> paperNewsList_492=getNewsBySort(492, 6);
		JSONPUtil.formatNews(paperNewsList_492);
		model.addAttribute("paperNewsList_492", paperNewsList_492);

		//真相调查
		List<PaperNews> paperNewsList_494=getNewsBySort(494, 5);
		JSONPUtil.formatNews(paperNewsList_494);
		model.addAttribute("paperNewsList_494", paperNewsList_494);
		
		//本地新闻
		List<PaperNews> paperNewsList_491=getNewsBySort(491, 5);
		JSONPUtil.formatNews(paperNewsList_491);
		model.addAttribute("paperNewsList_491", paperNewsList_491);
		
		//报料
		List<PaperNews> paperNewsList_1=paperNewsDao.getNewsBySort(1,SystemHWUtil.NEGATIVE_ONE, 5);
		JSONPUtil.formatNews(paperNewsList_1);
		model.addAttribute("paperNewsList_1", paperNewsList_1);
		
		List<CommonDictionary> commonDictionaries_news=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_NEWS_SORT_GROUP);
		model.addAttribute("commonDictionaries_news", commonDictionaries_news);
		
		List<CommonDictionary> commonDictionaries_gov=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_government_GROUP);
		model.addAttribute("commonDictionaries_gov", commonDictionaries_gov);
		
		List<CommonDictionary> commonDictionaries_people=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_PEOPLE_LIVELIHOOD_GROUP);
		model.addAttribute("commonDictionaries_people", commonDictionaries_people);
		
		List<CommonDictionary> commonDictionaries_jl=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_JL_GROUP);
		model.addAttribute("commonDictionaries_jl", commonDictionaries_jl);
		
		//点播
		List<CommonDictionary> commonDictionaries_broadcast=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_BROADCAST_GROUP);
		//返回:{"3":"足球","2":"体育","1":"娱乐","7":"篮球2","6":"篮球","5":"NBA22","4":"NBA","9":"篮球231","8":"篮球23"}
		model.addAttribute("commonDictionaries_broadcast", commonDictionaries_broadcast);
		
		List<PaperNews> paperNewsList_gov=paperNewsDao.getNewsBySort(Constant2.TYPE_GOVERNMENT, -1, 3);
		JSONPUtil.formatNews(paperNewsList_gov);
		model.addAttribute("paperNewsList_gov", paperNewsList_gov);
		
		List<PaperNews> paperNewsList_people=paperNewsDao.getNewsBySort(Constant2.TYPE_PEOPLE, -1, 3);
		JSONPUtil.formatNews(paperNewsList_people);
		model.addAttribute("paperNewsList_people", paperNewsList_people);
		
		List<PaperNews> paperNewsList_jl=paperNewsDao.getNewsBySort(Constant2.TYPE_JIANLI, -1, 3);
		JSONPUtil.formatNews(paperNewsList_jl);
		model.addAttribute("paperNewsList_jl", paperNewsList_jl);
		
		List<PaperNews> paperNewsList_business=paperNewsDao.getNewsBySort(Constant2.TYPE_BUSINESS, -1, 3);
		JSONPUtil.formatNews(paperNewsList_business);
		model.addAttribute("paperNewsList_business", paperNewsList_business);
		
		List<PaperNews> paperNewsList_tip=paperNewsDao.getNewsBySort(Constant2.TYPE_TIPS, -1, 3);
		JSONPUtil.formatNews(paperNewsList_tip);
		model.addAttribute("paperNewsList_tip", paperNewsList_tip);
		
		//获取前n个直播
		Map conditionLive=new HashMap();
		conditionLive.put("status", Constant2.STATUS_ACTIVE);
		conditionLive.put("type", Constant2.VIDEO_TYPE_LIVES_TV);//直播
		List<TVVideo>tVVideosLive=this.tVVideoDao.getFrontRecords(conditionLive, 5);
		JSONPUtil.formatTVVideo(tVVideos);
		model.addAttribute("tVVideosLive", tVVideosLive);
				
		if(!ValueWidget.isNullOrEmpty(targetView)){
			return targetView;
		}
		return "qinli/index";
	}
	private List<PaperNews> getNewsBySort(int sort,int frontNum){
		return this.paperNewsDao.getNewsBySort(Constant2.TYPE_NEWS, sort, frontNum);
	}
	
	/***
	 * favicon.ico 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/favicon.ico")
	public ResponseEntity<byte[]> faviconIco(HttpServletRequest request) throws IOException {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        String faviconIcoName="sms-4.ico";
        headers.set(Constant2.CONTENT_DISPOSITION,WebServletUtil.getContentDisposition(true, faviconIcoName));
        ///home/whuang/software/apache-tomcat-7.0.53/webapps/ROOT/
        String webappPath=null;
        if(WebServletUtil.isLocalIp(request)){//服务器在本机(访问ip为127或localhost)
        	webappPath=WebServletUtil.getRealPath(request);
        }else{
        	webappPath=DictionaryParam.get(Constant2.DICTIONARY_GROUP_GLOBAL_SETTING, "WEB-INF_LOC");
        }
        return new ResponseEntity<byte[]>(FileUtils.getBytes4File(
        		webappPath
        		+"WEB-INF/static/img/"+faviconIcoName),
                                          headers, HttpStatus.CREATED);

	}

	public CarouselDiagramDao getCarouselDiagramDao() {
		return carouselDiagramDao;
	}
	@Resource
	public void setCarouselDiagramDao(CarouselDiagramDao carouselDiagramDao) {
		this.carouselDiagramDao = carouselDiagramDao;
	}
	public PaperNewsDao getPaperNewsDao() {
		return paperNewsDao;
	}
	@Resource
	public void setPaperNewsDao(PaperNewsDao paperNewsDao) {
		this.paperNewsDao = paperNewsDao;
	}


	public TVVideoDao gettVVideoDao() {
		return tVVideoDao;
	}

	@Resource
	public void settVVideoDao(TVVideoDao tVVideoDao) {
		this.tVVideoDao = tVVideoDao;
	}


	public PicNewsOneDao getPicNewsOneDao() {
		return picNewsOneDao;
	}


	@Resource
	public void setPicNewsOneDao(PicNewsOneDao picNewsOneDao) {
		this.picNewsOneDao = picNewsOneDao;
	}



	@Override
	protected void beforeAddInput(Model model) {
	}



	@Override
	protected void errorDeal(Model model) {
	}



	@Override
	public String getJspFolder() {
		return null;
	}
	
	
}
