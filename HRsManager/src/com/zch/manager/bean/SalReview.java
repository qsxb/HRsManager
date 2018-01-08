package com.zch.manager.bean;

/**
 * Created by ch.zhang on 2017年10月20日 下午7:55:59
 * 薪资异议表,属性有唯一标识id,异议原因,该条记录状态,薪资表的id(一对一的关系),员工表的
 */
public class SalReview {
	private Integer srid;
	private String srreason;
	private String srstatus; // 异议信息的状态
	private Salary salary; // 薪资表id但是这里写其对应的类
	private Employee employee;// 员工id但是这里写其对应的类

	public Integer getSrid() {
		return srid;
	}

	public void setSrid(Integer srid) {
		this.srid = srid;
	}

	public String getSrreason() {
		return srreason;
	}

	public void setSrreason(String srreason) {
		this.srreason = srreason;
	}

	public String getSrstatus() {
		return srstatus;
	}

	public void setSrstatus(String srstatus) {
		this.srstatus = srstatus;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SalReview(String srreason, String srstatus, Salary salary, Employee employee) {
		super();
		this.srreason = srreason;
		this.srstatus = srstatus;
		this.salary = salary;
		this.employee = employee;
	}

	public SalReview() {
		super();
	}

	@Override
	public String toString() {
		return "SalReview [srid=" + srid + ", srreason=" + srreason + ", srstatus=" + srstatus + ", salary=" + salary
				+ ", employee=" + employee + "]";
	}

}
