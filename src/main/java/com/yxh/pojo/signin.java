package com.yxh.pojo;

import java.util.Date;

public class signin {
	private Integer id;
	private String temp;
	private String about;
	private String pic;
	private String address;
	private String lat;
	private String lng;
	private String dan;
	public String getDan() {
		return dan;
	}
	public void setDan(String dan) {
		this.dan = dan;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	private Date time;
	private String cid;

	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "signin [id=" + id + ", temp=" + temp + ", about=" + about + ", pic=" + pic + ", address=" + address
				+ ", lat=" + lat + ", lng=" + lng + ", dan=" + dan + ", time=" + time + ", studentid=" + studentid
				+ ", thesignid=" + thesignid + ", sname=" + sname + ", istrue=" + istrue + ", istrue2=" + istrue2
				+ ", istrue3=" + istrue3 + "]";
	}
	private String studentid;
	private Integer thesignid;
	public Integer getThesignid() {
		return thesignid;
	}
	private String sname;
	public String getSname() {
		return sname;
	}

	public void setThesignid(Integer thesignid) {
		this.thesignid = thesignid;
	}
	private Integer istrue;
	private Integer istrue2;
	private Integer istrue3;
	public Integer getId() {
		return id;
	}
	public Integer getIstrue2() {
		return istrue2;
	}
	public void setIstrue2(Integer istrue2) {
		this.istrue2 = istrue2;
	}
	public Integer getIstrue3() {
		return istrue3;
	}
	public void setIstrue3(Integer istrue3) {
		this.istrue3 = istrue3;
	}
	public Integer getIstrue() {
		return istrue;
	}
	public void setIstrue(Integer istrue) {
		this.istrue = istrue;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	 
}
