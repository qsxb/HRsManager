package com.zch.manager.bean;

/**
 * Created by ch.zhang on 2017年10月21日 上午9:48:41
 */
public class EmployeeCusttom extends Employee {
	private int kgDay;// 缺勤天数,缺勤就是旷工,没打卡也算缺勤旷工,扣100RMB
	private int cdDay;// 迟到天数,迟到扣20RMB
	private int ztDay;// 早退天数,早退扣20RMB
	private double sbsal; // 员工每月社保金额
	private double jxsal;// 绩效工资 管理员适当的给出
	private double pesal;// 该月罚款金额
	private double resal;// 该月奖励金额
	private double totalSal;// 本月应得的工资
	private int year; // 获得年份
	private int month;// 获取月份

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

	public double getTotalSal() {
		return totalSal;
	}

	public void setTotalSal(double totalSal) {
		this.totalSal = totalSal;
	}

	public double getSbsal() {
		return sbsal;
	}

	public void setSbsal(double sbsal) {
		this.sbsal = sbsal;
	}

	public double getPesal() {
		return pesal;
	}

	public void setPesal(double pesal) {
		this.pesal = pesal;
	}

	public double getResal() {
		return resal;
	}

	public void setResal(double resal) {
		this.resal = resal;
	}

	public double getJxsal() {
		return jxsal;
	}

	public void setJxsal(double jxsal) {
		this.jxsal = jxsal;
	}

	public int getKgDay() {
		return kgDay;
	}

	public void setKgDay(int kgDay) {
		this.kgDay = kgDay;
	}

	public int getCdDay() {
		return cdDay;
	}

	public void setCdDay(int cdDay) {
		this.cdDay = cdDay;
	}

	public int getZtDay() {
		return ztDay;
	}

	public void setZtDay(int ztDay) {
		this.ztDay = ztDay;
	}

	@Override
	public String toString() {
		return "EmployeeCusttom [kgDay=" + kgDay + ", cdDay=" + cdDay + ", ztDay=" + ztDay + ", sbsal=" + sbsal
				+ ", jxsal=" + jxsal + ", pesal=" + pesal + ", resal=" + resal + ", totalSal=" + totalSal + ", year="
				+ year + ", month=" + month + ", getDept()=" + getDept() + ", getEid()=" + getEid() + ", getEname()="
				+ getEname() + ", getEjob()=" + getEjob() + "]";
	}

}
