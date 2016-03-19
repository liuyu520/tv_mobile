package com.web.controller;

import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.dao.UserDao;
import com.dict.Constant2;
import com.entity.User;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.view.UserView;
import oa.entity.common.AccessLog;
import oa.service.DictionaryParam;
import oa.util.AuthenticateUtil;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author huangwei
 * @since 2015年3月6日
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
	private String label = "user";
	/***
	 * 登录
	 * @param model
	 * @param status
	 * @param view
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/login", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String login(Model model, Integer status, UserView view,Integer issavePasswd,
			HttpSession session, HttpServletRequest request,HttpServletResponse response, String callback)
			throws IOException {
		init(request);
		String content = null;
		UserDao userDao = (UserDao) getDao();
		User user2 = null;
		Map map = new HashMap();
		int login_result = 0;
		AccessLog accessLog=logInto(request);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			accessLog.setDescription("会员登录");
		}
		
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
		} else if (!user2.getPassword().equals(view.getPassword())) {
			login_result = Constant2.LOGIN_RESULT_FAILED;//登录失败(用户名和密码不匹配)
			accessLog.setOperateResult("登录失败(用户名和密码不匹配)");
		} else {
			login_result = Constant2.LOGIN_RESULT_SUCCESS;
			session.setAttribute(Constant2.SESSION_KEY_LOGINED_USER, user2);
			session.setAttribute(Constant2.SESSION_KEY_LOGINED_FLAG, Constant2.FLAG_LOGIN_SUCCESS);//登录成功的标识有两个:"user",Constant2.SESSION_KEY_LOGINED_FLAG
			//解决关闭浏览器之后需要重新登录的问题
			Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
			c.setPath("/");
			//先设置cookie有效期为2天
			c.setMaxAge(48 * 60 * 60);
			response.addCookie(c);
			map.put("session", session.getId());// 下载session id到客户端
			map.put("userId", user2.getId());// 下载session id到客户端
			System.out.println("session id:" + session.getId());
			Map mapCookie=new HashMap();
			if(issavePasswd/*包装类型*/!=null&&issavePasswd==1)	{//记住密码
				mapCookie.put(Constant2.COOKIE_KEY_USERNAME, user2.getUsername());
				mapCookie.put(Constant2.COOKIE_KEY_PASSWORD, user2.getPassword());
			}else{
				mapCookie.put(Constant2.COOKIE_KEY_USERNAME, false);
				mapCookie.put(Constant2.COOKIE_KEY_PASSWORD, false);
			}
			WebServletUtil.rememberMe(request,response,mapCookie);
			if(!ValueWidget.isNullOrEmpty(accessLog)){
				accessLog.setOperateResult("登录成功,session id:"+session.getId());
			}
		}
		// boolean isExist = userDao.isExist(view.getUsername(),
		// view.getPassword());
		
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			logSave(accessLog, request);
		}

		
		content = HWJacksonUtils.getJsonP(map, callback);
		return content;
	}

	/***
	 * {
username: "120437193"
}
{} if id does not exist
	 * @param model
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/username", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String getUsername(Model model, @RequestParam(required=true)int id,
			HttpSession session, HttpServletRequest request,HttpServletResponse response, String callback)
			throws IOException {
		init(request);
		UserDao userDao = (UserDao) getDao();
		String propertyName="username";
		String username=userDao.getStringById(id, propertyName);
		Map map=new HashMap();
		if(!ValueWidget.isNullOrEmpty(username)){
			map.put(propertyName, username);
		}
		String content = HWJacksonUtils.getJsonP(map, callback);
		return content;
	}
	@ResponseBody
	@RequestMapping(value = "/username_email", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String getUsernameEmail(Model model, @RequestParam(required=true)int id,
			HttpSession session, HttpServletRequest request,HttpServletResponse response, String callback)
			throws IOException {
		init(request);
		UserDao userDao = (UserDao) getDao();
		String propertyName1="username";
		String propertyName2="email";
		Object[] result=userDao.getPropertiesById2(id, propertyName1,propertyName2);
		Map map=new HashMap();
		if(!ValueWidget.isNullOrEmpty(result)){
			map.put(propertyName1, result[0]);
			map.put(propertyName2, result[1]);
		}
		String content = HWJacksonUtils.getJsonP(map, callback);
		return content;
	}
	/***
	 * 用户自己注册
	 * 
	 * @param model
	 * @param view
	 * @param user
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/register", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String register(Model model, UserView view, User user,
			HttpSession session, HttpServletRequest request, String callback)
			throws IOException {
		init(request);
		String content = null;
		int login_result = 0;
		UserDao userDao = (UserDao) getDao();
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("会员注册");
		
		if (ValueWidget.isNullOrEmpty(user)) {
			login_result = Constant2.LOGIN_RESULT_USERNAME_EMPTY;//用户名为空
			accessLog.setOperateResult("用户名为空");
		} else if (ValueWidget.isNullOrEmpty(user.getPassword())) {
			login_result = Constant2.LOGIN_RESULT_PASSWORD_EMPTY;//密码为空
			accessLog.setOperateResult("密码为空");
		} else if (DictionaryParam.getBoolean("global_setting", "isCheckEmail4Register")
				&& ValueWidget.isNullOrEmpty(user.getEmail())) {
			login_result = Constant2.REGISTER_RESULT_EMAIL_EMPTY;//邮箱为空
			accessLog.setOperateResult("邮箱为空");
		}else if (!ValueWidget.isNullOrEmpty(userDao.getByUsername(user
				.getUsername()))) {
			login_result = Constant2.REGISTER_RESULT_USERNAME_EXIST;//用户名已经存在
			accessLog.setOperateResult("用户名已经存在");
		}
		Map map = new HashMap();
		
		if (login_result == 0) {
			long time = TimeHWUtil.getCurrentTimeSecond();
			user.setCreateTime(time);
			user.setUpdateTime(time);
			userDao.add(user);
			session.setAttribute(Constant2.SESSION_KEY_LOGINED_USER, user);
			session.setAttribute(Constant2.SESSION_KEY_LOGINED_FLAG, Constant2.FLAG_LOGIN_SUCCESS);//登录成功的标识有两个:"user",Constant2.SESSION_KEY_LOGINED_FLAG
			map.put("session", session.getId());// 下载session id到客户端
			map.put("userId", user.getId());// 下载session id到客户端
			login_result = Constant2.LOGIN_RESULT_SUCCESS;
			accessLog.setOperateResult("注册成功,session id:"+session.getId());
		}
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		content = HWJacksonUtils.getJsonP(map,callback);
		logSave(accessLog, request);
		return content;
	}

	/***
	 * view中的password 是旧密码,password2是新密码
	 * 
	 * @param model
	 * @param view
	 * @param user
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/mod_pass", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String modifyPass(/*@CookieValue("JSESSIONID") String jsessionid,*/Model model, UserView view, User user,
			HttpSession session, HttpServletRequest request, String callback,boolean forced)
			throws IOException {
		init(request);
//		System.out.println("jsessionid:"+jsessionid);
		String content = null;
		int login_result = 0;
		UserDao userDao = (UserDao) getDao();
		User user2 = null;
		String newPassword = view.getPassword2();
		String oldPassword=view.getPassword();
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("修改密码");
		
		if(!ValueWidget.isNullOrEmpty(newPassword)){
			newPassword =WebServletUtil.dealWithJsessionid(newPassword) /*.replaceAll(";jsessionid=[\\w]+$", "")*/;
		}
		if (ValueWidget.isNullOrEmpty(newPassword)) {
			login_result = Constant2.MODIFY_PASS_RESULT_NEW_PASS_EMPTY;//新密码为空
			accessLog.setOperateResult("新密码为空");
		} else if (user2.getPassword().equals(newPassword)) {//新旧密码不能相同
			login_result = Constant2.MODIFY_PASS_RESULT_PASS_SAME;
			accessLog.setOperateResult("新旧密码不能相同");
		} else if ((user2 = (User) session.getAttribute("user")) == null
				|| ValueWidget.isNullOrEmpty(user2.getUsername())) {
			login_result = Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//还没有登录
			accessLog.setOperateResult("还没有登录");
		} else if (!user2.getPassword().equals(oldPassword)) {//旧密码与session中的密码不同,并不是表示不对(因为session可能是旧的)
			login_result = Constant2.MODIFY_PASS_RESULT_OLD_PASS_WRONG;
			accessLog.setOperateResult("旧密码与session中的密码不同");
		} else {
			int updateResult=userDao.modifyPass(user2.getId(),oldPassword/*旧密码*/, newPassword);
			if(updateResult<=0){//旧密码不对
				if(forced){//强制修改密码,不用验证码旧密码
					userDao.modifyPass(user2.getId(), newPassword);
					login_result = Constant2.LOGIN_RESULT_SUCCESS;
				}else{
					login_result = Constant2.MODIFY_PASS_RESULT_OLD_PASS_WRONG;
					accessLog.setOperateResult("旧密码不对");
				}
			}else{
				login_result = Constant2.LOGIN_RESULT_SUCCESS;
				user2.setPassword(newPassword);// 同时更新session中的密码
			}
		}
		content = HWJacksonUtils.getJsonP(Constant2.LOGIN_RESULT_KEY, login_result,
				callback);
		logSave(accessLog, request);
		return content;
	}

	/***
	 * 手机端从服务器获取最新密码
	 * @param model
	 * @param view
	 * @param user
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/get_pass", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String getPass(Model model, UserView view, User user,
			HttpSession session, HttpServletRequest request, String callback)
			throws IOException {
		init(request);
		String content = null;
		int login_result = 0;
		UserDao userDao = (UserDao) getDao();
		User user2 = null;
		Map map = new HashMap();
		
		String password = view.getPassword();
		
		password=WebServletUtil.dealWithJsessionid(password);
		System.out.println("手机端的密码:"+password);
		if ((user2 = (User) session.getAttribute("user")) == null
				|| ValueWidget.isNullOrEmpty(user2.getUsername())) {
			login_result = Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;//用户还没有登录
		} else if (!user2.getPassword().equals(password)) {//密码不一致
			login_result = Constant2.GET_PASS_RESULT_NEED_REFRESH;
			user2=userDao.get(user2.getId());//session的密码也是旧的,所以需要查数据库
			map.put("password", user2.getPassword());
			session.setAttribute("user", user2);
		}else{
			login_result = Constant2.GET_PASS_RESULT_ALREADY_NEWEST;
		}
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		content = HWJacksonUtils.getJsonP(map,callback);
		return content;
	}
	/***
	 * 手机端的注销
	 * @param jsessionid
	 * @param model
	 * @param view
	 * @param user
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String logout(@CookieValue("JSESSIONID") String jsessionid,Model model, UserView view, User user,
			HttpSession session, HttpServletRequest request, String callback)
			throws IOException {
		init(request);
		System.out.println("jsessionid:"+jsessionid);
		String content = null;
		int login_result = 0;
		User user2 = null;
		String oldPassword=view.getPassword();
		AccessLog accessLog=logInto(request);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			accessLog.setDescription("手机端注销");
		}
		
		if(!ValueWidget.isNullOrEmpty(oldPassword)){
			oldPassword = WebServletUtil.dealWithJsessionid(oldPassword)/* .replaceAll(";jsessionid=[\\w]+$", "")*/;
		}
		if ((user2 = (User) session.getAttribute("user")) == null
				|| ValueWidget.isNullOrEmpty(user2.getUsername())) {
			login_result = Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;
			accessLog.setOperateResult("还未登录");
		} else if (!user2.getPassword().equals(oldPassword)) {//旧密码不对
			login_result = Constant2.MODIFY_PASS_RESULT_OLD_PASS_WRONG;
			accessLog.setOperateResult("旧密码不对");
		} else {
//			User user3=(User)session.getAttribute(Constant2.SESSION_KEY_LOGINED_USER);
			if(!ValueWidget.isNullOrEmpty(user2)&&!ValueWidget.isNullOrEmpty(user2.getUsername())){//没有必要
				if(!ValueWidget.isNullOrEmpty(accessLog)){
					accessLog.setUserId(user2.getId());
					accessLog.setUsername(user2.getUsername());
				}
			}
			if(!ValueWidget.isNullOrEmpty(accessLog)){
				accessLog.setOperateResult("注销成功.原session id:"+jsessionid);
			}
			
			AuthenticateUtil.logout(session);
			login_result = Constant2.LOGIN_RESULT_SUCCESS;
			
		}
		content = HWJacksonUtils.getJsonP(Constant2.LOGIN_RESULT_KEY, login_result,
				callback);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			logSave(accessLog, request);
		}
		return content;
	}

	/***
	 * PC端的注销
	 * @param jsessionid
	 * @param model
	 * @param view
	 * @param user
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/logoutPC", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String logoutPC(Model model, UserView view, User user,
			HttpSession session, HttpServletRequest request, String callback)
			throws IOException {
		init(request);
		String content = null;
		int login_result = 0;
		User user2 = null;
		String oldPassword=view.getPassword();
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("PC端注销");
		if (!AuthenticateUtil.isLogined(session)) {
			login_result = Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET;
			accessLog.setOperateResult("还未登录");
			AuthenticateUtil.logout(session);
		} else {
//			session.removeAttribute("user");
			AuthenticateUtil.logout(session);
			login_result = Constant2.LOGIN_RESULT_SUCCESS;
		}
		content = HWJacksonUtils.getJsonP(Constant2.LOGIN_RESULT_KEY, login_result,
				callback);
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
}
