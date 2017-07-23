package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.OrderDetails;

public interface OrderDetailsMapper {
	List<OrderDetails> listBySearch(Map<String, Object> param); //订单明细查询

}
