package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.RechargeListModel;
import com.bms.model.RechargeRecordModel;

public interface RechargeRecordMapper {
//	void insertRechargeRecord(RechargeRecordModel rechargeRecordModel);
//	void deleteRechargeRecord(RechargeRecordModel rechargeRecordModel);
	List<RechargeRecordModel> listByPage(Map<String, Object> param);
	Long getTotal(Map<String , Object> param);	
	List<RechargeListModel> listBySearch(Map<String, Object> param);
	int insert(RechargeRecordModel rechargeRecordModel);
}
