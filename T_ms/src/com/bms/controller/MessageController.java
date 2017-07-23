package com.bms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.MessageModel;
import com.bms.model.PageModel;
import com.bms.service.MessageService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/message")
public class MessageController {
	private MessageService messageService = (MessageService) ApplicationContextFactory.getInstance().getBean("messageService");
//
//	@RequestMapping("/login")
//	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		LogLoginService logLoginService = (LogLoginService) ApplicationContextFactory.getInstance().getBean("logLoginService");
//		String username = null;
//		String password = null;
//		String strCode = null;// session中保存的验证码
//		if (request.getParameter("username").trim() != null && request.getParameter("password").trim() != null
//				&& request.getParameter("authCode") != null) {
//			strCode = (String) request.getSession().getAttribute("strCode");
//			if (request.getParameter("authCode").trim().equals(strCode.trim())) {
//				username = request.getParameter("username").trim();
//				password = request.getParameter("password").trim();
//				List<UserModel> list = null;
//				list = userService.findByUser(username, password);
//
//				if (list.size() != 0) {
//					UserModel userModel = list.get(0);
//					if (userModel.getUsername() != null) {//登录成功
//						modelAndView.setViewName("/index.jsp");
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
//						LogLoginModel logLoginModel = new LogLoginModel();
//						logLoginModel.setRole("YH");
//						logLoginModel.setUserid(userModel.getId());
//						logLoginModel.setUsername(userModel.getUsername());
//						logLoginModel.setTime(sdf.format(new Date()));
//						logLoginService.insertLogLogin(logLoginModel);
//						modelAndView.addObject("user", userModel);
//						request.getSession().setAttribute("user", userModel);
//					}
//				} else {
//					modelAndView.setViewName("/login.jsp");
//					modelAndView.addObject("msg", "用户不存在或密码错误");
//				}
//				return modelAndView;
//			} else {
//				modelAndView.setViewName("/login.jsp");
//				modelAndView.addObject("msg", "验证码错误");
//				return modelAndView;
//			}
//		} else {
//			modelAndView.setViewName("/login.jsp");
//			modelAndView.addObject("msg", "用户名或密码不能为空");
//		}
//		return modelAndView;
//
//	}
//
//	@RequestMapping("/register")
//	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		UserModel userModel = new UserModel();
//		if (request.getParameter("authCode") != null) {
//			String strCode =(String) request.getSession().getAttribute("strCode");
//			if (!(request.getParameter("authCode").trim().equals(strCode.trim()))) {
//				modelAndView.setViewName("/register.jsp");
//				modelAndView.addObject("msg", "验证码错误");
//				return modelAndView;
//			}
//		}
//		if (request.getParameter("username") != null) {
//			userModel.setUsername(request.getParameter("username").trim());
//		}
//		if (request.getParameter("password") != null && request.getParameter("repassword") != null) {
//			if (!(request.getParameter("password").trim().equals(request.getParameter("repassword").trim()))) {
//				modelAndView.setViewName("/register.jsp");
//				modelAndView.addObject("msg", "密码错误，请确保两次输入密码相同");
//				return modelAndView;
//			}
//			userModel.setPassword(request.getParameter("password").trim());
//		}
//		
//		if (request.getParameter("phonenum") != null) {
//			userModel.setPhonenum(request.getParameter("phonenum"));
//		}
//		if (request.getParameter("email") != null) {
//			userModel.setEmail(request.getParameter("email"));	
//		}
//	
//		userService.insertUser(userModel);
//		return new ModelAndView("/success.jsp");
//
//	}
	
