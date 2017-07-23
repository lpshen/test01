package com.bms.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.AccountModel;
import com.bms.model.GreensModel;
import com.bms.model.LogLoginModel;
import com.bms.model.PayRecordModel;
import com.bms.model.RechargeRecordModel;
import com.bms.model.UserModel;
import com.bms.service.AccountDao;
import com.bms.service.LogLoginService;
import com.bms.service.OrderService;
import com.bms.service.PayRecordService;
import com.bms.service.RechargeRecordService;
import com.bms.service.UserService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = Logger.getLogger(this.getClass());
	private UserService userService = (UserService) ApplicationContextFactory.getInstance().getBean("userService");
	private RechargeRecordService rechargeRecordService = (RechargeRecordService) ApplicationContextFactory
			.getInstance().getBean("rechargeRecordService");
	private PayRecordService payRecordService = (PayRecordService) ApplicationContextFactory.getInstance()
			.getBean("payRecordService");

	//
	// @RequestMapping("/login")
	// public ModelAndView login(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
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
	// //
	// request.getRequestDispatcher("../greens/listByPage.do").forward(request,
	// response);
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

	// ots 登录
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogLoginService logLoginService = (LogLoginService) ApplicationContextFactory.getInstance()
				.getBean("logLoginService");
		String username = null;
		String password = null;
		List<Map<String, String>> list2 = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		String msg = "";
		// String strCode = null;// session中保存的验证码
		if (request.getParameter("username").trim() != null && !"".equals(request.getParameter("username"))
				&& request.getParameter("password").trim() != null && !"".equals(request.getParameter("password"))) {
			// strCode = (String) request.getSession().getAttribute("strCode");
			// if
			// (request.getParameter("authCode").trim().equals(strCode.trim()))
			// {
			username = request.getParameter("username").trim();
			password = request.getParameter("password").trim();
			List<UserModel> list = null;
			list = userService.findByUser(username, password);
			if (list.size() != 0) {
				UserModel userModel = list.get(0);
				if (userModel.getUsername() != null) {// 登录成功
					logger.info("前台登录成功");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
					LogLoginModel logLoginModel = new LogLoginModel();
					logLoginModel.setRole("YH");
					logLoginModel.setUserid(userModel.getId());
					logLoginModel.setUsername(userModel.getUsername());
					logLoginModel.setTime(sdf.format(new Date()));
					logLoginService.insertLogLogin(logLoginModel);
					// modelAndView.addObject("user", userModel);
					request.getSession().setAttribute("user", userModel);
					msg = "success";
					// request.getRequestDispatcher("../greens/listByPage.do").forward(request,
					// response);
				}
			} else {
				msg = "error";
			}
		} else {
			msg = "empty1";
		}
		map.put("msg", msg);
		list2.add(map);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String json = JSONArray.fromObject(list2).toString();
		System.out.println("json:" + json);
		out.print(json);
		out.flush();
		out.close();
		return null;

	}

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

	// ots 注册
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		UserModel userModel = new UserModel();
		if (request.getParameter("authCode") != null) {
			String strCode = (String) request.getSession().getAttribute("strCode");
			if (!(request.getParameter("authCode").trim().equals(strCode.trim()))) {
				modelAndView.setViewName("/ots/register.jsp");
				modelAndView.addObject("msg", "验证码错误");
				return modelAndView;
			}
		}
		if (request.getParameter("username") != null) {
			userModel.setUsername(request.getParameter("username").trim());
		}
		if (request.getParameter("password") != null && request.getParameter("repassword") != null) {
			if (!(request.getParameter("password").trim().equals(request.getParameter("repassword").trim()))) {
				modelAndView.setViewName("/ots/register.jsp");
				modelAndView.addObject("msg", "密码错误，请确保两次输入密码相同!");
				return modelAndView;
			}
			userModel.setPassword(request.getParameter("password").trim());
		}

		if (request.getParameter("phonenum") != null) {
			userModel.setPhonenum(request.getParameter("phonenum"));
		}
		if (request.getParameter("email") != null) {
			userModel.setEmail(request.getParameter("email"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userModel.setMoney(new BigDecimal(0));
		userModel.setCredits(2);
		userModel.setAddtime(sdf.format(new Date()));
		userModel.setState("已启用");

		userService.insertUser(userModel);
		return new ModelAndView("/ots/registerSuccess.jsp");

	}

	@RequestMapping("/deleteSubmit")
	public ModelAndView delete(@ModelAttribute("accountModel") AccountModel accountModel, HttpServletRequest request,
			HttpServletResponse response) {

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		accountModel.setUsername("admin");
		dao.deleteUser(accountModel);
		return new ModelAndView("/success.jsp");

	}

	// easyui 页面 单条多条删除
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer length = null;
		int result = 0;
		System.out.println("lengthstring:" + request.getParameter("selectedIDsLength"));
		if (request.getParameter("selectedIDsLength") != null) {
			UserModel userModel = new UserModel();
			length = Integer.parseInt(request.getParameter("selectedIDsLength"));
			System.out.println("length:" + length);
			for (int i = 0; i < length; i++) {
				System.out.println(request.getParameter("selectedIDs[" + i + "]"));
				System.out.println(Integer.parseInt(request.getParameter("selectedIDs[" + i + "]")));
				userModel.setId(Integer.parseInt(request.getParameter("selectedIDs[" + i + "]")));
				result = userService.deleteById(userModel);

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
	// easyui 页面 用户管理
	@RequestMapping("/list")
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phonenum = null;// 电话
		String userName = null;// 用户名称
		String time = null;// 添加时间
		// String type = null;
		// request.setCharacterEncoding("utf-8");
		if (request.getParameter("date") != null && !("".equals(request.getParameter("date")))) {
			// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			// Date date = sdf.parse(request.getParameter("date"));
			// time = sdf2.format(date);
			// System.out.println("time" + time);
			time = request.getParameter("date");
		}
		if (request.getParameter("userName") != null && !("".equals(request.getParameter("userName")))) {
			userName = new String(request.getParameter("userName").getBytes("ISO8859-1"), "utf-8");
			System.out.println("userName" + userName);
		}
		// if (request.getParameter("type") != null &&
		// !("".equals(request.getParameter("type")))) {
		// type = new String(request.getParameter("type").getBytes("ISO8859-1"),
		// "utf-8");
		// System.out.println("type" + type);
		// }
		if (request.getParameter("phonenum") != null && !("".equals(request.getParameter("phonenum")))) {
			phonenum = request.getParameter("phonenum");
		}

		List<UserModel> list = userService.listBySearch(userName, phonenum, time);
		String json = JSONArray.fromObject(list).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("json:" + json);
		out.print(json);
		out.flush();
		out.close();
		return null;

	}

	// easyui 菜单启用与停用
	@RequestMapping("/editState")
	public String editState(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("state:" + request.getParameter("state"));
		System.out.println("userid:" + request.getParameter("userid"));
		Integer userid = null;
		String state = "";
		int result = 0;
		if (request.getParameter("state") != null && request.getParameter("userid") != null) {

			userid = Integer.parseInt(request.getParameter("userid"));
			if ("已启用".equals(request.getParameter("state").trim())) {
				state = "未启用";
			} else if ("未启用".equals(request.getParameter("state").trim())) {
				state = "已启用";
			}
			result = userService.editState(state, userid);
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
		return null;
	}

	@RequestMapping("/updateSubmit")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		AccountModel accountModel = new AccountModel();
		if (request.getParameter("username") != null && request.getParameter("password") != null
				&& request.getParameter("accountId") != null) {
			accountModel.setUsername(request.getParameter("username").trim());
			accountModel.setPassword(request.getParameter("password").trim());
			accountModel.setAccountId(request.getParameter("accountId"));
			System.out.println(request.getParameter("username").trim() + "=" + request.getParameter("password").trim());
		} else {
			return new ModelAndView("/error.jsp");
		}

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		dao.update(accountModel);
		return new ModelAndView("/success.jsp");

	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView("/ots/login.jsp");
	}

	@RequestMapping("/recharge")
	public ModelAndView recharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		if (request.getParameter("userid") != null && request.getParameter("remoney") != null) {
			UserModel userModel = new UserModel();
			userModel.setId(Integer.parseInt(request.getParameter("userid")));
			userModel.setMoney(new BigDecimal(request.getParameter("remoney")).setScale(2));
			result = userService.recharge(userModel);
		}
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		if (result > 0) {
			RechargeRecordModel rechargeRecordModel = new RechargeRecordModel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			rechargeRecordModel.setMoney(new BigDecimal(request.getParameter("remoney")).setScale(2));
			rechargeRecordModel.setOperationid(1);
			rechargeRecordModel.setTime(sdf.format(new Date()));
			rechargeRecordModel.setUserId(Integer.parseInt(request.getParameter("userid")));
			int re = rechargeRecordService.insert(rechargeRecordModel);
			map.put("msg", "成功");
		} else {
			map.put("msg", "失败");
		}
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		System.out.println("json" + json);
		return null;
	}

	// ots 账户余额支付
	@RequestMapping("/pay")
	public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/ots/payResult.jsp");
		if (request.getParameter("ordernum") != null && request.getParameter("totalprice") != null
				&& request.getParameter("userid") != null) {
			String ordernum = request.getParameter("ordernum");
			BigDecimal totalprice = new BigDecimal(request.getParameter("totalprice"));
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			UserModel userModel = userService.findByUserid(userid);
			if (userModel.getMoney().compareTo(totalprice) == -1) {
				return modelAndView.addObject("msg", "buzu");
			}
			int result = userService.pay(userid, totalprice);
			if (result > 0) {
				OrderService orderService = (OrderService) ApplicationContextFactory.getInstance()
						.getBean("orderService");
				orderService.updatePayState(ordernum, "已支付");
				PayRecordModel payRecordModel = new PayRecordModel();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				payRecordModel.setMoney(totalprice);
				payRecordModel.setOrdernum(ordernum);
				payRecordModel.setUserId(userid);
				payRecordModel.setTime(sdf.format(new Date()));
				if (payRecordService.insertPayRecord(payRecordModel) > 0) {
					System.out.println("支付记录已保存");
				}
				BigDecimal value1 = new BigDecimal(10);
				BigDecimal value2 = totalprice.divide(value1).setScale(0, BigDecimal.ROUND_HALF_UP);
				if (userService.addCredits(value2.intValue(), userid) > 0) {
					logger.info("积分添加成功");
				} else {
					logger.info("积分添加失败");
				}
				modelAndView.addObject("msg", "success");
				modelAndView.addObject("ordernum", ordernum);
			} else {
				modelAndView.addObject("msg", "fail");
			}
		} else {
			modelAndView.addObject("msg", "fail");
		}
		return modelAndView;
	}

	// ots 用户信息
	@RequestMapping("findByUserid")
	public ModelAndView findByUserid(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/ots/userinfo.jsp");
		if (request.getParameter("userid") != null) {
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			UserModel userModel = userService.findByUserid(userid);
			if (userModel.getUsername() != null) {
				modelAndView.addObject("userModel", userModel);
				modelAndView.addObject("msg", "success");
			} else {
				modelAndView.addObject("msg", "fail");
			}
		} else {
			modelAndView.addObject("msg", "error");
		}
		return modelAndView;
	}

	@RequestMapping("findByUserid1")
	public ModelAndView findByUserid1(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/ots/change.jsp");
		if (request.getParameter("userid") != null) {
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			UserModel userModel = userService.findByUserid(userid);
			if (userModel.getUsername() != null) {
				modelAndView.addObject("userModel", userModel);
				modelAndView.addObject("msg", "success");
			} else {
				modelAndView.addObject("msg", "fail");
			}
		} else {
			modelAndView.addObject("msg", "error");
		}
		return modelAndView;
	}

	@RequestMapping("/update")
	public ModelAndView re(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/update.jsp");
	}

	@RequestMapping("/backstage")
	public ModelAndView backIndex(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView("/bsm/login.jsp");
	}

	// @RequestMapping("/listByPage")
	// public ModelAndView listByPage(HttpServletRequest request,
	// HttpServletResponse response) {
	// Integer page;
	// if (request.getParameter("pageNo") == null) {
	// page = 1;
	// } else {
	// page = Integer.parseInt(request.getParameter("pageNo"));
	// }
	// AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance()
	// .getBean("accountDao");
	// AccountModel accountModel = new AccountModel();
	// accountModel.setUsername("admin");
	// List<AccountModel> list = dao.listByPage(page, 2,
	// accountModel.getUsername());
	// PageModel<AccountModel> pageModel = new PageModel<>();
	// pageModel.setList(list);
	// pageModel.setPageNo(page);
	// pageModel.setPageSize(2);
	// pageModel.setTotalCount(dao.getTotal().intValue());
	// System.out.println(dao.getTotal().intValue());
	//
	// return new ModelAndView("/List.jsp", "pageModel", pageModel);
	// }

	/*
	 * 后台对用户表分页查询前端实现分页
	 */
	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/bsm/user_list.jsp");

		String username = null;
		String phonenum = null;
		String time = null;
		if (request.getParameter("username") != null) {
			username = request.getParameter("username").trim();
		}
		if (request.getParameter("phonenum") != null) {
			phonenum = request.getParameter("phonenum").trim();
		}

		System.out.println("username" + username + "phonenum" + phonenum);
		List<UserModel> list = userService.listBySearch(username, phonenum, time);
		modelAndView.addObject("list", list);
		modelAndView.addObject("username", username);
		modelAndView.addObject("phonenum", phonenum);
		System.out.println("用户表查询长度：" + list.size());
		return modelAndView;
	}

	@RequestMapping("/authCode")
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		int width = 63;
		int height = 37;
		Random random = new Random();
		// 设置response头信息
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(this.getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);
		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(this.getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 绘制字符
		String strCode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			strCode = strCode + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}
		// 将字符保存到session中用于前端的验证
		session.setAttribute("strCode", strCode);
		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();

	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping("/updateUserAll")
	public ModelAndView updateUserAll(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		int id;
		String username;
		String email;
		String phonenum;
		String msg;
		UserModel userModel = new UserModel();
		id = Integer.parseInt(request.getParameter("userid"));
		System.out.println(id);
		username = request.getParameter("username");
		email = request.getParameter("email");
		phonenum = request.getParameter("phonenum");
		if (request.getParameter("userid") != null && request.getParameter("username") != null
				&& request.getParameter("email") != null && request.getParameter("phonenum") != null) {
			userModel.setId(id);
			userModel.setUsername(username);
			userModel.setEmail(email);
			userModel.setPhonenum(phonenum);
		}
		if (userService.updateAll(userModel) > 0) {
			System.out.println("修改成功");
			msg = "success";
		} else {
			System.out.println("修改失败");
			msg = "err";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", msg);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(map);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String json = JSONArray.fromObject(list).toString();
		System.out.println("json:" + json);
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/updatePassword")
	public ModelAndView updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserModel userModel = new UserModel();
		if (request.getParameter("userid") != null && request.getParameter("password") != null) {
			userModel.setId(Integer.parseInt(request.getParameter("userid")));
			userModel.setPassword(request.getParameter("password"));
		}
		System.out.println(request.getParameter("userid"));
		String msg = "";
		if (userService.updatePass(userModel) > 0) {
			msg = "success";
			System.out.println(msg);
		} else {
			msg = "err";
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", msg);
		list.add(map);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String json = JSONArray.fromObject(list).toString();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	// 判断当前积分是否大于5 能否抽奖
	@RequestMapping("/judgeCredits")
	public String judgeCredits(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		if (request.getParameter("userid") != null && !"".equals(request.getParameter("userid"))) {
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			UserModel userModel = userService.findByUserid(userid);
			if (userModel.getCredits() != null && userModel.getCredits() > 5) {
				msg = "CZ";
			} else {
				msg = "BZ";
			}
		}
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		PrintWriter out = response.getWriter();
		JSONArray json = JSONArray.fromObject(list);
		out.println(json);
		out.flush();
		out.close();
		return null;
	}
}
