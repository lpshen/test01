package com.bms.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.AccountModel;
import com.bms.model.LogLoginModel;
import com.bms.model.PageModel;
import com.bms.service.AccountDao;
import com.bms.service.LogLoginService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/loglogin")
public class LogLoginController {
	private LogLoginService logLoginService = (LogLoginService) ApplicationContextFactory.getInstance().getBean("logLoginService");

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView modelAndView = new ModelAndView();
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
//					if (userModel.getUsername() != null) {
//						modelAndView.setViewName("/index.jsp");
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
		return modelAndView;

	}

	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
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
		return new ModelAndView("/success.jsp");

	}

	@RequestMapping("/deleteSubmit")
	public ModelAndView delete(@ModelAttribute("accountModel") AccountModel accountModel, HttpServletRequest request,
			HttpServletResponse response) {

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		accountModel.setUsername("admin");
		dao.deleteUser(accountModel);
		return new ModelAndView("/success.jsp");

	}
//easyui 页面 登录日志
	@RequestMapping("/list")
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		Integer userid = null;
		String username = null;
		String time = null;
		System.out.println(request.getParameter("date"));
		if (request.getParameter("date") != null&&!"".equals(request.getParameter("date"))) {
//			System.out.println("date:"+request.getParameter("date"));
//			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			Date  date  = sdf.parse(request.getParameter("date"));
//			System.out.println("date "+date);
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			 time = sdf2.format(date);
//			System.out.println("time"+time);
			time = request.getParameter("date");
			
		}
		if (request.getParameter("loginname") != null&& !"".equals(request.getParameter("loginname"))) {
			System.out.println("loginname:"+request.getParameter("loginname"));
			username = request.getParameter("loginname");
		}
		
		List<LogLoginModel> list = logLoginService.listBySearch(username, userid, time);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String json = JSONArray.fromObject(list).toString();
		System.out.println("json"+json);
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

	@RequestMapping("/update")
	public ModelAndView re(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/update.jsp");
	}
	@RequestMapping("/backstage")
	public ModelAndView backIndex(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/bsm/login.jsp");
	}

	@RequestMapping("/listByPage")
	public ModelAndView listByPage(HttpServletRequest request, HttpServletResponse response) {
		Integer page;
		if (request.getParameter("pageNo") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("pageNo"));
		}
		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		AccountModel accountModel = new AccountModel();
		accountModel.setUsername("admin");
		List<AccountModel> list = dao.listByPage(page, 2, accountModel.getUsername());
		PageModel<AccountModel> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(page);
		pageModel.setPageSize(2);
		pageModel.setTotalCount(dao.getTotal().intValue());
		System.out.println(dao.getTotal().intValue());

		return new ModelAndView("/List.jsp", "pageModel", pageModel);
	}
/*
 * 后台对用户表分页查询前端实现分页
 */
	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/bsm/loglogin.jsp");
		
		String username = null;
		String useridstr = null;
		Integer userid = null;
		String time = null;
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		if (request.getParameter("userid") != null) {
			useridstr = request.getParameter("userid").trim();
			if (!useridstr.equals("")) {
				userid = Integer.parseInt(useridstr); 
			}
		}
		
		System.out.println("username: 11"+username+"11  userid: "+userid);
		List<LogLoginModel> list = logLoginService.listBySearch(username, userid,time);	
		modelAndView.addObject("list", list);
		modelAndView.addObject("username", username);
		modelAndView.addObject("userid", userid);
		System.out.println("登录日志查询长度："+list.size());
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
}
