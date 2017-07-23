package com.bms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.ApplicationContextFactory;
import com.bms.model.AccountModel;
import com.bms.model.PageModel;
import com.bms.service.AccountDao;

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
@RequestMapping("/account")
public class AccountController {

	/**
	 * 
	 * @MethodName: add @Description: 初始化页面请求 @author lixiu @param
	 * request @param response @return ModelAndView @throws
	 */

	@RequestMapping("/addinit")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		System.err.println(basePath);

		AccountModel model = new AccountModel();
		return new ModelAndView("/add.jsp", "accountModel", model);
	}

	/**
	 * 
	 * @MethodName: create @Description: TODO @author lixiu @param
	 * accountModel @param request @param response @return ModelAndView @throws
	 */
	@RequestMapping("/addSubmit")
	public ModelAndView create(@ModelAttribute("accountModel") AccountModel accountModel, HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		System.err.println(basePath);

		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		dao.insertUser(accountModel);
		return new ModelAndView("/success.jsp");

	}

	@RequestMapping("/deleteSubmit")
	public ModelAndView delete(@ModelAttribute("accountModel") AccountModel accountModel,HttpServletRequest request, HttpServletResponse response) {
		

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
			&&request.getParameter("accountId") != null) {
			accountModel.setUsername(request.getParameter("username").trim());
			accountModel.setPassword(request.getParameter("password").trim());
			accountModel.setAccountId(request.getParameter("accountId"));
			System.out.println(request.getParameter("username").trim()+"="+request.getParameter("password").trim());
		}else {
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
		if (request.getParameter("pageNo") == null) {
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("pageNo"));
		}
		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		AccountModel accountModel = new  AccountModel();
		accountModel.setUsername("admin");
		List<AccountModel> list =  dao.listByPage(page, 2, accountModel.getUsername());
		PageModel<AccountModel> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(page);
		pageModel.setPageSize(2);
		pageModel.setTotalCount(dao.getTotal().intValue());
		System.out.println(dao.getTotal().intValue());

		return new ModelAndView("/List.jsp","pageModel",pageModel);
	}
	
	@RequestMapping("/listBySearch")
	public ModelAndView listBySearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/ListBySearch.jsp");
		Integer page;
		if (request.getParameter("pageNo") == null) {
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("pageNo"));
		}
		String searchType = null;
		String searchField = null;
		if (request.getParameter("searchType") != null && request.getParameter("searchField") != null) {
			searchField = request.getParameter("searchField");
			searchType = request.getParameter("searchType");
		}else {
			searchField = null;
			searchType = null;
		}
		
		AccountDao dao = (AccountDao) ApplicationContextFactory.getInstance().getBean("accountDao");
		List<AccountModel> list =  dao.listBySearch(page, 2, searchType, searchField);
		PageModel<AccountModel> pageModel = new PageModel<>();
		pageModel.setList(list);
		pageModel.setPageNo(page);
		pageModel.setPageSize(2);
		pageModel.setTotalCount(dao.getTotalBySearch(searchType, searchField).intValue());
		System.out.println(dao.getTotal().intValue());
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("searchType",searchType);
		modelAndView.addObject("searchField", searchField);
		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}
}
