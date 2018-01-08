package com.zch.manager.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zch.manager.bean.Attence;
import com.zch.manager.bean.Dept;
import com.zch.manager.bean.Employee;
import com.zch.manager.bean.Repe;
import com.zch.manager.bean.SalReview;
import com.zch.manager.bean.Salary;
import com.zch.manager.bean.Train;
import com.zch.manager.dao.EmployeeDao;
import com.zch.manager.service.EmployeeService;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年10月6日 下午3:04:14
 */
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public boolean validateEmplLogin(Employee employee) {
		Employee empl = employeeDao.validateEmplLogin(employee);
		if (empl != null) {
			return true;
		}
		return false;
	}

	public PageBean<Dept> getDeptMsgByPage(Integer page) {
		PageBean<Dept> pb = new PageBean<Dept>();
		// 设置当前页
		pb.setPage(page);
		// 设置总记录数
		int totalCount = employeeDao.getTotalCountDeptMsg();
		pb.setTotalCount(totalCount);
		// 设置每页显示条数
		int pagesize = 2;
		pb.setPagesize(pagesize);
		// 设置总页数
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		// 设置每页显示的数据集合
		int begin = (page - 1) * pagesize;
		List<Dept> list = employeeDao.getDeptMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public List<Employee> findAllEmployeeMsgByJobId(Integer jobId) {

		return employeeDao.findAllEmployeeMsgByJobId(jobId);
	}

	public Employee getEmployeeMsgById(Integer eid) {
		return employeeDao.getEmployeeMsgById(eid);
	}

	public Train getTrainMsg(int tid) {
		return employeeDao.getTrainMsg(tid);
	}

	public Employee getEmployeeMsgByName(String ename) {
		// 根据员工名字匹配到员工id
		return employeeDao.getEmployeeMsgByName(ename);
	}

	public Integer getEmployeeEtidByid(Integer eid) {
		return employeeDao.getEmployeeEtidByid(eid);
	}

	public PageBean<Repe> getRepeMsgBypage(Integer page, Integer eid) {
		PageBean<Repe> pb = new PageBean<Repe>();
		// 设置当前页
		pb.setPage(page);
		// 设置总记录数
		int totalCount = employeeDao.getTotalCountRepeMsg(eid);
		pb.setTotalCount(totalCount);
		// 设置每页显示条数
		int pagesize = 3;
		pb.setPagesize(pagesize);
		// 设置总页数
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		// 设置每页显示的数据集合
		int begin = (page - 1) * pagesize;
		List<Repe> list = employeeDao.getRepeMsgList(begin, pagesize, eid);
		pb.setList(list);
		return pb;
	}

	// 保存员工的打卡记录到数据库中,在这里对时间格式的处理以及后续的一些操作
	public void saveAttenceMsg(Date date, int eid, String ename) {
		int monthodfdays = 0;
		String comelatestatus = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		System.out.println("---" + year + "-" + month + "-" + day + "-" + hour + ":" + min);
		// 规定好朝九晚六上班时间,每月上24天班
		// 迟到或早退三小时算旷工一天,生成一条惩罚记录
		String morningTime = year + "-" + month + "-" + day + " 09:00";// 规定上班时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d1 = null;
		try {
			d1 = sdf.parse(morningTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long da = date.getTime() - d1.getTime(); // 迟到时间的毫秒数
		int latehour = (int) (da / (1000 * 60 * 60));// 迟到的小时数
		if (da > 0) {
			if (latehour >= 3) {
				comelatestatus = "旷工";
				// 生成一条关于旷工的惩罚记录,罚50块钱
				Repe repe = new Repe();
				repe.setPesal(50);
				String petime = sdf.format(date);
				repe.setPetime(petime);
				repe.setPereason(comelatestatus);
				System.out.println("---" + repe);
				employeeDao.saveRepeMsg(repe, eid, ename);
			} else {
				comelatestatus = "迟到";
				// 生成一天关于迟到的惩罚记录,罚10块钱
				Repe repe = new Repe();
				repe.setPesal(10);
				String petime = sdf.format(date);
				repe.setPetime(petime);
				repe.setPereason(comelatestatus);
				employeeDao.saveRepeMsg(repe, eid, ename);
			}
		} else {
			comelatestatus = "正常";
		}
		// 判断出勤月份对应的天数
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			monthodfdays = 31;
			break;
		default:
			monthodfdays = 30;
			break;
		}
		Attence attence = new Attence();
		attence.setOnworktime(date);
		attence.setComelatestatus(comelatestatus);
		attence.setYear(year);
		attence.setMonth(month);
		attence.setDay(day);
		attence.setMonthodfdays(monthodfdays);
		Employee employee = new Employee();
		employee.setEid(eid);
		employee.setEname(ename);
		attence.setEmployee(employee);
		// 判断当前日期是否已经打过卡(哪个员工的,不然会有错误的!)
		Attence existAttence = employeeDao.isExistAtttenceMsgByDay(eid, year, month, day);
		if (existAttence == null) {
			employeeDao.saveAttenceMsg(attence);
		}
	}

	// 更新员工的一天考勤的下班打卡记录到数据库中
	public void updateAttenceMsg(Date date, int eid, String ename) {
		// 通过eid得到employee的信息
		String leftearlystatus = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// 下班打卡判断早退信息
		String eveningTime = year + "-" + month + "-" + day + " 18:00";// 规定下班时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d2 = null;
		try {
			d2 = sdf.parse(eveningTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long da2 = d2.getTime() - date.getTime();
		int earlyDate = (int) (da2 / (1000 * 60 * 60));
		if (da2 > 0) {
			if (earlyDate >= 3) {
				leftearlystatus = "旷工";
				// 生成一条关于旷工的惩罚记录,罚50块钱
				Repe repe = new Repe();
				repe.setPesal(50);
				String petime = sdf.format(date);
				repe.setPetime(petime);
				repe.setPereason(leftearlystatus);
				employeeDao.saveRepeMsg(repe, eid, ename);
			} else {
				leftearlystatus = "早退";
				// 生成一天关于迟到的惩罚记录,罚10块钱
				Repe repe = new Repe();
				repe.setPesal(10);
				String petime = sdf.format(date);
				repe.setPetime(petime);
				repe.setPereason(leftearlystatus);
				employeeDao.saveRepeMsg(repe, eid, ename);
			}
		} else {
			leftearlystatus = "正常";
		}
		Attence attence = new Attence();
		attence.setLeftearlystatus(leftearlystatus);
		attence.setOffworktime(date);
		Employee employee = new Employee();
		employee.setEid(eid);
		employee.setEname(ename);
		attence.setEmployee(employee);
		employeeDao.updateAttenceMsg(attence);
	}

	public Salary getSalMsgDate(int eid, int year, int month) {
		return employeeDao.getSalMsgDate(eid, year, month);
	}

	public Salary getSalMsgById(Integer sid) {

		return employeeDao.getSalMsgById(sid);
	}

	public void saveSalReview(SalReview salReview) {
		employeeDao.saveSalReview(salReview);
	}

	public void updateEmployeeMsg(Employee employee) {
		employeeDao.updateEmployeeMsg(employee);
	}

}
