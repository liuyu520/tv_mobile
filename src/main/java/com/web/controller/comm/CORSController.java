package com.web.controller.comm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
/***
 * 用于测试跨域
 * @author huangweii
 * 2015年5月29日
 */
@Controller
@RequestMapping("/cors")
public class CORSController {
	protected Logger logger=Logger.getLogger(this.getClass());
	@ResponseBody
	@RequestMapping(value = "/simple",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String corsJsonSimple(HttpServletRequest request,String callback){
		String content;
		Map map=new HashMap();
		map.put("username", "黄威");
		map.put("age", "27");
		map.put("address", "beijing");
		content=HWJacksonUtils.getJsonP(map, callback);
		logger.info("getIpAddr:"+WebServletUtil.getIpAddr(request));
		logger.info("getRemoteAddr:"+WebServletUtil.getRemoteAddr(request));
		logger.info("getClientIpAddr:"+WebServletUtil.getClientIpAddr(request));
		logger.info("getClientIpAddress:"+WebServletUtil.getClientIpAddress(request));
		System.out.println("request.getContextPath():"+request.getContextPath());
		System.out.println("request.getRequestURL():"+request.getRequestURL());
		return content;
	}
	
	@ResponseBody
	@RequestMapping(value = "/complex",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String corsJsonComplex(String callback,String json){
		if(ValueWidget.isNullOrEmpty(json)){
			json="{username:\"huangweii\"}";
		}
		String content=callback+"("+json+");";
		logger.info(content);
		return content;
	}
}
