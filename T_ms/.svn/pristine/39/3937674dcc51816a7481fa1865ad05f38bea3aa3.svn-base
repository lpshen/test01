package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.GreensModel;

public interface GreensMapper {

	int insertGreens(GreensModel greensModel);
	int deleteById(GreensModel greensModel);
	List<GreensModel> findAll();
	int updateGreens(GreensModel greensModel);
	List<GreensModel> listByPage(Map<String , Object> param);
	Long getTotal(Map<String , Integer> param);
	Long getTotalBySearch(Map<String , Object> param);
	List<GreensModel> listBySearch(Map<String, Object> param);
	List<GreensModel> listBySearchQT(Map<String, Object> param);
	int countByType(Map<String, Object> param);
	List<GreensModel> listByType(Map<String, Object> param);
	int editState(GreensModel greensModel);
}
