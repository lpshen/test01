package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.mapper.OrderDetailsMapper;
import com.bms.model.OrderDetails;

@Service
public class OrderDetailsService {

	@Autowired
	private OrderDetailsMapper orderDetailsMapper;
	
	public List<OrderDetails> listBySearch(String ordernum) {
		Map<String, Object> param = new HashMap<>();
		param.put("ordernum", ordernum);
		return orderDetailsMapper.listBySearch(param);
	}
	
}
