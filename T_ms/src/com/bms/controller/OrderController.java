package com.bms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.CartModel;
import com.bms.model.OrderDetails;
import com.bms.model.OrderMenu;
import com.bms.model.OrdersModel;
import com.bms.model.PageModel;
import com.bms.model.UserOrder;
import com.bms.service.CartService;
import com.bms.service.OrderDetailsService;
import com.bms.service.OrderService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/order")
public class OrderController {
	private OrderService orderService = (OrderService) ApplicationContextFactory.getInstance().getBean("orderService");
	private OrderDetailsService orderDetailsService = (OrderDetailsService) ApplicationContextFactory.getInstance().getBean("orderDetailsService");

	// @RequestMapping("/login")
	// public ModelAndView login(HttpServletRequest request, HttpServletResponse
	// response) {
	// ModelAndView modelAndView = new ModelAndView();
	// LogLoginService logLoginService = (LogLoginService)
	// ApplicationContextFactory.getInstance().getBean("logLoginService");
	// String username = null;
	// String password = null;
	// String strCode = null;// session中保存的验证码
	// if (request.getParameter("username").trim() != null &&
	// request.getParameter("password").trim() != null
	// && request.getParameter("authCode") != null) {
	// strCode = (String) request.getSession().getAttribute("strCode");
	// if (request.getParameter("authCode").trim().equals(strCode.trim())) {
	// username = request.getParameter("username").trim();
	// password = request.getParameter("password").trim();
	// List<UserModel> list = null;
	// list = userService.findByUser(username, password);
	//
	// if (list.size() != 0) {
	// UserModel userModel = list.get(0);
	// if (userModel.getUsername() != null) {//登录成功
	// modelAndView.setViewName("/index.jsp");
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	// LogLoginModel logLoginModel = new LogLoginModel();
	// logLoginModel.setRole("YH");
	// logLoginModel.setUserid(userModel.getId());
	// logLoginModel.setUsername(userModel.getUsername());
	// logLoginModel.setTime(sdf.format(new Date()));
	// logLoginService.insertLogLogin(logLoginModel);
	// modelAndView.addObject("user", userModel);
	// request.getSession().setAttribute("user", userModel);
	// }
	// } else {
	// modelAndView.setViewName("/login.jsp");
	// modelAndView.addObject("msg", "用户不存在或密码错误");
	// }
	// return modelAndView;
	// } else {
	// modelAndView.setViewName("/login.jsp");
	// modelAndView.addObject("msg", "验证码错误");
	// return modelAndView;
	// }
	// } else {
	// modelAndView.setViewName("/login.jsp");
	// modelAndView.addObject("msg", "用户名或密码不能为空");
	// }
	// return modelAndView;
	//
	// }
	//
	// @RequestMapping("/register")
	// public ModelAndView register(HttpServletRequest request,
	// HttpServletResponse response) {
	// ModelAndView modelAndView = new ModelAndView();
	// UserModel userModel = new UserModel();
	// if (request.getParameter("authCode") != null) {
	// String strCode =(String) request.getSession().getAttribute("strCode");
	// if (!(request.getParameter("authCode").trim().equals(strCode.trim()))) {
	// modelAndView.setViewName("/register.jsp");
	// modelAndView.addObject("msg", "验证码错误");
	// return modelAndView;
	// }
	// }
	// if (request.getParameter("username") != null) {
	// userModel.setUsername(request.getParameter("username").trim());
	// }
	// if (request.getParameter("password") != null &&
	// request.getParameter("repassword") != null) {
	// if
	// (!(request.getParameter("password").trim().equals(request.getParameter("repassword").trim())))
	// {
	// modelAndView.setViewName("/register.jsp");
	// modelAndView.addObject("msg", "密码错误，请确保两次输入密码相同");
	// return modelAndView;
	// }
	// userModel.setPassword(request.getParameter("password").trim());
	// }
	//
	// if (request.getParameter("phonenum") != null) {
	// userModel.setPhonenum(request.getParameter("phonenum"));
	// }
	// if (request.getParameter("email") != null) {
	// userModel.setEmail(request.getParameter("email"));
	// }
	//
	// userService.insertUser(userModel);
	// return new ModelAndView("/success.jsp");
	//
	// }

