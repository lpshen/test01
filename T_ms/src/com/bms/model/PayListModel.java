package com.bms.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayListModel {
	Integer id;
	String username;
	BigDecimal money;
	String time;
	String state;
	String ordernum;
	//连表查询 用户表 支付记录表 订单表
	public PayListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayListModel(Integer id, String username, BigDecimal money, String time, String state, String ordernum) {
		super();
		this.id = id;
		this.username = username;
		this.money = money;
		this.time = time;
		this.state = state;
		this.ordernum = ordernum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
	
	
	
}
