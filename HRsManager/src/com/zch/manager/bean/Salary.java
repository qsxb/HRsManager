package com.zch.manager.bean;

/**
 * Created by ch.zhang on 2017年10月11日 上午9:45:28 薪资表,属性有
 * 员工的基本工资,绩效奖金,奖励金额,惩罚金额,社保费用
 */
public class Salary {
	private Integer sid; // 薪资记录id
	private double ssalary;// 基本薪资
	private double sjxsal;// 绩效金额
	private double sresal;// 奖励金额
	private double spesal;// 惩罚金额
	private double ssbsal;// 社保金额
	private double stotalsal;// 该月所得金额
	private int year;
	private int month;
	private Employee employee;

	// 下面写了employeee属性
	public double getStotalsal() {
		return stotalsal;
	}

	public void setStotalsal(double stotalsal) {
		this.stotalsal = stotalsal;
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

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public double getSsalary() {
		return ssalary;
	}

	public void setSsalary(double ssalary) {
		this.ssalary = ssalary;
	}

	public double getSjxsal() {
		return sjxsal;
	}

	public void setSjxsal(double sjxsal) {
		this.sjxsal = sjxsal;
	}

	public double getSresal() {
		return sresal;
	}

	public void setSresal(double sresal) {
		this.sresal = sresal;
	}

	public double getSpesal() {
		return spesal;
	}

	public void setSpesal(double spesal) {
		this.spesal = spesal;
	}

	public double getSsbsal() {
		return ssbsal;
	}

	public void setSsbsal(double ssbsal) {
		this.ssbsal = ssbsal;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Salary(double ssalary, double sjxsal, double sresal, double spesal, double ssbsal, Employee employee) {
		super();
		this.ssalary = ssalary;
		this.sjxsal = sjxsal;
		this.sresal = sresal;
		this.spesal = spesal;
		this.ssbsal = ssbsal;
		this.employee = employee;
	}

	public Salary() {
		super();
	}

	@Override
	public String toString() {
		return "Salary [sid=" + sid + ", ssalary=" + ssalary + ", sjxsal=" + sjxsal + ", sresal=" + sresal + ", spesal="
				+ spesal + ", ssbsal=" + ssbsal + ", stotalsal=" + stotalsal + ", year=" + year + ", month=" + month
				+ ", employee=" + employee + "]";
	}
}
