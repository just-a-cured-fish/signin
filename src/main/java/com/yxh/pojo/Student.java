package com.yxh.pojo;

public class Student {
	private	String sid;
	private String sname;
	private String ssid;
	private String sphone;
	private String ssex;
	private String semail;
	private String sdor;
	private String sfamphone;
	private String sclass;
	private String cname;
	private String sphoto;
	public String getSphoto() {
		return sphoto;
	}
	public void setSphoto(String sphoto) {
		this.sphoto = sphoto;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", ssid=" + ssid + ", sphone=" + sphone + ", ssex=" + ssex
				+ ", semail=" + semail + ", sdor=" + sdor + ", sfamphone=" + sfamphone + ", sclass=" + sclass + "]";
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSdor() {
		return sdor;
	}
	public void setSdor(String sdor) {
		this.sdor = sdor;
	}
	public String getSfamphone() {
		return sfamphone;
	}
	public void setSfamphone(String sfamphone) {
		this.sfamphone = sfamphone;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
}
