package com.web.controller.version;

import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.dict.Constant2;
import com.io.hw.file.util.FileUtils;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.util.JSONPUtil;
import com.view.PaperNewsView;
import com.web.controller.comm.ConfigController;
import oa.dao.common.CommonDictionaryDao;
import oa.entity.common.AccessLog;
import oa.entity.common.CommonDictionary;
import oa.service.DictionaryParam;
import oa.web.controller.base.BaseController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/***
 * apk 版本管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/version")
public class VersionController  extends BaseController {
	public static final String GROUPID_CLIENT_VERSION="client_version";
	private CommonDictionaryDao commonDictionaryDao;

	@ResponseBody
	@RequestMapping(value = "/add", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String add(Model model, String latestversion, String md5,String path,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request) throws IOException {
		Map map = new HashMap();
		int login_result = 0;
		if(ValueWidget.isNullOrEmpty(latestversion)||ValueWidget.isNullOrEmpty(md5)||ValueWidget.isNullOrEmpty(path)){
			login_result=Constant2.ERROR_CODE_NEED_ARGUMENT;
		}else{
			CommonDictionary latestversionDictionary=commonDictionaryDao.getDictionary(GROUPID_CLIENT_VERSION, "latestversion");
			CommonDictionary pathDictionary=commonDictionaryDao.getDictionary(GROUPID_CLIENT_VERSION, "path");
			CommonDictionary md5Dictionary=commonDictionaryDao.getDictionary(GROUPID_CLIENT_VERSION, "md5");
			latestversionDictionary.setValue(latestversion);
			latestversionDictionary.setDescription(latestversion);
			pathDictionary.setValue(path);
			pathDictionary.setDescription(path);
			md5Dictionary.setValue(md5);
			md5Dictionary.setDescription(md5);
			this.commonDictionaryDao.update(latestversionDictionary);
			this.commonDictionaryDao.update(pathDictionary);
			this.commonDictionaryDao.update(md5Dictionary);
			login_result=Constant2.LOGIN_RESULT_SUCCESS;
			//刷新字典
			WebApplicationContext webApp=RequestContextUtils.getWebApplicationContext(request	, request.getSession().getServletContext());
			DictionaryParam dictionaryParam=(DictionaryParam)webApp.getBean("dictionaryParam");
			dictionaryParam.refresh2();
		}
		map.put(Constant2.LOGIN_RESULT_KEY, login_result);
		String content = HWJacksonUtils.getJsonP(map);
		return content;
	}
	
	/***
	 * {"latestversion":"a","path":"c","md5":"b"}
	 * 
{"latestversion":"17",
"path":"20150618005257_383Lovejianli17_new.apk",
"md5":"1c4cf807779eb3bb680cf184e9cf4a3e",
"force":"true"}
	 * @param model
	 * @param latestversion
	 * @param md5
	 * @param path
	 * @param view
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model, String latestversion, String md5,String path,
			PaperNewsView view, HttpSession session,
			HttpServletRequest request) throws IOException {
		Map map=DictionaryParam.get(GROUPID_CLIENT_VERSION);
		Map<String, String> configMap = ConfigController.getConfigMap();
		map.putAll(configMap);
		String content = HWJacksonUtils.getJsonP(map);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("获取版本信息");
		accessLog.setOperateResult(content);
		logSave(accessLog, request);
		return content;
	}
	public CommonDictionaryDao getCommonDictionaryDao() {
		return commonDictionaryDao;
	}
	
	@Resource
	public void setCommonDictionaryDao(CommonDictionaryDao commonDictionaryDao) {
		this.commonDictionaryDao = commonDictionaryDao;
	}

	/***
	 * 下载apk
	 * @param path
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download"/*, headers = {"content-type=application/json"}*/)
    public ResponseEntity<byte[]> download( String path,HttpServletRequest request) throws IOException {
    	AccessLog accessLog=logInto(request);
		accessLog.setDescription("下载客户端");
    	if(!ValueWidget.isNullOrEmpty(request.getContentType())&& request.getContentType().toLowerCase().contains("application/json")){
	    	String requestStr=WebServletUtil.getRequestQueryStr(request, null);
	    	System.out.println(requestStr);
	    	Map queryMap=JSONPUtil.getMapFromJson(requestStr);
	    	if(!ValueWidget.isNullOrEmpty(queryMap)){
	    		path=(String) queryMap.get("path");
	    	}
    	}
    	if(ValueWidget.isNullOrEmpty(path)){
    		System.out.println("download failed");
    		accessLog.setOperateResult("下载失败,没有传递path参数");
    		logSave(accessLog, request);
    		return null;
    	}
    	String realpath =WebServletUtil.getUploadPath(request, "upload/download/apk", request
				.getSession().getServletContext(), Constant2.SRC_MAIN_WEBAPP);
    	if(!realpath.endsWith(File.separator)){
    		realpath=realpath+File.separator;
    	}
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fullpath=realpath+path;
        
        System.out.println("download path:"+fullpath);
        headers.set(Constant2.CONTENT_DISPOSITION,WebServletUtil.getContentDisposition(true, path));
        accessLog.setOperateResult("下载成功,下载文件:"+fullpath+" ,size:"+FileUtils.getFileSize2(fullpath));
		logSave(accessLog, request);
        return new ResponseEntity<byte[]>(FileUtils.getBytes4File(fullpath),
                                          headers, HttpStatus.OK);
    }

	@Override
    protected void beforeAddInput(Model model, HttpServletRequest request) {
    }

	@Override
	protected void errorDeal(Model model) {
	}

	@Override
	public String getJspFolder() {
		return null;
	}

}
