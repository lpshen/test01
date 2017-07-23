package com.bms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.ApplicationContextFactory;
import com.bms.model.PageModel;
import com.bms.model.ReserveTableModel;
import com.bms.service.ReserveTableService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/reserveTable")
public class ReserveTableController {
	private ReserveTableService reserveTableService = (ReserveTableService)ApplicationContextFactory.getInstance().getBean("reserveTableService");
	//ots
	@RequestMapping("/insert")
	public String  insert(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		System.out.println("insert");
		if (request.getParameter("date") != null&&request.getParameter("tableType") != null
				&&request.getParameter("useTime") != null&&request.getParameter("diningTableId") != null
				&&request.getParameter("phoneNum") != null&&request.getParameter("linkMan") != null&&
				request.getParameter("userId") != null) {
			ReserveTableModel reserveTableModel = new ReserveTableModel();
			reserveTableModel.setUserId(Integer.parseInt(request.getParameter("userId")));
			reserveTableModel.setDiningTableId(Integer.parseInt(request.getParameter("diningTableId")));
			reserveTableModel.setLinkman(request.getParameter("linkMan"));
			reserveTableModel.setPhonenum(request.getParameter("phoneNum"));
			reserveTableModel.setReserveDate(request.getParameter("date"));
			reserveTableModel.setReserveTime(request.getParameter("useTime"));
			reserveTableModel.setState("未处理");
			List<ReserveTableModel> list = reserveTableService.listBySearch(null,Integer.parseInt(request.getParameter("diningTableId")),request.getParameter("date"),request.getParameter("useTime"),"未处理",null,null,null,null);
			System.out.println("list:"+list.size());
			if (list.size() > 0) {
				msg = "exists";
			}else{
				int result = reserveTableService.insert(reserveTableModel);
				if (result > 0) {
					msg="success";
				}else{
					msg ="fail";
				}
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
	//ots
	@RequestMapping("/listBySearch")
	public ModelAndView listById(HttpServletRequest request ,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/ots/reserveTableList.jsp");
		if (request.getParameter("userId") != null&&request.getParameter("userId") != "") {
			System.out.println("userid444:"+request.getParameter("userId"));
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			System.out.println("userid:"+userId);
			Integer page;
			if (request.getParameter("pageNo") == null) {
				page = 1;
			} else {
				page = Integer.parseInt(request.getParameter("pageNo"));
			}

			 List<ReserveTableModel> list = reserveTableService.listBySearch(userId,null, null, null, null,null,null, page, 5);

			PageModel<ReserveTableModel> pageModel = new PageModel<>();
			pageModel.setList(list);
			pageModel.setPageNo(page);
			pageModel.setPageSize(5);
			pageModel.setTotalCount(reserveTableService.getTotalBySearch(userId).intValue());
			System.out.println(reserveTableService.getTotalBySearch(userId).intValue());
			modelAndView.addObject("pageModel", pageModel);
			
		}else {
			modelAndView.setViewName("/ots/login.jsp");
			modelAndView.addObject("msg", "nologin");
		}
		return modelAndView;
	}
	// easyui 页面 订桌管理
	@RequestMapping("/list")
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reserveDate = null;//预定时间
		String phoneNum = null;// 手机号
		String linkman = null;// 联系人
		String state = null;// 订单状态
		// request.setCharacterEncoding("utf-8");
		if (request.getParameter("reserve_date") != null && !("".equals(request.getParameter("reserve_date")))) {
//			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(request.getParameter("date"));
//			time = sdf2.format(date);
//			System.out.println("time" + time);
			reserveDate = request.getParameter("reserve_date");
			System.out.println("reserveDate@@"+reserveDate);
		}
		if (request.getParameter("phonenum") != null && !("".equals(request.getParameter("phonenum")))) {
			phoneNum = request.getParameter("phonenum").trim();
			System.out.println("phonenum" + phoneNum);
		}
		if (request.getParameter("state") != null && !("".equals(request.getParameter("state")))) {
			state = new String(request.getParameter("state").getBytes("ISO8859-1"), "utf-8");
			System.out.println("state" + state);
		}
		if (request.getParameter("linkman") != null && !("".equals(request.getParameter("linkman")))) {
		
			linkman = new String(request.getParameter("linkman").getBytes("ISO8859-1"), "utf-8");
		}

		List<ReserveTableModel> list = reserveTableService.listBySearch(null,null,reserveDate,null,state,phoneNum,linkman,null,null);
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
		if (request.getParameter("reserveId")!= null) {
			Integer reserveId = Integer.parseInt(request.getParameter("reserveId"));
			int result = reserveTableService.updateState(reserveId, "已处理");
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
	
	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/ots/error.jsp";
	}
}
