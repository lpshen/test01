package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.UserOrder;

public interface UserOrderMapper {
	 List<UserOrder> listByUserid(Map<String, Integer> param);
	 UserOrder findByOrdernum(Map<String, String> param);
	 List<UserOrder> listByPage(Map<String, Object> map);
	 Long getTotal(Map<String, Object> param);

}
