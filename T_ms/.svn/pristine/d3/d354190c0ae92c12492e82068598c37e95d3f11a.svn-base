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
@RequestMapping("/address")
public class AddressController {
	private AddressService addressService = (AddressService) ApplicationContextFactory.getInstance().getBean("addressService");
	private AreaService areaService = (AreaService) ApplicationContextFactory.getInstance().getBean("areaService");
	//ots
	@RequestMapping("/addSubmit")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		AddressModel addressModel = new AddressModel();
		System.out.println("userid11:"+request.getParameter("userid"));
		if (request.getParameter("userid") != null) {
			addressModel.setUserid(Integer.parseInt(request.getParameter("userid")));
		}
		if (request.getParameter("postcode") != null) {
			addressModel.setPostcode(request.getParameter("postcode"));
		}
		if (request.getParameter("name") != null) {
			addressModel.setName(request.getParameter("name"));
		}
		if (request.getParameter("phonenum") != null) {
			addressModel.setPhonenum(request.getParameter("phonenum"));
		}
		System.out.println("province"+request.getParameter("province"));
		if (request.getParameter("province")!=null&&request.getParameter("city")!=null&&request.getParameter("area")!=null) {
			String province = areaService.findById(request.getParameter("province")).getName();
			String city = areaService.findById(request.getParameter("city")).getName();
			String area = areaService.findById(request.getParameter("area")).getName();
			addressModel.setAddress(province+city+area+request.getParameter("address"));
		}
		System.out.println(addressModel.toString());
		int result = addressService.insertAdderss(addressModel);
		System.out.println("result:"+result);
		return new ModelAndView("/ots/success.jsp");

	}

	@RequestMapping("/deleteSubmit")
	public ModelAndView delete(@ModelAttribute("accountModel") AccountModel accountModel, HttpServletRequest request,
			HttpServletResponse response) {

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		accountModel.setUsername("admin");
		dao.deleteUser(accountModel);
		return new ModelAndView("/success.jsp");

	}

	@RequestMapping("/list")
	public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/List.jsp");

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		List<AccountModel> list = dao.findAll();
		modelAndView.addObject("list", list);
		modelAndView.addObject("aa", "aa");
		return modelAndView;

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

	@RequestMapping("/listByPage")
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
		List<AddressModel> list = addressService.listByPage(page, 5, userid);
		PageModel<AddressModel> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(page);
		pageModel.setPageSize(5);
		pageModel.setTotalCount(addressService.getTotal(userid).intValue());
		System.out.println(addressService.getTotal(userid).intValue());
		return new ModelAndView("/user_addresslist.jsp","pageModel",pageModel);
	}
	//ots
	@RequestMapping("listByUserid")
	public ModelAndView listByUserid(HttpServletRequest request ,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/ots/addresslist.jsp");
		if (request.getParameter("userid")!= null) {
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			List<AddressModel> addresslist = addressService.listByUserid(userid);
			if (addresslist.size() > 0) {
				modelAndView.addObject("addresslist", addresslist);
				modelAndView.addObject("msg", "");
				return modelAndView;
			}else {
				return modelAndView.addObject("msg", "empty1");
			}
		}else {
			return modelAndView.addObject("msg", "error");
		}
	}

	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/ListBySearch.jsp");
		Integer page;
		if (request.getParameter("pageNo") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("pageNo"));
		}
		String searchType = null;
		String searchField = null;
		if (request.getParameter("searchType") != null && request.getParameter("searchField") != null) {
			searchField = request.getParameter("searchField");
			searchType = request.getParameter("searchType");
		} else {
			searchField = null;
			searchType = null;
		}

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		List<AccountModel> list = dao.listBySearch(page, 2, searchType, searchField);
		PageModel<AccountModel> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(page);
		pageModel.setPageSize(2);
		pageModel.setTotalCount(dao.getTotalBySearch(searchType, searchField).intValue());
		System.out.println(dao.getTotal().intValue());
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("searchType", searchType);
		modelAndView.addObject("searchField", searchField);
		return modelAndView;
	}
	
	//判断当前地址条数是否小于5
	@RequestMapping("/judgeCount")
	public String judgeCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		if (request.getParameter("userid")!= null&&!"".equals(request.getParameter("userid"))) {
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			Long count = addressService.getTotal(userid);
			if(count >= 0&&count< 5){
				msg = "YES";
			}else {
				msg = "NO";
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
	//ots 地址删除
	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		if (request.getParameter("addressId") != null&&!"".equals(request.getParameter("addressId"))) {
			Integer addressId = Integer.parseInt(request.getParameter("addressId"));
			AddressModel addressModel = new AddressModel();
			addressModel.setId(addressId);
			int result = addressService.deleteAddress(addressModel);
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
		return "/error.jsp";
	}
}
