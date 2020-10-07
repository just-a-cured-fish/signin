package com.yxh.pojo;

public class User {
	private int userid;
	private String username;
	private String userpwd;

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}



	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public User(int userid, String username, String userpwd) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", userpwd=" + userpwd + "]";
	}

}
