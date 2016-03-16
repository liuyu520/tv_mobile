package com.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import oa.web.controller.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.SystemHWUtil;
import com.dao.PushDeviceDao;
import com.dao.ReceivedPushMessageDao;
import com.dict.Constant2;
import com.entity.PushDevice;
import com.entity.ReceivedPushMessage;
import com.entity.User;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;

/***
 * 
 * @author huangweii
 * 2015年6月21日
 */
@Controller
@RequestMapping("/received_push")
public class ReceivedPushMessageController extends BaseController<ReceivedPushMessage> {
	private PushDeviceDao pushDeviceDao;
	@ResponseBody
	@RequestMapping(value = "/save",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String pushDeviceRegister(HttpServletRequest request, String callback
			,@RequestParam(value = "client_id", required = true) String deviceId
			,@RequestParam(value = "push_title", required = false) String pushTitle
			,@RequestParam(value = "push_content", required = true) String pushContent){
		init(request);
		ReceivedPushMessageDao receivedPushDao=(ReceivedPushMessageDao)getDao();
		Map map=new HashMap();
		PushDevice pushDevice=pushDeviceDao.get("deviceId", deviceId);
		if(ValueWidget.isNullOrEmpty(pushDevice)){
			map.put(Constant2.LOGIN_RESULT_KEY, false);
			map.put(Constant2.RESPONSE_KEY_ERROR_MESSAGE, "未找到设备id:"+deviceId);
			return HWJacksonUtils.getJsonP(map,callback);
		}
		User user=pushDevice.getUser();
		
		ReceivedPushMessage receivedPushMessage=new ReceivedPushMessage();
		if(!ValueWidget.isNullOrEmpty(user)){
			receivedPushMessage.setToUser(user);
			receivedPushMessage.setToUsername(user.getUsername());
		}
		receivedPushMessage.setPushDevice(pushDevice);
		receivedPushMessage.setPushTitle(pushTitle);
		receivedPushMessage.setPushContent(pushContent);
		receivedPushMessage.setReceivedTime(TimeHWUtil.getCurrentDateTime());
		receivedPushDao.add(receivedPushMessage);
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

	public PushDeviceDao getPushDeviceDao() {
		return pushDeviceDao;
	}

	@Resource
	public void setPushDeviceDao(PushDeviceDao pushDeviceDao) {
		this.pushDeviceDao = pushDeviceDao;
	}

}
