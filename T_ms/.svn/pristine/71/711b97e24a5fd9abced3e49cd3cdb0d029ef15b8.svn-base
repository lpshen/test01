package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.RechargeRecordMapper;
import com.bms.model.RechargeListModel;
import com.bms.model.RechargeRecordModel;

@Service
public class RechargeRecordService {

	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;

//	@Transactional
//	public void insertUser(RechargeRecordModel rechargeRecordModel) {
//		rechargeRecordMapper.insertRechargeRecord(rechargeRecordModel);
//	}
//	@Transactional
//	public void deleteUser(RechargeRecordModel rechargeRecordModel) {
//		rechargeRecordMapper.deleteRechargeRecord(rechargeRecordModel);
//	}
//
	@Transactional
	public Long getTotal(Integer userid) {
		Map<String , Object> param = new HashMap<>();
		param.put("userid", userid);
		return rechargeRecordMapper.getTotal(param);
	}
	@Transactional
	public List<RechargeRecordModel> listByPage(int pageNo,int pageSize,Integer userid) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("userid", userid);
		return rechargeRecordMapper.listByPage(param);
	}
	@Transactional
	public List<RechargeListModel> listBySearch(String username,Integer operationid,String time) {
		Map<String, Object> param = new HashMap<>();
		param.put("username", username);
		param.put("operationid", operationid);
		param.put("time", time);
		return rechargeRecordMapper.listBySearch(param);
	}
	public int insert(RechargeRecordModel rechargeRecordModel) {
		return rechargeRecordMapper.insert(rechargeRecordModel);
	}

}
