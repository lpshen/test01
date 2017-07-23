package com.bms.model;

import java.math.BigDecimal;

public class OrderDetails {//用于订单明细查询
	private String ordernum;//订单编号
	private String grename;//菜名
	private BigDecimal oriprice;//现价
	private String type;//菜类型
	private Integer count;//数量
	private String time;//订单时间
	private BigDecimal totalprice;//总价
	private String name; //收货人姓名
	private String address;//配送地址
	private String phonenum;//收货电话
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetails(String ordernum, String grename, BigDecimal oriprice, String type, Integer count, String time,
			BigDecimal totalprice, String name, String address, String phonenum) {
		super();
		this.ordernum = ordernum;
		this.grename = grename;
		this.oriprice = oriprice;
		this.type = type;
		this.count = count;
		this.time = time;
		this.totalprice = totalprice;
		this.name = name;
		this.address = address;
		this.phonenum = phonenum;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getGrename() {
		return grename;
	}
	public void setGrename(String grename) {
		this.grename = grename;
	}
	public BigDecimal getOriprice() {
		return oriprice;
	}
	public void setOriprice(BigDecimal oriprice) {
		this.oriprice = oriprice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	@Override
	public String toString() {
		return "OrderDetail [ordernum=" + ordernum + ", grename=" + grename + ", oriprice=" + oriprice + ", type="
				+ type + ", count=" + count + ", time=" + time + ", totalprice=" + totalprice + ", name=" + name
				+ ", address=" + address + ", phonenum=" + phonenum + "]";
	}
}
