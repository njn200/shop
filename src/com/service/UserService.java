package com.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.LogsDao;
import com.dao.UsersDao;
import com.entity.Logs;
import com.entity.Users;
import com.util.BrowserUtils;
import com.util.ContextHolderUtils;
import com.util.DataUtils;
import com.util.SafeUtil;
import com.util.oConvertUtils;

/**
 * 用户服务
 */
@Service	// 注解为service层spring管理bean
@Transactional	// 注解此类所有方法加入spring事务, 具体设置默认
public class UserService {

	@Autowired		//spring注入类对象
	private UsersDao userDao;
	
	@Autowired		//spring注入类对象
	private LogsDao logsDao;
	
	/**
	 * 验证用户密码
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password){
		
		return userDao.getByUsernameAndPassword(username, SafeUtil.encode(password)) != null;
	}

	/**
	 * 用户是否存在
	 * @param username
	 * @return
	 */
	public boolean isExist(String username) {
		return userDao.getByUsername(username) != null;
	}

	/**
	 * 添加
	 * @param user
	 * @return
	 */
	public boolean add(Users user) {
		user.setPassword(SafeUtil.encode(user.getPassword()));
		return userDao.insert(user) > 0;
	}
	
	/**
	 * 通过id获取
	 * @param userid
	 * @return
	 */
	public Users get(int userid){
		return userDao.selectById(userid);
	}
	
	/**
	 * 通过username获取
	 * @param username
	 * @return
	 */
	public Users get(String username){
		return userDao.getByUsername(username);
	}
	
	/**
	 * 列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Users> getList(int page, int rows) {
		return userDao.getList(rows * (page-1), rows);
	}
	
	/**
	 * 通过名称搜索列表
	 * @return
	 */
	public List<Users> getListByName(String name) {
		return userDao.getListByName(name);
	}

	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return userDao.getTotal();
	}

	/**
	 * 更新
	 * @param user
	 */
	public boolean update(Users user) {
		return userDao.updateById(user) > 0;
	}

	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Users user) {
		return userDao.deleteById(user.getId()) > 0;
	}
	
   //添加日志
	public  void  AddLogs(String message, Integer  uid, Short  log_Type_LOGIN, Short  log_Leavel_INFO,Integer user_user) {
		
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		Logs log=new Logs();
		log.setBroswer(broswer);
		log.setLoglevel(log_Leavel_INFO);
		log.setOperatetype(log_Type_LOGIN);
		log.setOperatetime(DataUtils.gettimestamp());
		log.setIp(oConvertUtils.getIp());
		log.setUserid(uid.toString());
		log.setRoleid(user_user);
		log.setLogcontent(message);
		logsDao.insert(log);
		
		
		
	}
}
