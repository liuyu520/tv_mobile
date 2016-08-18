package com.web.controller;

import com.common.util.PageUtil;
import com.common.util.SystemHWUtil;
import com.dao.UserDao;
import com.dict.Constant2;
import com.entity.User;
import com.entity.UserFeedback;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.view.BusinessInformationView;
import com.view.UserFeedbackView;
import oa.web.controller.base.BaseController;
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
@RequestMapping("/feedback")
public class UserFeedbackController extends BaseController<UserFeedback> {
	private String label = "feedback";
	private UserDao userDao;
	
	@ResponseBody
	@RequestMapping(value = "/json",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model, Integer type, Integer status,
			BusinessInformationView view, HttpSession session, HttpServletRequest request,String callback)
			throws IOException {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (type != null) {
			condition.put("type", type);
		}
		if (status != null) {
			condition.put("status", status);
		}

		PageUtil.paging(condition, view, getDao(), "desc", "releaseTime", null,
				null, null);
		/*List data = view.getRecordList();
		map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		map.put(Constant2.JSON_RETURN_OVER,
				view.getCurrentPage() >= view.getTotalPages());
		map.put("recordList", data);*/
		setJsonPaging(map, view);
	
		String content;
		content=HWJacksonUtils.getJsonP(map, callback);
		return content;
	}
	@ResponseBody
	@RequestMapping(value = "/json_add_feedback", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonAddTips(Model model, UserFeedback userFeedback,
			UserFeedbackView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		int login_result = 0;
		User user2 =null;
		String content;
		Map map=new HashMap();
		if(ValueWidget.isNullOrEmpty(userFeedback)||ValueWidget.isNullOrEmpty(userFeedback.getContent())){
			login_result=Constant2.ADD_FEEDBACK_RESULT_CONTENT_EMPTY;//content不能为空
		}else /*if(ValueWidget.isNullOrEmpty(())||ValueWidget.isNullOrEmpty(user2.getUsername())){
			login_result=Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//用户还没有登录
		}else*/{			
			user2 = (User) session.getAttribute("user");
			if(!ValueWidget.isNullOrEmpty(user2)&&!ValueWidget.isNullOrEmpty(user2.getUsername())){
				userFeedback.setUser(this.getUserDao().get(user2.getId()));
			}
		
			userFeedback.setReleaseTime(TimeHWUtil.getCurrentTimeSecond());
			getDao().add(userFeedback);
			login_result=Constant2.LOGIN_RESULT_SUCCESS;
			map.put("feedback", userFeedback);
		}
		
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		content =HWJacksonUtils.getJsonP(map, callback);
		return content;
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
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
