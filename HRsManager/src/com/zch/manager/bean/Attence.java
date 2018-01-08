package com.zch.manager.bean;

import java.util.Date;

/**
 * Created by ch.zhang on 2017年10月9日 下午4:03:25
 * 考勤实体类,包括员工每天的签到,有属性上班打卡时间,下班打卡时间,迟到状态(和上班打卡时间有关),早退状态(和下班打卡时间有关)
 * 年,月,月份对应每月的天数,哪个员工的考勤记录(属性员工id,员工name) attence :考勤表
 */
public class Attence {
	private Integer aid;
	private Date onworktime;// 上班打卡时间
	private Date offworktime;// 下班打卡时间
	private String comelatestatus;// 迟到状态
	private String leftearlystatus;// 早退状态
	private int year;// 打卡哪个年份
	private int month;// 打卡哪个月份
	private int day;// 打卡哪个天数
	private int monthodfdays;// 打卡哪个月份对应的天数
	private Employee employee;// 哪个员工的考勤(打卡)记录

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Date getOnworktime() {
		return onworktime;
	}

	public void setOnworktime(Date onworktime) {
		this.onworktime = onworktime;
	}

	public Date getOffworktime() {
		return offworktime;
	}

	public void setOffworktime(Date offworktime) {
		this.offworktime = offworktime;
	}

	public String getComelatestatus() {
		return comelatestatus;
	}

	public void setComelatestatus(String comelatestatus) {
		this.comelatestatus = comelatestatus;
	}

	public String getLeftearlystatus() {
		return leftearlystatus;
	}

	public void setLeftearlystatus(String leftearlystatus) {
		this.leftearlystatus = leftearlystatus;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonthodfdays() {
		return monthodfdays;
	}

	public void setMonthodfdays(int monthodfdays) {
		this.monthodfdays = monthodfdays;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Attence(Date onworktime, Date offworktime, String comelatestatus, String leftearlystatus, int year,
			int month, int day, int monthodfdays, Employee employee) {
		super();
		this.onworktime = onworktime;
		this.offworktime = offworktime;
		this.comelatestatus = comelatestatus;
		this.leftearlystatus = leftearlystatus;
		this.year = year;
		this.month = month;
		this.day = day;
		this.monthodfdays = monthodfdays;
		this.employee = employee;
	}

	public Attence() {
		super();
	}

	@Override
	public String toString() {
		return "Attence [aid=" + aid + ", onworktime=" + onworktime + ", offworktime=" + offworktime
				+ ", comelatestatus=" + comelatestatus + ", leftearlystatus=" + leftearlystatus + ", year=" + year
				+ ", month=" + month + ", day=" + day + ", monthodfdays=" + monthodfdays + ", employee=" + employee
				+ "]";
	}

}
