package com.bms.model;

public class DiningTableModel {
	private Integer diningTableId;
	private String tableType;
	private Integer tableNum;
	private String updateTime;
	public DiningTableModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiningTableModel(Integer diningTableId, String tableType, Integer tableNum, String updateTime) {
		super();
		this.diningTableId = diningTableId;
		this.tableType = tableType;
		this.tableNum = tableNum;
		this.updateTime = updateTime;
	}
	public Integer getDiningTableId() {
		return diningTableId;
	}
	public void setDiningTableId(Integer diningTableId) {
		this.diningTableId = diningTableId;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public Integer getTableNum() {
		return tableNum;
	}
	public void setTableNum(Integer tableNum) {
		this.tableNum = tableNum;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "DiningTableModel [diningTableId=" + diningTableId + ", tableType=" + tableType + ", tableNum="
				+ tableNum + ", updateTime=" + updateTime + "]";
	}
	
	
}
