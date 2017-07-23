package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.AdminModel;

public interface AdminMapper {

	void insertAdmin(AdminModel adminModel);
	int deleteById(AdminModel adminModel);
	List<AdminModel> findAll();
	int updateAdmin(AdminModel adminModel);
	int updateAdminPW(AdminModel adminModel);
	Long getTotalBySearch(Map<String , Object> param);
	List<AdminModel> listBySearch(Map<String, Object> param);//
	int countByCJGLY();
	int countByGLY();
	int countBySH();
	int countAll();
	List<AdminModel> findByAdmin(Map<String, String> param);
	AdminModel findById(AdminModel adminModel);
	int editState(AdminModel adminModel);
	
}
