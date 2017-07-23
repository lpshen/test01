package com.bms.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.bms.model.AdminModel;
import com.bms.model.LogLoginModel;
import com.bms.model.PageModel;
import com.bms.service.AccountDao;
import com.bms.service.AdminService;
import com.bms.service.LogLoginService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private AdminService adminService = (AdminService) ApplicationContextFactory.getInstance().getBean("adminService");
	private LogLoginService logLoginService = (LogLoginService) ApplicationContextFactory.getInstance()
			.getBean("logLoginService");

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		String username = null;
		String password = null;
		if (request.getParameter("username").trim() != null && request.getParameter("password").trim() != null) {
			if (request.getParameter("username").equals("") || request.getParameter("password").equals("")) {
				modelAndView.setViewName("/bsmui/login.jsp");
				modelAndView.addObject("msg", "用户名或密码不能为空");
				return modelAndView;
			}

			username = request.getParameter("username").trim();
			password = request.getParameter("password").trim();
			List<AdminModel> list = null;
			list = adminService.findByAdmin(username, password);

			if (list.size() != 0) {
				AdminModel adminModel = list.get(0);
				if("未启用".equals(adminModel.getState())){
					modelAndView.setViewName("/bsmui/login.jsp");
					modelAndView.addObject("msg", "该账号未启用");
					return modelAndView;
				}
				if (adminModel.getUsername() != null) {
					modelAndView.setViewName("/bsmui/index.jsp");// 登录成功后转到
					// 记录登录日志
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
					LogLoginModel logLoginModel = new LogLoginModel();
					logLoginModel.setRole(adminModel.getRole().trim());
					logLoginModel.setUserid(adminModel.getId());
					logLoginModel.setUsername(adminModel.getUsername());
					logLoginModel.setTime(sdf.format(new Date()));
					logLoginService.insertLogLogin(logLoginModel);

					modelAndView.addObject("adminModel", adminModel);
					modelAndView.addObject("dateTime", new Date());
					request.getSession().setAttribute("adminModel", adminModel);
				}
			} else {
				modelAndView.setViewName("/bsmui/login.jsp");
				modelAndView.addObject("msg", "用户不存在或密码错误");
			}
			return modelAndView;

		} else {
			modelAndView.setViewName("/bsm/login.jsp");
			modelAndView.addObject("msg", "参数错误！");
		}
		return modelAndView;

	}

	// @RequestMapping("/register")
	// public ModelAndView register(HttpServletRequest request,
	// HttpServletResponse response) {
	// ModelAndView modelAndView = new ModelAndView();
	// UserModel userModel = new UserModel();
	// if (request.getParameter("authCode") != null) {
	// String strCode = (String) request.getSession().getAttribute("strCode");
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
	// adminService.insertUser(userModel);
	// return new ModelAndView("/success.jsp");
	//
	// }
	@RequestMapping("/addSubmit")
	public ModelAndView addSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("../admin/listBySearch.do");
		AdminModel adminModel = new AdminModel();
		if (request.getParameter("username") != null) {
			adminModel.setUsername(request.getParameter("username"));
		}
		if (request.getParameter("password") != null) {
			adminModel.setPassword(request.getParameter("password"));
		}
		if (request.getParameter("phonenum") != null) {
			adminModel.setPhonenum(request.getParameter("phonenum"));
		}
		if (request.getParameter("email") != null) {
			adminModel.setEmail(request.getParameter("email"));
		}
		if (request.getParameter("role") != null) {
			adminModel.setRole(request.getParameter("role"));
		}
		if (request.getParameter("state") != null) {
			adminModel.setState(request.getParameter("state"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		adminModel.setTime(sdf.format(new Date()));
		adminService.insertAdmin(adminModel);
		response.sendRedirect("../admin/listBySearch.do");// 重定向
		return modelAndView;

	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();// session失效
		// request.getSession().removeAttribute("user");
		return new ModelAndView("/bsmui/login.jsp");

	}

	@RequestMapping("/deleteSubmit")
	public ModelAndView delete(@ModelAttribute("accountModel") AccountModel accountModel, HttpServletRequest request,
			HttpServletResponse response) {

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		accountModel.setUsername("admin");
		dao.deleteUser(accountModel);
		return new ModelAndView("/success.jsp");

	}
	//easyui 菜单启用与停用	
			@RequestMapping("/editState")
			public String editState(HttpServletRequest request, HttpServletResponse response) throws IOException {
				System.out.println("state:"+request.getParameter("state"));
				System.out.println("id:"+request.getParameter("id"));
				Integer id = null;
				int result = 0;	
				if (request.getParameter("state")!=null&&request.getParameter("id")!=null) {
					AdminModel adminModel = new AdminModel();
					id = Integer.parseInt(request.getParameter("id"));
					if ("已启用".equals(request.getParameter("state").trim())) {
						adminModel.setId(id);
						adminModel.setState("未启用");
					}else if ("未启用".equals(request.getParameter("state").trim())) {
						adminModel.setId(id);
						adminModel.setState("已启用");
					}
					result = adminService.editState(adminModel);
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
				return null;
			}
	//easyui 页面 单条多条删除	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 Integer length = null;
	 int result = 0;
	 System.out.println("lengthstring:"+request.getParameter("selectedIDsLength"));
	 if (request.getParameter("selectedIDsLength")!= null) {
		 AdminModel adminModel = new AdminModel();
		length = Integer.parseInt(request.getParameter("selectedIDsLength"));
		System.out.println("length:"+length);
		for(int i= 0 ;i < length;i++){
			System.out.println(request.getParameter("selectedIDs["+i+"]"));
			System.out.println(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
		 adminModel.setId(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
		 result = adminService.deleteById(adminModel);
		
		}
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

//	@RequestMapping("/list")
//	public String findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		
//		List<AdminModel> list = adminService.findAll();
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter();
//		String json = JSONArray.fromObject(list).toString();
//		System.out.println("json"+json);
//		out.print(json);
//		out.flush();
//		out.close();
//		return null;
//
//	}
	//easyui 页面 用户管理
			@RequestMapping("/list")
			public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
				String role = null;//角色
				String userName = null;//用户名称
				String time = null;//添加时间
			//	String type = null;
				// request.setCharacterEncoding("utf-8");
				if (request.getParameter("date") != null && !("".equals(request.getParameter("date")))) {
//					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = sdf.parse(request.getParameter("date"));
//					time = sdf2.format(date);
//					System.out.println("time" + time);
					time = request.getParameter("date");
				}
				if (request.getParameter("userName") != null && !("".equals(request.getParameter("userName")))) {
					userName = new String(request.getParameter("userName").getBytes("ISO8859-1"), "utf-8");
					System.out.println("userName" + userName);
				}
//				if (request.getParameter("type") != null && !("".equals(request.getParameter("type")))) {
//					type = new String(request.getParameter("type").getBytes("ISO8859-1"), "utf-8");
//					System.out.println("type" + type);
//				}
				if (request.getParameter("role")!= null&&!("".equals(request.getParameter("role")))) {
					role = request.getParameter("role");
					System.out.println("role:"+role);
				}

				List<AdminModel> list = adminService.listBySearch (userName,time,role);
				String json = JSONArray.fromObject(list).toString();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				System.out.println("json:" + json);
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
	public ModelAndView re(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("phonenum"));
		System.out.println(request.getParameter("email"));
		AdminModel adminModel = new AdminModel();
		if (request.getParameter("username") != null) {
			adminModel.setUsername(request.getParameter("username"));
		}
		if (request.getParameter("phonenum") != null) {
			adminModel.setPhonenum(request.getParameter("phonenum"));
		}
		if (request.getParameter("email") != null) {
			adminModel.setEmail(request.getParameter("email"));
		}
		if (request.getParameter("userid") != null) {
			System.out.println(request.getParameter("userid"));
			adminModel.setId(Integer.parseInt(request.getParameter("userid")));
		}
		int result = adminService.updateAdmin(adminModel);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> m1 = new HashMap<String, String>();

		if (result > 0) {
			m1.put("msg", "成功");
		}else {
			m1.put("msg", "失败");
		}
		list.add(m1);

		String s = JSONArray.fromObject(list).toString();
		out.print(s);
		out.close();

		return null;
		// return new ModelAndView("/update.jsp");
	}

	@RequestMapping("/updatePW")
	public ModelAndView updatePW(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminModel adminModel = new AdminModel();
		if (request.getParameter("newPassword") != null) {
			adminModel.setPassword(request.getParameter("newPassword"));
		}
		if (request.getParameter("userid") != null) {
			System.out.println(request.getParameter("userid"));
			adminModel.setId(Integer.parseInt(request.getParameter("userid")));
		}
		System.out.println(adminModel.getId() + "." + adminModel.getPassword());

		AdminModel adminModel2 = adminService.findById(adminModel.getId());
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> m1 = new HashMap<String, String>();
		System.out.println(request.getParameter("password") + "##" + adminModel2.getPassword());
		if (request.getParameter("password") != null) {
			if (request.getParameter("password").equals(adminModel2.getPassword())) {
				int result = adminService.updateAdminPW(adminModel);
				System.out.println("执行状态：" + result);
				if (result > 0) {
					m1.put("msg", "成功");
				} else {
					m1.put("msg", "失败");
				}
			} else {
				m1.put("msg", "原密码不正确");
			}
		} else {
			m1.put("msg", "失败");
		}
		list.add(m1);

		String s = JSONArray.fromObject(list).toString();
		out.print(s);
		out.close();

		return null;
	}

	@RequestMapping("/updateINFO")
	public ModelAndView updateINFO(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/update.jsp");
	}

	@RequestMapping("/listAdminInfo")
	public ModelAndView listAdminInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/bsm/admin_info.jsp");
		Integer userid = null;
		if (request.getParameter("userid") != null) {
			System.out.println(request.getParameter("userid"));
			userid = Integer.valueOf(request.getParameter("userid"));
		}
		System.out.println("userid" + userid);
		// 根据id查日志
		List<LogLoginModel> list = logLoginService.findByUserId(userid);
		// 查登录用户信息
		AdminModel admininfo = adminService.findById(userid);
		System.out.println("查询logLoginService.findByUserId(userid)长度：" + list.size());
		System.out.println("adminimfo:" + admininfo.getPhonenum());
		modelAndView.addObject("list", list);
		modelAndView.addObject("admininfo", admininfo);
		return modelAndView;
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

	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/bsm/administrator.jsp");

		String username = null;
		String time = null;
		String role = null;
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		if (request.getParameter("time") != null) {
			time = request.getParameter("time");
		}
		if (request.getParameter("role") != null) {
			role = request.getParameter("role");
		}
		int countCJGLY = adminService.countByCJGLY();
		int countGLY = adminService.countByGLY();
		int countSH = adminService.countBySH();
		int countAll = adminService.countAll();
		System.out.println("CJGLY:" + countCJGLY + "GLY:" + countGLY + "SH:" + countSH);
		List<AdminModel> list = adminService.listBySearch(username, time, role);
		modelAndView.addObject("list", list);
		modelAndView.addObject("username", username);
		modelAndView.addObject("time", time);
		modelAndView.addObject("countCJGLY", countCJGLY);
		modelAndView.addObject("countGLY", countGLY);
		modelAndView.addObject("countSH", countSH);
		modelAndView.addObject("countAll", countAll);

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