	@RequestMapping("/confirm_order")
	public ModelAndView confirm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/confirm_order_result.jsp");
		if (request.getParameter("orderNum") != null && request.getParameter("totalPrice") != null) {
			modelAndView.addObject("orderNum", request.getParameter("orderNum"));
			modelAndView.addObject("totalPrice", request.getParameter("totalPrice"));
		}
		System.out.println("##");
		return modelAndView;

	}
	@RequestMapping("/confirm_order")
	public ModelAndView addOrder(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("");
		return modelAndView;
	}
	

	@RequestMapping("/pay")
	public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/yeepayDemo.jsp");
		if (request.getParameter("orderNum") != null && request.getParameter("totalPrice") != null) {
			modelAndView.addObject("orderNum", request.getParameter("orderNum"));
			modelAndView.addObject("totalPrice", request.getParameter("totalPrice"));
			System.out.println("o" + request.getParameter("orderNum") + "" + request.getParameter("totalPrice"));
		}
		System.out.println("##");
		return modelAndView;

	}

	//
	// @RequestMapping("/list")
	// public ModelAndView findAll(HttpServletRequest request,
	// HttpServletResponse response) {
	// ModelAndView modelAndView = new ModelAndView("/List.jsp");
	//
	// AccountDao dao = (AccountDao)
	// ApplicationContextFactory.getInstance().getBean("accountDao");
	// List<AccountModel> list = dao.findAll();
	// modelAndView.addObject("list", list);
	// modelAndView.addObject("aa", "aa");
	// return modelAndView;
	//
	// }
	//
	// @RequestMapping("/updateSubmit")
	// public ModelAndView update(HttpServletRequest request,
	// HttpServletResponse response) {
	// AccountModel accountModel = new AccountModel();
	// if (request.getParameter("username") != null &&
	// request.getParameter("password") != null
	// && request.getParameter("accountId") != null) {
	// accountModel.setUsername(request.getParameter("username").trim());
	// accountModel.setPassword(request.getParameter("password").trim());
	// accountModel.setAccountId(request.getParameter("accountId"));
	// System.out.println(request.getParameter("username").trim() + "=" +
	// request.getParameter("password").trim());
	// } else {
	// return new ModelAndView("/error.jsp");
	// }
	//
	// AccountDao dao = (AccountDao)
	// ApplicationContextFactory.getInstance().getBean("accountDao");
	// dao.update(accountModel);
	// return new ModelAndView("/success.jsp");
	//
	// }
	//
	// @RequestMapping("/logout")
	// public ModelAndView logout(HttpServletRequest request,
	// HttpServletResponse response) {
	// request.getSession().invalidate();
	// return new ModelAndView("/login.jsp");
	// }
	//
	// @RequestMapping("/update")
	// public ModelAndView re(HttpServletRequest request, HttpServletResponse
	// response) {
	// return new ModelAndView("/update.jsp");
	// }
	// @RequestMapping("/backstage")
	// public ModelAndView backIndex(HttpServletRequest request,
	// HttpServletResponse response) {
	// request.getSession().invalidate();
	// return new ModelAndView("/bsm/login.jsp");
	// }
	//
	@RequestMapping("/listByPage")
	public ModelAndView listByPage(HttpServletRequest request, HttpServletResponse response) {
		Integer pageNo;
		Integer userid = null;
		if (request.getParameter("pageNo") == null) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if (request.getParameter("userid") != null) {
			userid = Integer.parseInt(request.getParameter("userid"));
		}
		List<UserOrder> list =orderService.listByPage(pageNo, 5, userid);
		PageModel<UserOrder> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(5);
		pageModel.setTotalCount(orderService.getTotal(userid).intValue());
		System.out.println("共"+orderService.getTotal(userid).intValue()+"页");
		System.out.println("查询出的长度："+pageModel.getList().size());
		return new ModelAndView("/user_orderlist.jsp", "pageModel", pageModel);
	}
	//ots
	//显示单条订单 有支付 和返回按钮 订单保存
	@RequestMapping("/bulidOrder")
	public ModelAndView bulidOrder(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/ots/oneOrder.jsp");
		if (request.getParameter("sel") != null&&request.getParameter("userid") != null&&request.getParameter("addressid") != null) {
			String sel = request.getParameter("sel");
			String userid = request.getParameter("userid");
			String addressid = request.getParameter("addressid");
			String[] arrsel = sel.split(",");//菜单id数组
			OrdersModel ordersModel = new OrdersModel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowTime = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(nowTime));
			ordersModel.setOrdernum(sdf.format(nowTime));
			ordersModel.setUserid(Integer.parseInt(userid));
			
			ordersModel.setTime(sdf1.format(nowTime));
			ordersModel.setPaystate("未支付");
			ordersModel.setMastate("未处理");
			ordersModel.setAddresssid(Integer.parseInt(addressid));
			System.out.println("merid"+request.getParameter("merid"));
			ordersModel.setMerid(Integer.parseInt(request.getParameter("merid")));//商户id
			
			OrderMenu orderMenu = new OrderMenu();
			int i;
			CartService cartService =(CartService) ApplicationContextFactory.getInstance()
					.getBean("cartService");
			BigDecimal totalprice = new BigDecimal("0");
			System.out.println("total"+totalprice);
			System.out.println(arrsel.length);
			BigDecimal temp;
			for (i = 0;i < arrsel.length;i++) {					//保存到订单菜单关系表
				System.out.println(arrsel[i]);
				CartModel cartModel;
				if (request.getParameter("count") == null&&request.getParameter("curprice") == null) {
					 cartModel = cartService.findById(Integer.parseInt(arrsel[i]));	
				}else {
					System.out.println("*"+request.getParameter("curprice"));
					System.out.println("*"+request.getParameter("count"));
					cartModel = new CartModel();//立即购买 不在购物车
					cartModel.setCurprice(new BigDecimal(request.getParameter("curprice")));
					cartModel.setCount(Integer.parseInt(request.getParameter("count")));;
					cartModel.setMenuid(Integer.parseInt(sel));
				}
				System.out.println(cartModel.toString());
				orderMenu.setMenuid(cartModel.getMenuid());
				orderMenu.setOrdernum(sdf.format(nowTime));
				orderMenu.setCount(cartModel.getCount());
				orderService.add(orderMenu);
				temp = totalprice;
				totalprice = cartModel.getCurprice().multiply(new BigDecimal(cartModel.getCount()));
				System.out.println("multiply:"+totalprice);
				totalprice = totalprice.add(temp);
			}
			ordersModel.setTotalprice(totalprice);//计算总价
			System.out.println("orderModel:"+ordersModel.toString());
			String msg1 = "";
			String msg2 = "";
			int result = orderService.addOrder(ordersModel);//添加到订单表
			if (result > 0) {
				msg1= "success";
			}else {
				msg1 = "fail";
			}
			
			UserOrder userOrder = orderService.findByOrdernum(sdf.format(nowTime));
			if (userOrder.getOrdernum() != null) {
				modelAndView.addObject("userOrder", userOrder);
			}else {
				msg2 = "empty2";
			}
			modelAndView.addObject("msg1", msg1);
			modelAndView.addObject("msg2", msg2);
		}
		
		return modelAndView;
	}
	//ots
	//显示用户订单
