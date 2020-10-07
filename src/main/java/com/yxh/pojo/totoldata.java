package com.yxh.pojo;

public class totoldata {
	private String gntotal;
	private String deathtotal;
	private	String curetotal;
	private String econNum;
	public String getAsymptoNum() {
		return asymptoNum;
	}
	public void setAsymptoNum(String asymptoNum) {
		this.asymptoNum = asymptoNum;
	}
	private String jwsrNum;
	private String asymptoNum;
	public String getDeathtotal() {
		return deathtotal;
	}
	public void setDeathtotal(String deathtotal) {
		this.deathtotal = deathtotal;
	}
	public String getCuretotal() {
		return curetotal;
	}
	public String getGntotal() {
		return gntotal;
	}
	public void setGntotal(String gntotal) {
		this.gntotal = gntotal;
	}
	public void setCuretotal(String curetotal) {
		this.curetotal = curetotal;
	}
	public String getEconNum() {
		return econNum;
	}
	public void setEconNum(String econNum) {
		this.econNum = econNum;
	}
	public String getJwsrNum() {
		return jwsrNum;
	}
	@Override
	public String toString() {
		return "totoldata [gntotal=" + gntotal + ", deathtotal=" + deathtotal + ", curetotal=" + curetotal
				+ ", econNum=" + econNum + ", jwsrNum=" + jwsrNum + "]";
	}
	public void setJwsrNum(String jwsrNum) {
		this.jwsrNum = jwsrNum;
	}
}
