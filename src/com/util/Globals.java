package com.util;




/**  
* 项目名称：jeecg
* 类名称：Globals   
* 类描述：  全局变量定义
* 创建人：        
* @version    
*
 */
public final class Globals {
	/**
	 * 保存用户到SESSION
	 */
	public static String USER_SESSION="USER_SESSION";
	/**
	 * 人员类型
	 */
	public static Integer User_admin=1;//管理员
	public static Integer User_salesman=0;//禁用
	public static Integer User_user=2;//超级管理员
	/**
	 *日志级别定义
	 */
	public static Short Log_Daliy=1;
	public static Short Log_Shopping=2;
	public static Short log_putong=3;
	 /**
	  * 日志类型
	  */
	 public static Short Log_Type_LOGIN=1; //登陆
	 public static Short Log_Type_EXIT=2;  //退出
	 public static Short Log_Type_INSERT=3; //插入
	 public static Short Log_Type_DEL=4; //删除
	 public static Short Log_Type_UPDATE=5; //更新
	 public static Short Log_Type_UPLOAD=6; //上传
	 public static Short Log_Type_OTHER=7; //其他
	 
	 
	 /**
	  * 词典分组定义
	  */
	 public static String TypeGroup_Database="database";//数据表分类
	 
	 /**
	  * 权限等级
	  */
	 public static Short Function_Leave_ONE=0;//一级权限
	 public static Short Function_Leave_TWO=1;//二级权限
	 
	 /**
	  * 权限等级前缀
	  */
	 public static String Function_Order_ONE="ofun";//一级权限
	 public static String Function_Order_TWO="tfun";//二级权限

	 /**
	  * 出入库单状态
	  */
	 public static String STATUS_lookGood = "1";//查看
	 public static String STATUS_submitOrder = "2";//提交订单
	 public static String STATUS_PayMoney = "3";//付钱
	 public static String STATUS_FINISH = "5";//订单完成
	
	 /**
	  * 后台人员后台操作
	  */
	 public static Short Worker_Dispatch=1; //发货
	 public static Short Worker_Putaway=2; //上架
	 public static Short Worker_Soldout=3;//下架
	 
	 

	 /**
	  * 配置系统是否开启按钮权限控制
	  */
	
}
