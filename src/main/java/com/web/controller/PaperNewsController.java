package com.web.controller;

import com.bean.news.NewsDetail;
import com.bean.news.NewsListItem;
import com.common.util.PageUtil;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.common.web.view.PageView;
import com.dao.NewsCommentDao;
import com.dict.Constant2;
import com.entity.Admin;
import com.entity.NewsComment;
import com.entity.PaperNews;
import com.entity.User;
import com.io.hw.file.util.FileUtils;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.util.JSONPUtil;
import com.util.TVUtils;
import com.view.NewsCommentView;
import com.view.PaperNewsView;
import oa.entity.common.AccessLog;
import oa.entity.common.CommonDictionary;
import oa.service.DictionaryParam;
import oa.web.controller.base.BaseController;
import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/news")
public class PaperNewsController extends BaseController<PaperNews> {
	private String label = "news";
	private SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
			.serializeAllExcept("content","newsCommentSet");
	private SimpleFilterProvider simpleFilterProvider=new SimpleFilterProvider();
	private NewsCommentDao newsCommentDao;
	
	public static String getJsonP(Object map,String callback)
	{
		SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
				.serializeAllExcept((String)null);
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		return HWJacksonUtils.getJsonP(map, callback, filters);
	}

	private static String getTypeTitle(Integer type, String title) {
		switch (type) {
			case Constant2.TYPE_NEWS:
				title = "新闻";
				break;
			case Constant2.TYPE_TIPS:
				title = "报料";
				break;
			case Constant2.TYPE_JIANLI:
				title = "监利";
				break;
			case Constant2.TYPE_BUSINESS:
				title = "商讯";
				break;
			case Constant2.TYPE_GOVERNMENT:
				title = "政务";
				break;
			case Constant2.TYPE_PEOPLE:
				title = "民生";
				break;
			default:
				break;
		}
		return title;
	}

	@ResponseBody
	@RequestMapping(value = "/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model, Integer type, Integer status,Integer sort,Integer userId,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		Map map = queryNewsList(type, status, sort, userId, view, request);
		/*
		 * List data = view.getRecordList();
		 * map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		 * map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		 * map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		 * map.put(Constant2.JSON_RETURN_OVER, view.getCurrentPage() >=
		 * view.getTotalPages()); map.put("recordList", data);
		 */
		int length2=view.getRecordList().size();
		for(int i=0;i<length2;i++){
			PaperNews paperNews=(PaperNews) view.getRecordList().get(i);
			String picPath=paperNews.getPic();
			if(!ValueWidget.isNullOrEmpty(picPath)){
				paperNews.setPic(JSONPUtil.getPicPath(picPath));
			}
		}
		setJsonPaging(map, view);

		String content;

		FilterProvider filters = simpleFilterProvider.addFilter(
				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);

		AccessLog accessLog=logInto(request);
		String title=null;
		if(type==null){//default value
			type=Constant2.TYPE_NEWS;
		}
		title = getTypeTitle(type, title);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			accessLog.setDescription("手机端"+title+"列表");
			accessLog.setOperateResult("总条数:"+view.getTotalRecords());
			logSave(accessLog, request);
		}

