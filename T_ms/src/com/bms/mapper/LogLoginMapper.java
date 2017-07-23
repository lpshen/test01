package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.LogLoginModel;

public interface LogLoginMapper {

	void insertLogLogin(LogLoginModel loginModel);
	void deleteLogLogin(LogLoginModel loginModel);
	List<LogLoginModel> findAll();
	void updateLogLogin(LogLoginModel loginModel);
	Long getTotalBySearch(Map<String , Object> param);
	//查询登录记录
	List<LogLoginModel> listBySearch(Map<String, Object> param);
	List<LogLoginModel> findByLogLogin(Map<String, String> param);
	List<LogLoginModel> findByUserId(LogLoginModel loginModel);
	
}
