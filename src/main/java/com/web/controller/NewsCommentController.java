package com.web.controller;

import com.common.util.PageUtil;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.common.web.view.PageView;
import com.dao.PaperNewsDao;
import com.dict.Constant2;
import com.entity.NewsComment;
import com.entity.PaperNews;
import com.entity.User;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.view.NewsCommentView;
import oa.entity.common.AccessLog;
import oa.util.AuthenticateUtil;
import oa.web.controller.base.BaseController;
import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class NewsCommentController extends BaseController<NewsComment> {
	private String label = "comment";
	private PaperNewsDao paperNewsDao;
	private SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
			.serializeAllExcept("content","newsCommentSet");
	private SimpleFilterProvider simpleFilterProvider=new SimpleFilterProvider();

	/***
	 * 主要的查询条件有:targetType,targetId,status
	 * @param model
	 * @param targetType
	 * @param newsId
	 * @param status
	 * @param view
	 * @param session
	 * @param paperNews
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model, Integer targetType,Integer newsId, Integer status,
			NewsCommentView view, HttpSession session,NewsComment newsComment,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		Map map = new HashMap();
		/*Map condition = new HashMap();
		if (targetType != null) {
			condition.put("targetType", targetType);
		}
		if (status != null) {
			condition.put("status", status);
		}
		if (newsId != null) {
			condition.put("newsId", newsId);
		}*/
		PageUtil.paging(newsComment,false, view, getDao(), "desc", "releaseTime", null,
				null, null);
		/*
		 * List data = view.getRecordList();
		 * map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		 * map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		 * map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		 * map.put(Constant2.JSON_RETURN_OVER, view.getCurrentPage() >=
		 * view.getTotalPages()); map.put("recordList", data);
		 */
		setJsonPaging(map, view);

		String content;
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端评论列表");
		accessLog.setOperateResult(String.format(Constant2.RECORD_TOTAL_SUM, view.getTotalRecords()));
		logSave(accessLog, request);
//		FilterProvider filters = simpleFilterProvider.addFilter(
//				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		content = HWJacksonUtils.getJsonP(map, callback);
		return content;
	}
	

	@ResponseBody
	@RequestMapping(value = "/search", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonSearch(Model model, Integer status, String keyword,
			NewsCommentView view, HttpSession session,
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
		PageUtil.paging(condition, columns, keyword, view, getDao(), "desc",
				"releaseTime", null, null);
		setJsonPaging(map, view);

		String content;
		content = HWJacksonUtils.getJsonP(map, callback);
		return content;
	}

	@ResponseBody
	@RequestMapping(value = "/json_detail", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonDetail(Model model, int id/*新闻的id*/,
			NewsCommentView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		NewsComment news=(NewsComment) getDao().get(id);
		String content;
		content =HWJacksonUtils.getJsonP(news, callback);
		return content;
	}
	/***
	 * 添加评论
	 * @param model
	 * @param view
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/json_add", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonAdd(Model model,
			NewsCommentView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		User user2 = (User) session.getAttribute("user");
		Map map=new HashMap();
		int login_result = 0;
		String comments=null;
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端保存评论");
		
		if(!AuthenticateUtil.isLogined(session)){
			login_result=Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//还没有登录
			accessLog.setOperateResult("还没有登录");
		}else if(ValueWidget.isNullOrEmpty(comments=WebServletUtil.dealWithJsessionid(view.getComments()))){
			login_result=Constant2.ADD_BBS_REPLY_RESULT_FOLLOWCARDCONTENT_EMPTY;
			accessLog.setOperateResult("评论内容为空");
		}else{
			NewsComment comment=new NewsComment();//新建一个评论对象
			comment.setComments(comments);
			comment.setTargetId(view.getTargetId());
			comment.setTargetType(view.getTargetType());
			comment.setFromUser(user2);
			comment.setFromUsername(user2.getUsername());
			comment.setReleaseTime(TimeHWUtil.getCurrentTimeSecond());
			PaperNews news2=new PaperNews();
//			comment.setPaperNews(this.paperNewsDao.get(view.getId()));
			comment.setStatus(Constant2.STATUS_ACTIVE);
			init(request);
			/*Integer id=(Integer) */getDao().add(comment);//持久化到数据库
			System.out.println("id:"+comment.getId());
			login_result=Constant2.LOGIN_RESULT_SUCCESS;
			map.put("data", comment);
		}
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		String content;
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		content =HWJacksonUtils.getJsonP(map, callback,filters);
		logSave(accessLog, request);//持久化日志
		return content;
	}
	/***
	 * 1:新闻;2:报料
	 * @param model
	 * @param newsComment
	 * @param view
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/json_comment", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonComment(Model model,  NewsComment newsComment,
			NewsCommentView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		String content=null;
		PageUtil.paging(newsComment, false, view, getDao(), "desc", "releaseTime");
		Map map = new HashMap();
		setJsonPaging(map, view);
		content=HWJacksonUtils.getJsonP(map, callback);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端评论列表");
		accessLog.setOperateResult(String.format(Constant2.RECORD_TOTAL_SUM, view.getTotalRecords()));
		logSave(accessLog, request);
		return content;
		
	}
	@RequestMapping(value = "/add_front")
	public String addInput(Integer targetId, Model model,HttpServletRequest request,String targetView) {
		if(targetId!=null){
			model.addAttribute("targetId", targetId);
		}
		if(!ValueWidget.isNullOrEmpty(targetView)){
			return targetView;
		}
		return getJspFolder2()+"/add";
	}
	@Override
	protected void beforeAddInput(Model model,HttpServletRequest request) {

	}

	@Override
	protected void errorDeal(Model model) {

	}

	@Override
	public String getJspFolder() {
		return label;
	}

	public PaperNewsDao getPaperNewsDao() {
		return paperNewsDao;
	}

	@Resource
	public void setPaperNewsDao(PaperNewsDao paperNewsDao) {
		this.paperNewsDao = paperNewsDao;
	}
	@Override
	protected void listTODO(Model model, PageView view,
			HttpServletRequest request) {
		super.listTODO(model, view, request);
		try {
			TimeHWUtil.formatTime(view.getRecordList(), "releaseTime", "releaseTimeStr");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	@Override
	public ListOrderedMap getListOrderBy() {
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("releaseTime", "desc");
		return orderColumnModeMap;
	}
}
