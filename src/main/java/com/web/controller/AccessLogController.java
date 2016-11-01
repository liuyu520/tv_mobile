package com.web.controller;

import com.common.util.SystemHWUtil;
import com.dict.Constant2;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.view.PaperNewsView;
import oa.dao.common.AccessLogDao;
import oa.entity.common.AccessLog;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
/***
 * 访问手机app的日志
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/access")
public class AccessLogController extends BaseController<AccessLog> {
	private String label = "access";
	
	@ResponseBody
	@RequestMapping(value = "/logs", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonDetail(Model model, AccessLog accessLog,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		String content =HWJacksonUtils.getJsonP(Constant2.LOGIN_RESULT_KEY, Constant2.LOGIN_RESULT_SUCCESS, callback);
		if(accessLog.getAccessType()==0){
			accessLog.setAccessType(Constant2.LOGS_ACCESS_TYPE_INTO);
		}
		super.logSave(accessLog, request);
		return content;
	}
	@ResponseBody
	@RequestMapping(value = "/updateLog", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String updateAccessDayTime(Model model, 
			PaperNewsView view, HttpSession session,
			HttpServletRequest request, int id) throws IOException {
		init(request);
		AccessLogDao accessLogDao=(AccessLogDao)this.getDao();
		AccessLog accessLog=accessLogDao.get(id);
		accessLogDao.updateAccessDayTime(accessLog.getId(), TimeHWUtil.formatDateTime(new Date()));
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateAllLog", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String updateAllAccessDayTime(Model model, 
			PaperNewsView view, HttpSession session,
			HttpServletRequest request) throws IOException {
		init(request);
		AccessLogDao accessLogDao=(AccessLogDao)this.getDao();
		List<AccessLog> accessLoges=accessLogDao.getAll("accessDayTime",null,null);
		int length=accessLoges.size();
		System.out.println("length:"+length);
		for(int i=0;i<length;i++){
			AccessLog accessLog=accessLoges.get(i);
			if(ValueWidget.isNullOrEmpty(accessLog.getAccessDayTime())){
				accessLogDao.updateAccessDayTime(accessLog.getId(), TimeHWUtil.formatDateTime(TimeHWUtil.getDateBySecond(accessLog.getTime())));
			}
		}
		
		return null;
	}
	
	@Override
    protected void beforeAddInput(Model model, HttpServletRequest request) {
    }
	
	@Override
	protected void errorDeal(Model model) {
	}

	@Override
	public String getJspFolder() {
		return label;
	}

}
