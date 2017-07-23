package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.PayRecordMapper;
import com.bms.model.PayListModel;
import com.bms.model.PayRecordModel;

@Service
public class PayRecordService {

	@Autowired
	private PayRecordMapper payRecordMapper;

	@Transactional
	public int insertPayRecord(PayRecordModel PayRecordModel) {
		return payRecordMapper.insertPayRecord(PayRecordModel);
	}
	@Transactional
	public void deleteUser(PayRecordModel PayRecordModel) {
		payRecordMapper.deletePayRecord(PayRecordModel);
	}

	@Transactional
	public Long getTotal(Integer userid) {
		Map<String , Object> param = new HashMap<>();
		param.put("userid", userid);
		return payRecordMapper.getTotal(param);
	}
	@Transactional
	public List<PayRecordModel> listByPage(int pageNo,int pageSize,Integer userid) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("userid", userid);
		return payRecordMapper.listByPage(param);
	}
	@Transactional
	public List<PayListModel> list(String time ,String username ,String ordernum) {
		Map<String , Object> param = new HashMap<>();
		param.put("time", time);
		param.put("username", username);
		param.put("ordernum", ordernum);
		return payRecordMapper.list(param);
	}

}
