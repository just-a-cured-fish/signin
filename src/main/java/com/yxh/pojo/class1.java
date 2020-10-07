package com.yxh.pojo;

import java.util.List;

public class class1 {
	private String cid;
	private String cmajor;
	private String cname;
	private String tid;
	private Integer amount;
	

	private List<Student> student;
	public Integer getAmount() {
		return amount;
	}
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCmajor() {
		return cmajor;
	}
	public void setCmajor(String cmajor) {
		this.cmajor = cmajor;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "class1 [cid=" + cid + ", cmajor=" + cmajor + ", cname=" + cname + "]";
	}
	
}
