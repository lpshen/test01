package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.AddressModel;
import com.bms.model.AreaModel;

public interface AreaMapper {

//	int insertAddress(AddressModel addressModel);
//	void deleteAddress(AddressModel addressModel);
//	List<AddressModel> findAll();
//	void updateAddress(AddressModel addressModel);
//	List<AddressModel> listByPage(Map<String, Object> param);
//	Long getTotal(Map<String, Integer> param);
//	Long getTotalBySearch(Map<String , Object> param);
//	List<AddressModel> listBySearch(Map<String, Object> param);
//	List<AddressModel> listByUserid(Map<String, Object> param);
	AreaModel findById(Map<String, String> param);
	List<AreaModel> listBySearch(Map<String, Object> param);
}
