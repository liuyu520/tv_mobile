package com.web.controller;

import com.common.util.ImageHWUtil;
import com.common.util.PageUtil;
import com.common.util.SortList;
import com.common.util.SystemHWUtil;
import com.common.web.view.PageView;
import com.dao.PicNewsOneDao;
import com.dict.Constant2;
import com.entity.PicNews;
import com.entity.PicNewsOne;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import com.util.JSONPUtil;
import oa.entity.common.AccessLog;
import oa.service.DictionaryParam;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 移动端首页轮播图
 * @author huangwei
 * @since 2015年3月12日
 */
@Controller
@RequestMapping("/pic_news")
public class PicNewsController extends BaseController<PicNews> {
	private String label = "pic_news";
	private PicNewsOneDao picNewsOneDao;
	
	@ResponseBody
	@RequestMapping(value = "/json",produces=SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String json(Model model,  Integer status,
			/*CarouselDiagramView view, */HttpSession session, HttpServletRequest request,String callback)
			throws IOException {
		init(request);
		/*Map map = new HashMap();
		Map condition = new HashMap();
		
*/
//		PageUtil.paging(condition, view, getDao(), "desc", "uploadTime", null,
//				null, null);
//		List<CarouselDiagram> carouselDiagrams=getDao().getAll(null, "desc", "uploadTime");
		Map condition=new HashMap();
		if (status != null) {
			condition.put("status", status);
		}
		List<PicNews> picNews=getDao().find(condition, "desc", "uploadTime", null, null);
		int size=picNews.size();
		//过滤掉PicNewsOnes 为空的picNews
		for(int i=0;i<size;i++){
			PicNews picNews22=picNews.get(i);
			Set PicNewsOneset=picNews22.getPicNewsOnes();
			if(ValueWidget.isNullOrEmpty(PicNewsOneset)|| PicNewsOneset.size()<DictionaryParam.getGlobalSettingInt("pic_min_num")){
				picNews.remove(picNews22);
				i--;
				size--;
			}
		}
		size=picNews.size();
		for(int i=0;i<size;i++){
			PicNews picNews22=picNews.get(i);
			List<PicNewsOne>picNewsOnes=SystemHWUtil.set2List(picNews22.getPicNewsOnes());//set-->List
			if(ValueWidget.isNullOrEmpty(picNewsOnes)){
				continue;
			}
			SortList<PicNewsOne> sortList=new SortList<PicNewsOne>();//根据UploadTime 排序
			sortList.sort(picNewsOnes, "getUploadTime", "asc");
			int picNewsOneSize=picNewsOnes.size();
			for(int j=0;j<picNewsOneSize;j++){
				PicNewsOne picNewsOne33=picNewsOnes.get(j);
				picNewsOne33.setPicPath(JSONPUtil.getPicPath(picNewsOne33.getPicPath()));
			}
			picNews22.setPicNewsOneList(picNewsOnes);
		}
		
		/*List data = view.getRecordList();
		map.put(Constant2.JSON_RETURN_CURRENTPAGE, view.getCurrentPage());
		map.put(Constant2.JSON_RETURN_LENGTH, data.size());
		map.put(Constant2.JSON_RETURN_SUM, view.getTotalRecords());
		map.put(Constant2.JSON_RETURN_OVER,
				view.getCurrentPage() >= view.getTotalPages());
		map.put("recordList", data);*/
//		setJsonPaging(map, view);
		String content;
		content=HWJacksonUtils.getJsonP(picNews, callback);
		AccessLog accessLog=logInto(request);
		accessLog.setDescription("手机端图文列表");
		logSave(accessLog, request);
		return content;
	}
	/***
	 * 用于后台管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list2")
	public String list_pic(Model model,PageView view,HttpSession session,String targetView,HttpServletRequest request){
		/*if(!ValueWidget.isNullOrEmpty(view.getPageFlag())&&view.getPageFlag().equals(Constant2.PAGEFLAG_NOT_QUERY)){
			
		}else{//查询
			System.out.println("是查询");
			session.setAttribute(sessionKey, product);
		}*/
		Object recordsPerPageObj=DictionaryParam.get("pic_news", "recordsPerPage");
		if(recordsPerPageObj!=null){
			int recordsPerPage;
			if(recordsPerPageObj instanceof String){
				recordsPerPage=Integer.parseInt((String)recordsPerPageObj);
			}else{
				recordsPerPage=(Integer)recordsPerPageObj;
			}
			view.setRecordsPerPage(recordsPerPage);
		}
		
		int start = (view.getCurrentPage() - 1)
				* view.getRecordsPerPage();
		
		Map condition=new HashMap();
		condition.put("status", Constant2.STATUS_ACTIVE);
		long count = this.picNewsOneDao.count(condition);
		List<Object[]> picNewsOnes=this.picNewsOneDao.getPicNewsOnes(start+","+view.getRecordsPerPage());
		int picNewsSize=picNewsOnes.size();
		for(int i=0;i<picNewsSize;i++){
			Object[] objArr=picNewsOnes.get(i);
			String picPath=(String)objArr[1];
//			System.out.println(objArr[6]);
			if(ValueWidget.isNullOrEmpty(objArr[5])&&ValueWidget.isNullOrEmpty(objArr[6])){
				
				if(!ValueWidget.isNullOrEmpty(picPath)){
					if(!picPath.startsWith("http://")){
						String absulotePrefix=DictionaryParam.get(Constant2.DICTIONARY_GROUP_GLOBAL_SETTING, "uploadAbsulotePrefix");
						String absulotePath=picPath.replaceAll("^\\.", absulotePrefix);
						File imageFile=new File(absulotePath);
						if(imageFile.exists()){
							Integer id=(Integer)objArr[0];
							try {
								BufferedImage image= ImageHWUtil.inputImage(imageFile);
								int width=image.getWidth();
								int height=image.getHeight();
								objArr[5]=width;
								objArr[6]=height;
								if(objArr[5]!=null&&(Integer)objArr[5]!=0){
									System.out.println("save image width:"+width+" ,  height:"+height);
									picNewsOneDao.updateWidthHight(id, width, height);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						System.out.println(absulotePath);
					}
				}
			}
			objArr[1]=JSONPUtil.getPicPath(picPath);
		}
		view.setRecordList(picNewsOnes);
		view.setRecordNumOfCurrent(picNewsOnes.size());
		PageUtil.paging(count, view);
		
		model.addAttribute("view", view);
		model.addAttribute("currentTime", TimeHWUtil.getCurrentTimestamp().getTime());
		model.addAttribute("label", label);
		if(!ValueWidget.isNullOrEmpty(targetView)){
			return targetView;
		}
		return label+"/index";
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
	public PicNewsOneDao getPicNewsOneDao() {
		return picNewsOneDao;
	}
	@Resource
	public void setPicNewsOneDao(PicNewsOneDao picNewsOneDao) {
		this.picNewsOneDao = picNewsOneDao;
	}
	protected String getListView(){
		return "/index";
	}

	@Override
	protected void beforeList(PicNews picNews) {
		picNews.setStatus(Constant2.NEWS_STATUS_ON);//额外的条件
		super.beforeList(picNews);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		AccessLog accessLog = logInto(request);
		accessLog.setDescription("list picNews");
		accessLog.setOperateResult("list test conditon:" + HWJacksonUtils.getJsonP(picNews));
		logSave(accessLog, request);
	}
}
