package com.yxh.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class thesign {
	private Integer thesignid;
	private String tid;
	private String cid;
	private String cname;
	private String cmajor;
	private String how;
	private String howface;
	private String lat;
	private String lng;
	private String endday1;
	public String getSamekey() {
		return samekey;
	}
	public void setSamekey(String samekey) {
		this.samekey = samekey;
	}
	private String samekey;
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	private String tname;

	public String getEndday1() {
		return endday1;
	}
	public void setEndday1(String endday1) {
		this.endday1 = endday1;
	}
	public String getHowface() {
		return howface;
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
	public void setHowface(String howface) {
		this.howface = howface;
	}
	public String getHowphoto() {
		return howphoto;
	}
	public void setHowphoto(String howphoto) {
		this.howphoto = howphoto;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String ques;
	private String howphoto;
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	private String address;
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date startday;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date endday;
	public String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartday() {
		return startday;
	}
	public void setStartday(Date startday) {
		this.startday = startday;
	}
	
	public Date getEndday() {
		return endday;
	}
	public void setEndday(Date endday) {
		this.endday = endday;
	}
	public String getCname() {
		return cname;
	}
	public String getCmajor() {
		return cmajor;
	}
	public void setCmajor(String cmajor) {
		this.cmajor = cmajor;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	private Date starttime;
	@Override
	public String toString() {
		return "thesign [thesignid=" + thesignid + ", tid=" + tid + ", cid=" + cid + ", cname=" + cname + ", cmajor="
				+ cmajor + ", how=" + how + ", howface=" + howface + ", lat=" + lat + ", lng=" + lng + ", endday1="
				+ endday1 + ", tname=" + tname + ", howphoto=" + howphoto + ", address=" + address + ", title=" + title
				+ ", content=" + content + ", startday=" + startday + ", endday=" + endday + ", starttime=" + starttime
				+ ", endtime=" + endtime + "]";
	}
	public Integer getThesignid() {
		return thesignid;
	}
	public void setThesignid(Integer thesignid) {
		this.thesignid = thesignid;
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
	
	
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	private Date endtime;
}
