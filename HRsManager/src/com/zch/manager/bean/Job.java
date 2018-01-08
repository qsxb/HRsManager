package com.zch.manager.bean;

import java.util.List;

/**
 * Created by ch.zhang on 2017年9月26日 下午1:10:19
 */
/*
 * 职位实体类 属性有 职位id,职位名称 ,存在多对一问题,多个职位对应一个部门
 */
public class Job {
	private Integer jid;
	private String jname;
	private String jcreatetime;// 职位创建时间
	private Dept dept;
	private List<Employee> employee;

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getJcreatetime() {
		return jcreatetime;
	}

	public void setJcreatetime(String jcreatetime) {
		this.jcreatetime = jcreatetime;
	}

	public Job(String jname, String jcreatetime, Dept dept, List<Employee> employee) {
		super();
		this.jname = jname;
		this.jcreatetime = jcreatetime;
		this.dept = dept;
		this.employee = employee;
	}

	public Job() {
		super();
	}

	@Override
	public String toString() {
		return "Job [jid=" + jid + ", jname=" + jname + ", jcreatetime=" + jcreatetime + ", dept=" + dept
				+ ", employee=" + employee + "]";
	}
}
