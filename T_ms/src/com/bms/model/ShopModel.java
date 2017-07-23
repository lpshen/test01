package com.bms.model;

public class ShopModel {
	Integer id;
	Integer adminid;
	String shopname;
	String pictureurl;
	String address;
	String phonenum;
	String descri;
	String time;
	String state;
	public ShopModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShopModel(Integer id, Integer adminid, String shopname, String pictureurl, String address, String phonenum,
			String descri, String time, String state) {
		super();
		this.id = id;
		this.adminid = adminid;
		this.shopname = shopname;
		this.pictureurl = pictureurl;
		this.address = address;
		this.phonenum = phonenum;
		this.descri = descri;
		this.time = time;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
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
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
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
		return "ShopModel [id=" + id + ", adminid=" + adminid + ", shopname=" + shopname + ", pictureurl=" + pictureurl
				+ ", address=" + address + ", phonenum=" + phonenum + ", descri=" + descri + ", time=" + time
				+ ", state=" + state + "]";
	}
	

}
