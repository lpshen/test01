package com.bms.model;

import java.math.BigDecimal;

public class UserOrder {//用户端订单表显示数据
	private Integer orderid;
	private String ordernum;
	private BigDecimal totalprice;
	private String address;
	private String name;
	private String phonenum;
	private String paystate;
	private String mastate;
	public UserOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserOrder(Integer orderid, String ordernum, BigDecimal totalprice, String address, String name,
			String phonenum, String paystate, String mastate) {
		super();
		this.orderid = orderid;
		this.ordernum = ordernum;
		this.totalprice = totalprice;
		this.address = address;
		this.name = name;
		this.phonenum = phonenum;
		this.paystate = paystate;
		this.mastate = mastate;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	public String getMastate() {
		return mastate;
	}
	public void setMastate(String mastate) {
		this.mastate = mastate;
	}
	@Override
	public String toString() {
		return "UserOrder [orderid=" + orderid + ", ordernum=" + ordernum + ", totalprice=" + totalprice + ", address="
				+ address + ", name=" + name + ", phonenum=" + phonenum + ", paystate=" + paystate + ", mastate="
				+ mastate + "]";
	}
	
	

}
