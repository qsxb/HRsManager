package com.zch.manager.bean;

import java.util.Date;

/**
 * Created by ch.zhang on 2017年9月27日 上午11:24:35
 */
/*
 * 简历信息实体类 属性有:简历id,姓名 年龄,性别,生日,学历 电话号码,邮箱,政治面貌 简历信息和用户信息是一对一的关系
 */
public class Resume {
	private Integer rid;
	private String rname;
	private int rage;
	private String rsex;
	private Date rbirthday;
	private String reducation;
	private String rtel;
	private String remail;
	private String rpolstatus;
	private User user;
	private String rreadStatus; // 简历消息读取状态
	private String rfaceStatus;// 面试状态
	private Date rfaceTime;// 管理员通知面试时间
	private String deptName; // 应聘的部门
	private String jobName;// 应聘的职位
	private double rsalary;// 应聘的薪资

	public double getRsalary() {
		return rsalary;
	}

	public void setRsalary(double rsalary) {
		this.rsalary = rsalary;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getRreadStatus() {
		return rreadStatus;
	}

	public void setRreadStatus(String rreadStatus) {
		this.rreadStatus = rreadStatus;
	}

	public String getRfaceStatus() {
		return rfaceStatus;
	}

	public void setRfaceStatus(String rfaceStatus) {
		this.rfaceStatus = rfaceStatus;
	}

	public Date getRfaceTime() {
		return rfaceTime;
	}

	public void setRfaceTime(Date rfaceTime) {
		this.rfaceTime = rfaceTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getRage() {
		return rage;
	}

	public void setRage(int rage) {
		this.rage = rage;
	}

	public String getRsex() {
		return rsex;
	}

	public void setRsex(String rsex) {
		this.rsex = rsex;
	}

	public Date getRbirthday() {
		return rbirthday;
	}

	public void setRbirthday(Date rbirthday) {
		this.rbirthday = rbirthday;
	}

	public String getReducation() {
		return reducation;
	}

	public void setReducation(String reducation) {
		this.reducation = reducation;
	}

	public String getRtel() {
		return rtel;
	}

	public void setRtel(String rtel) {
		this.rtel = rtel;
	}

	public String getRemail() {
		return remail;
	}

	public void setRemail(String remail) {
		this.remail = remail;
	}

	public String getRpolstatus() {
		return rpolstatus;
	}

	public void setRpolstatus(String rpolstatus) {
		this.rpolstatus = rpolstatus;
	}

	public Resume(String rname, int rage, String rsex, Date rbirthday, String reducation, String rtel, String remail,
			String rpolstatus) {
		super();
		this.rname = rname;
		this.rage = rage;
		this.rsex = rsex;
		this.rbirthday = rbirthday;
		this.reducation = reducation;
		this.rtel = rtel;
		this.remail = remail;
		this.rpolstatus = rpolstatus;
	}

	public Resume(Integer rid, String rname, int rage, String rsex, String reducation, String rtel, String remail,
			String rpolstatus) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.rage = rage;
		this.rsex = rsex;
		this.reducation = reducation;
		this.rtel = rtel;
		this.remail = remail;
		this.rpolstatus = rpolstatus;
	}

	public Resume(String rname, int rage, String rsex, String reducation, String rtel, String remail, String rpolstatus,
			User user) {
		super();
		this.rname = rname;
		this.rage = rage;
		this.rsex = rsex;
		this.reducation = reducation;
		this.rtel = rtel;
		this.remail = remail;
		this.rpolstatus = rpolstatus;
		this.user = user;
	}

	public Resume() {
		super();
	}

	@Override
	public String toString() {
		return "Resume [rid=" + rid + ", rname=" + rname + ", rage=" + rage + ", rsex=" + rsex + ", rbirthday="
				+ rbirthday + ", reducation=" + reducation + ", rtel=" + rtel + ", remail=" + remail + ", rpolstatus="
				+ rpolstatus + ", user=" + user + ", rreadStatus=" + rreadStatus + ", rfaceStatus=" + rfaceStatus
				+ ", rfaceTime=" + rfaceTime + ", deptName=" + deptName + ", jobName=" + jobName + ", rsalary="
				+ rsalary + "]";
	}

}