		content = HWJacksonUtils.getJsonP(map, callback, filters);
		return content;
	}

	public NewsListItem parsePaperNews(PaperNews paperNews) {
		NewsListItem newsListItem = new NewsListItem();
		newsListItem.setDate(paperNews.getReleaseTimeStr());
		newsListItem.setTitle(paperNews.getTitle());
		newsListItem.setNewsType(String.valueOf(paperNews.getSort()));
		newsListItem.setUrl("/app/json_detail/" + paperNews.getId());
		return newsListItem;
	}

	@ResponseBody
	@RequestMapping(value = "/list/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String listJson(Model model, Integer type, Integer status, Integer sort, Integer userId,
						   PaperNewsView view, HttpSession session,
						   HttpServletRequest request, String callback) throws IOException {
		Map map = queryNewsList(type, status, sort, userId, view, request);


		setJsonPaging(map, view);
		List<NewsListItem> newsListItems = new ArrayList<NewsListItem>();
		int length2 = view.getRecordList().size();
		for (int i = 0; i < length2; i++) {
			PaperNews paperNews = (PaperNews) view.getRecordList().get(i);
			newsListItems.add(parsePaperNews(paperNews));
		}
		String content;
		FilterProvider filters = simpleFilterProvider.addFilter(
				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);

		AccessLog accessLog = logInto(request);
		String title = null;
		if (type == null) {//default value
			type = Constant2.TYPE_NEWS;
		}
		/*title = getTypeTitle(type, title);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			accessLog.setDescription("手机端"+title+"列表");
			accessLog.setOperateResult("总条数:"+view.getTotalRecords());
			logSave(accessLog, request);
		}*/

		content = HWJacksonUtils.getJsonP(newsListItems, callback, filters);
		return content;
	}

	private Map queryNewsList(Integer type, Integer status, Integer sort, Integer userId, PaperNewsView view, HttpServletRequest request) {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (type != null) {
			condition.put("type", type);
		}
		if (status != null) {
			condition.put("status", status);
		}
		if (sort != null) {
			condition.put("sort", sort);
		}
		if (userId != null) {//添加报料的用户
			condition.put("userId", userId);
		}
		ListOrderedMap orderColumnModeMap = getListOrderBy();

		PageUtil.paging(condition, view, getDao(), null, orderColumnModeMap);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/search", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonSearch(Model model, Integer status, String keyword,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (status != null) {
			condition.put("status", status);
		}
		/*
		 * if(!ValueWidget.isNullOrEmpty(title)){ condition.put("title", title);
		 * }
		 */
		String[] columns = new String[] { "title", "content", "keyword" };
		ListOrderedMap orderColumnModeMap=getListOrderBy();
		PageUtil.paging(condition, columns, keyword, view, getDao(), orderColumnModeMap);
		setJsonPaging(map, view);

		String content;
		FilterProvider filters = simpleFilterProvider.addFilter(
				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		content = HWJacksonUtils.getJsonP(map, callback,filters);
		return content;
	}

	@ResponseBody
	@RequestMapping(value = "/json_detail", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonDetail(Model model, int id/*新闻的id*/,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		PaperNews news=(PaperNews) getDao().get(id);
		String content;
		/*int comment_type=0;
		if(type==2){//新闻
			comment_type=Constant2.COMMENT_TARGET_TYPE_NEWS;
		}else if(type==1){
			
		}*/
		String title=null;
		if(news.getType()==Constant2.TYPE_NEWS){
			title="新闻";
		}else{
			title="报料";
		}
		long commentCount=this.newsCommentDao.getCount(null, id, 1);
		news.setCommentSum(commentCount);
		if(!ValueWidget.isNullOrEmpty(news.getPic())){
			news.setPic(JSONPUtil.getPicPath(news.getPic()));
		}
		content = getJsonP(news, callback);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端"+title+"详情,id:"+id);
		logSave(accessLog, request);
		return content;
	}

	@ResponseBody
	@RequestMapping(value = "/app/json_detail/{id}", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String appJsonDetail(Model model, @PathVariable int id/*新闻的id*/,
								PaperNewsView view, HttpSession session,
								HttpServletRequest request, String callback) throws IOException {
		init(request);
		PaperNews news = (PaperNews) getDao().get(id);
		String content;

		String title = null;
		if (news.getType() == Constant2.TYPE_NEWS) {
			title = "新闻";
		} else {
			title = "报料";
		}
		NewsDetail newsDetail = new NewsDetail();
		newsDetail.setId(news.getId());
		newsDetail.setNewsDetailsBody(news.getContent());
		newsDetail.setNewsDetailsTitle(news.getTitle());
		newsDetail.setNewsType(news.getSort());
		String serverUrl = request.getRequestURL().toString().replaceAll("(https?://[^/]+)/.*$", "$1") + request.getContextPath();
		newsDetail.setNewsDetailsUrl(serverUrl + "/news/app/json_detail/" + news.getId());
		newsDetail.setNewsDetailsCreateDate(TimeHWUtil.formatDateTime(news.getReleaseTime() * 1000));
		Admin admin = news.getReleaseAdmin();
		String author = null;
		if (null != admin) {
			author = admin.getUsername();
		} else {
			author = SystemHWUtil.EMPTY;
		}
		newsDetail.setNewsDetailsAuthor(author);
		return HWJacksonUtils.getJsonP(newsDetail);
	}
	@ResponseBody
	@RequestMapping(value = "/json_add_tips", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonAddTips(Model model, PaperNews paperNews,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		int login_result = 0;
		User user2 =null;
		String content;
		Map map=new HashMap();
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端保存报料");
		
		if(ValueWidget.isNullOrEmpty(paperNews)||ValueWidget.isNullOrEmpty(paperNews.getTitle())){
			login_result=Constant2.ADD_TIPS_RESULT_TITLE_EMPTY;//标题不能为空
			accessLog.setOperateResult("标题不能为空");
		}else if(ValueWidget.isNullOrEmpty((user2 = (User) session.getAttribute("user")))||ValueWidget.isNullOrEmpty(user2.getUsername())){
			login_result=Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//用户还没有登录
			accessLog.setOperateResult("用户还没有登录");
		}else{
			paperNews.setUserId(user2.getId());
			paperNews.setReleaseTime(TimeHWUtil.getCurrentTimeSecond());
			paperNews.setType(Constant2.TYPE_TIPS);
			paperNews.setStatus(Constant2.STATUS_ACTIVE);
			String imgBase64=paperNews.getPic();
			if(ValueWidget.isNullOrEmpty(imgBase64)){
				
			}else{
				try {
					byte[]picBytes=SystemHWUtil.decodeBase64(imgBase64);
					String finalFileName = TimeHWUtil.formatDateByPattern(TimeHWUtil
							.getCurrentTimestamp(),"yyyyMMddHHmmss")+ "_"
									+ new Random().nextInt(1000)+".jpg";
					String relativePath=Constant2.UPLOAD_FOLDER_NAME + "/image";
					File savedFile = WebServletUtil.getUploadedFilePath(request,relativePath
							, finalFileName,
							Constant2.SRC_MAIN_WEBAPP);// "D:\\software\\eclipse\\workspace2\\demo_channel_terminal\\ upload\\pic\\ys4-1.jpg"
					File parentFolder=SystemHWUtil.createParentFolder(savedFile);
					FileUtils.makeWritable(parentFolder);//使...可写
					FileUtils.writeBytesToFile(picBytes, savedFile);
					if(!relativePath.endsWith("/")){
						relativePath=relativePath+"/";
					}
					relativePath=relativePath+finalFileName;//upload/image/20150329170823_2122015-03-23_01-42-03.jpg
					String prefixPath=WebServletUtil.dealWithJsessionid(request.getRequestURL().toString()).replaceAll(request.getServletPath(), "");
					if(!prefixPath.endsWith("/")){
						prefixPath=prefixPath+"/";
					}
					paperNews.setPic(prefixPath+relativePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			beforeSave(paperNews, model);
			getDao().add(paperNews);
			login_result=Constant2.LOGIN_RESULT_SUCCESS;
			map.put("tips", paperNews);
		}
		
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		logSave(accessLog, request);
		content = getJsonP(map, callback);
		return content;
	}
	@Override
	protected void beforeAddInput(Model model) {

	}

	@Override
	protected void errorDeal(Model model) {

	}

	@Override
	public String getJspFolder() {
		return label;
	}

	public NewsCommentDao getNewsCommentDao() {
		return newsCommentDao;
	}

	@Resource
	public void setNewsCommentDao(NewsCommentDao newsCommentDao) {
		this.newsCommentDao = newsCommentDao;
	}

	@Override
	protected void listTODO(Model model,PageView view, HttpServletRequest request) {
		super.listTODO(model,view, request);
		System.out.println("新闻列表每页显示:"+view.getRecordsPerPage());
		List list=view.getRecordList();
		JSONPUtil.formatNews(list);
		System.out.println("截取长内容");
		String typeStr=request.getParameter("type");
		int type = Constant2.TYPE_NEWS;
		if(ValueWidget.isNullOrEmpty(typeStr)){
			PaperNews roleLevel=(PaperNews)request.getSession().getAttribute(getJspFolder());
			if(roleLevel==null||roleLevel.getType()==0){
				logger.error("type is null:"+request.getRequestURL());
				logger.error(view);
				return;
			}else{
				type=roleLevel.getType();
			}
		}else{
			type=Integer.parseInt(typeStr);
		}
		
		String groupId=null;
		switch (type) {
		case 2:
			groupId=Constant2.DICTIONARY_GROUPID_NEWS_SORT_GROUP;
			break;
		case 3:
			groupId=Constant2.DICTIONARY_GROUPID_JL_GROUP;
			break;
		case 4:
			groupId=Constant2.DICTIONARY_GROUPID_business_GROUP;
			break;
		case 5:
			groupId=Constant2.DICTIONARY_GROUPID_government_GROUP;
			break;
		case 6:
			groupId=Constant2.DICTIONARY_GROUPID_PEOPLE_LIVELIHOOD_GROUP;
			break;
		default:
			break;
		}
		List<CommonDictionary> commonDictionaries=DictionaryParam.getList(groupId);
		//返回:{"3":"足球","2":"体育","1":"娱乐","7":"篮球2","6":"篮球","5":"NBA22","4":"NBA","9":"篮球231","8":"篮球23"}
		model.addAttribute("commonDictionaries", commonDictionaries);
		model.addAttribute("news_type", type);
		
	}

	@Override
	protected PaperNews detailTODO(int id,  Model model,
			HttpServletRequest request) {
		PaperNews paperNews=super.detailTODO(id,  model, request);
		paperNews.setReleaseTimeStr(TimeHWUtil.formatSecondTime(paperNews.getReleaseTime()));
		String titlePic=paperNews.getPic();
		if(!ValueWidget.isNullOrEmpty(titlePic)){
			paperNews.setPic(JSONPUtil.getPicPath(titlePic));
		}
		
		paperNews.setSplitAndFilterString(SystemHWUtil.splitAndFilterString(paperNews.getContent(), 120/*TODO */));
		
		
		Map condition=new HashMap();
		NewsCommentView newsCommentView=new NewsCommentView();
		condition.put("targetId", id);
		condition.put("status", Constant2.STATUS_ACTIVE);
		
		NewsComment newsComment2=new NewsComment();
		newsComment2.setTargetId(id);
		newsComment2.setStatus(Constant2.STATUS_ACTIVE);
		request.getSession().setAttribute("comment", newsComment2);
		
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("releaseTime", "desc");
		PageUtil.paging(condition, newsCommentView, this.newsCommentDao,null,orderColumnModeMap);
		try {
			TimeHWUtil.formatTime(newsCommentView.getRecordList(), "releaseTime", "releaseTimeStr");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		model.addAttribute("view", newsCommentView);
		return paperNews;
	}
	/***
	 * 电脑端增加报料
	 */
	@RequestMapping(value = "/add_front")
	public String addInput(String practiceWay, Model model,HttpServletRequest request,String targetView) {
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("电脑端添加报料页面");
		logSave(accessLog, request);
		if(!ValueWidget.isNullOrEmpty(targetView)){
			return targetView;
		}
		return getJspFolder2()+"/add";
	}
	@Override
	public ListOrderedMap getListOrderBy() {
//		orderColumnModeMap.put("releaseTime", "desc");
		return TVUtils.getListOrderedMap();
	}

	@Override
	protected void beforeSave(PaperNews roleLevel, Model model) {
		super.beforeSave(roleLevel, model);
		String picPath=roleLevel.getPic();
		String webInf="WEB-INF/static/img/";
		if(!ValueWidget.isNullOrEmpty(picPath)&&picPath.startsWith(webInf)){
			picPath=picPath.replaceAll(webInf, SystemHWUtil.EMPTY);
			roleLevel.setPic(picPath);
		}
		
	}
	protected String getListView(){
		return "/index";
	}
	protected void setRecordsPerPageBeforeQuery(PageView view) {
		view.setRecordsPerPage(9);
	}

	@Override
	protected void beforeList(PaperNews roleLevel) {
		roleLevel.setStatus(Constant2.NEWS_STATUS_ON);//额外的条件
		super.beforeList(roleLevel);

		/*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		AccessLog accessLog = logInto(request);
		accessLog.setDescription("list test");
		accessLog.setOperateResult("list test conditon:" + HWJacksonUtils.getJsonP(roleLevel));
		logSave(accessLog, request);*/
	}
}
