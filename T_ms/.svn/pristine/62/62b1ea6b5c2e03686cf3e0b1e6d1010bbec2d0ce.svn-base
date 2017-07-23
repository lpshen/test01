package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.CartMapper;
import com.bms.model.CartModel;

/**
 * 
 * @Project：mybatisForSpring3
 * @ClassName：AccountDao
 * @Description：TODO
 * @author：lixiu
 * @CreateTime：Jul 30, 2011 1:56:49 PM
 * @Modifier：lixiu
 * @ChangeTime：Jul 30, 2011 1:56:49 PM
 * @Remark：TODO
 * @version 1.0
 */

@Service
public class CartService {

	@Autowired
	private CartMapper cartMapper;

	@Transactional 
	public int insert(CartModel cartModel) {
		return cartMapper.insert(cartModel);
	}
	@Transactional
	public int delete(Integer id) {
		CartModel cartModel = new CartModel();
		cartModel.setId(id);
		return cartMapper.delete(cartModel);
	}

	@Transactional
	public List<CartModel> findByUserid(Integer userid) {
		CartModel cartModel = new CartModel();
		cartModel.setUserid(userid);
		return cartMapper.findByUserid(cartModel);
	}
	@Transactional
	public CartModel findById(Integer id) {
		CartModel cartModel = new CartModel();
		cartModel.setId(id);
		return cartMapper.findById(cartModel);
	}
	@Transactional
	public CartModel findByMenuid(Integer menuid){
		return null;
	}
	@Transactional
	public CartModel updateById(Integer id){
		return null;
	}
	@Transactional
	public List<CartModel> findByPage(int pageNo,int pageSize,int userid){
		Integer temp = (pageNo-1)*pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", temp);
		map.put("pageSize", pageSize);
		map.put("userid", userid);
		return cartMapper.listByPage(map);	
	}
	@Transactional
	public Long getTotal(Integer userid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		return cartMapper.getTotal(map);	
	}
}
