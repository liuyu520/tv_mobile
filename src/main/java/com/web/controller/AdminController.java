package com.web.controller;

import com.common.util.SystemHWUtil;
import com.dao.AdminDao;
import com.dict.Constant2;
import com.entity.Admin;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.view.UserView;
import oa.entity.common.AccessLog;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/admin")
public class AdminController  extends BaseController<Admin> {
	private String label = "admin";
	
	@ResponseBody
	@RequestMapping(value = "/login", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String login(Model model, Integer status, UserView view,Integer issavePasswd,
			HttpSession session, HttpServletRequest request,HttpServletResponse response, String callback)
			throws IOException, NoSuchAlgorithmException {
		init(request);
		String content = null;
		AdminDao userDao = (AdminDao) getDao();
		Admin user2 = null;
		Map map = new HashMap();
		int login_result = 0;
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("管理员登录");
		
		if (ValueWidget.isNullOrEmpty(view.getUsername())) {
			login_result = Constant2.LOGIN_RESULT_USERNAME_EMPTY;//用户名不能为空
			accessLog.setOperateResult("用户名不能为空");
		} else if (ValueWidget.isNullOrEmpty(view.getPassword())) {
			login_result = Constant2.LOGIN_RESULT_PASSWORD_EMPTY;//密码不能为空
			accessLog.setOperateResult("密码不能为空");
		} else if (ValueWidget.isNullOrEmpty(user2 = userDao.getByUsername(view
				.getUsername()))) {
			login_result = Constant2.LOGIN_RESULT_USERNAME_INVALID;//用户名不存在
			accessLog.setOperateResult("用户名不存在");
		} else if (!user2.getPassword().equals(SystemHWUtil.getMD5(view.getPassword(),null))) {
			login_result = Constant2.LOGIN_RESULT_FAILED;//登录失败(用户名和密码不匹配)
			accessLog.setOperateResult("登录失败(用户名和密码不匹配)");
		} else {
			login_result = Constant2.LOGIN_RESULT_SUCCESS;
			session.setAttribute(Constant2.SESSION_KEY_LOGINED_ADMIN, user2);
			map.put("session", session.getId());// 下载session id到客户端
			map.put("userId", user2.getId());// 下载session id到客户端
			System.out.println("session id:" + session.getId());
			/*Map mapCookie=new HashMap();
			if(issavePasswd包装类型!=null&&issavePasswd==1)	{//记住密码
				mapCookie.put(Constant2.COOKIE_KEY_USERNAME, user2.getUsername());
				mapCookie.put(Constant2.COOKIE_KEY_PASSWORD, user2.getPassword());
			}else{
				mapCookie.put(Constant2.COOKIE_KEY_USERNAME, false);
				mapCookie.put(Constant2.COOKIE_KEY_PASSWORD, false);
			}
			WebServletUtil.rememberMe(request,response,mapCookie);
			*/
			accessLog.setOperateResult("登录成功,session id:"+session.getId());
		}
		// boolean isExist = userDao.isExist(view.getUsername(),
		// view.getPassword());
		
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		logSave(accessLog, request);
		content = HWJacksonUtils.getJsonP(map, callback);
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

}
