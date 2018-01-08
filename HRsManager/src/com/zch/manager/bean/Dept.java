package com.zch.manager.bean;

import java.util.List;

/**
 * Created by ch.zhang on 2017年9月26日 下午1:06:57
 */
/*
 * 部门实体类 属性有部门id 部门名称 ,存在部门与职位一对多的关系
 */
public class Dept {
	private Integer did;
	private String dname;
	private List<Job> job;
	private String dcreatetime;

	public String getDcreatetime() {
		return dcreatetime;
	}

	public void setDcreatetime(String dcreatetime) {
		this.dcreatetime = dcreatetime;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}

	public Dept(String dname, List<Job> job) {
		super();
		this.dname = dname;
		this.job = job;
	}

	public Dept() {
		super();
	}

	@Override
	public String toString() {
		return "Dept [did=" + did + ", dname=" + dname + ", job=" + job + ", dcreatetime=" + dcreatetime + "]";
	}
}
