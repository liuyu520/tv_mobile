package com.web.controller;

import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.dao.HouseBuildingDao;
import com.dao.VoteDao;
import com.dao.VoteLogDao;
import com.dict.Constant2;
import com.entity.User;
import com.entity.Vote;
import com.entity.VoteLog;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import oa.util.AuthenticateUtil;
import oa.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/vote")
public class VoteController extends BaseController<Vote>{
	private VoteDao voteDao;
	private VoteLogDao voteLogDao;
	private HouseBuildingDao houseBuildingDao;
	
	
	public VoteLogDao getVoteLogDao() {
		return voteLogDao;
	}
	@Resource
	public void setVoteLogDao(VoteLogDao voteLogDao) {
		this.voteLogDao = voteLogDao;
	}
	
	public HouseBuildingDao getHouseBuildingDao() {
		return houseBuildingDao;
	}
	@Resource
	public void setHouseBuildingDao(HouseBuildingDao houseBuildingDao) {
		this.houseBuildingDao = houseBuildingDao;
	}
	@Override
	protected void beforeAddInput(Model model,HttpServletRequest request) {
		
	}
	@Override
	protected void errorDeal(Model model) {
		
	}
	@Override
	public String getJspFolder() {
		return null;
	}
	/***
	 * 
	 * @param model
	 * @param type
	 * @param houseBuildingId
	 * @param session
	 * @param request
	 * @param callback
	 * @return :result:2--未登录;3--已经投票过
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/vote", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonVote(Model model, int type/*投票的类型*/,String houseBuildingId, HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		User user2 = (User) session.getAttribute("user");
		Map map=new HashMap();
		if(!AuthenticateUtil.isLogined(session)){
			map.put(Constant2.LOGIN_RESULT_KEY, Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET);//没有登录
			return HWJacksonUtils.getJsonP(map, callback);
		}
		VoteLog voteLogTmp=this.voteLogDao.get("type", type, "user.id", user2.getId());
		
		map.put("type", type);
		int houseBuildingIdInt = 0;
		if(!ValueWidget.isNullOrEmpty(houseBuildingId)){
			houseBuildingIdInt =Integer.parseInt(WebServletUtil.dealWithJsessionid(houseBuildingId).trim()) /*.replaceAll(";jsessionid=[\\w]+$", "")*/;
		}
		init(request);
		voteDao=(VoteDao)getDao();
		if(voteLogTmp==null){
			
			long voteCount=this.voteDao.updateVote(type, houseBuildingIdInt);
			VoteLog voteLog=new VoteLog();
			voteLog.setHouseBuilding(this.houseBuildingDao.get(houseBuildingIdInt));
			
			voteLog.setUser(user2);
			voteLog.setType(type);
			voteLog.setVoteTime(TimeHWUtil.getCurrentFormattedTime());
			this.voteLogDao.save(voteLog);
			map.put("voteCount", voteCount);
			
			map.put(Constant2.LOGIN_RESULT_KEY, Constant2.LOGIN_RESULT_SUCCESS);
		}else{
			//查询投票数
			Vote vote=this.voteDao.get("type", type, "houseBuilding.id", houseBuildingIdInt);
			map.put("voteCount", vote==null?0:vote.getVoteCount());
			map.put(Constant2.LOGIN_RESULT_KEY, 3);//已经投票过
		}
		map.put("houseBuildingId", houseBuildingIdInt);
		return HWJacksonUtils.getJsonP(map, callback);
	}

	/***
	 * 投票结果
	 * @param model
	 * @param type
	 * @param houseBuildingId
	 * @param session
	 * @param request
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/result", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
	public String jsonVoteResult(Model model,Integer type/*投票的类型*/ ,/*String houseBuildingId,*/ HttpSession session,
			HttpServletRequest request, String callback) throws IOException {
		Map map=new HashMap();
		if(!AuthenticateUtil.isLogined(session)){
			map.put(Constant2.LOGIN_RESULT_KEY, Constant2.MODIFY_PASS_RESULT_NOT_LOGINED_YET);//没有登录
			return HWJacksonUtils.getJsonP(map, callback);
		}
		init(request);
		voteDao=(VoteDao)getDao();
		if(type==null){
			type=1;
		}
		List<Vote> votes=this.voteDao.getVoteResult(type);
		if(votes==null){
			votes=new ArrayList<Vote>();
		}
		map.put(Constant2.LOGIN_RESULT_KEY, Constant2.LOGIN_RESULT_SUCCESS);
		map.put("votes", votes);
		map.put("type", type);
		return HWJacksonUtils.getJsonP(map, callback);
	}
}