	//ots 前台留言
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		System.out.println("usermsg"+request.getParameter("usermsg")+"userId "+request.getParameter("userId"));
		if (request.getParameter("usermsg")!= null && request.getParameter("userId")!= null) {
			MessageModel messageModel = new MessageModel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			messageModel.setContent(request.getParameter("usermsg"));
			messageModel.setUserid(Integer.parseInt(request.getParameter("userId")));
			messageModel.setMessagetime(sdf.format(new Date()));
			int result = messageService.insertMessage(messageModel);
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
	@RequestMapping("/replySingle")
	public ModelAndView recharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		if (request.getParameter("messageid")!= null && request.getParameter("reply")!= null) {
			MessageModel messageModel = new MessageModel();
			messageModel.setId(Integer.parseInt(request.getParameter("messageid")));
			messageModel.setReply(request.getParameter("reply"));
			result = messageService.reply(messageModel);
		}
		Map<String, String> map =new HashMap<>();
		List<Map<String , String>> list = new ArrayList<>();
		if (result > 0) {
			map.put("msg", "成功");
		}else {
			map.put("msg", "失败");
		}
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		System.out.println("json"+json);
		return null;
	}
	@RequestMapping("/batch_reply")
	public ModelAndView batchReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 System.out.println(request.getParameter("selectedIDsLength"));
		 Integer length = null;
		 String batchReply = null;
		 int result = 0;
		 if (request.getParameter("selectedIDsLength")!= null&&request.getParameter("batch_reply")!=null) {
			 MessageModel messageModel = new MessageModel();
			length = Integer.parseInt(request.getParameter("selectedIDsLength"));
//			batchReply = new String(request.getParameter("batch_reply").getBytes("ISO8859-1"), "utf-8");
			batchReply = request.getParameter("batch_reply");
			System.out.println("reply"+batchReply);
			for(int i= 0 ;i < length;i++){
			 messageModel.setId(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
			 messageModel.setReply(batchReply);
			 result = messageService.reply(messageModel);			}
		}
		 response.setCharacterEncoding("utf-8");
		 PrintWriter out = response.getWriter();
		 Map<String, String> map = new HashMap<>();
		 List<Map<String, String>> list = new ArrayList<>();
		 if (result > 0) {
			map.put("msg", "成功");
		}else{
			map.put("msg", "失败");
		}
		 list.add(map);
		 String json = JSONArray.fromObject(list).toString();
		 out.print(json);
		 out.flush();
		 out.close();
		 System.out.println("result:"+result);
			return null;
	}
//
//	@RequestMapping("/deleteSubmit")
//	public ModelAndView delete(@ModelAttribute("accountModel") AccountModel accountModel, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
//		accountModel.setUsername("admin");
//		dao.deleteUser(accountModel);
//		return new ModelAndView("/success.jsp");
//
//	}
//	//easyui 页面 单条多条删除	
//			@RequestMapping("/delete")
//			public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//			 Integer length = null;
//			 int result = 0;
//			 System.out.println("lengthstring:"+request.getParameter("selectedIDsLength"));
//			 if (request.getParameter("selectedIDsLength")!= null) {
//				 UserModel userModel = new UserModel();
//				length = Integer.parseInt(request.getParameter("selectedIDsLength"));
//				System.out.println("length:"+length);
//				for(int i= 0 ;i < length;i++){
//					System.out.println(request.getParameter("selectedIDs["+i+"]"));
//					System.out.println(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
//				 userModel.setId(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
//				 result = userService.deleteById(userModel);
//				
//				}
//			}
//			 response.setCharacterEncoding("utf-8");
//			 PrintWriter out = response.getWriter();
//			 Map<String, String> map = new HashMap<>();
//			 List<Map<String, String>> list = new ArrayList<>();
//			 if (result > 0) {
//				map.put("msg", "成功");
//			}else{
//				map.put("msg", "失败");
//			}
//			 list.add(map);
//			 String json = JSONArray.fromObject(list).toString();
//			 out.print(json);
//			 out.flush();
//			 out.close();
//			 System.out.println("result:"+result);
//				return null;
//			}
//
////	@RequestMapping("/list")
////	public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) {
////		ModelAndView modelAndView = new ModelAndView("/List.jsp");
////
////		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
////		List<AccountModel> list = dao.findAll();
////		modelAndView.addObject("list", list);
////		modelAndView.addObject("aa", "aa");
////		return modelAndView;
////
////	}
//	//easyui 页面 用户管理
//		@RequestMapping("/list")
//		public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			String phonenum = null;//电话
//			String userName = null;//用户名称
//			String time = null;//添加时间
//		//	String type = null;
//			// request.setCharacterEncoding("utf-8");
//			if (request.getParameter("date") != null && !("".equals(request.getParameter("date")))) {
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//				Date date = sdf.parse(request.getParameter("date"));
//				time = sdf2.format(date);
//				System.out.println("time" + time);
//			}
//			if (request.getParameter("userName") != null && !("".equals(request.getParameter("userName")))) {
//				userName = new String(request.getParameter("userName").getBytes("ISO8859-1"), "utf-8");
//				System.out.println("userName" + userName);
//			}
////			if (request.getParameter("type") != null && !("".equals(request.getParameter("type")))) {
////				type = new String(request.getParameter("type").getBytes("ISO8859-1"), "utf-8");
////				System.out.println("type" + type);
////			}
//			if (request.getParameter("phonenum")!= null&&!("".equals(request.getParameter("phonenum")))) {
//				phonenum = request.getParameter("phonenum");
//			}
//
//			List<UserModel> list = userService.listBySearch (userName,phonenum, time);
//			String json = JSONArray.fromObject(list).toString();
//			response.setCharacterEncoding("utf-8");
//			PrintWriter out = response.getWriter();
//			System.out.println("json:" + json);
//			out.print(json);
//			out.flush();
//			out.close();
//			return null;
//
//		}
//
//	@RequestMapping("/updateSubmit")
//	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
//		AccountModel accountModel = new AccountModel();
//		if (request.getParameter("username") != null && request.getParameter("password") != null
//				&& request.getParameter("accountId") != null) {
//			accountModel.setUsername(request.getParameter("username").trim());
//			accountModel.setPassword(request.getParameter("password").trim());
//			accountModel.setAccountId(request.getParameter("accountId"));
//			System.out.println(request.getParameter("username").trim() + "=" + request.getParameter("password").trim());
//		} else {
//			return new ModelAndView("/error.jsp");
//		}
//
//		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
//		dao.update(accountModel);
//		return new ModelAndView("/success.jsp");
//
//	}
//	
//	@RequestMapping("/logout")
//	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
//		request.getSession().invalidate();
//		return new ModelAndView("/login.jsp");
//	}
//	
//	@RequestMapping("/recharge")
//	public ModelAndView recharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		int result = 0;
//		if (request.getParameter("userid")!= null && request.getParameter("remoney")!= null) {
//			UserModel userModel = new UserModel();
//			userModel.setId(Integer.parseInt(request.getParameter("userid")));
//			userModel.setMoney(new BigDecimal(request.getParameter("remoney")).setScale(2));
//			result = userService.recharge(userModel);
//		}
//		Map<String, String> map =new HashMap<>();
//		List<Map<String , String>> list = new ArrayList<>();
//		if (result > 0) {
//			map.put("msg", "成功");
//		}else {
//			map.put("msg", "失败");
//		}
//		list.add(map);
//		String json = JSONArray.fromObject(list).toString();
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter();
//		out.print(json);
//		out.flush();
//		out.close();
//		System.out.println("json"+json);
//		return null;
//	}
//
//	@RequestMapping("/update")
//	public ModelAndView re(HttpServletRequest request, HttpServletResponse response) {
//		return new ModelAndView("/update.jsp");
//	}
//	@RequestMapping("/backstage")
//	public ModelAndView backIndex(HttpServletRequest request, HttpServletResponse response) {
//		request.getSession().invalidate();
//		return new ModelAndView("/bsm/login.jsp");
//	}
//
//	@RequestMapping("/listByPage")
//	public ModelAndView listByPage(HttpServletRequest request, HttpServletResponse response) {
//		Integer page;
//		if (request.getParameter("pageNo") == null) {
//			page = 1;
//		} else {
//			page = Integer.parseInt(request.getParameter("pageNo"));
//		}
//		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
//		AccountModel accountModel = new AccountModel();
//		accountModel.setUsername("admin");
//		List<AccountModel> list = dao.listByPage(page, 2, accountModel.getUsername());
//		PageModel<AccountModel> pageModel = new PageModel<>();
//		pageModel.setList(list);
//		pageModel.setPageNo(page);
//		pageModel.setPageSize(2);
//		pageModel.setTotalCount(dao.getTotal().intValue());
//		System.out.println(dao.getTotal().intValue());
//
//		return new ModelAndView("/List.jsp", "pageModel", pageModel);
//	}
/*
 * 后台对用户表分页查询前端实现分页
 */
	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String messagetime = null;//留言时间
		String replytime = null;//回复时间
		Integer userid = null;
		String state = null;//阅读状态
		// request.setCharacterEncoding("utf-8");
		if (request.getParameter("messagetime") != null && !("".equals(request.getParameter("messagetime")))) {
//			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(request.getParameter("messagetime"));
//			messagetime = sdf2.format(date);
//			System.out.println("messagetime" + messagetime);
			messagetime = request.getParameter("messagetime");
		}
		if (request.getParameter("replytime") != null && !("".equals(request.getParameter("replytime")))) {
//			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(request.getParameter("replytime"));
//			replytime = sdf2.format(date);
//			System.out.println("replytime" + replytime);
			replytime = request.getParameter("replytime");
		}
		if (request.getParameter("state") != null && !("".equals(request.getParameter("state")))) {
			state = new String(request.getParameter("state").getBytes("ISO8859-1"),"utf-8");
			System.out.println("state" + state);
		}
//		if (request.getParameter("type") != null && !("".equals(request.getParameter("type")))) {
//			type = new String(request.getParameter("type").getBytes("ISO8859-1"), "utf-8");
//			System.out.println("type" + type);
//		}
		if (request.getParameter("userid")!= null&&!("".equals(request.getParameter("userid")))) {
			userid = Integer.parseInt(request.getParameter("userid"));
			System.out.println("userid"+userid);
		}

