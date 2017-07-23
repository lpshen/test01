package com.bms.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayRecordModel {
	Integer id;
	Integer userId;
	BigDecimal money;
	String time;
	String ordernum; //连表查询 订单表 支付记录表
	public PayRecordModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayRecordModel(Integer id, Integer userId, BigDecimal money, String time, String ordernum) {
		super();
		this.id = id;
		this.userId = userId;
		this.money = money;
		this.time = time;
		this.ordernum = ordernum;
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
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
	

	

}
