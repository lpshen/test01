package com.bms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.mapper.AccountMapper;
import com.bms.model.AccountModel;

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
public class AccountDao {

	@Autowired
	private AccountMapper accountMapper;

	@Transactional
	public void insertUser(AccountModel accountModel) {
		accountMapper.insertUser(accountModel);
	}

	@Transactional
	public void deleteUser(AccountModel accountModel) {
		accountMapper.deleteUser(accountModel);
	}

	@Transactional
	public List<AccountModel> findAll() {
		return accountMapper.findAll();
	}

	@Transactional
	public void update(AccountModel accountModel) {
		accountMapper.updateUser(accountModel);
	}

	@Transactional
	public List<AccountModel> listByPage(int pageNo, int pageSize, String username) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		return accountMapper.listByPage(temp, pageSize, username);
	}

	@Transactional
	public Long getTotal() {
		return accountMapper.getTotal();
	}
	@Transactional
	public Long getTotalBySearch(String searchType,String searchField) {
		Map<String , Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return accountMapper.getTotalBySearch(param);
	}
	@Transactional
	public List<AccountModel> listBySearch(int pageNo,int pageSize,String searchType,String searchField) {
		int temp = (pageNo-1)*pageSize;//所在页的第一条（不包含）
		Map<String, Object> param = new HashMap<>();
		param.put("pageNo", temp);
		param.put("pageSize", pageSize);
		param.put("searchType", searchType);
		param.put("searchField", searchField);
		return accountMapper.listBySearch(param);
	}

}
