package com.yxh.pojo;

public class news {
	private String pubDateStr;
	private String title;
	private String summary;
	public String getPubDateStr() {
		return pubDateStr;
	}
	public void setPubDateStr(String pubDateStr) {
		this.pubDateStr = pubDateStr;
	}
	public String getTitle() {
		return title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "news [pubDateStr=" + pubDateStr + ", title=" + title + ", summary=" + summary + "]";
	}
	public void setTitle(String title) {
		this.title = title;
	}

}	
