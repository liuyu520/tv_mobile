package com.web.controller;

import com.common.dict.Constant2;
import com.common.web.view.PageView;
import com.entity.SimpleInfo;
import com.string.widget.util.RegexUtil;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import oa.web.controller.base.BaseController;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/***
 * 简单的消息,不记录发布者
 * @author huangweii
 * 2015年6月16日
 */
@Controller
@RequestMapping("/sinfo")
public class SimpleInfoController extends BaseController<SimpleInfo>{
	private String label = "sinfo";
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
	@Override
	public ListOrderedMap getListOrderBy() {
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("createTime", "desc");
		return orderColumnModeMap;
	}

	@Override
	protected void beforeSave(SimpleInfo roleLevel, Model model) {
		super.beforeSave(roleLevel, model);
		roleLevel.setCreateTime(TimeHWUtil.getCurrentTimeLong());
		roleLevel.setStatus(Constant2.NEWS_STATUS_ON);
	}

	@Override
	protected void listTODO(Model model, PageView view,
			HttpServletRequest request) {
		super.listTODO(model, view, request);
		List list=view.getRecordList();
		int size = list.size();
        String isXss = request.getParameter("xss");
        for (int i = 0; i < size; i++) {
			Object obj=list.get(i);
			if(obj!=null && obj instanceof SimpleInfo){
				SimpleInfo info=(SimpleInfo)obj;
				String content=info.getInfo();
                if (!ValueWidget.isNullOrEmpty(isXss) && isXss.equalsIgnoreCase("true")) {
                    content = StringEscapeUtils.escapeHtml4(content);
                }
                info.setInfo(RegexUtil.convertBr(content));

			}
		}
	}


	@Override
	protected void deleteTODO(int id, SimpleInfo roleLevel, Model model,
			HttpServletRequest request) {
		roleLevel.setStatus(Constant2.NEWS_STATUS_OFF);
		roleLevel.setDeleteTime(TimeHWUtil.getCurrentTimeLong());
		getDao().update(roleLevel);
	}

	@Override
	protected void beforeList(SimpleInfo roleLevel) {
		super.beforeList(roleLevel);
		roleLevel.setStatus(Constant2.NEWS_STATUS_ON);
	}
	
}
