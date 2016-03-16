package com.web.controller.comm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oa.bean.statistics.StatisticsBean;
import oa.dao.common.AccessLogDao;
import oa.entity.common.AccessLog;
import oa.web.controller.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.SystemHWUtil;
import com.dict.Constant2;
import com.io.hw.json.HWJacksonUtils;

@Controller
@RequestMapping("/statistics")
public class StatisticsController  extends BaseController<AccessLog> {
	private String label = "statistics";

	@ResponseBody
	@RequestMapping(value = "/statis", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String statis(Model model,HttpServletRequest request,Integer statisticsType,Integer times){
		init(request);
		AccessLogDao accessLogDao=(AccessLogDao)getDao();
		Map map = new HashMap();
		if(statisticsType==null){
			statisticsType=Constant2.STATISTICS_TYPE_DAY;
		}
		switch (statisticsType) {
		case Constant2.STATISTICS_TYPE_DAY:
			List<Object[]> statisActivationDataByDay=accessLogDao.getStatisticsActivation(Constant2.REQUESTTARGET_REGISTER);//注册
			List<Object[]> statisDataByDay=accessLogDao.getStatisticsActivation(null);//注册
			map.put("statisActivationDataByDay", statisActivationDataByDay);
			map.put("statisDataByDay", statisDataByDay);
			break;
		case Constant2.STATISTICS_TYPE_WEEK:

		case Constant2.STATISTICS_TYPE_MONTH:
			if(times==null){
				times=10;
			}
			StatisticsBean[]countsActivation=accessLogDao.countBatch(Constant2.REQUESTTARGET_REGISTER, null, statisticsType, times);
			StatisticsBean[]counts=accessLogDao.countBatch(null, null, statisticsType, times);
			map.put("countsActivation", countsActivation);
			map.put("counts", counts);
			break;

		default:
			break;
		}
		String content;
		content = HWJacksonUtils.getJsonP(map, null);
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
