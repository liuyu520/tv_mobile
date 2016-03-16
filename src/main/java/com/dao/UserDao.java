package com.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.entity.User;
import com.string.widget.util.ValueWidget;
@Component
public class UserDao extends GenericDao<User> {
	/***
	 * 
	 * @param username : 登录账号
	 * @return
	 */
	public User getByUsername(String username){
		User user = (User) super.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
		return user;
	}
	/***
	 * 
	 * @param username : 登录账号
	 * @param password : 密码
	 * @return
	 */
	public User getByUsernameAndPasswd(String username,String password){
		User user = (User) super.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).uniqueResult();
		return user;
	}
	/***
	 * 
	 * @param username
	 * @param password
	 * @param status : 0:审核中;1:激活,2:失效(不能登录)
	 * @return
	 */
	public User get(String username,String password,int status){
		User user = (User) super.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password))
				.add(Restrictions.eq("status", status)).uniqueResult();
		return user;
	}
	/***
	 * 检查用户是否存在
	 * @param username
	 * @return
	 */
	public boolean isExist(String username){
		User user=getByUsername(username);
		if(!ValueWidget.isNullOrEmpty(user)&&!ValueWidget.isNullOrEmpty(user.getUsername())){
			return true;
		}else{
			return false;
		}
	}
	/***
	 * 检查用户是否存在
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean isExist(String username,String password){
		User user=getByUsernameAndPasswd(username,password);
		if(!ValueWidget.isNullOrEmpty(user)&&!ValueWidget.isNullOrEmpty(user.getUsername())){
			return true;
		}else{
			return false;
		}
	}
	/***
	 * 修改会员密码
	 * <br>需要权限验证
	 * @param userId
	 * @param new_password
	 */
	public int modifyPass(int userId,String new_password){
		String hql="update User u set u.password=:new_password where id=:id";
		return this.getCurrentSession().createQuery(hql).setString("new_password", new_password)
		.setInteger("id", userId)
		.executeUpdate();
	}
	/***
	 * 修改会员密码
	 * @param userId
	 * @param new_password : 新密码
	 */
	public int modifyPass(int userId,String old_password,String new_password){
		String hql="update User u set u.password=:new_password where id=:id and password=:old_password";
		return this.getCurrentSession().createQuery(hql).setString("new_password", new_password)
		.setString("old_password", old_password)
		.setInteger("id", userId)
		.executeUpdate();
	}
	/***
	 * 
	 * @param id
	 * @return : String
	 */
	public String getUserNameById(int id){
		return getStringById(id, "username");
	}
}
