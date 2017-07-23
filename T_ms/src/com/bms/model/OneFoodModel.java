package com.bms.model;

import java.math.BigDecimal;

public class OneFoodModel {
	Integer menuid;
	Integer merid;
	String grename;
	String pictureurl;
	BigDecimal oriprice;
	String  type;
	BigDecimal curprice;
	String shopname;
	public OneFoodModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OneFoodModel(Integer menuid, Integer merid, String grename, String pictureurl, BigDecimal oriprice,
			String type, BigDecimal curprice, String shopname) {
		super();
		this.menuid = menuid;
		this.merid = merid;
		this.grename = grename;
		this.pictureurl = pictureurl;
		this.oriprice = oriprice;
		this.type = type;
		this.curprice = curprice;
		this.shopname = shopname;
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
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	@Override
	public String toString() {
		return "OneFoodModel [menuid=" + menuid + ", merid=" + merid + ", grename=" + grename + ", pictureurl="
				+ pictureurl + ", oriprice=" + oriprice + ", type=" + type + ", curprice=" + curprice + ", shopname="
				+ shopname + "]";
	}
	

}
