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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.AwardModel;
import com.bms.model.PageModel;
import com.bms.service.AwardMenuService;
import com.bms.service.AwardService;
import com.bms.service.UserService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/award")
public class AwardController {
	private AwardService awardService = (AwardService) ApplicationContextFactory.getInstance().getBean("awardService");
	private AwardMenuService awardMenuService = (AwardMenuService) ApplicationContextFactory.getInstance().getBean("awardMenuService");
	private UserService userService = (UserService) ApplicationContextFactory.getInstance().getBean("userService");
	private Logger logger = Logger.getLogger(this.getClass());
	//ots
//	@RequestMapping("/list")
//	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ModelAndView modelAndView = new ModelAndView("/ots/JFCJlist.jsp");
//		List<AwardModel> awardModels = awardService.list();
////		String json = JSONArray.fromObject(list).toString();
////		response.setCharacterEncoding("utf-8");
////		PrintWriter out = response.getWriter();
////		System.out.println("award json :"+json);
////		out.print(json);
////		out.flush();
////		out.close();
//		modelAndView.addObject("awardModels", awardModels);
//		return modelAndView;
//	}
	//ots
	@RequestMapping("/list")
	public ModelAndView listByPage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/ots/JFCJlist.jsp");
		Integer pageNo;
		Integer userid = null;
		if(request.getParameter("pageNo") == null){
			pageNo = 1;
		}else{
			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());
		}
		if(request.getParameter("userid") != null){
			userid = Integer.parseInt(request.getParameter("userid"));
			System.out.println("userid="+userid);
		}
		List<AwardModel> list = awardService.listByPage(pageNo,4,userid);
		PageModel<AwardModel> pageModel = new PageModel<AwardModel>();
		pageModel.setList(list);
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(4);
		pageModel.setTotalCount(awardService.getTotal(userid).intValue());
		//pageModel.setTotalCount(8);
		modelAndView.addObject("pageModel",pageModel);
		return modelAndView;
	}
	
	// easyui 页面 抽奖管理
	@RequestMapping("/listBySearch")
	public String listBySearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = null;// 用户名
		String awardTime = null;// 获奖时间
		String state = null;// 对换状态
		// request.setCharacterEncoding("utf-8");
		if (request.getParameter("awardTime") != null && !("".equals(request.getParameter("awardTime")))) {
//			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(request.getParameter("date"));
//			time = sdf2.format(date);
//			System.out.println("time" + time);
			awardTime = request.getParameter("awardTime");
		}
		if (request.getParameter("userName") != null && !("".equals(request.getParameter("userName")))) {
			userName = request.getParameter("userName").trim();
			System.out.println("userName" + userName);
		}
		if (request.getParameter("state") != null && !("".equals(request.getParameter("state")))) {
			state = new String(request.getParameter("state").getBytes("ISO8859-1"), "utf-8");
			System.out.println("state" + state);
		}


		List<AwardModel> list = awardService.listBySearch(userName, awardTime, state);
		String json = JSONArray.fromObject(list).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("json:" + json);
		out.print(json);
		out.flush();
		out.close();
		return null;

	}
	//easyui
		@RequestMapping("/editState")
		public String editState(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String msg = "error";
			if (request.getParameter("awardId")!= null) {
				Integer reserveId = Integer.parseInt(request.getParameter("awardId"));
				int result = awardService.updateState(reserveId, "已兑换");
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
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		if (request.getParameter("awardName") != null&&request.getParameter("value") != null) {
			System.out.println(request.getParameter("awardName"));
			String value = request.getParameter("value");
			String[] vals = value.split(",");
			String userName = vals[0];
			Integer userId = Integer.parseInt(vals[1]);
			String awardName = request.getParameter("awardName");			
			//根据awardNum 查询awardMenu 表 
//			AwardMenuModel awardMenuModel = awardMenuService.findByAwardNum(awardNum);
			if(userService.useCredits(5, userId) > 0){
				logger.info("扣除积分成功");
			}else {
				logger.info("扣除积分失败");
			}
			//保存到 awards 表
			AwardModel awardModel = new AwardModel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			awardModel.setAwardTime(sdf.format(new Date()));
			awardModel.setAwardName(awardName);
			awardModel.setUserId(userId);
			awardModel.setUserName(userName);
			int result = awardService.add(awardModel);
			if (result > 0) {
				msg = "success";
			}else {
				msg = "fail";
			}
		}
		Map<String,String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		response.getWriter().println(json);
		return null;
	}

	

	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}
}
