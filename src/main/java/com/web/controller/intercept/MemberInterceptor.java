package com.web.controller.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oa.util.AuthenticateUtil;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.SystemHWUtil;
import com.util.TVUtils;

public class MemberInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
//		modelAndView=new ModelAndView(viewName)
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		
		if (!AuthenticateUtil.isLogined(session)) {
			//session.setAttribute(Constant2.SESSION_KEY_LOGINED_FLAG, Constant2.FLAG_LOGIN_SUCCESS);
			String path=request.getRequestURI();//"/demo_channel_terminal/news/list"
			System.out.println("您无权访问:"+path);
			System.out.println();
			String contextPath=request.getContextPath();
			response.setCharacterEncoding(SystemHWUtil.CHARSET_UTF);
			request.setCharacterEncoding("UTF-8");
//			String message="您没有权限访问,请先登录.";
			response.setStatus(401);//401表示拒绝访问
//			response.sendRedirect(contextPath);//因为发帖时需要明切地返回错误码
			return false;
		}
		return true;
	}

}
