package com.web.controller;

import com.common.util.PageUtil;
import com.common.util.SortList;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.common.web.view.PageView;
import com.dao.UserDao;
import com.dao.UserSendcardDao;
import com.dict.Constant2;
import com.entity.BBS;
import com.entity.User;
import com.entity.UserSendcard;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.util.JSONPUtil;
import com.view.PaperNewsView;
import com.view.UserSendcardView;
import oa.dao.common.CommonDictionaryDao;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bbs")
public class BBSController extends BaseController<BBS> {
	private String label = "bbs";
//	private SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
//			.serializeAllExcept("content","newsCommentSet");
//	private SimpleFilterProvider simpleFilterProvider=new SimpleFilterProvider();
	private UserSendcardDao userSendcardDao;
	private UserDao userDao;
	private CommonDictionaryDao commonDictionaryDao;

	public static String getJsonP(Object map, String callback) {
		SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
				.serializeAllExcept((String) null);
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		return HWJacksonUtils.getJsonP(map, callback, filters);
	}
	
	@ResponseBody
	@RequestMapping(value = "/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model, Integer type, Integer status,Integer sort,Integer userId,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (type != null) {
			condition.put("type", type);
		}
		if (status != null) {
			condition.put("status", status);
		}

		if (userId != null) {//添加报料的用户
			condition.put("userId", userId);
		}
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
//		orderColumnModeMap.put("sticktop", "desc");
//		orderColumnModeMap.put("stickTime", "desc");
		orderColumnModeMap.put("releaseTime", "desc");
		PageUtil.paging(condition, view, getDao(),null,orderColumnModeMap);
		/*
		 * List data = view.getRecordList();
		 * map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		 * map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		 * map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		 * map.put(Constant2.JSON_RETURN_OVER, view.getCurrentPage() >=
		 * view.getTotalPages()); map.put("recordList", data);
		 */
		List<BBS> data = view.getRecordList();
		int settings_cardcontent_max=Integer.parseInt(DictionaryParam.get("bbs_settings","cardcontent_max"));
		int size=data.size();
		for(int i=0;i<size;i++){
			BBS bbs2=data.get(i);
			String content=bbs2.getCardcontent();
			if(!ValueWidget.isNullOrEmpty(content)/*&&content.length()>settings_cardcontent_max*/){
//				bbs2.setCardcontent(content.substring(0, settings_cardcontent_max));
				bbs2.setCardcontent(SystemHWUtil.splitAndFilterString(content, settings_cardcontent_max));
			}
		}

