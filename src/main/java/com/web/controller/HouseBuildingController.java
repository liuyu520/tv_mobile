package com.web.controller;

import com.entity.HouseBuilding;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/***
 * 访问手机app的日志
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/house")
public class HouseBuildingController extends BaseController<HouseBuilding> {
	private String label = "access";
	
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
