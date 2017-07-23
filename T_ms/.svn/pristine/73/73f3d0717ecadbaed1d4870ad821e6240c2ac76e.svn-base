package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.AdminMapper;
import com.bms.model.AdminModel;

@Service
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Transactional
	public void insertAdmin(AdminModel adminModel) {
		adminMapper.insertAdmin(adminModel);
	}
	@Transactional
	public int deleteById(AdminModel adminModel) {
		return adminMapper.deleteById(adminModel);
	}

	@Transactional
	public List<AdminModel> findAll() {
		return adminMapper.findAll();
	}

	@Transactional
	public int updateAdminPW(AdminModel adminModel) {
		 return adminMapper.updateAdminPW(adminModel);
	}
	
	@Transactional
	public int updateAdmin(AdminModel adminModel) {
		return adminMapper.updateAdmin(adminModel);
	}
	@Transactional
	public int editState(AdminModel adminModel) {
		return adminMapper.editState(adminModel);
	}


	@Transactional
	public Long getTotalBySearch(String searchType,String searchField) {
		Map<String , Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return adminMapper.getTotalBySearch(param);
	}
	//前台分页
	@Transactional
	public List<AdminModel> listBySearch(String username,String time,String role) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("role", role);
		param.put("username", username);
		param.put("time", time);
		return adminMapper.listBySearch(param);
	}
	@Transactional
	public int countByCJGLY() {
		return adminMapper.countByCJGLY();
	}
	@Transactional
	public int countByGLY() {
		return adminMapper.countByGLY();
	}
	@Transactional
	public int countBySH() {
		return adminMapper.countBySH();
	}
	@Transactional
	public int countAll() {
		return adminMapper.countAll();
	}
	@Transactional
	public List<AdminModel> findByAdmin(String username,String password) {
		Map<String, String> param = new HashMap<>();
		param.put("username", username);
		param.put("password", password);
		return adminMapper.findByAdmin(param);
	}
	
	@Transactional
	public AdminModel findById(Integer id) {
		AdminModel adminModel = new AdminModel();
		adminModel.setId(id);
		return adminMapper.findById(adminModel);
	}
}
