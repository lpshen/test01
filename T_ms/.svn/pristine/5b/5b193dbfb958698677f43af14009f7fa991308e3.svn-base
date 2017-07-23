package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.GreensMapper;
import com.bms.mapper.OneFoodMapper;
import com.bms.model.GreensModel;
import com.bms.model.OneFoodModel;

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
public class GreensService {

	@Autowired
	private GreensMapper greensMapper;
	
	@Autowired
	private OneFoodMapper oneFoodMapper;

	@Transactional
	public int insertGreens(GreensModel greensModel) {
		return greensMapper.insertGreens(greensModel);
	}

	@Transactional
	public int deleteById(GreensModel greensModel) {
		return greensMapper.deleteById(greensModel);
	}

//	@Transactional
//	public List<AccountModel> findAll() {
//		return greensMapper.findAll();
//	}
//
	@Transactional
	public int update(GreensModel greensModel) {
		return greensMapper.updateGreens(greensModel);
	}

	@Transactional
	public List<GreensModel> listByPage(int pageNo, int pageSize, Integer merid) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String , Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("merid",merid);
		return greensMapper.listByPage(param);
	}

	@Transactional
	public Long getTotal( Integer merid) {
		Map<String, Integer> param = new HashMap<>();
		param.put("merid", merid);
		return greensMapper.getTotal(param);
	}
	@Transactional
	public Long getTotalBySearch(String grename,String type) {
		Map<String , Object> param = new HashMap<>();
		param.put("grename", grename);
		param.put("type", type);
		return greensMapper.getTotalBySearch(param);
	}
	@Transactional
	public List<GreensModel> listBySearch(String type,String grename,String time,Integer merid) {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("grename", grename);
		param.put("time", time);
		param.put("merid", merid);
		return greensMapper.listBySearch(param);
	}
	@Transactional
	public List<GreensModel> listBySearchQT(int pageNo,int pageSize,String grename,String type) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("grename", grename);
		param.put("type", type);
		System.out.println("pageNo"+pageNo+"pageSize"+pageNo+"grename"+grename);
		return greensMapper.listBySearchQT(param);
	}

	@Transactional
	public int countByType(String type) {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		return greensMapper.countByType(param);
	}
	public int editState(GreensModel greensModel) {
		return greensMapper.editState(greensModel);
	}
	
	@Transactional
	public OneFoodModel findOne(Integer menuid) {
		Map<String, Object> param = new HashMap<>();
		param.put("menuid", menuid);
		return oneFoodMapper.findOne(param);
	}

}
