package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.AwardMenuMapper;
import com.bms.model.AwardMenuModel;



@Service
public class AwardMenuService {

	@Autowired
	private AwardMenuMapper awardMenuMapper;

	@Transactional
	public int insert(AwardMenuModel awardMenuModel) {
		return awardMenuMapper.insert(awardMenuModel);
	}
	@Transactional
	public List<AwardMenuModel> list() {
		return awardMenuMapper.list();
	}
	@Transactional
	public int count() {
		return awardMenuMapper.count();
	}
	@Transactional
	public int delete(Integer awardId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("awardId", awardId);
		return awardMenuMapper.delete(param);
	}
	@Transactional
	public int  update(Integer awardId,String awardName) {
		Map<String, Object> param = new HashMap<>();
		param.put("awardId", awardId);
		param.put("awardName",awardName);
		return awardMenuMapper.update(param);
	}
	@Transactional
	public AwardMenuModel findByAwardNum(Integer awardNum){
		Map<String, Integer> param  = new  HashMap<>();
		param.put("awardNum", awardNum);
		return awardMenuMapper.findByAwardNum(param);
	}
	

}
