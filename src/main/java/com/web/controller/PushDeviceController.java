package com.web.controller;

import com.common.util.SystemHWUtil;
import com.dao.PushDeviceDao;
import com.dao.UserDao;
import com.dict.Constant2;
import com.entity.PushDevice;
import com.entity.User;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/***
 * 
 * @author huangweii
 * 2015年6月20日
 */
@Controller
@RequestMapping("/device")
public class PushDeviceController extends BaseController<PushDevice> {
	private UserDao userDao;
	/***
	 * 什么时候调用呢?设备第一进入应用时调用,以后就不会调用了
	 * @param request
	 * @param callback
	 * @param osVersion
	 * @param clientId
	 * @param osType
	 * @param deviceInfo : 移动设备的其他信息,比如uuid,mac地址,手机型号等
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/push_register",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String pushDeviceRegister(HttpServletRequest request, String callback
			,@RequestParam(value = "os_version", required = false) String osVersion
			,@RequestParam(value = "client_id", required = true) String clientId
			,@RequestParam(value = "os_type", required = true) String osType
			,@RequestParam(value = "device_info", required = false) String deviceInfo){
		init(request);
		PushDeviceDao pushDeviceDao=(PushDeviceDao)getDao();
		Map map=new HashMap();
		PushDevice pushDeviceUnique=pushDeviceDao.get("deviceId", clientId);
		if(!ValueWidget.isNullOrEmpty(pushDeviceUnique)){
			map.put(Constant2.LOGIN_RESULT_KEY, false);
			map.put(Constant2.RESPONSE_KEY_ERROR_MESSAGE, "该设备ID已经注册");
			return HWJacksonUtils.getJsonP(map,callback);
		}
		PushDevice pushDevice=new PushDevice();
		pushDevice.setDeviceId(clientId);
		pushDevice.setOsType(osType);
		pushDevice.setOsVersion(osVersion);
		pushDevice.setDeviceInfo(deviceInfo);
		pushDevice.setDeviceRegisterTime(TimeHWUtil.getCurrentDateTime());
		pushDeviceDao.add(pushDevice);
		return Constant2.RESPONSE_RIGHT_RESULT;
		
	}

	/***
	 * 用户登录时会同时调用该接口,目的是:设置userid
	 * @param request
	 * @param callback
	 * @param clientId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String login(HttpServletRequest request, String callback
			,@RequestParam(value = "client_id", required = true) String clientId
			,@RequestParam(value = "user_id", required = true) int userId){
		init(request);
		PushDeviceDao pushDeviceDao=(PushDeviceDao)getDao();
		PushDevice pushDevice=pushDeviceDao.get("deviceId", clientId);
		User user=this.userDao.get(userId);
		pushDevice.setUser(user);
		pushDevice.setUsername(user.getUsername());
		pushDevice.setLoginedTime(TimeHWUtil.getCurrentDateTime());
		pushDeviceDao.update(pushDevice);
		return Constant2.RESPONSE_RIGHT_RESULT;
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

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
