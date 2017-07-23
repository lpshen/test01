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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bms.ApplicationContextFactory;
import com.bms.model.DiningTableModel;
import com.bms.model.UserModel;
import com.bms.service.DiningTableService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/diningTable")
public class DiningTableController {
	private DiningTableService diningTableService = (DiningTableService)ApplicationContextFactory.getInstance().getBean("diningTableService");
	@RequestMapping("/listBySearch")
	public String listBySearch(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Integer  tableNum = null;
		String tableType = null;
		String  updateTime = null;
		if (request.getParameter("tableNum") != null&&request.getParameter("tableNum") != "") {
			 tableNum = Integer.parseInt(request.getParameter("tableNum"));
			System.out.println("tableNum:"+tableNum);
		}
		if (request.getParameter("tableType")!= null&&request.getParameter("tableType") != "") {
			 tableType = new String(request.getParameter("tableType").getBytes("ISO-8859-1"),"utf-8");
			System.out.println("tableType:"+tableType);
		}
		if (request.getParameter("updateTime")!= null&&request.getParameter("updateTime") != "") {
			 updateTime = request.getParameter("updateTime");
			 System.out.println("updteTime:"+updateTime);
		}
		List<DiningTableModel> diningTableModels = diningTableService.listBySearch(tableNum, updateTime, tableType);
		String json = JSONArray.fromObject(diningTableModels).toString();
		PrintWriter out = response.getWriter();
		System.out.println("json:"+json);
		out.println(json);
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping("insert")
	public String  insert(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		if (request.getParameter("tableNum")!= null&&request.getParameter("tableType")!=null) {
			Integer tableNum = Integer.parseInt(request.getParameter("tableNum"));
			String tableType= request.getParameter("tableType");
			System.out.println("tableNum:"+tableNum+"tableType:"+tableType);
			DiningTableModel diningTableModel = new DiningTableModel();
			diningTableModel.setTableNum(tableNum);
			diningTableModel.setTableType(tableType);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			diningTableModel.setUpdateTime(sdf.format(new Date()));
			
			int result = diningTableService.insert(diningTableModel);

			if (result > 0) {
				msg = "success";
			}else{
				msg = "fail";
			}
		}
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		map.put("msg", msg);
		list.add(map);
		String json = JSONArray.fromObject(list).toString();
		System.out.println("json:"+json);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping("/listByTableType")
	public String listByTableType(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (request.getParameter("tableType") != null) {
			System.out.println("tableType:"+request.getParameter("tableType"));
			List<DiningTableModel> list = diningTableService.listBySearch(null, null, request.getParameter("tableType"));
			String json = JSONArray.fromObject(list).toString();
			System.out.println("json:"+json);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		}
		return null;
	}

	// easyui 页面 单条多条删除
		@RequestMapping("/delete")
		public String delete(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			Integer length = null;
			int result = 0;
			System.out.println("lengthstring:"
					+ request.getParameter("selectedIDsLength"));
			if (request.getParameter("selectedIDsLength") != null) {
				
				length = Integer
						.parseInt(request.getParameter("selectedIDsLength"));
				System.out.println("length:" + length);
				for (int i = 0; i < length; i++) {
					System.out.println(request.getParameter("selectedIDs[" + i
							+ "]"));
					System.out.println(Integer.parseInt(request
							.getParameter("selectedIDs[" + i + "]")));
					result = diningTableService.delete(Integer.parseInt(request
							.getParameter("selectedIDs[" + i + "]")));
					System.out.println("%%re%%"+result);
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
	
	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}
}
