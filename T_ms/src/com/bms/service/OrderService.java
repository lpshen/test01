package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.OrderMapper;
import com.bms.mapper.OrderMenuMapper;
import com.bms.mapper.UserOrderMapper;
import com.bms.model.OrderMenu;
import com.bms.model.OrdersModel;
import com.bms.model.UserOrder;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private OrderMenuMapper orderMenuMapper ;

//	@Transactional
//	public void insertUser(UserModel userModel) {
//		userMapper.insertUser(userModel);
//	}
//	@Transactional
//	public void deleteUser(UserModel userModel) {
//		userMapper.deleteUser(userModel);
//	}
//
//	@Transactional
//	public List<UserModel> findAll() {
//		return userMapper.findAll();
//	}
//
//	@Transactional
//	public void update(UserModel userModel) {
//		userMapper.updateUser(userModel);
//	}
	@Transactional
	public List<UserOrder> listByPage(Integer pageNo,Integer pageSize,Integer userid ) {
		pageNo = (pageNo - 1) * pageSize;
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", pageNo);
		param.put("pageSize", pageSize);
		param.put("userid", userid);
		return orderMapper.listByPage(param);
	}
	@Transactional
	public Long getTotal(Integer userid){
		Map<String, Integer> param = new HashMap<>();
		param.put("userid", userid);
		return orderMapper.getTotal(param);
	}
//
//
//	@Transactional
//	public Long getTotalBySearch(String searchType,String searchField) {
//		Map<String , Object> param = new HashMap<>();
//		param.put("searchType", searchType);
//		param.put("searchField", searchField);
//		return userMapper.getTotalBySearch(param);
//	}
	//后台查询使用前端分页
	@Transactional
	public List<OrdersModel> listBySearch(String ordernum,String time,Integer userid,String mastate) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("ordernum", ordernum);
		param.put("time", time);
		param.put("userid", userid);
		param.put("mastate", mastate);
		return orderMapper.listBySearch(param);
	}
	
	@Transactional
	public int deleteById(OrdersModel ordersModel) {
		return orderMapper.deleteById(ordersModel);
	}
	@Transactional
	public int addOrder(OrdersModel ordersModel) {
		return orderMapper.addOrder(ordersModel);
	}
	@Transactional
	public List<UserOrder> listByUserid(Integer userid) {
		Map<String, Integer> param = new HashMap<>();
		param.put("userid", userid);
		return userOrderMapper.listByUserid(param);
	}
	@Transactional
	public UserOrder findByOrdernum(String ordernum) {
		Map<String, String> param = new HashMap<>();
		param.put("ordernum", ordernum);
		return userOrderMapper.findByOrdernum(param);
	}
	@Transactional
	public int add(OrderMenu orderMenu) {
		return orderMenuMapper.add(orderMenu);
	}
	
	public int updatePayState(String ordernum,String paystate) {
		Map<String, Object> param = new HashMap<>();
		param.put("ordernum", ordernum);
		param.put("paystate", paystate);
		return orderMapper.updatePayState(param);
	}
	public int updateMastate(String ordernum,String mastate) {
		Map<String, Object> param = new HashMap<>();
		param.put("ordernum", ordernum);
		param.put("mastate", mastate);
		return orderMapper.updateMastate(param);
	}
//	@Transactional
//	public List<UserModel> findByUser(String username,String password) {
//		Map<String, String> param = new HashMap<>();
//		param.put("username", username);
//		param.put("password", password);
//		return userMapper.findByUser(param);
//	}
	public List<UserOrder> listByPage1(Integer pageNo, int pageSize, Integer userid) {
		Integer temp = (pageNo-1)*pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", temp);
		map.put("pageSize", pageSize);
		map.put("userid", userid);
	    return userOrderMapper.listByPage(map);
	}
	public Long getTotal1(int userid){
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		return userOrderMapper.getTotal(param);
	}
}
