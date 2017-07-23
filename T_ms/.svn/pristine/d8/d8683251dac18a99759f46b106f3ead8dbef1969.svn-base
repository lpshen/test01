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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.AccountModel;
import com.bms.model.AddressModel;
import com.bms.model.AreaModel;
import com.bms.model.PageModel;
import com.bms.service.AccountDao;
import com.bms.service.AddressService;
import com.bms.service.AreaService;

import net.sf.json.JSONArray;

/**
 * 
 * @Project：mybatisForSpring3
 * @ClassName：AccountController
 * @Description：TODO
 * @author：lixiu
 * @CreateTime：Jul 30, 2011 1:55:49 PM
 * @Modifier：lixiu
 * @ChangeTime：Jul 30, 2011 1:55:49 PM
 * @Remark：TODO
 * @version 1.0
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	private AreaService areaService = (AreaService) ApplicationContextFactory.getInstance().getBean("areaService");

	@RequestMapping("/findById")
	public String findById(HttpServletRequest request ,HttpServletResponse response){
		return null;
	}
	@RequestMapping("/listBySearch")
	public String listBySearch(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		System.out.println("@@"+request.getParameter("parent_id")+""+request.getParameter("type"));
		if (request.getParameter("parent_id")!= null&&request.getParameter("type")!=null) {
			String parent_id = request.getParameter("parent_id");
			Integer type = Integer.parseInt(request.getParameter("type"));
			List<AreaModel> areaModels = areaService.listBySearch(parent_id,type);
			System.out.println("areamodels"+areaModels.size());
			List<Map<String, Object>> param = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			map.put("citys", areaModels);
			param.add(map);
			String json = JSONArray.fromObject(areaModels).toString();
			response.setContentType("text/html;charset=utf-8");
			System.out.println(json);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
			
		}
		return null;
	}
	@RequestMapping("/addressAdd")
	public ModelAndView  addressAdd(HttpServletRequest request ,HttpServletResponse response){
		ModelAndView  modelAndView = new ModelAndView("/ots/addressAdd.jsp");
		System.out.println(request.getParameter("userid"));
		if (request.getParameter("userid") != null) {
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			List<AreaModel> areaModels = areaService.listBySearch(null, 2);//初始化时加载省名
			System.out.println("areamodels"+areaModels.size());
			if (areaModels.size() > 0) {
				return modelAndView.addObject("provinces", areaModels);	
			}
		}
		return modelAndView;
	}



	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}
}
