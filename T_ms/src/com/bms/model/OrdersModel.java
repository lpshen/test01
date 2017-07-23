package com.bms.model;

import java.math.BigDecimal;

public class OrdersModel {
	Integer orderid;
	String ordernum;
	Integer menuid;
	Integer userid;
	String message;
	BigDecimal totalprice;
	String time;
	String paystate;
	String mastate;
	BigDecimal privilege;
	BigDecimal freight;
	Integer merid;
	String melist;
	String paymet;
	Integer addresssid;
	public OrdersModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrdersModel(Integer orderid, String ordernum, Integer menuid, Integer userid, String message,
			BigDecimal totalprice, String time, String paystate, String mastate, BigDecimal privilege,
			BigDecimal freight, Integer merid, String melist, String paymet, Integer addresssid) {
		super();
		this.orderid = orderid;
		this.ordernum = ordernum;
		this.menuid = menuid;
		this.userid = userid;
		this.message = message;
		this.totalprice = totalprice;
		this.time = time;
		this.paystate = paystate;
		this.mastate = mastate;
		this.privilege = privilege;
		this.freight = freight;
		this.merid = merid;
		this.melist = melist;
		this.paymet = paymet;
		this.addresssid = addresssid;
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
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public BigDecimal getPrivilege() {
		return privilege;
	}
	public void setPrivilege(BigDecimal privilege) {
		this.privilege = privilege;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public Integer getMerid() {
		return merid;
	}
	public void setMerid(Integer merid) {
		this.merid = merid;
	}
	public String getMelist() {
		return melist;
	}
	public void setMelist(String melist) {
		this.melist = melist;
	}
	public String getPaymet() {
		return paymet;
	}
	public void setPaymet(String paymet) {
		this.paymet = paymet;
	}
	public Integer getAddresssid() {
		return addresssid;
	}
	public void setAddresssid(Integer addresssid) {
		this.addresssid = addresssid;
	}
	@Override
	public String toString() {
		return "OrdersModel [orderid=" + orderid + ", ordernum=" + ordernum + ", menuid=" + menuid + ", userid="
				+ userid + ", message=" + message + ", totalprice=" + totalprice + ", time=" + time + ", paystate="
				+ paystate + ", mastate=" + mastate + ", privilege=" + privilege + ", freight=" + freight + ", merid="
				+ merid + ", melist=" + melist + ", paymet=" + paymet + ", addresssid=" + addresssid + "]";
	}

}
