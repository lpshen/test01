package com.bms.model;

public class OrderMenu {
	private Integer id;
	private String ordernum;
	private Integer menuid;
	private Integer count;
	public OrderMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderMenu(Integer id, String ordernum, Integer menuid, Integer count) {
		super();
		this.id = id;
		this.ordernum = ordernum;
		this.menuid = menuid;
		this.count = count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "OrderMenu [id=" + id + ", ordernum=" + ordernum + ", menuid=" + menuid + ", count=" + count + "]";
	}
	
}
