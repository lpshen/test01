package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.ReserveTableMapper;
import com.bms.model.ReserveTableModel;



@Service
public class ReserveTableService {

	@Autowired
	private ReserveTableMapper reserveTableMapper;

	@Transactional
	public int insert(ReserveTableModel reserveTableModel){
		return reserveTableMapper.insert(reserveTableModel);
	}
	@Transactional
	public List<ReserveTableModel> listBySearch( Integer userId,Integer diningTableId ,String reserveDate ,String reserveTime,String state,String phoneNum ,String linkman, Integer pageNo ,Integer pageSize){
		Map<String , Object> param = new HashMap<>();
		if (pageSize != null) {
			if (pageNo != null) {
				int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
				param.put("pageNo", temp);
			}			
		}
		param.put("userId", userId);
		param.put("diningTableId", diningTableId);
		param.put("reserveDate", reserveDate);
		param.put("reserveTime", reserveTime);
		param.put("phoneNum", phoneNum);
		param.put("linkman", linkman);
		param.put("state", state);
		param.put("pageSize", pageSize);
		System.out.println("reserveDate##"+reserveDate);
		return reserveTableMapper.listBySearch(param);
	}
	public Long getTotalBySearch(Integer userId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("userId", userId);
		return reserveTableMapper.getTotalBySearch(param);
	  
	}
	public int updateState(Integer reserveId,String state) {
		Map<String, Object> param = new HashMap<>();
		param.put("reserveId", reserveId);
		param.put("state", state);
		return reserveTableMapper.updateState(param);
	}
	
}