//	@RequestMapping("/listByUserid")
//	public ModelAndView listByUserid(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView("/ots/orderlist.jsp");
//		if (request.getParameter("userid")!=null) {
//			Integer userid = Integer.parseInt(request.getParameter("userid"));
//			List<UserOrder> userOrders = orderService.listByUserid(userid);
//			System.out.println("userOrders:"+userOrders.size());
//			if (userOrders.size() <= 0) {
//				return modelAndView.addObject("msg", "empty1");
//			}
//			modelAndView.addObject("userOrders", userOrders);
//			modelAndView.addObject("msg", "success");
//		}else {
//			modelAndView.addObject("msg", "error");
//		}
//		
//		return modelAndView;
//	}
	@RequestMapping("/listByUserid")
	public ModelAndView listByUserid(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/ots/orderlist.jsp");
		Integer pageNo;
		Integer userid = null;
		String msg;
		if(request.getParameter("pageNo")==null){
			pageNo = 1;
		}else{
			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());
		}
		if(request.getParameter("userid")!=null){
			userid =Integer.parseInt(request.getParameter("userid").trim());
		}
		List<UserOrder> list = orderService.listByPage1(pageNo, 5, userid);
        if(list.size()>0){
        	msg = "success";
        }else{
        	msg = "empty1";
        }
        modelAndView.addObject("msg",msg);
		PageModel<UserOrder> pageModel = new PageModel<UserOrder>();
		pageModel.setList(list);
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(5);
		pageModel.setTotalCount(orderService.getTotal1(userid).intValue());
        modelAndView.addObject("pageModel",pageModel);
		return modelAndView;
	}
	//easyui 处理操作
	@RequestMapping("/editState")
	public String editState(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String msg = "error";
		if (request.getParameter("ordernum")!= null) {
			int result = orderService.updateMastate(request.getParameter("ordernum"), "已处理");
			System.out.println("result"+result);
			if (result > 0) {
				msg = "success";
			}else {
				msg = "fail";
			}
		}
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		
		return null;
	}


	// easyui 页面 店铺管理
	@RequestMapping("/list")
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer userid = null;// 用户id
		String orderNum = null;// 订单编号
		String time = null;// 添加时间
		String type = null;// 订单状态
		// request.setCharacterEncoding("utf-8");
		if (request.getParameter("date") != null && !("".equals(request.getParameter("date")))) {
//			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(request.getParameter("date"));
//			time = sdf2.format(date);
//			System.out.println("time" + time);
			time = request.getParameter("date");
		}
		if (request.getParameter("orderNum") != null && !("".equals(request.getParameter("orderNum")))) {
			orderNum = request.getParameter("orderNum").trim();
			System.out.println("orderNum" + orderNum);
		}
		if (request.getParameter("type") != null && !("".equals(request.getParameter("type")))) {
			type = new String(request.getParameter("type").getBytes("ISO8859-1"), "utf-8");
			System.out.println("type" + type);
		}
		if (request.getParameter("userid") != null && !("".equals(request.getParameter("userid")))) {
			userid = Integer.parseInt(request.getParameter("userid"));
		}

		List<OrdersModel> list = orderService.listBySearch(orderNum, time, userid, type);
		String json = JSONArray.fromObject(list).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("json:" + json);
		out.print(json);
		out.flush();
		out.close();
		return null;

	}
	//ots 订单删除
	
	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("$$$$");
		String msg = "";
		if (request.getParameter("orderid") != null) {
			Integer orderid = Integer.parseInt(request.getParameter("orderid"));
			OrdersModel ordersModel = new OrdersModel();
			ordersModel.setOrderid(orderid);
			int result = orderService.deleteById(ordersModel);
			if (result >0 ) {
				msg =  "success";
			}else {
				msg = "fail";
			}
		}else {
			msg = "error";			
		}
		Map< String, String> map = new HashMap<>();
		List<Map< String , String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}
	

	// easyui 页面 单条多条删除
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer length = null;
		int result = 0;
		if (request.getParameter("selectedIDsLength") != null) {
			OrdersModel ordersModel = new OrdersModel();
			length = Integer.parseInt(request.getParameter("selectedIDsLength"));
			System.out.println("length:" + length);
			for (int i = 0; i < length; i++) {
				System.out.println(request.getParameter("selectedIDs[" + i + "]"));
				System.out.println(Integer.parseInt(request.getParameter("selectedIDs[" + i + "]")));
				ordersModel.setOrderid(Integer.parseInt(request.getParameter("selectedIDs[" + i + "]")));
				result = orderService.deleteById(ordersModel);

			}
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		if (result > 0) {
			map.put("msg", "成功");
		} else {
			map.put("msg", "失败");
		}
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		out.print(json);
		out.flush();
		out.close();
		System.out.println("result:" + result);
		return null;
	}

	/*
	 * 后台对用户表分页查询前端实现分页
	 */
	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		String ordernum = null;
		String time = null;
		Integer userid = null;
		String type = null;
		if (request.getParameter("ordernum") != null) {
			ordernum = request.getParameter("ordernum").trim();
		}
		if (request.getParameter("time") != null) {
			time = request.getParameter("time").trim();
		}
		if (request.getParameter("userid") != null) {
			userid = Integer.parseInt(request.getParameter("userid"));
			modelAndView.setViewName("/user_orderlist.jsp");
		} else {
			modelAndView.setViewName("/bsm/Order_Manage.jsp");
		}

		System.out.println("ordernum" + ordernum + "time" + time + "userid" + userid);
		List<OrdersModel> list = orderService.listBySearch(ordernum, time, userid, type);
		modelAndView.addObject("list", list);
		modelAndView.addObject("ordernum", ordernum);
		modelAndView.addObject("time", time);
		System.out.println("订单表查询长度：" + list.size());
		return modelAndView;
	}

	// @RequestMapping("/authCode")
	// public void getAuthCode(HttpServletRequest request, HttpServletResponse
	// response, HttpSession session)
	// throws IOException {
	// int width = 63;
	// int height = 37;
	// Random random = new Random();
	// // 设置response头信息
	// // 禁止缓存
	// response.setHeader("Pragma", "No-cache");
	// response.setHeader("Cache-Control", "no-cache");
	// response.setDateHeader("Expires", 0);
	//
	// // 生成缓冲区image类
	// BufferedImage image = new BufferedImage(width, height, 1);
	// // 产生image类的Graphics用于绘制操作
	// Graphics g = image.getGraphics();
	// // Graphics类的样式
	// g.setColor(this.getRandColor(200, 250));
	// g.setFont(new Font("Times New Roman", 0, 28));
	// g.fillRect(0, 0, width, height);
	// // 绘制干扰线
	// for (int i = 0; i < 40; i++) {
	// g.setColor(this.getRandColor(130, 200));
	// int x = random.nextInt(width);
	// int y = random.nextInt(height);
	// int x1 = random.nextInt(12);
	// int y1 = random.nextInt(12);
	// g.drawLine(x, y, x + x1, y + y1);
	// }
	//
	// // 绘制字符
	// String strCode = "";
	// for (int i = 0; i < 4; i++) {
	// String rand = String.valueOf(random.nextInt(10));
	// strCode = strCode + rand;
	// g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
	// 20 + random.nextInt(110)));
	// g.drawString(rand, 13 * i + 6, 28);
	// }
	// // 将字符保存到session中用于前端的验证
	// session.setAttribute("strCode", strCode);
	// g.dispose();
	//
	// ImageIO.write(image, "JPEG", response.getOutputStream());
	// response.getOutputStream().flush();
	//
	// }
	
	//后台订单明细
	@RequestMapping("/orderdetailHT")
	public String OrderdetailHT(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		//String msg = "fail";
		String json = "";
		System.out.println("ordernum:"+request.getParameter("ordernum"));
		if (request.getParameter("ordernum")!= null&&!"".equals(request.getParameter("ordernum"))) {
			String ordernum = request.getParameter("ordernum").trim();
			List<OrderDetails> list = orderDetailsService.listBySearch(ordernum);
			//request.getSession().setAttribute("OrderDetails", list);
//			if (list.size() > 0) {
//				request.getSession().setAttribute("oneDetail", list.get(0));				
//			}
//			msg = "success";
			json = JSONArray.fromObject(list).toString();
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("json"+json);
		out.println(json);
		out.flush();
		out.close();
		return null;
	}
	
	//前台订单明细
	@RequestMapping("/orderdetailQT")
	public ModelAndView OrderdetailQT(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		//String msg = "fail";
		ModelAndView modelAndView = new ModelAndView("/ots/orderDetail.jsp");
		System.out.println("ordernum:"+request.getParameter("ordernum"));
		if (request.getParameter("ordernum")!= null&&!"".equals(request.getParameter("ordernum"))) {
			String ordernum = request.getParameter("ordernum").trim();
			List<OrderDetails> list = orderDetailsService.listBySearch(ordernum);
			//request.getSession().setAttribute("OrderDetails", list);
//			if (list.size() > 0) {
//				request.getSession().setAttribute("oneDetail", list.get(0));				
//			}
//			msg = "success";
			System.out.println("listsize:"+list.size());
			for (OrderDetails orderDetails : list) {
				System.out.println("#$:"+orderDetails.toString());
			}
			if (list.size() > 0) {
				modelAndView.addObject("one", list.get(0));
			}
			modelAndView.addObject("list", list);
			modelAndView.addObject("msg","success");
		}else {
			modelAndView.addObject("msg","empty1");
		}

		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}

	// Color getRandColor(int fc, int bc) {
	// Random random = new Random();
	// if (fc > 255)
	// fc = 255;
	// if (bc > 255)
	// bc = 255;
	// int r = fc + random.nextInt(bc - fc);
	// int g = fc + random.nextInt(bc - fc);
	// int b = fc + random.nextInt(bc - fc);
	// return new Color(r, g, b);
	// }
}
