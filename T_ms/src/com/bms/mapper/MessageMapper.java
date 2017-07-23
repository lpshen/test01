package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.MessageModel;

public interface MessageMapper {

	int insertMessage(MessageModel messageModel);//添加方法 ots
	int deleteById(MessageModel messageModel);
	List<MessageModel> findAll();
	void updateMessage(MessageModel messageModel);
	Long getTotalBySearch(Map<String , Object> param);
	List<MessageModel> listBySearch(Map<String, Object> param);

	List<MessageModel> findByMessage(Map<String, String> param);
	int reply(MessageModel messageModel);
	List<MessageModel> listByPage(Map<String, Object> param);
	Long getTotal(Map<String, Integer> param);
}