		setJsonPaging(map, view);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端论坛列表");
		accessLog.setOperateResult("共有帖子:"+view.getTotalRecords());
		logSave(accessLog, request);
		String content;

//		FilterProvider filters = simpleFilterProvider.addFilter(
//				Constant2.SIMPLEFILTER_JACKSON_PAPERNEWS, theFilter);
		content = HWJacksonUtils.getJsonP(map, callback);
		return content;
	}

	@ResponseBody
	@RequestMapping(value = "/json_detail", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonDetail(Model model, int id/*新闻的id*/,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		BBS bbs=(BBS) getDao().get(id);
		String content;
		
		Map all=new HashMap();
		AccessLog accessLog=logInto(request);
		if(ValueWidget.isNullOrEmpty(bbs)||bbs.getStatus()!=Constant2.STATUS_ACTIVE){
			accessLog.setOperateResult("该论坛帖子不存在或已经删除");
		}else{
		UserSendcardView userSendcardView=new UserSendcardView();
		Map condition=new HashMap();
		condition.put("cardid", id);
		condition.put("status", Constant2.STATUS_ACTIVE);
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
//		orderColumnModeMap.put("sticktop", "desc");
//		orderColumnModeMap.put("stickTime", "desc");
		orderColumnModeMap.put("releaseTime", "desc");
		PageUtil.paging(condition, userSendcardView, this.userSendcardDao,null,orderColumnModeMap);
		Map commentMap=new HashMap();
		setJsonPaging(commentMap, userSendcardView);
		
		all.put("comment", commentMap);
		all.put("bbs", bbs);
		accessLog.setDescription("手机端论坛详情[评论总数: "+userSendcardView.getTotalRecords()+" ]");
		}
		content = getJsonP(all, callback);
		
		logSave(accessLog, request);
		return content;
	}
	/***
	 * 添加一个帖子
	 * @param model
	 * @param bbs
	 * @param view
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/json_add_bbs", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonAddTips(Model model, BBS bbs,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		int login_result = 0;
		User user2 =null;
		String content;
		Map map=new HashMap();
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("发表论坛帖子");
		if(ValueWidget.isNullOrEmpty(bbs)||ValueWidget.isNullOrEmpty(bbs.getTitle())){
			login_result=Constant2.ADD_TIPS_RESULT_TITLE_EMPTY;//标题不能为空
			accessLog.setOperateResult("标题不能为空");
		}else if(ValueWidget.isNullOrEmpty((user2 = (User) session.getAttribute("user")))||ValueWidget.isNullOrEmpty(user2.getUsername())){
			login_result=Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//用户还没有登录
			accessLog.setOperateResult("用户还没有登录");
		}else{
			bbs.setCardcontent(WebServletUtil.dealWithJsessionid(bbs.getCardcontent()));
			bbs.setReleaseUser(this.userDao.get(user2.getId()));;
			bbs.setReleaseTime(TimeHWUtil.getCurrentTimeSecond());
			bbs.setReleaseName(user2.getUsername());
			bbs.setStatus(Constant2.STATUS_ACTIVE);
			getDao().add(bbs);
			login_result=Constant2.LOGIN_RESULT_SUCCESS;
			map.put("bbs", bbs);
			accessLog.setOperateResult("新增论坛帖子ID:"+bbs.getId());
		}
		
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		content = getJsonP(map, callback);
		
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

	public UserSendcardDao getUserSendcardDao() {
		return userSendcardDao;
	}
	@Resource
	public void setUserSendcardDao(UserSendcardDao userSendcardDao) {
		this.userSendcardDao = userSendcardDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected void listTODO(Model model,PageView view, HttpServletRequest request) {
		super.listTODO(model,view, request);
		Map<String,String>map=null;
//		map=DictionaryParam.getIdValue(Constant2.DICTIONARY_GROUPID_BBS_GROUP);
		List<CommonDictionary> commonDictionaries=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_BBS_GROUP);
			//返回:{"3":"足球","2":"体育","1":"娱乐","7":"篮球2","6":"篮球","5":"NBA22","4":"NBA","9":"篮球231","8":"篮球23"}
		model.addAttribute("commonDictionaries", commonDictionaries);
		List list=view.getRecordList();
		SortList<BBS> sortList=new SortList<BBS>();
		sortList.sort(list, "getReleaseTime", "desc");
		JSONPUtil.formatBBS(list);
	}

	public CommonDictionaryDao getCommonDictionaryDao() {
		return commonDictionaryDao;
	}

	@Resource
	public void setCommonDictionaryDao(CommonDictionaryDao commonDictionaryDao) {
		this.commonDictionaryDao = commonDictionaryDao;
	}

	@Override
	protected BBS detailTODO(int id,Model model, HttpServletRequest request) {
		BBS bbs=super.detailTODO(id, model, request);
		Map condition=new HashMap();
		UserSendcardView userSendcardView=new UserSendcardView();
		condition.put("cardid", id);
		condition.put("status", Constant2.STATUS_ACTIVE);
		
		UserSendcard userSendcard=new UserSendcard();
		userSendcard.setCardid(id);
		userSendcard.setStatus(Constant2.STATUS_ACTIVE);
		request.getSession().setAttribute("reply", userSendcard);
		
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("releaseTime", "desc");
		PageUtil.paging(condition, userSendcardView, this.userSendcardDao,null,orderColumnModeMap);
		try {
			TimeHWUtil.formatTime(userSendcardView.getRecordList(), "releaseTime", "releaseTimeStr");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		model.addAttribute("view", userSendcardView);
		bbs.setReleaseTimeStr(TimeHWUtil.formatSecondTime(bbs.getReleaseTime()));
		
		//设置发帖区名称
		long bbsType=bbs.getType();
		String typeStr=commonDictionaryDao.get(bbsType).getValue();
		bbs.setTypeStr(typeStr);
		return bbs;
	}

	@Override
	public ListOrderedMap getListOrderBy() {
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("releaseTime", "desc");
		return orderColumnModeMap;
	}
	@RequestMapping(value = "/add_front")
	public String addInput(String practiceWay, Model model,HttpServletRequest request,String targetView) {
		List<CommonDictionary> commonDictionaries=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_BBS_GROUP);
		//返回:{"3":"足球","2":"体育","1":"娱乐","7":"篮球2","6":"篮球","5":"NBA22","4":"NBA","9":"篮球231","8":"篮球23"}
		model.addAttribute("commonDictionaries", commonDictionaries);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("增加BBS页面");
		logSave(accessLog, request);
		if(!ValueWidget.isNullOrEmpty(targetView)){
			return targetView;
		}
		return getJspFolder2()+"/add";
	}
	protected String getListView(){
		return "/index";
	}

	@Override
	protected void beforeList(BBS roleLevel) {
		roleLevel.setStatus(Constant2.NEWS_STATUS_ON);//额外的条件
		super.beforeList(roleLevel);

		/*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		AccessLog accessLog = logInto(request);
		accessLog.setDescription("list test");
		accessLog.setOperateResult("list test conditon:" + HWJacksonUtils.getJsonP(roleLevel));
		logSave(accessLog, request);*/
	}
}
