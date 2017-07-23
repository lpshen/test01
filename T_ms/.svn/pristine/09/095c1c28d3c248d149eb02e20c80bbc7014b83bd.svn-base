package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.ShopMapper;
import com.bms.model.ShopModel;

@Service
public class ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Transactional
	public int insertShop(ShopModel ShopModel) {
		 return shopMapper.insertShop(ShopModel);
	}
	@Transactional
	public int  deleteById(ShopModel ShopModel) {
		return shopMapper.deleteShop(ShopModel);
	}

	@Transactional
	public List<ShopModel> findAll() {
		return shopMapper.findAll();
	}

	@Transactional
	public int  editState(ShopModel ShopModel) {
		return shopMapper.editState(ShopModel);
	}
	@Transactional
	public int  updateShop(ShopModel ShopModel) {
		return shopMapper.updateShop(ShopModel);
	}


	@Transactional
	public Long getTotalBySearch(String searchType,String searchField) {
		Map<String , Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return shopMapper.getTotalBySearch(param);
	}
	//后台查询使用前端分页
	@Transactional
	public List<ShopModel> listBySearch(String shopname,String addtime,Integer adminid) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("shopname", shopname);
		param.put("addtime", addtime);
		param.put("adminid", adminid);
		return shopMapper.listBySearch(param);
	}
	@Transactional
	public List<ShopModel> findByShop(String Shopname,String password) {
		Map<String, String> param = new HashMap<>();
		param.put("Shopname", Shopname);
		param.put("password", password);
		return shopMapper.findByShop(param);
	}
	@Transactional
	public List<ShopModel> listBySearchQT(int pageNo,int pageSize,String shopname) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("shopname", shopname);
		System.out.println("pageNo"+pageNo+"pageSize"+pageNo+"shopname"+shopname);
		return shopMapper.listBySearchQT(param);
	}
	@Transactional
	public int  update(ShopModel shopModel) {
		return shopMapper.update(shopModel);
	}
	@Transactional
	public ShopModel getOne() {
		return shopMapper.getOne().get(0);
	}
}
