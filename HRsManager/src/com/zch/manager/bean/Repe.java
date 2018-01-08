package com.zch.manager.bean;

/**
 * Created by ch.zhang on 2017年10月8日 下午2:26:01 奖惩实体类,奖惩时间,金额,原因,对哪个员工进行操作的
 */
public class Repe {
	private Integer rpid; // 奖惩信息的id
	private double resal;// 奖励金额
	private String retime;// 奖励时间
	private String rereason;// 奖励原因
	private double pesal;// 惩罚金额
	private String petime;// 惩罚时间
	private String pereason;// 惩罚原因
	private Employee employee;// 对哪个员工进行奖惩操作

	public Integer getRpid() {
		return rpid;
	}

	public void setRpid(Integer rpid) {
		this.rpid = rpid;
	}

	public double getResal() {
		return resal;
	}

	public void setResal(double resal) {
		this.resal = resal;
	}

	public String getRetime() {
		return retime;
	}

	public void setRetime(String retime) {
		this.retime = retime;
	}

	public String getRereason() {
		return rereason;
	}

	public void setRereason(String rereason) {
		this.rereason = rereason;
	}

	public double getPesal() {
		return pesal;
	}

	public void setPesal(double pesal) {
		this.pesal = pesal;
	}

	public String getPetime() {
		return petime;
	}

	public void setPetime(String petime) {
		this.petime = petime;
	}

	public String getPereason() {
		return pereason;
	}

	public void setPereason(String pereason) {
		this.pereason = pereason;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Repe(double resal, String retime, String rereason, double pesal, String petime, String pereason) {
		super();
		this.resal = resal;
		this.retime = retime;
		this.rereason = rereason;
		this.pesal = pesal;
		this.petime = petime;
		this.pereason = pereason;
	}

	public Repe(double resal, String retime, String rereason, double pesal, String petime, String pereason,
			Employee employee) {
		super();
		this.resal = resal;
		this.retime = retime;
		this.rereason = rereason;
		this.pesal = pesal;
		this.petime = petime;
		this.pereason = pereason;
		this.employee = employee;
	}

	public Repe() {
		super();
	}

	@Override
	public String toString() {
		return "Repe [rpid=" + rpid + ", resal=" + resal + ", retime=" + retime + ", rereason=" + rereason + ", pesal="
				+ pesal + ", petime=" + petime + ", pereason=" + pereason + ", employee=" + employee + "]";
	}

}
