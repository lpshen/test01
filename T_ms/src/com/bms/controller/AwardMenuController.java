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

import com.bms.common.ApplicationContextFactory;
import com.bms.model.AwardMenuModel;
import com.bms.service.AwardMenuService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/awardMenu")
public class AwardMenuController {
	private AwardMenuService awardMenuService = (AwardMenuService) ApplicationContextFactory.getInstance().getBean("awardMenuService");

	
	/*
	 * 
	 */
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		if (awardMenuService.count() < 6) {
			
		
		System.out.println("&&&&"+request.getParameter("awardNum")+","+request.getParameter("awardName"));
		if (request.getParameter("awardNum")!= null&&request.getParameter("awardName")!=null) {
			String awardName = request.getParameter("awardName");
			Integer awardNum = Integer.parseInt(request.getParameter("awardNum"));
			AwardMenuModel awardMenuModel = new AwardMenuModel();
			awardMenuModel.setAwardName(awardName);
			awardMenuModel.setAwardNum(awardNum);
			int result = awardMenuService.insert(awardMenuModel);
			System.out.println("result:"+result+"awardName:"+awardName+"awardNum"+awardNum);
			if (result > 0) {
				msg = "success";
			}else {
				msg = "fail";
			}
		}
		}else {
			msg = "bigger"; //数据超过6条
		}
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		response.getWriter().println(json);
		return null;
	}
	//积分菜单获取ots
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("/ots/JF/JFCJ.jsp");
		List<AwardMenuModel> awardMenuModels = awardMenuService.list();
		System.out.println(awardMenuModels.size());
		request.getSession().setAttribute("awardMenus", awardMenuModels);
		return modelAndView;
	}
	//easyui
	@RequestMapping("/listMenu")
	public String listMenu(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		List<AwardMenuModel> awardMenuModels = awardMenuService.list();
		String json = JSONArray.fromObject(awardMenuModels).toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(json);
		out.println(json);
		out.flush();
		out.close();
		return null;
	}
//	@RequestMapping("count")
//	public String count(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Map<String, Integer> map = new HashMap<>();
//		List<Map<String, Integer>> list = new ArrayList<>();
//		int count = awardMenuService.count();
//		map.put("count", count);
//		list.add(map);
//		String json = JSONArray.fromObject(list).toString();
//		System.out.println("count json:"+count);
//		response.getWriter().print(json);
//		return null;
//	}
	//easyui 多条删除
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer length = null;
		 int result = 0;
		 System.out.println("lengthstring:"+request.getParameter("selectedIDsLength"));
		 if (request.getParameter("selectedIDsLength")!= null) {
			length = Integer.parseInt(request.getParameter("selectedIDsLength"));
			System.out.println("length:"+length);
			for(int i= 0 ;i < length;i++){
				System.out.println(request.getParameter("selectedIDs["+i+"]"));
				System.out.println(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
			 result = awardMenuService.delete(Integer.parseInt(request.getParameter("selectedIDs["+i+"]")));
			}
		}
		 response.setCharacterEncoding("utf-8");
		 PrintWriter out = response.getWriter();
		 Map<String, String> map = new HashMap<>();
		 List<Map<String, String>> list = new ArrayList<>();
		 if (result > 0) {
			map.put("msg", "success");
		}else{
			map.put("msg", "fail");
		}
		 list.add(map);
		 String json = JSONArray.fromObject(list).toString();
		 out.print(json);
		 out.flush();
		 out.close();
		 System.out.println("result:"+result);
			return null;
	}
	@RequestMapping("/update")
	public String update(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		System.out.println(request.getParameter("awardId") +","+request.getParameter("awardName"));
		if (request.getParameter("awardId") != null&& request.getParameter("awardName") != null) {
			Integer awardId = Integer.parseInt(request.getParameter("awardId"));
			String awardName = request.getParameter("awardName");
			int result =  awardMenuService.update(awardId, awardName);
			if (result > 0) {
				msg= "success";
			}else{
				msg = "fail";
			}
		}
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	

	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}
}
