package com.zch.manager.bean;

/**
 * Created by ch.zhang on 2017年9月26日 下午6:55:44
 * 用户可以分为游客和注册的用户,一个用户对应一张简历表(这是我的业务逻辑) 属性有 用户id,用户名,密码,年龄,邮箱,是否在邮箱激活,录取状态,简历
 */
public class User {
	private Integer uid;
	private String uname;
	private String upwd;
	private int uage;
	private String uemail;
	private int ustate;
	private int uadmit; // 是否录取状态1/0

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public int getUage() {
		return uage;
	}

	public void setUage(int uage) {
		this.uage = uage;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}

	public int getUadmit() {
		return uadmit;
	}

	public void setUadmit(int uadmit) {
		this.uadmit = uadmit;
	}

	public User(String uname, String upwd, int uage, String uemail, int ustate, int uadmit) {
		super();
		this.uname = uname;
		this.upwd = upwd;
		this.uage = uage;
		this.uemail = uemail;
		this.ustate = ustate;
		this.uadmit = uadmit;
	}

	public User(String uname, String upwd) {
		super();
		this.uname = uname;
		this.upwd = upwd;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", uage=" + uage + ", uemail=" + uemail
				+ ", ustate=" + ustate + ", uadmit=" + uadmit + "]";
	}
}
