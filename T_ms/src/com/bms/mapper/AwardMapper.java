package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.AwardModel;

public interface AwardMapper {

	List<AwardModel> list();//查询
	int add(AwardModel awardModel);//添加
	List<AwardModel> listBySearch(Map<String, String> param);// 后台按条件查询
	List<AwardModel> listByPage(Map<String, Object> param);
	Long getTotal(Map<String, Object> param);
	int updateState(Map<String, Object> param); //修改状态

}
