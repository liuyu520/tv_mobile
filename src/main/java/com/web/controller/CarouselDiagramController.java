package com.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.dict.Constant2;
import com.entity.CarouselDiagram;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.util.JSONPUtil;
import com.view.BusinessInformationView;
/***
 * 移动端首页轮播图
 * @author huangwei
 * @since 2015年3月12日
 */
@Controller
@RequestMapping("/carousel")
public class CarouselDiagramController extends BaseController<CarouselDiagram> {
	private String label = "carousel";
	
	@ResponseBody
	@RequestMapping(value = "/json",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model,  Integer status,
			/*CarouselDiagramView view, */HttpSession session, HttpServletRequest request,String callback)
			throws IOException {
		init(request);
//		Map map = new HashMap();
		Map condition = new HashMap();
		if (status != null) {
			condition.put("status", status);
		}

//		PageUtil.paging(condition, view, getDao(), "desc", "uploadTime", null,
//				null, null);
//		List<CarouselDiagram> carouselDiagrams=getDao().getAll(null, "desc", "uploadTime");
		List<CarouselDiagram> carouselDiagrams=getDao().find(condition, "desc", "uploadTime", null, null);
		/*List data = view.getRecordList();
		map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		map.put(Constant2.JSON_RETURN_OVER,
				view.getCurrentPage() >= view.getTotalPages());
		map.put("recordList", data);*/
//		setJsonPaging(map, view);
		AccessLog accessLog=logInto(request);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			accessLog.setDescription("手机端轮播图");
			accessLog.setOperateResult(String.format(Constant2.RECORD_TOTAL_SUM, carouselDiagrams.size()));
			logSave(accessLog, request);
		}
		
		listCommonAction(carouselDiagrams);
		String content;
		content=HWJacksonUtils.getJsonP(carouselDiagrams, callback);
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
		PageUtil.paging(condition, columns,keyword,view, getDao(), "desc", "uploadTime", null,
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
	protected void listTODO(Model model, PageView view,
			HttpServletRequest request) {
		super.listTODO(model, view, request);
		List<CarouselDiagram> list=view.getRecordList();
		listCommonAction(list);
	}
	protected void listCommonAction(List<CarouselDiagram> list){
		int size=list.size();
		for(int i=0;i<size;i++){
			CarouselDiagram tVVideo=list.get(i);
			String picPath=tVVideo.getPicPath();
			if(!ValueWidget.isNullOrEmpty(picPath)){
				tVVideo.setPicPath(JSONPUtil.getPicPath(picPath));
			}
		}
	}
}
