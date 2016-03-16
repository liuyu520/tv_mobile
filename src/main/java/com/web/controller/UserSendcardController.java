package com.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oa.entity.common.AccessLog;
import oa.web.controller.base.BaseController;

import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.PageUtil;
import com.common.util.SystemHWUtil;
import com.common.web.view.PageView;
import com.dao.UserDao;
import com.dict.Constant2;
import com.entity.NewsComment;
import com.entity.User;
import com.entity.UserSendcard;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.view.NewsCommentView;
import com.view.UserSendcardView;
/**
 * 论坛评论
 * @author huangwei
 * @since 2015年3月13日
 */
@Controller
@RequestMapping("/reply")
public class UserSendcardController extends BaseController<UserSendcard> {
	private String label = "reply";
	private UserDao userDao;
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
	public String json(Model model,  Integer status,
			UserSendcardView view, HttpSession session,UserSendcard userSendcard,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		Map map = new HashMap();
		
		PageUtil.paging(userSendcard,false, view, getDao(), "desc", "releaseTime", null,
				null, null);
		
		setJsonPaging(map, view);

		String content;
		
//		FilterProvider filters = simpleFilterProvider.addFilter(
//				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端论坛评论列表");
		accessLog.setOperateResult(String.format(Constant2.RECORD_TOTAL_SUM, view.getTotalRecords()));
		logSave(accessLog, request);
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
			UserSendcardView view, HttpSession session,UserSendcard userSendcard,
			HttpServletRequest request, String callback) throws IOException {
		User user2 = (User) session.getAttribute("user");
		Map map=new HashMap();
		int login_result = 0;
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("添加论坛评论");
		if(ValueWidget.isNullOrEmpty(user2)||ValueWidget.isNullOrEmpty(user2.getUsername())){
			login_result=Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//还没有登录
			accessLog.setOperateResult("还没有登录");
		}else if(ValueWidget.isNullOrEmpty(userSendcard)
				||ValueWidget.isNullOrEmpty(userSendcard.getFollowcardcontent())){
			login_result=Constant2.ADD_BBS_REPLY_RESULT_FOLLOWCARDCONTENT_EMPTY;
			accessLog.setOperateResult("论坛评论内容为空");
		}else
		{
			
			userSendcard.setStatus(Constant2.STATUS_ACTIVE);
			userSendcard.setReleaseTime(TimeHWUtil.getCurrentTimeSecond());
			userSendcard.setFrom_username(user2.getUsername());
			userSendcard.setClick(100);
			userSendcard.setReleaseUser(this.userDao.get(user2.getId()));
			init(request);
			/*Integer id=(Integer) */getDao().add(userSendcard);//持久化到数据库
			login_result=Constant2.LOGIN_RESULT_SUCCESS;
			map.put("data", userSendcard);
		}
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		String content;
//		FilterProvider filters = new SimpleFilterProvider().addFilter(
//				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		content =HWJacksonUtils.getJsonP(map, callback);
		logSave(accessLog, request);
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
		accessLog.setDescription("评论列表");
		logSave(accessLog, request);
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


	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
	@RequestMapping(value = "/add_front")
	public String addInput(Integer cardid, Model model,HttpServletRequest request,String targetView) {
		if(cardid!=null){
			model.addAttribute("cardid", cardid);
		}
		if(!ValueWidget.isNullOrEmpty(targetView)){
			return targetView;
		}
		return getJspFolder2()+"/add";
	}
	@Override
	public ListOrderedMap getListOrderBy() {
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("releaseTime", "desc");
		return orderColumnModeMap;
	}
}