		List<MessageModel> list = messageService.listBySearch (messagetime,replytime,state,userid);
		String json = JSONArray.fromObject(list).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("json:" + json);
		out.print(json);
		out.flush();
		out.close();
		return null;
	}
	
	//ots 前台留言分页 按userid
	@RequestMapping("/listByPageAndUserid")
	public ModelAndView listByPage(HttpServletRequest request, HttpServletResponse response) {
		Integer page;
		Integer userid = null;
		if (request.getParameter("pageNo") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("pageNo"));
		}
		if (request.getParameter("userid")!= null) {
			userid = Integer.parseInt(request.getParameter("userid"));
		}
		
		System.out.println("userid:"+userid+"page:"+page);
		List<MessageModel> list = messageService.listByPage(page, 5, userid);
		PageModel<MessageModel> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(page);
		pageModel.setPageSize(5);
		pageModel.setTotalCount(messageService.getTotal(userid).intValue());
		System.out.println(messageService.getTotal(userid).intValue());
		return new ModelAndView("/user_addresslist.jsp","pageModel",pageModel);
	}
//
//	@RequestMapping("/authCode")
//	public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
//			throws IOException {
//		int width = 63;
//		int height = 37;
//		Random random = new Random();
//		// 设置response头信息
//		// 禁止缓存
//		response.setHeader("Pragma", "No-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//
//		// 生成缓冲区image类
//		BufferedImage image = new BufferedImage(width, height, 1);
//		// 产生image类的Graphics用于绘制操作
//		Graphics g = image.getGraphics();
//		// Graphics类的样式
//		g.setColor(this.getRandColor(200, 250));
//		g.setFont(new Font("Times New Roman", 0, 28));
//		g.fillRect(0, 0, width, height);
//		// 绘制干扰线
//		for (int i = 0; i < 40; i++) {
//			g.setColor(this.getRandColor(130, 200));
//			int x = random.nextInt(width);
//			int y = random.nextInt(height);
//			int x1 = random.nextInt(12);
//			int y1 = random.nextInt(12);
//			g.drawLine(x, y, x + x1, y + y1);
//		}
//
//		// 绘制字符
//		String strCode = "";
//		for (int i = 0; i < 4; i++) {
//			String rand = String.valueOf(random.nextInt(10));
//			strCode = strCode + rand;
//			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
//			g.drawString(rand, 13 * i + 6, 28);
//		}
//		// 将字符保存到session中用于前端的验证
//		session.setAttribute("strCode", strCode);
//		g.dispose();
//
//		ImageIO.write(image, "JPEG", response.getOutputStream());
//		response.getOutputStream().flush();
//
//	}
//
//	@ExceptionHandler(Exception.class)
//	public String exception(Exception e, HttpServletRequest request) {
//		e.printStackTrace();
//		request.setAttribute("exception", e);
//		return "/error.jsp";
//	}
//
//	Color getRandColor(int fc, int bc) {
//		Random random = new Random();
//		if (fc > 255)
//			fc = 255;
//		if (bc > 255)
//			bc = 255;
//		int r = fc + random.nextInt(bc - fc);
//		int g = fc + random.nextInt(bc - fc);
//		int b = fc + random.nextInt(bc - fc);
//		return new Color(r, g, b);
//	}
}
