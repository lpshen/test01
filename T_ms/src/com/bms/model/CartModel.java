package com.bms.model;
import java.math.BigDecimal;

public class CartModel {
	private Integer id;
	private Integer menuid;
	private Integer merid;
	private Integer userid;
	private String good;
	private String pictureurl;
	private BigDecimal oriprice;
	private Integer count;
	private BigDecimal curprice;
	private String state;
	public CartModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartModel(Integer id, Integer menuid, Integer merid, Integer userid, String good, String pictureurl,
			BigDecimal oriprice, Integer count, BigDecimal curprice, String state) {
		super();
		this.id = id;
		this.menuid = menuid;
		this.merid = merid;
		this.userid = userid;
		this.good = good;
		this.pictureurl = pictureurl;
		this.oriprice = oriprice;
		this.count = count;
		this.curprice = curprice;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getCurprice() {
		return curprice;
	}
	public void setCurprice(BigDecimal curprice) {
		this.curprice = curprice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "CartModel [id=" + id + ", menuid=" + menuid + ", merid=" + merid + ", userid=" + userid + ", good="
				+ good + ", pictureurl=" + pictureurl + ", oriprice=" + oriprice + ", count=" + count + ", curprice="
				+ curprice + ", state=" + state + "]";
	}
	
	
	

}