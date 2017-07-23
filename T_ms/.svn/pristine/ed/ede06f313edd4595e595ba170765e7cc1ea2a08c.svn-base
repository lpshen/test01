package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.AreaMapper;
import com.bms.model.AreaModel;

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
public class AreaService {

	@Autowired
	private AreaMapper areaMapper;


	@Transactional
	public List<AreaModel> listBySearch(String parent_id,Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("parent_id", parent_id);
		param.put("type", type);
		return areaMapper.listBySearch(param);
	}
	@Transactional
	public AreaModel findById(String id){
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		return areaMapper.findById(param);
	}
	

}
