package com.yxh.pojo;

public class data {
	private String times;
	private totoldata totoldata;
	private dailydata dailydata;
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public totoldata getTotoldata() {
		return totoldata;
	}
	public void setTotoldata(totoldata totoldata) {
		this.totoldata = totoldata;
	}
	public dailydata getDailydata() {
		return dailydata;
	}
	@Override
	public String toString() {
		return "data [times=" + times + ", totoldata=" + totoldata + ", dailydata=" + dailydata + "]";
	}
	public void setDailydata(dailydata dailydata) {
		this.dailydata = dailydata;
	}
}
