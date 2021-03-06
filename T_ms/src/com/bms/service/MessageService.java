package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.MessageMapper;
import com.bms.model.MessageModel;

@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Transactional
	public int insertMessage(MessageModel messageModel) {
		return messageMapper.insertMessage(messageModel);
	}
	@Transactional
	public int deleteById(MessageModel messageModel) {
		return messageMapper.deleteById(messageModel);
	}

	@Transactional
	public List<MessageModel> findAll() {
		return messageMapper.findAll();
	}

	@Transactional
	public void update(MessageModel messageModel) {
		messageMapper.updateMessage(messageModel);
	}


	@Transactional
	public Long getTotalBySearch(String searchType,String searchField) {
		Map<String , Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return messageMapper.getTotalBySearch(param);
	}
	//easyui 留言管理
	@Transactional
	public List<MessageModel> listBySearch(String messagetime,String replytime,String state,Integer userid) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("messagetime", messagetime);
		param.put("replytime", replytime);
		param.put("state", state);
		param.put("userid", userid);
		return messageMapper.listBySearch(param);
	}
	@Transactional
	public List<MessageModel> findByMessage(String Messagename,String password) {
		Map<String, String> param = new HashMap<>();
		param.put("Messagename", Messagename);
		param.put("password", password);
		return messageMapper.findByMessage(param);
	}
	@Transactional
	public int reply(MessageModel MessageModel) {
		return messageMapper.reply(MessageModel);
	}
	@Transactional
	public List<MessageModel> listByPage(int pageNo, int pageSize, Integer userid) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("userid", userid);
		System.out.println("service:pageNo"+pageNo+"pageSize:"+pageSize+"userid"+userid);
		return messageMapper.listByPage(param);
	}
	public Long getTotal(Integer userid) {
		Map<String, Integer> param = new HashMap<>();
		param.put("userid", userid);
		return messageMapper.getTotal(param);
	}
}
