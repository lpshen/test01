package com.bms.mapper;

import java.util.List;
import java.util.Map;

import com.bms.model.AccountModel;

public interface AccountMapper {

	void insertUser(AccountModel accountModel);
	void deleteUser(AccountModel accountModel);
	List<AccountModel> findAll();
	void updateUser(AccountModel accountModel);
	List<AccountModel> listByPage(int pageNo ,int pageSize,String username);
	Long getTotal();
	Long getTotalBySearch(Map<String , Object> param);
	List<AccountModel> listBySearch(Map<String, Object> param);
}
