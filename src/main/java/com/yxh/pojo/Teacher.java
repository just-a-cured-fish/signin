package com.yxh.pojo;

import java.util.List;

public class Teacher {
	private	String tid;
	private String tname;
	private String tclass;
	private List<Student> student;
	private List<class1> class1;

	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	public List<class1> getClass1() {
		return class1;
	}
	public void setClass1(List<class1> class1) {
		this.class1 = class1;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", tclass=" + tclass + "]";
	}
}
