package com.controller;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Admins;
import com.entity.Goods;
import com.entity.Tops;
import com.entity.Types;
import com.entity.Users;
import com.service.AdminService;
import com.service.GoodService;
import com.service.LogsService;
import com.service.OrderService;
import com.service.TopService;
import com.service.TypeService;
import com.service.UserService;
import com.util.Globals;
import com.util.PageUtil;
import com.util.SafeUtil;
import com.util.UploadUtil;

/**
 * 后台相关接口
 */
@Controller
@RequestMapping("/saleman")
public class SaleManController {

	private static final int rows = 10;

	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private TopService topService;
	@Autowired
	private TypeService typeService;
	
	@Resource
	private LogsService logsService;
	
	private String message = null;

	

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Admins admin = (Admins) session.getAttribute("admin"); 
        message = "管理员: " + admin.getUsername()  + "退出成功";
     
		logsService.AddLogs(message,admin.getId(), Globals.Log_Type_LOGIN, Globals.Log_Daliy,Globals.User_admin);
		
 	   session.setAttribute("admin", admin);
		return "/admin/login.jsp";
	}
	
	/**
	 * 后台首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("msg", "恭喜你! 管理员登录成功了");
		return "/admin/index.jsp";
	}

	/**
	 * 订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/orderList")
	public String orderList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request,
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 1);
		request.setAttribute("status", status);
		request.setAttribute("orderList", orderService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, orderService.getTotal(status), page, rows));
		return "/saleman/order_list.jsp";
	}
	
	/**
	 * 订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/orderSearch")
	public String orderSearch(@RequestParam(required=false, defaultValue="0")int id, HttpServletRequest request) {
		if(id > 0) {
			request.setAttribute("flag", 1);
			request.setAttribute("orderList", orderService.getListById(id));
			return "/saleman/order_list.jsp";
		}else {
			return "redirect:/saleman/orderList";
		}
	}

	/**
	 * 订单发货
	 * 
	 * @return
	 */
	@RequestMapping("/orderDispose")
	public String orderDispose(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) {
		Admins admin = (Admins) session.getAttribute("admin"); 
        message = "管理员: " + admin.getUsername()  + "给订单"+id+"发货";
     
		logsService.AddLogs(message,admin.getId(), Globals.Worker_Dispatch, Globals.Log_Daliy,Globals.User_admin);
		orderService.dispose(id);
		return "redirect:orderList?flag=1&status="+status+"&page="+page;
	}
	
	/**
	 * 订单完成
	 * 
	 * @return
	 */
	@RequestMapping("/orderFinish")
	public String orderFinish(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.finish(id);
		return "redirect:orderList?flag=1&status="+status+"&page="+page;
	}

	/**
	 * 订单删除
	 * 
	 * @return
	 */
	@RequestMapping("/orderDelete")
	public String orderDelete(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) {
		Admins admin = (Admins) session.getAttribute("saleman"); 
        message = "管理员: " + admin.getUsername()  + "对"+id+"删除";
   		logsService.AddLogs(message,admin.getId(), Globals.Log_Type_DEL, Globals.Log_Daliy,Globals.User_admin);
		orderService.delete(id);
		return "redirect:orderList?flag=1&status="+status+"&page="+page;
	}

	

	
	/**
	 * 产品列表
	 * 
	 * @return
	 */
	@RequestMapping("/goodList")
	public String goodList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 3);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("goodList", goodService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotal(status), page, rows));
		return "/admin/good_list.jsp";
	}
	
	/**
	 * 通过名称获取列表
	 * 
	 * @return
	 */
	@RequestMapping("/goodSearch")
	public String goodSearch(String name, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 3);
		request.setAttribute("goodList", goodService.getListByName(name, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotalByName(name), page, rows));
		return "/admin/good_list.jsp";
	}

	/**
	 * 产品添加
	 * 
	 * @return
	 */
	@RequestMapping("/goodAdd")
	public String goodAdd(HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("typeList", typeService.getList());
		return "/admin/good_add.jsp";
	}

	/**
	 * 产品添加
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/goodSave")
	public String goodSave(String name, int price, String intro, int stock, int typeId, 
			MultipartFile cover, MultipartFile image1, MultipartFile image2, 
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) throws Exception {
		
		
		Goods good = new Goods();
		good.setName(name);
		good.setPrice(price);
		good.setIntro(intro);
		good.setStock(stock);
		good.setTypeId(typeId);
		good.setCover(UploadUtil.fileUpload(cover));
		good.setImage1(UploadUtil.fileUpload(image1));
		good.setImage2(UploadUtil.fileUpload(image2));
		goodService.add(good);
		
		 Admins admin = (Admins) session.getAttribute("admin"); 
		 message = "管理员: " + admin.getUsername()  + "上架"+name+"商品";
	     
	    logsService.AddLogs(message,admin.getId(), Globals.Worker_Putaway, Globals.Log_Daliy,Globals.User_admin);
		return "redirect:goodList?flag=3&page="+page;
	}

	/**
	 * 产品更新
	 * 
	 * @return
	 */
	@RequestMapping("/goodEdit")
	public String goodEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("good", goodService.get(id));
		return "/admin/good_edit.jsp";
	}

	/**
	 * 产品更新
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/goodUpdate")
	public String goodUpdate(int id, String name, int price, String intro, int stock, int typeId,  
			MultipartFile cover, MultipartFile image1, MultipartFile image2,
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) throws Exception {
		Goods good = goodService.get(id);
		good.setName(name);
		good.setPrice(price);
		good.setIntro(intro);
		good.setStock(stock);
		good.setTypeId(typeId);
		if (Objects.nonNull(cover) && !cover.isEmpty()) {
			good.setCover(UploadUtil.fileUpload(cover));
		}
		if (Objects.nonNull(image1) && !image1.isEmpty()) {
			good.setImage1(UploadUtil.fileUpload(image1));
		}
		if (Objects.nonNull(image2) && !image2.isEmpty()) {
			good.setImage2(UploadUtil.fileUpload(image2));
		}
		 Admins admin = (Admins) session.getAttribute("admin"); 
		 message = "管理员: " + admin.getUsername()  + "修改"+name+"商品";
	     
	    logsService.AddLogs(message,admin.getId(), Globals.Worker_Putaway, Globals.Log_Daliy,Globals.User_admin);
		
		goodService.update(good);
		return "redirect:goodList?flag=3&page="+page;
	}

	/**
	 * 产品删除
	 * 
	 * @return
	 */
	@RequestMapping("/goodDelete")
	public String goodDelete(int id, 
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) {
		
		 Admins admin = (Admins) session.getAttribute("admin"); 
		 message = "管理员: " + admin.getUsername()  + "下架编号"+id+"商品";
	     
	    logsService.AddLogs(message,admin.getId(), Globals.Worker_Putaway, Globals.Log_Daliy,Globals.User_admin);
		goodService.delete(id);
		return "redirect:goodList?flag=3&page="+page;
	}
	
	/**
	 * 添加推荐
	 * @return
	 */
	@RequestMapping("/topSave")
	public @ResponseBody String topSave(Tops tops, 
			@RequestParam(required=false, defaultValue="0")byte status,
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) {
		 Admins admin = (Admins) session.getAttribute("admin"); 
		 message = "管理员: " + admin.getUsername()  + "添加推荐"+tops.getGoodId()+"商品";
	     
	    logsService.AddLogs(message,admin.getId(), Globals.Worker_Putaway, Globals.Log_Daliy,Globals.User_admin);
		
		int id = topService.add(tops);
		return id > 0 ? "ok" : null;
	}
	
	/**
	 * 删除推荐
	 * @return
	 */
	@RequestMapping("/topDelete")
	public @ResponseBody String topDelete(Tops tops, 
			@RequestParam(required=false, defaultValue="0")byte status,
			@RequestParam(required=false, defaultValue="1") int page,HttpSession session) {
		
		 Admins admin = (Admins) session.getAttribute("admin"); 
		 message = "管理员: " + admin.getUsername()  + "添加推荐"+tops.getGoodId()+"商品";
		   logsService.AddLogs(message,admin.getId(), Globals.Worker_Putaway, Globals.Log_Daliy,Globals.User_admin);
			
	     
		boolean flag = topService.delete(tops);
		return flag ? "ok" : null;
	}

	/**
	 * 类目列表
	 * 
	 * @return
	 */
	@RequestMapping("/typeList")
	public String typeList(HttpServletRequest request) {
		request.setAttribute("flag", 4);
		request.setAttribute("typeList", typeService.getList());
		return "/admin/type_list.jsp";
	}
	
	/**
	 * 通过名字获取列表
	 * 
	 * @return
	 */
	@RequestMapping("/typeSearch")
	public String typeSearch(String name,HttpServletRequest request) {
		request.setAttribute("flag", 4);
		request.setAttribute("typeList", typeService.getList(name));
		return "/admin/type_list.jsp";
	}

	/**
	 * 类目添加
	 * 
	 * @return
	 */
	@RequestMapping("/typeSave")
	public String typeSave(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.add(type);
		return "redirect:typeList?flag=4&page="+page;
	}

	/**
	 * 类目更新
	 * 
	 * @return
	 */
	@RequestMapping("/typeEdit")
	public String typeUp(int id, HttpServletRequest request) {
		request.setAttribute("flag", 4);
		request.setAttribute("type", typeService.get(id));
		return "/admin/type_edit.jsp";
	}

	/**
	 * 类目更新
	 * 
	 * @return
	 */
	@RequestMapping("/typeUpdate")
	public String typeUpdate(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.update(type);
		return "redirect:typeList?flag=4&page="+page;
	}

	/**
	 * 类目删除
	 * 
	 * @return
	 */
	@RequestMapping("/typeDelete")
	public String typeDelete(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.delete(type);
		return "redirect:typeList?flag=4&page="+page;
	}

	
	/**
	 * 管理员修改自己密码
	 * 
	 * @return
	 */
	@RequestMapping("/adminRe")
	public String adminRe(HttpServletRequest request, HttpSession session) {
		Admins admin = (Admins) session.getAttribute("admin"); 
		 message = "管理员: " + admin.getUsername()  + "修改自己的密码";
		 logsService.AddLogs(message,admin.getId(), Globals.Log_Type_UPDATE, Globals.Log_Daliy,Globals.User_admin);
		request.setAttribute("flag", 5);
		request.setAttribute("admin", adminService.getByUsername(String.valueOf(session.getAttribute("username"))));
		return "/admin/admin_reset.jsp";
	}

	/**
	 * 管理员修改自己密码
	 * 
	 * @return
	 */
	@RequestMapping("/adminReset")
	public String adminReset(Admins admin, HttpServletRequest request) {
		request.setAttribute("flag", 5);
		if (adminService.get(admin.getId()).getPassword().equals(SafeUtil.encode(admin.getPassword()))) {
			admin.setPassword(SafeUtil.encode(admin.getPasswordNew()));
			adminService.update(admin);
			request.setAttribute("admin", admin);
			request.setAttribute("msg", "修改成功!");
		}else {
			request.setAttribute("msg", "原密码错误!");
		}
		return "/admin/admin_reset.jsp";
	}

	

	
	/**
	 * 日志列表
	 * 
	 * @return
	 */
	@RequestMapping("/logList")
	public String adminLogList(HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 6);
		request.setAttribute("logList", logsService.getListOperateList(page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, logsService.getNotClientTotal(), page, rows));
		return "/admin/logs_list.jsp";
	}

}