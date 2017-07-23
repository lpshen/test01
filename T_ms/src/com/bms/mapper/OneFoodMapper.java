package com.bms.mapper;

import java.util.Map;

import com.bms.model.OneFoodModel;

public interface OneFoodMapper {

	OneFoodModel findOne(Map<String, Object> param);
}
