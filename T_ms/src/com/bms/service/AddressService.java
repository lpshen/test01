package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.AddressMapper;
import com.bms.model.AddressModel;

/**
 * 
 * @Project：mybatisForSpring3
 * @ClassName：AccountDao
 * @Description：TODO
 * @author：lixiu
 * @CreateTime：Jul 30, 2011 1:56:49 PM
 * @Modifier：lixiu
 * @ChangeTime：Jul 30, 2011 1:56:49 PM
 * @Remark：TODO
 * @version 1.0
 */

@Service
public class AddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Transactional
	public int insertAdderss(AddressModel addressModel) {
		return addressMapper.insertAddress(addressModel);
	}
	@Transactional
	public int deleteAddress(AddressModel addressModel) {
		return addressMapper.deleteAddress(addressModel);
	}

	@Transactional
	public List<AddressModel> findAll() {
		return addressMapper.findAll();
	}

	@Transactional
	public void update(AddressModel accountModel) {
		addressMapper.updateAddress(accountModel);
	}

	@Transactional
	public List<AddressModel> listByPage(int pageNo, int pageSize, Integer userid) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("userid", userid);
		return addressMapper.listByPage(param);
	}

	@Transactional
	public Long getTotal(Integer userid) {
		Map<String, Integer> param = new HashMap<>();
		param.put("userid", userid);
		return addressMapper.getTotal(param);
	}
	@Transactional
	public Long getTotalBySearch(String searchType,String searchField) {
		Map<String , Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return addressMapper.getTotalBySearch(param);
	}
	@Transactional
	public List<AddressModel> listBySearch(int pageNo,int pageSize,String searchType,String searchField) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return addressMapper.listBySearch(param);
	}
	@Transactional
	public List<AddressModel> listByUserid(Integer userid){
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		return addressMapper.listByUserid(param);
	}
}
