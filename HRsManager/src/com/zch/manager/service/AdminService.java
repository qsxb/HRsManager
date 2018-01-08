package com.zch.manager.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.zch.manager.bean.Admin;
import com.zch.manager.bean.Advertise;
import com.zch.manager.bean.Attence;
import com.zch.manager.bean.Dept;
import com.zch.manager.bean.Employee;
import com.zch.manager.bean.EmployeeCusttom;
import com.zch.manager.bean.Job;
import com.zch.manager.bean.Repe;
import com.zch.manager.bean.Resume;
import com.zch.manager.bean.SalReview;
import com.zch.manager.bean.Salary;
import com.zch.manager.bean.Train;
import com.zch.manager.bean.User;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年9月26日 下午1:00:01
 */
public interface AdminService {
	// 第一个操作.把数据库中的部门与职位信息加载出来,只需要得到部门的信息即可(部门里有职位信息)
	public List<Dept> getDeptMsg();

	// 第一个操作.把数据库中的部门与职位信息加载出来(带有分页),只需要得到部门的信息即可(部门里有职位信息)
	public PageBean<Dept> getDeptMsgByPage(int page);

	public PageBean<Advertise> getAdvertiseMsgByPage(Integer page);

	// 管理员添加招聘信息时查找数据库中的部门信息,带着对应的职位信息在界面供选择
	public List<Dept> getAllDeptMsg();

	// 二级联动是通过部门id查找职位信息
	public List<Job> findAllJobMsgByDeptId(Integer deptId);

	// 把管理员发布的信息保存到招聘表中
	public boolean saveAdvertiseMsg(Advertise advertise);

	// 通过部门名字找到对应的部门id(主要是它) 和部门的相关信息
	public List<Dept> findAllJobMsgByDeptName(String deptName);

	// 根据id进行删除招聘信息
	public boolean delAdvertiseById(Integer id);

	// 根据id拿到招聘信息(好多好多的字段,回显修改招聘信息那一块)
	public Advertise getAdvertiseMsgById(Integer id);

	// 更新管理员修改的招聘信息
	public boolean updateAdvertise(Advertise advertise);

	// 分页显示投递者的简历信息
	public PageBean<Resume> getAllResumeMsgByPage(Integer page);

	// 用户在邮箱确认了面试,管理员把简历信息的面试状态改为录取状态
	public void updateResumeFaceStatusByRid(int rid);

	// 保存部门名字到数据库中
	public boolean saveDeptName(String encodeDeptName);

	// 新增部门时判断部门名在数据库中是否已经存在
	public boolean isExistDeptByDeptName(String encodeDeptName);

	// 管理员删除部门操作
	public void delDeptMsg(Integer id);

	// 通过简历id取到简历的相关信息
	public Resume getResumeMsg(Integer rid);

	// 录取成功后动态取出简历中的信息并保存到员工表中
	public void saveResumeMsgToEmployee(Resume resume, String upwd);

	// 根据简历中得到用户id再得到用户密码
	public User getUserMsg(int getuid);

	// 分页显示职位的相关信息
	public PageBean<Job> getJobMsgByPage(Integer page);

	// 添加职位操作,id为对应的部门id,加在哪个部门上的职位
	public void saveJobMsg(String encodeJobname, Integer id);

	// 根据职位id查找对应的所有员工信息
	public List<Employee> findAllEmployeeMsgByJobId(Integer jobId);

	// 根据部门id判断时候存在在职员工
	public boolean isExistEmployeeDeptJobEmployee(Integer id);

	// 根据职位id判断是否该职位存在员工
	public boolean isExistEmployee(Integer id);

	// 根据职位id来删除职位信息
	public void delJobById(Integer id);

	// 根据职位id得到职位名称和职位id
	public Job getJobMsg(Integer id);

	// 修改职位名字
	public void updateJobName(String encodeJobname, Integer id);

	// 根据部门id得到部门id和部门名称
	public Dept getDeptMsg4Depename(Integer id);

	// 修改部门名称信息
	public void updateDeptName(String encodeDeptname, Integer id);

	// 分页拿到员工信息
	public PageBean<Employee> getEmployeeMsgByPage(Integer page);

	// 根据员工id得到员工基本信息
	public Employee getEmployeeMsgById(Integer id);

	// 根据id删除员工
	public void delEmployeeMsgById(Integer id);

	// 更新换岗后的员工表信息
	public void updateEmployeeMsg(Integer did, String encodeJname, Integer id);

	// 分页拿到培训信息
	public PageBean<Train> getTrainMsgByPage(Integer page);

	// 得到所有的员工姓名集合
	public List<Employee> getAllEmployeeMsg();

	// 保存培训信息
	public void saveTrainMsg(Train train, Integer eid, String ename, Integer ebossId, String ebossName);

	// 根据e_id拿到培训表信息
	public Train getTrainMsgByEid(Integer eid);

	// 修改员工表中外键et_id=t_id 培训表
	public void updateEmployeeMsgByTrain(Integer tid, Integer eid);

	// 根据员工id判断et_id是否为空,
	public boolean isExistMsg(Integer eid);

	// 删除培训信息通过id
	public void delTrainMsgById(Integer id);

	// 查询培训信息
	public Train getTrainMsg(Integer id);

	// 更新培训表里面的信息
	public void updateTrainMsg(Integer eid, String ename, String ttime, String encodeTcontent, String encodeTaddress,
			Integer id);

	// 分页查询奖惩表的信息
	public PageBean<Repe> getRepeMsgByPage(Integer page);

	public void saveRepeMsg(Repe repe) throws UnsupportedEncodingException;

	public void savePeReMsg(Repe repe) throws UnsupportedEncodingException;

	// 管理员删除奖惩表的信息
	public void delRepeMsg(Integer id);

	// 根据奖惩表的id得到信息
	public Repe getAllRepeMsgById(Integer id);

	// 修改奖惩表的信息
	public void updateRepeMsg(Repe repe);

	// 根据年月日来查询员工打卡(考勤的信息)
	public PageBean<Attence> getAttenceBydateAndPage(Date d, Integer page);

	// 获取员工总人数
	public int getEmployeeCount();

	// 根据年月日判断打了卡的员工数
	public int getEmployeeAttenceCount(Date date);

	// 根据部门名字得到部门信息
	public Dept getDeptMsgByname(String deptName);

	// 根据部门id得到员工的集合信息
	public List<Employee> getListEmployeeMsgById(int did);

	// 根据员工id更改员工的状态
	public void updateEmployeeStatusById(Integer id);

	// 得到所有的员工集合,一个扩展类
	public List<EmployeeCusttom> getAllEmployeeMsgCustom();

	// 根据员工id 年,月得到考勤信息
	public List<Attence> getAttenceMsgByidAndDate(Integer eid, int year, int month);

	// 保存员工每个月的薪资记录
	public void saveSalMsgYearAndMonth(EmployeeCusttom employeeCusttom);

	// 管理员分页查看员工异议的记录
	public PageBean<SalReview> getSalReviewMsgByPage(Integer page);

	// 根据工资异议iD找到异议信息(对应的薪资表id(里面对应的内容))
	public SalReview getSalReviewMsgById(Integer id);

	// 根据薪资表id得到对应的薪资信息
	public Salary getSalaryMsgBySid(int sid);

	// 根据srid更改状态为已操作
	public void updateSalReviewMsg(Integer srid);

	// 验证管理员登录的姓名和密码
	public boolean validateAdminMsg(Admin admin);
}
