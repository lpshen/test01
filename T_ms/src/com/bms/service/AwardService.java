package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.AwardMapper;
import com.bms.model.AwardModel;



@Service
public class AwardService {

	@Autowired
	private AwardMapper awardMapper;

	@Transactional
	public List<AwardModel> list() {
		return awardMapper.list();
	}
	@Transactional
	public int  add(AwardModel awardModel) {
		return awardMapper.add(awardModel);
	}
	@Transactional
	public List<AwardModel>  listBySearch(String userName,String awardTime,String state) {
		Map<String, String> param = new HashMap<>();
		param.put("userName", userName);
		param.put("awardTime", awardTime);
		param.put("state", state);
		return awardMapper.listBySearch(param);
	}
	public List<AwardModel> listByPage(int pageNo, int pageSize, int userid) {
		Integer temp = (pageNo-1)*pageSize;
		Map<String, Object> param = new HashedMap();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("userid", userid);
		return awardMapper.listByPage(param);
	}
	public Long getTotal(int userid) {
		Map<String, Object> param  = new HashMap<>();
		param.put("userid", userid);
		System.out.println(awardMapper.getTotal(param));
		return awardMapper.getTotal(param);
	}
	public int updateState(Integer awardId,String state) {
		Map<String, Object> param = new HashMap<>();
		param.put("awardId", awardId);
		param.put("state", state);
		return awardMapper.updateState(param);
	}
}
