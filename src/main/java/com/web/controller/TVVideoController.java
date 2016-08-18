package com.web.controller;

import com.common.util.PageUtil;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.common.web.view.PageView;
import com.dict.Constant2;
import com.entity.TVVideo;
import com.io.hw.file.util.FileUtils;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.util.JSONPUtil;
import com.view.TVVideoView;
import oa.entity.common.AccessLog;
import oa.entity.common.CommonDictionary;
import oa.service.DictionaryParam;
import oa.web.controller.base.BaseController;
import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class TVVideoController extends BaseController<TVVideo> {
	private String label = "video";
	private SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
			.serializeAllExcept("content");
	private SimpleFilterProvider simpleFilterProvider=new SimpleFilterProvider();
	
	public static String getJsonP(Object map,String callback)
	{
		SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
				.serializeAllExcept((String)null);
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				Constant2.SIMPLEFILTER_JACKSON_BROADCAST, theFilter);
		return HWJacksonUtils.getJsonP(map, callback, filters);
	}

	@Override
	protected void beforeAddInput(Model model,HttpServletRequest request) {

	}

	@Override
	protected void errorDeal(Model arg0) {

	}

	@Override
	public String getJspFolder() {
		return label;
	}

	/*@ResponseBody
	@RequestMapping(value = "/json")
	public ModelAndView json(Model model, Integer type, Integer status,
			TVVideoView view, HttpSession session, HttpServletRequest request)
			throws IOException {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (type != null) {
			condition.put("type", type);
		}
		if (status != null) {
			condition.put("status", status);
		}

		PageUtil.paging(condition, view, getDao(), "desc", "releaseTime", null,
				null, null);
		List data = view.getRecordList();
		map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		map.put(Constant2.JSON_RETURN_OVER,
				view.getCurrentPage() >= view.getTotalPages());
		map.put("recordList", data);
		// model.addAttribute("view", view);
		// model.addAttribute("currentTime",
		// TimeHWUtil.getCurrentTimestamp().getTime());
		model.addAttribute("TVVideoView", null);
		MappingJackson2JsonView mappingJacksonJsonView = new MappingJackson2JsonView();
		ModelAndView modelAndView = new ModelAndView(mappingJacksonJsonView,
				map);
		return modelAndView;
	}*/
	@ResponseBody
	@RequestMapping(value = "/json",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model, Integer type, Integer status,Integer columnType,
			TVVideoView view, HttpSession session, HttpServletRequest request,String callback)
			throws IOException {
		init(request);
		Map map = new HashMap();
		Map condition = new HashMap();
		if (type != null) {
			condition.put("type", type);
		}
		if (status != null) {
			condition.put("status", status);
		}
		if (columnType != null) {
			condition.put("columnType", columnType);
		}

		PageUtil.paging(condition, view, getDao(), "desc", "releaseTime", null,
				null, null);
		List<TVVideo> list=view.getRecordList();
		listCommonAction(list, false);
		setJsonPaging(map, view);
		// model.addAttribute("view", view);
		// model.addAttribute("currentTime",
		// TimeHWUtil.getCurrentTimestamp().getTime());
//		model.addAttribute("TVVideoView", null);
//		MappingJackson2JsonView mappingJacksonJsonView = new MappingJackson2JsonView();
//		ModelAndView modelAndView = new ModelAndView(mappingJacksonJsonView,
//				map);
		String content;
		FilterProvider filters = simpleFilterProvider.addFilter(
				Constant2.SIMPLEFILTER_JACKSON_BROADCAST, theFilter);
		content=HWJacksonUtils.getJsonP(map, callback,filters);
		AccessLog accessLog=logInto(request);
		if(!ValueWidget.isNullOrEmpty(accessLog)){
			accessLog.setDescription("手机端视频列表");
			accessLog.setOperateResult("总条数:"+view.getTotalRecords());
			logSave(accessLog, request);
		}
		return content;
	}

	@ResponseBody
	@RequestMapping(value = "/json_detail", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonDetail(Model model, int id/*新闻的id*/,
			TVVideoView view, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		init(request);
		TVVideo tVVideo=(TVVideo) getDao().get(id);
		detailCommonAction(tVVideo);
		String content;

		content = getJsonP(tVVideo, callback);
		AccessLog accessLog=logInto(request);
		String videoPath=tVVideo.getPath();
		accessLog.setDescription("手机端点播详情:"+videoPath);
		accessLog.setOperateResult(videoPath);
		logSave(accessLog, request);
		return content;
	}

	@ResponseBody
	@RequestMapping(value = "/json2",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF )
	public String upload(HttpServletRequest request, HttpServletResponse response,String contentType2)
			throws IOException {
		String content = null;
		Map map = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		map.put("fileName", "a.txt");
		try {
			content = mapper.writeValueAsString(map);
			System.out.println(content);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if("json".equals(contentType2)){
			response.setContentType(SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF);
		}
		return content;

	}
	@ResponseBody
	@RequestMapping(value = "/text",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JAVASCRIPT2 )
	public String text1(HttpServletRequest request, HttpServletResponse response,String contentType2,String callback)
			throws IOException {
		String content = null;
		Map map = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		map.put("fileName", "a.txt");
		try {
			content = mapper.writeValueAsString(map);
			System.out.println(content);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if("json".equals(contentType2)){
			response.setContentType(SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF);
		}
		return callback+"("+content+")";

	}
	@ResponseBody
	@RequestMapping(value = "/text2",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JAVASCRIPT2 )
	public String text2(HttpServletRequest request, HttpServletResponse response,String contentType2,String callback)
			throws IOException {
		String content = null;
		Map map = new HashMap();

		map.put("fileName", "a.txt");
		content=HWJacksonUtils.getJsonP(map, callback);
		System.out.println(content);
		return content;

	}
	
	@ResponseBody
	@RequestMapping(value = "/download",produces="image/jpeg")
	public byte[] downloadFile(HttpServletRequest request, HttpServletResponse response,String contentType2,boolean isInline)
			throws IOException {
		byte[]bytes=FileUtils.getBytes4File("D:\\Temp\\cc.jpg");
//		response.addHeader("Content-Disposition", downloadType+";filename=\"a.jpg\"");
		WebServletUtil.setDownloadContentDisposition(isInline, "c.jpg", response);
		return bytes;

	}
    @RequestMapping(value = "/download3"/*,produces="image/jpeg"*/)
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
//        headers.setContentDispositionFormData("inline", "dict.jpg");//attachment
        headers.set(Constant2.CONTENT_DISPOSITION,WebServletUtil.getContentDisposition(true, "dict.jpg"));
        return new ResponseEntity<byte[]>(FileUtils.getBytes4File("D:\\Temp\\cc.jpg"),
                                          headers, HttpStatus.CREATED);
    }

	@Override
	protected void listTODO(Model model, PageView view,
			HttpServletRequest request) {
		super.listTODO(model, view, request);
		List<TVVideo> list=view.getRecordList();
		List<CommonDictionary> commonDictionaries=DictionaryParam.getList(Constant2.DICTIONARY_GROUPID_BROADCAST_GROUP);
		//返回:{"3":"足球","2":"体育","1":"娱乐","7":"篮球2","6":"篮球","5":"NBA22","4":"NBA","9":"篮球231","8":"篮球23"}
		model.addAttribute("commonDictionaries", commonDictionaries);
		listCommonAction(list, true);
	}

	protected void listCommonAction(List<TVVideo> list,boolean isSetContentLength){
		int size=list.size();
		
		for(int i=0;i<size;i++){
			TVVideo tVVideo=list.get(i);
			String picPath=tVVideo.getTitlePic();
			String videoPath=tVVideo.getPath();
			if(!ValueWidget.isNullOrEmpty(picPath)){
				tVVideo.setTitlePic(JSONPUtil.getPicPath(picPath));
			}
			String absulotePrefix=DictionaryParam.get(Constant2.DICTIONARY_GROUP_GLOBAL_SETTING, "uploadAbsulotePrefix");
			if(!ValueWidget.isNullOrEmpty(videoPath)){
				if(!videoPath.startsWith("http://")){
					String absulotePath=videoPath.replaceAll("^\\.", absulotePrefix);
					String videoSize=FileUtils.formatFileSize2(absulotePath,1,false);
					tVVideo.setBroadcastSize(videoSize);
//					File file=new File(absulotePath);
					/*if(!file.exists()){
						list.remove(tVVideo);
						size=size-1;
						i=i-1;
						continue;
					}*/
				}
			}/*else{
				list.remove(tVVideo);
				size=size-1;
				i=i-1;
				continue;
			}*/
			String path=tVVideo.getPath();
			if(!ValueWidget.isNullOrEmpty(path)){
				tVVideo.setPath(JSONPUtil.getVideoPath(path));
			}
			if(isSetContentLength){
				String content=tVVideo.getContent();
				tVVideo.setContent(SystemHWUtil.splitAndFilterString(content, DictionaryParam.getInt("broadcast_settings", "content_max")));
			
			}
			
		}
		try {
			TimeHWUtil.formatTime(list, "releaseTime", "releaseTimeStr");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected TVVideo detailTODO(int id,  Model model,
			HttpServletRequest request) {
		TVVideo tVVideo=super.detailTODO(id, model, request);
		detailCommonAction(tVVideo);
		return tVVideo;
	}
	protected void detailCommonAction(TVVideo tVVideo){
		if(ValueWidget.isNullOrEmpty(tVVideo)){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			logger.error("tVVideo is null:"+request.getRequestURL());
			return;
		}
		String picPath=tVVideo.getTitlePic();
		if(!ValueWidget.isNullOrEmpty(picPath)){
			tVVideo.setTitlePic(JSONPUtil.getPicPath(picPath));
		}
		String path=tVVideo.getPath();
		if(!ValueWidget.isNullOrEmpty(path)){
			tVVideo.setPath(JSONPUtil.getVideoPath(path));
		}
		tVVideo.setReleaseTimeStr(TimeHWUtil.formatSecondTime(tVVideo.getReleaseTime()));
	}
	
	@Override
	public ListOrderedMap getListOrderBy() {
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("releaseTime", "desc");
		return orderColumnModeMap;
	}
	protected String getListView(){
		return "/index";
	}

	@Override
	protected void beforeList(TVVideo roleLevel) {
		roleLevel.setStatus(Constant2.NEWS_STATUS_ON);//额外的条件
		super.beforeList(roleLevel);

		/*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		AccessLog accessLog = logInto(request);
		accessLog.setDescription("list test");
		accessLog.setOperateResult("list test conditon:" + HWJacksonUtils.getJsonP(roleLevel));
		logSave(accessLog, request);*/
	}
}
