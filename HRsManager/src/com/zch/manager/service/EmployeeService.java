package com.zch.manager.service;

import java.util.Date;
import java.util.List;

import com.zch.manager.bean.Dept;
import com.zch.manager.bean.Employee;
import com.zch.manager.bean.Repe;
import com.zch.manager.bean.SalReview;
import com.zch.manager.bean.Salary;
import com.zch.manager.bean.Train;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年10月6日 下午3:03:55
 */
public interface EmployeeService {

	boolean validateEmplLogin(Employee employee);

	PageBean<Dept> getDeptMsgByPage(Integer page);

	List<Employee> findAllEmployeeMsgByJobId(Integer jobId);

	Employee getEmployeeMsgById(Integer eid);

	Train getTrainMsg(int tid);

	Employee getEmployeeMsgByName(String ename);

	Integer getEmployeeEtidByid(Integer eid);

	// 员工分页得到关于自己的奖惩信息
	PageBean<Repe> getRepeMsgBypage(Integer page, Integer eid);

	// 保存员工的打卡记录到数据库中
	void saveAttenceMsg(Date date, int eid, String ename);

	// 更新员工的打卡记录到数据库中(主要是下班打卡的一些操作)
	void updateAttenceMsg(Date date, int eid, String ename);

	// 员工查看自己当月的信息,这里点击显示的是当前计算机对应的月份的薪资记录
	Salary getSalMsgDate(int eid, int year, int month);

	// 根据sid得到薪资对象
	Salary getSalMsgById(Integer sid);

	// 保存异议信息到工资异议表中
	void saveSalReview(SalReview salReview);

	// 员工修改个人基本信息
	void updateEmployeeMsg(Employee employee);

}
