package com.zch.manager.bean;

/**
 * Created by ch.zhang on 2017年10月20日 下午5:38:54 管理员实体类 属性简单,id 姓名 密码
 */
public class Admin {
	private Integer aid;
	private String aname;
	private String apwd;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public Admin(String aname, String apwd) {
		super();
		this.aname = aname;
		this.apwd = apwd;
	}

	public Admin() {
		super();
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aname=" + aname + ", apwd=" + apwd + "]";
	}
}
