package com.web.controller.mob;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import oa.web.controller.base.BaseController;

import com.entity.mob.Blog;
//@Controller
//@RequestMapping("/blog")
public class BlogController extends BaseController<Blog>{

	@Override
	protected void beforeAddInput(Model model) {
		
	}

	@Override
	protected void errorDeal(Model model) {
		
	}

	@Override
	public String getJspFolder() {
		return null;
	}

}
