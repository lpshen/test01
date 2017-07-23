package com.bms.model;

public class MessageModel {
	//留言管理 多表连接 留言表
	private Integer id ;
	private Integer userid = 0;
	private Integer adminid = 0;
	private String content;
	private String reply;
	private String state = "未回复";
	private String evaluate;
	private Integer replyid = 0;
	private String messagetime;
	private String replytime;
	public MessageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageModel(Integer id, Integer userid, Integer adminid, String content, String reply, Integer replyid,
			String messagetime, String replytime, String state, String evaluate) {
		super();
		this.id = id;
		this.userid = userid;
		this.adminid = adminid;
		this.content = content;
		this.reply = reply;
		this.replyid = replyid;
		this.messagetime = messagetime;
		this.replytime = replytime;
		this.state = state;
		this.evaluate = evaluate;
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
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Integer getReplyid() {
		return replyid;
	}
	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
	}
	public String getMessagetime() {
		return messagetime;
	}
	public void setMessagetime(String messagetime) {
		this.messagetime = messagetime;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	

}
