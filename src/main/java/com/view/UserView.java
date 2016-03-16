package com.view;

import com.common.util.WebServletUtil;
import com.util.JSONPUtil;

public class UserView {
	/***
	 * 登录名
	 */
	private String username;
	private String password;
	/***
	 * 新密码
	 */
	private String password2;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		//aaa;jsessionid=C3EA8F68A1B685D3F820C4A729A9D0A7
		this.password2 = WebServletUtil.dealWithJsessionid(password2);
	}
	
}
