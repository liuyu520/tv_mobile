package com.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oa.entity.common.AccessLog;
import oa.web.controller.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.PageUtil;
import com.common.util.SystemHWUtil;
import com.common.web.view.PageView;
import com.entity.BusinessInformation;
import com.io.hw.json.HWJacksonUtils;
import com.util.JSONPUtil;
import com.view.BusinessInformationView;
@Controller
@RequestMapping("/business_information")
public class BusinessInformationController extends BaseController<BusinessInformation> {
	private String label = "business";
	
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
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端商讯列表页面");
		logSave(accessLog, request);
		String content;
		content=HWJacksonUtils.getJsonP(map, callback);
		return content;
	}
	@ResponseBody
	@RequestMapping(value = "/search",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonSearch(Model model,  Integer status,String keyword,
			BusinessInformationView view, HttpSession session, HttpServletRequest request,String callback)
			throws IOException {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (status != null) {
			condition.put("status", status);
		}
		/*if(!ValueWidget.isNullOrEmpty(title)){
			condition.put("title", title);
		}*/
		String[]columns=new String[]{"title","content","keyword"};
		PageUtil.paging(condition, columns,keyword,view, getDao(), "desc", "releaseTime", null,
				 null);
		setJsonPaging(map, view);
	
		String content;
		content=HWJacksonUtils.getJsonP(map, callback);
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
	@Override
	protected void listTODO(Model model,PageView view, HttpServletRequest request) {
		super.listTODO(model,view, request);
		JSONPUtil.formatTimeBusinessInformation(view.getRecordList());
	}

}
