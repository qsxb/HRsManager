package com.zch.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zch.manager.bean.Attence;
import com.zch.manager.bean.Dept;
import com.zch.manager.bean.Employee;
import com.zch.manager.bean.Repe;
import com.zch.manager.bean.SalReview;
import com.zch.manager.bean.Salary;
import com.zch.manager.bean.Train;

/**
*Created by ch.zhang on 2017年10月6日 下午3:09:22
*/
public interface EmployeeDao {
    //验证员工登录(用户名,密码)
	Employee validateEmplLogin(Employee employee);
    //得到部门总记录数
	int getTotalCountDeptMsg();
	//得到每页显示的数据集合
	List<Dept> getDeptMsgList(@Param("begin")int begin, @Param("pagesize")int pagesize);
	//二级联动
	List<Employee> findAllEmployeeMsgByJobId(Integer jobId);
	//和管理员adminDao里面的操作一样,根据id获取员工信息(主要还是et_id)
	Employee getEmployeeMsgById(Integer eid);
	//根据培训表id得到培训相关消息
	Train getTrainMsg(int tid);
	//根据员工名匹配员工id
	Employee getEmployeeMsgByName(String ename);
	//根据员工id得到et_id外键,判断是否为空
	Integer getEmployeeEtidByid(Integer eid);
	//根据员工id统计其对应的奖惩记录信息
	int getTotalCountRepeMsg(Integer eid);
	//根据员工得到每页显示奖惩数据的集合
	List<Repe> getRepeMsgList(@Param("begin")int begin,@Param("pagesize")int pagesize,@Param("eid")Integer eid);
	//保存用户考勤信息记录
	void saveAttenceMsg(Attence attence);
	//更新用户的考勤信息记录(打下班卡的时间)
	void updateAttenceMsg(Attence attence);
	//判断员工当天是否打过卡,不然会重复保存到数据库中的
	Attence isExistAtttenceMsgByDay(@Param("eid")int eid,@Param("year")int year,@Param("month")int month,@Param("day")int day);
	//给哪个员工添加奖惩信息(这里都是旷工,早退,迟到的惩罚记录,自动生成的)
	void saveRepeMsg(@Param("repe")Repe repe,@Param("eid")int eid,@Param("ename")String ename);
	//得到当前员工当月的薪资信息
	Salary getSalMsgDate(@Param("eid")int eid, @Param("year")int year,@Param("month")int month);
	//根据sid(薪资表主键得到薪资表信息)
	Salary getSalMsgById(Integer sid);
	//保存异议信息到工资复议表中去
	void saveSalReview(SalReview salReview);
	//员工修改个人基本信息
	void updateEmployeeMsg(Employee employee);

}
