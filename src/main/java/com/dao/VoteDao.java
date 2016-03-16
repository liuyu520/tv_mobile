package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.entity.Vote;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;

/***
 * @author huangweii
 *         2015年9月30日
 */
@Component
public class VoteDao extends GenericDao<Vote> {
	private HouseBuildingDao houseBuildingDao;
	/***
	 * 
	 * @param type
	 * @param houseBuildingId
	 * @return : 投票数
	 */
	public long updateVote(int type,int houseBuildingId){
//		Vote vote=super.get("type", type,"houseBuilding.id",houseBuildingId);
		Vote vote=(Vote) super.getCurrentSession().createCriteria(clz)
		.add(Restrictions.eq("type", type))
		.createAlias("houseBuilding", "houseBuilding222")
		.add(Restrictions.eq("houseBuilding222.id", houseBuildingId))
//		.add(Restrictions.eq("houseBuilding.id",houseBuildingId))
		.uniqueResult();
		long voteCount=0;
		if(ValueWidget.isNullOrEmpty(vote)){//数据库中没有
			vote=new Vote();
			vote.setType(type);
			vote.setVoteCount(1);
			vote.setHouseBuilding(this.houseBuildingDao.get(houseBuildingId));
			vote.setUpdateTime(TimeHWUtil.getCurrentFormattedTime());
			save(vote);
			voteCount=1;
		}else{
			super.getCurrentSession().createQuery("update Vote v set v.voteCount=v.voteCount+1,v.updateTime='"+TimeHWUtil.getCurrentFormattedTime()+"' where v.type=:type and v.houseBuilding.id=:houseBuildingId and v.id=:id")
			.setInteger("type", type)
			.setInteger("houseBuildingId", houseBuildingId)
			.setInteger("id", vote.getId())
			.executeUpdate();
			voteCount=vote.getVoteCount()+1;
		}
		System.out.println("voteCount:"+voteCount);
		return voteCount;
	}
	public List<Vote> getVoteResult(){
		return super.getAll("type", "asc", "type", "desc", "voteCount");
	}

	/***
	 * 1:最宜居<br>
	 * 2:最优户型<br>
	 * 3:最佳物业
	 * @param type
	 * @return
	 */
	public List<Vote> getVoteResult(int type){
		/*Map condition=new HashMap();
		condition.put("type", type);*/
		return super.find("type", type, "desc", "voteCount", null, null);
	}
	
	public HouseBuildingDao getHouseBuildingDao() {
		return houseBuildingDao;
	}
	@Resource
	public void setHouseBuildingDao(HouseBuildingDao houseBuildingDao) {
		this.houseBuildingDao = houseBuildingDao;
	}
	
}
