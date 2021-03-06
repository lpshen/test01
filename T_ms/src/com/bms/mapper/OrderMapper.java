package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.OrdersModel;
import com.bms.model.UserOrder;

public interface OrderMapper {
	
	List<OrdersModel> listBySearch(Map<String, Object> param);
	int deleteById(OrdersModel ordersModel);
	List<UserOrder> listByPage(Map<String, Object> param);
	Long getTotal(Map<String, Integer> param);
	int addOrder(OrdersModel ordersModel);
	int updatePayState(Map<String, Object> param);
	int updateMastate(Map<String, Object> param);
	
}
