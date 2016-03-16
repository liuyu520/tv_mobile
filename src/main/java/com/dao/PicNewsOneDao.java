package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.entity.PicNewsOne;
/***
 * 
 * @author huangwei
 * @since 2015年3月12日
 */
@Component
public class PicNewsOneDao extends GenericDao<PicNewsOne> {
	/***
	 * 返回前front 个图文,并且pic_news_id不重复
	 * @param condition
	 * @param front
	 * @return
	 */
	public List<Object[]> getFrontPicNewsOnes(/*Map condition,*/int front){
//		Criteria criteria=super.getCriteriaByPage(condition, 0, front, true);
//		criteria.setFetchMode("picNews", FetchMode.LAZY);
//		criteria.setProjection( Projections.groupProperty("picNews") );
//		return super.getCurrentSession().createQuery("select one.* from PicNewsOne one group by PicNews").list();
//		return criteria.list();
		String sql="select pic_path ,desc2,upload_time,pic_news_id from t_pic_news_one where status=1 group by pic_news_id order by upload_time desc limit "+front;
		List<Object[]> objs=super.getCurrentSession().createSQLQuery(sql).list();
		return objs;
	}
	public List<Object[]> getPicNewsOnes(/*Map condition,*/String front){
		String sql="select id,pic_path ,desc2,upload_time,pic_news_id,real_width,real_height from t_pic_news_one where status=1  order by pic_news_id desc,upload_time asc limit "+front;
		List<Object[]> objs=super.getCurrentSession().createSQLQuery(sql).list();
		return objs;
	}
	public int updateWidthHight(int id,int width,int height){
		if(width==0||height==0){
			System.out.println("width:"+width+" , height:"+height);
			return -1;
		}
		String sql="update t_pic_news_one set real_width="+width+",real_height="+height+"  where id= "+id;
		return super.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}
