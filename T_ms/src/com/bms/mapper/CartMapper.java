package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.CartModel;

public interface CartMapper{
	int insert(CartModel cartModel);
	int delete(CartModel cartModel);
	List<CartModel> findByUserid(CartModel cartModel);
	CartModel findById(CartModel cartModel);
	CartModel findByMenuid(Map<String, Integer> param);
	List<CartModel> listByPage(Map<String, Object> map);
	Long getTotal(Map<String, Object> map);
}
