package com.bms.model;

public class AreaModel {
	private  String id;
	private String parent_id;
	private String name; 
	private String code;
	private Integer type; //国家1 省2 市3 区4
	public AreaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AreaModel(String id, String parent_id, String name, String code, Integer type) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.name = name;
		this.code = code;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AreaModel [id=" + id + ", parent_id=" + parent_id + ", name=" + name + ", code=" + code + ", type="
				+ type + "]";
	}
	

}
