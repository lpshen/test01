package com.bms.model;

import java.math.BigDecimal;

public class RechargeRecordModel {
	Integer id;
	Integer userId;
	BigDecimal money;
	String  time;
	Integer operationid;
	public RechargeRecordModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RechargeRecordModel(Integer id, Integer userId, BigDecimal money, String time, Integer operationid) {
		super();
		this.id = id;
		this.userId = userId;
		this.money = money;
		this.time = time;
		this.operationid = operationid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getOperationid() {
		return operationid;
	}
	public void setOperationid(Integer operationid) {
		this.operationid = operationid;
	}
	
}
