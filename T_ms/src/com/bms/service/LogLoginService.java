package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.LogLoginMapper;
import com.bms.model.LogLoginModel;

@Service
public class LogLoginService {

	@Autowired
	private LogLoginMapper logLoginMapper;
	//记录登录
	@Transactional
	public void insertLogLogin(LogLoginModel loginModel) {
		logLoginMapper.insertLogLogin(loginModel);
	}
//	@Transactional
//	public void deleteUser(UserModel userModel) {
//		logLoginMapper.deleteUser(userModel);
//	}
//
	@Transactional
	public List<LogLoginModel> findAll() {
		return logLoginMapper.findAll();
	}
//
//	@Transactional
//	public void update(UserModel userModel) {
//		logLoginMapper.updateUser(userModel);
//	}
//
//
//	@Transactional
//	public Long getTotalBySearch(String searchType,String searchField) {
//		Map<String , Object> param = new HashMap<>();
//		param.put("searchType", searchType);
//		param.put("searchField", searchField);
//		return logLoginMapper.getTotalBySearch(param);
//	}
	//后台查询使用前端分页
	@Transactional
	public List<LogLoginModel> listBySearch(String username,Integer userid,String time) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("username", username);
		param.put("userid", userid);
		param.put("time", time);
		return logLoginMapper.listBySearch(param);
	}
	//后台查询使用前端分页
		@Transactional
		public List<LogLoginModel> findByUserId(Integer userid) {	
		LogLoginModel loginModel =  new LogLoginModel();
		loginModel.setUserid(userid);
			return logLoginMapper.findByUserId(loginModel);
		}
//	@Transactional
//	public List<UserModel> findByUser(String username,String password) {
//		Map<String, String> param = new HashMap<>();
//		param.put("username", username);
//		param.put("password", password);
//		return logLoginMapper.findByUser(param);
//	}
}
