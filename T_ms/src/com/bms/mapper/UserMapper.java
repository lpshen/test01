package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.GreensModel;
import com.bms.model.UserModel;

public interface UserMapper {
	//注册
	void insertUser(UserModel userModel);
	int deleteById(UserModel userModel);
	List<UserModel> findAll();
	int updateUser(UserModel userModel);
	Long getTotalBySearch(Map<String , Object> param);
	List<UserModel> listBySearch(Map<String, Object> param);
	//登录
	List<UserModel> findByUser(Map<String, String> param);
	int recharge(UserModel userModel);
	int pay(Map<String, Object> param);
	UserModel findByUserid(Map<String, Integer> param);
	int updateUserAll(UserModel userModel);
	int useCredits(Map<String, Integer> param); //消耗积分
	int addCredits(Map<String, Integer> param); //获得积分
	int editState(Map<String, Object> param);//用户状态修改 
}
