package com.zch.manager.bean;

import java.util.List;

/**
 * Created by ch.zhang on 2017年9月26日 下午1:00:43
 */
/*
 * 招聘信息实体类,字段有招聘信息id,部门,职位,对应招聘的人数,和简历表(信息 )存在一对多的关系,所以再加一个字段 List<Resume>
 * resumeList;
 */

public class Advertise {
	private Integer aid;
	private String adeptName;
	private String ajobName;
	private double asalary;
	private int anumber;
	private int astatus;// 求职者数量(也可理解为状态)
	private List<Resume> resumeList;

	public double getAsalary() {
		return asalary;
	}

	public void setAsalary(double asalary) {
		this.asalary = asalary;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAdeptName() {
		return adeptName;
	}

	public void setAdeptName(String adeptName) {
		this.adeptName = adeptName;
	}

	public String getAjobName() {
		return ajobName;
	}

	public void setAjobName(String ajobName) {
		this.ajobName = ajobName;
	}

	public int getAnumber() {
		return anumber;
	}

	public void setAnumber(int anumber) {
		this.anumber = anumber;
	}

	public int getAstatus() {
		return astatus;
	}

	public void setAstatus(int astatus) {
		this.astatus = astatus;
	}

	public List<Resume> getResumeList() {
		return resumeList;
	}

	public void setResumeList(List<Resume> resumeList) {
		this.resumeList = resumeList;
	}

	public Advertise(String adeptName, String ajobName, double asalary) {
		super();
		this.adeptName = adeptName;
		this.ajobName = ajobName;
		this.asalary = asalary;
	}

	public Advertise(String adeptName, String ajobName, double asalary, int anumber) {
		super();
		this.adeptName = adeptName;
		this.ajobName = ajobName;
		this.asalary = asalary;
		this.anumber = anumber;
	}

	public Advertise(Integer aid, String adeptName, String ajobName, double asalary, int anumber) {
		super();
		this.aid = aid;
		this.adeptName = adeptName;
		this.ajobName = ajobName;
		this.asalary = asalary;
		this.anumber = anumber;
	}

	public Advertise(String adeptName, String ajobName, double asalary, int anumber, List<Resume> resumeList) {
		super();
		this.adeptName = adeptName;
		this.ajobName = ajobName;
		this.asalary = asalary;
		this.anumber = anumber;
		this.resumeList = resumeList;
	}

	public Advertise() {
		super();
	}

	@Override
	public String toString() {
		return "Advertise [aid=" + aid + ", adeptName=" + adeptName + ", ajobName=" + ajobName + ", asalary=" + asalary
				+ ", anumber=" + anumber + ", resumeList=" + resumeList + "]";
	}

}
