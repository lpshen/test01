package com.bms.model;

import java.math.BigDecimal;

public class GreensModel {
	Integer menuid;
	Integer merid;
	String grename;
	String pictureurl;
	BigDecimal oriprice;
	String  type;
	BigDecimal curprice;
	String time;
	String state;
	public GreensModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GreensModel(Integer menuid, Integer merid, String grename, String pictureurl, BigDecimal oriprice,
			String type, BigDecimal curprice, String time, String state) {
		super();
		this.menuid = menuid;
		this.merid = merid;
		this.grename = grename;
		this.pictureurl = pictureurl;
		this.oriprice = oriprice;
		this.type = type;
		this.curprice = curprice;
		this.time = time;
		this.state = state;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public Integer getMerid() {
		return merid;
	}
	public void setMerid(Integer merid) {
		this.merid = merid;
	}
	public String getGrename() {
		return grename;
	}
	public void setGrename(String grename) {
		this.grename = grename;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
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
	public BigDecimal getCurprice() {
		return curprice;
	}
	public void setCurprice(BigDecimal curprice) {
		this.curprice = curprice;
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
	@Override
	public String toString() {
		return "GreensModel [menuid=" + menuid + ", merid=" + merid + ", grename=" + grename + ", pictureurl="
				+ pictureurl + ", oriprice=" + oriprice + ", type=" + type + ", curprice=" + curprice + ", time=" + time
				+ ", state=" + state + "]";
	}
	
}
