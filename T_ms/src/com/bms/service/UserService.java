package com.bms.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.UserMapper;
import com.bms.model.UserModel;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void insertUser(UserModel userModel) {
		userMapper.insertUser(userModel);
	}
	@Transactional
	public int deleteById(UserModel userModel) {
		return userMapper.deleteById(userModel);
	}

	@Transactional
	public List<UserModel> findAll() {
		return userMapper.findAll();
	}

	@Transactional
	public void update(UserModel userModel) {
		userMapper.updateUser(userModel);
	}
	@Transactional
	public Long getTotalBySearch(String searchType,String searchField) {
		Map<String , Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return userMapper.getTotalBySearch(param);
	}
	//后台查询使用前端分页
	@Transactional
	public List<UserModel> listBySearch(String username,String phonenum,String addtime) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("username", username);
		param.put("phonenum", phonenum);
		param.put("addtime", addtime);
		return userMapper.listBySearch(param);
	}
	@Transactional
	public List<UserModel> findByUser(String username,String password) {
		Map<String, String> param = new HashMap<>();
		param.put("username", username);
		param.put("password", password);
		return userMapper.findByUser(param);
	}
	@Transactional
	public int recharge(UserModel userModel) {
		return userMapper.recharge(userModel);
	}
	@Transactional
	public int pay(Integer userid ,BigDecimal totalprice) {
		Map<String, Object> param = new HashMap<>();
		param.put("totalprice", totalprice);
		param.put("userid", userid);
		return userMapper.pay(param);
	}
	@Transactional
	public UserModel findByUserid(Integer userid) {
		Map<String, Integer> param = new HashMap<>();
		param.put("userid", userid);
		return userMapper.findByUserid(param);
	}
	@Transactional
	public int updateAll(UserModel userModel){	
		return userMapper.updateUserAll(userModel);	
	}
	@Transactional
	public int updatePass(UserModel userModel){
		return userMapper.updateUser(userModel);
		
	}
	
	public int useCredits(Integer credits,Integer userid){
		Map<String, Integer> param = new HashMap<>();
		param.put("credits", credits);
		param.put("userid", userid);
		return userMapper.useCredits(param);
	}; 
	public int addCredits(Integer credits,Integer userid){
		Map<String, Integer> param = new HashMap<>();
		param.put("credits", credits);
		param.put("userid", userid);
		return userMapper.addCredits(param);
	};
	public int editState(String state ,Integer userid) {
		Map<String, Object> param = new HashMap<>();
		param.put("state", state);
		param.put("userid", userid);
		return userMapper.editState(param);
	}
}
