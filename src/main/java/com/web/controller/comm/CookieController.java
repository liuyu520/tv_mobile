package com.web.controller.comm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.SystemHWUtil;
import com.io.hw.json.HWJacksonUtils;

/****
 * 测试cookie的有效期
 * @author huangweii
 * 2015年10月11日
 */
@Controller
@RequestMapping("/cookie")
public class CookieController  {
	@ResponseBody
	@RequestMapping(value = "/period",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String cookiePeriod(HttpServletRequest request,String name2
			,HttpServletResponse response){
		Cookie emailCook = new Cookie(name2, "whuang");
		emailCook.setPath("/");
		emailCook.setMaxAge(60000);
		response.addCookie(emailCook);
		Map map=new HashMap();
		map.put(name2, name2);
		return HWJacksonUtils.getJsonP(map);
	}
}
