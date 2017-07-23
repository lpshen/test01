package com.bms.model;

public class AddressModel {
	Integer id;
	Integer userid;
	String address;
	String postcode;
	String name;
	String phonenum;
	String state ="0" ;//删除为1 默认为0
	public AddressModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddressModel(Integer id, Integer userid, String address, String postcode, String name, String phonenum,
			String state) {
		super();
		this.id = id;
		this.userid = userid;
		this.address = address;
		this.postcode = postcode;
		this.name = name;
		this.phonenum = phonenum;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "AddressModel [id=" + id + ", userid=" + userid + ", address=" + address + ", postcode=" + postcode
				+ ", name=" + name + ", phonenum=" + phonenum + ", state=" + state + "]";
	}
	

}
