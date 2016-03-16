package com.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oa.service.DictionaryParam;
import oa.web.controller.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dict.Constant2;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
/***
 * 
 * @author huangweii
 * 2015年6月15日
 */
@Controller
@RequestMapping("/ios")
public class AppStoreController extends BaseController{
	/***
	 * {"static_res_loc":"local","url":"http://123.57.250.51/ios_www/www/index.html"}
	 * @param request
	 * @param callback
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/static")
	public String iosSwitch(HttpServletRequest request, String callback){
		Map map=new HashMap();
		String static_res_loc=DictionaryParam.get(Constant2.DICTIONARY_GROUPID_IOS_APPSTORE, "static_res_loc");
		String remoteUrl=DictionaryParam.get(Constant2.DICTIONARY_GROUPID_IOS_APPSTORE, "remote_url");
		if(ValueWidget.isNullOrEmpty(remoteUrl)){
			remoteUrl=Constant2.IOS_REMOTE_STATIC_IP_URL;
		}
		map.put(Constant2.KEY_IOS_STATIC_RES_LOC, static_res_loc);
		if(static_res_loc.equalsIgnoreCase(Constant2.OS_STATIC_RES_LOC_REMOTE)){
			map.put("url", remoteUrl);//远程服务器地址
		}
		
		String content;
		content = HWJacksonUtils.getJsonP(map, callback);
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
		return null;
	}

}
