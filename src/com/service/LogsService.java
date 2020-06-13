package com.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminsDao;
import com.dao.LogsDao;
import com.dao.UsersDao;
import com.entity.Admins;
import com.entity.Logs;
import com.entity.Users;
import com.util.BrowserUtils;
import com.util.ContextHolderUtils;
import com.util.DataUtils;

import com.util.oConvertUtils;

/**
 * 用户服务
 */
@Service	// 注解为service层spring管理bean
@Transactional	// 注解此类所有方法加入spring事务, 具体设置默认
public class LogsService {

	
	@Autowired		//spring注入类对象
	private LogsDao logsDao;
	
	@Autowired		//spring注入类对象
	private AdminsDao adminsDao;
	
	
	@Autowired		//spring注入类对象
	private UsersDao usersDao;
	
	
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

	/**
	 * 列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Logs> getListOperateList(int page, int rows) {
		
		List<Logs> loglist= logsDao.getListOperateList(rows * (page-1), rows);
		
		 for(int i=0;i<loglist.size();i++){
		 
			 Logs log=loglist.get(i);
			 Admins admin= adminsDao.selectById(Integer.parseInt(log.getUserid()));
			 log.setAdmins(admin);
			 loglist.set(i, log);
		 }
		
		  return loglist;
	}
	
	
	public List<Logs> getListClientOperateList(int page, int rows) {
		
		List<Logs> loglist= logsDao.getListClientOperateList(rows * (page-1), rows);
		
		 for(int i=0;i<loglist.size();i++){
		 
			 Logs log=loglist.get(i);
			 Users user= usersDao.selectById(Integer.parseInt(log.getUserid()));
			 log.setUsers(user);

			 loglist.set(i, log);
		 }
		
		  return loglist;
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getNotClientTotal() {
		// TODO Auto-generated method stub
		return logsDao.getTotalNotClient();
	}
	public long getClientTotal() {
		// TODO Auto-generated method stub
		return logsDao.getTotalClient();
	}
	
}
