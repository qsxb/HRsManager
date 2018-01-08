package com.zch.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ch.zhang on 2017年10月2日 下午2:11:16 员工实体类,把录取的简历信息基本动态取出存放进员工表中
 */
public class Employee implements Serializable {
	private Integer eid;
	private String ename;// 姓名
	private String epwd;// 密码
	private Job ejob;// 工作
	private String email;// 邮箱
	private int eage;// 年龄
	private String esex;// 性别
	private double esalary;// 薪资
	private String eeducation;// 学历
	private String etel;// 联系方式
	private String epolstatus; // 政治面貌
	private String estatus; // 员工的状态(实习,就职,离职)
	private Dept dept;// 员工对应的职位
	private Train train;// 员工对应的培训信息
	private Repe repe;// 员工对应的奖惩信息
	private List<Attence> listAttence;// 员工对应的考勤信息,一位员工对应多条考勤记录

	public List<Attence> getListAttence() {
		return listAttence;
	}

	public void setListAttence(List<Attence> listAttence) {
		this.listAttence = listAttence;
	}

	public Repe getRepe() {
		return repe;
	}

	public void setRepe(Repe repe) {
		this.repe = repe;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Dept getDept() {
		return dept;
	}

	public String getEpwd() {
		return epwd;
	}

	public void setEpwd(String epwd) {
		this.epwd = epwd;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Job getEjob() {
		return ejob;
	}

	public void setEjob(Job ejob) {
		this.ejob = ejob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEage() {
		return eage;
	}

	public void setEage(int eage) {
		this.eage = eage;
	}

	public String getEsex() {
		return esex;
	}

	public void setEsex(String esex) {
		this.esex = esex;
	}

	public double getEsalary() {
		return esalary;
	}

	public void setEsalary(double esalary) {
		this.esalary = esalary;
	}

	public String getEeducation() {
		return eeducation;
	}

	public void setEeducation(String eeducation) {
		this.eeducation = eeducation;
	}

	public String getEtel() {
		return etel;
	}

	public void setEtel(String etel) {
		this.etel = etel;
	}

	public String getEpolstatus() {
		return epolstatus;
	}

	public void setEpolstatus(String epolstatus) {
		this.epolstatus = epolstatus;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Employee(String ename, String epwd) {
		super();
		this.ename = ename;
		this.epwd = epwd;
	}

	public Employee(String ename, Job ejob, String email, int eage, String esex, double esalary, String eeducation,
			String etel, String epolstatus, String estatus) {
		super();
		this.ename = ename;
		this.ejob = ejob;
		this.email = email;
		this.eage = eage;
		this.esex = esex;
		this.esalary = esalary;
		this.eeducation = eeducation;
		this.etel = etel;
		this.epolstatus = epolstatus;
		this.estatus = estatus;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", epwd=" + epwd + ", ejob=" + ejob + ", email=" + email
				+ ", eage=" + eage + ", esex=" + esex + ", esalary=" + esalary + ", eeducation=" + eeducation
				+ ", etel=" + etel + ", epolstatus=" + epolstatus + ", estatus=" + estatus + ", dept=" + dept
				+ ", train=" + train + ", repe=" + repe + ", listAttence=" + listAttence + "]";
	}
}
