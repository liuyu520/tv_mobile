package com.web.controller;

import javax.annotation.Resource;

import oa.web.controller.DatabaseCustomizedController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.dao.generic.GenericDao;
import com.dao.UserDao;
import com.entity.User;
@Controller
@RequestMapping("/db/user")
public class UserCustomizedController extends DatabaseCustomizedController<User>{
	private UserDao userDao;
	@Override
	public GenericDao<User> getDao() {
		return userDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
