package com.bms.model;

import java.util.Date;

public class LogLoginModel {
	Integer id;
	Integer userid;
	String username;
	String role;
	String time;

	public LogLoginModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogLoginModel(Integer id, Integer userid, String username, String role, String time) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.role = role;
		this.time = time;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
