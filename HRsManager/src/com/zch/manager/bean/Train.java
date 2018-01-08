package com.zch.manager.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by ch.zhang on 2017年10月7日 下午12:08:28 培训信息,字段包括,信息id,培训时间,培训内容,
 * 培训地点,给哪些员工培训
 */
public class Train {
	private Integer tid;
	private String ttime;
	private String tcontent;
	private String taddress;
	private List<Employee> list;
	private Employee employee; // 培训官的信息(也是从员工里面选出来的)

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTaddress() {
		return taddress;
	}

	public void setTaddress(String taddress) {
		this.taddress = taddress;
	}

	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	public String getTcontent() {
		return tcontent;
	}

	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public Train(String ttime, String tcontent, String taddress, List<Employee> list) {
		super();
		this.ttime = ttime;
		this.tcontent = tcontent;
		this.taddress = taddress;
		this.list = list;
	}

	public Train(String tcontent, String taddress, List<Employee> list) {
		super();
		this.tcontent = tcontent;
		this.taddress = taddress;
		this.list = list;
	}

	public Train() {
		super();
	}

	@Override
	public String toString() {
		return "Train [tid=" + tid + ", ttime=" + ttime + ", tcontent=" + tcontent + ", taddress=" + taddress
				+ ", list=" + list + ", employee=" + employee + "]";
	}
}
