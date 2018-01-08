package com.zch.manager.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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

/**
*Created by ch.zhang on 2017年9月26日 下午1:04:10
*/
public interface AdminDao {

	List<Dept> getDeptMsg();
    //计算部门信息总记录数
	int getTotalCountDeptMsg();
	//得到每页显示部门数据的集合
	List<Dept> getDeptMsgList(@Param("begin") Integer begin,@Param("pagesize") Integer pagesize);
	//计算招聘信息总记录数
	int getTotalCountAdvertisetMsg();
	//得到每页显示招聘数据的集合
	List<Advertise> getDeptAdvertiseList(@Param("begin") Integer begin, @Param("pagesize") Integer pagesize);
	//管理员添加招聘信息是需要查找数据库显示部门信息和职位信息
	List<Dept> getAllDeptMsg();
	//二级联动操作
	List<Job> getAllJobMsgByDeptId(Integer deptId);
	//管理员保存发布的招聘信息进招聘表中
	int saveAdvertiseMsg(Advertise advertise);
	//通过部门名字找到部门对应的id和相关信息
	List<Dept> findDeptMsgByDeptName(String deptName);
	//根据id删除招聘信息
	int delAdvertiseById(Integer id);
	//根据id拿到招聘信息
	Advertise getAdvertiseMsgById(Integer id);
	//根据招聘表的部门名 查询数据库得到所有的部门集合信息
	List<Dept> getAllDeptMsgByName(String adeptName);
	//更新数据库中招聘信息
	int updateAdvertise(Advertise advertise);
     //统计简历总记录数	
	int getTotalCountResumetMsg();
	//得到每页的简历信息数据的集合
	List<Resume> getResumeMsgList(@Param("begin")int begin, @Param("pagesize")int pagesize);
	//更改面试状态为录取状态
	void updateResumeFaceStatusByRid(@Param("rid") int rid, @Param("rfaceStatus")String rfaceStatus);
	//保存部门名字到数据库中,包含创建时间(时间是转换的String格式的)
	int saveDeptName(@Param("encodeDeptName")String encodeDeptName,@Param("createTime")String createTime);
	//保存新增部门名前判断是否已经存在该部门信息
	Dept isExistDeptByDeptName(String encodeDeptName);
	Resume getResumeMsg(Integer rid);
	//动态取出简历信息并保存在员工表中
	void getResumeMsgToEmployee(@Param("name")String name, @Param("age")int age, @Param("sex")String sex, @Param("education")String education, @Param("tel")String tel, @Param("email")String email,
			@Param("polstatus")String polstatus, @Param("jobName")String jobName,@Param("rsalary")double rsalary,@Param("upwd")String upwd,@Param("estatus")String estatus,
			@Param("ej_id")int ej_id,@Param("ed_id")int ed_id);
	//取出录取用户的密码信息
	User getUserMsg(int getuid);
	//计算职位信息的总记录数
	int getTotalCountJobMsg();
	//得到职位信息分页显示的数据集合数
	List<Job> getJobMsgList(@Param("begin")int begin, @Param("pagesize")int pagesize);
	//添加职位信息
	void saveJobMsg(@Param("encodeJobname")String encodeJobname, @Param("id")Integer id,@Param("strDate")String strDate);
	//根据职位id查找对应所有的员工信息(员工姓名)
	List<Employee> findAllEmployeeMsgByJobId(Integer jobId);
	//根据部门id判断是否存在在职员工
	List<Employee> isExistEmployee(Integer id);
	//根据部门id进行部门删除
	void delDeptMsg(Integer id);
	//根据职位id进行删除
	void delJobById(Integer id);
	//根据职位id判断是否存在在职员工------sql语句不一样,不然会有小bug的
	List<Employee> isExistEmployeeForJob(Integer id);
	//根据职位id得到对应的职位名称
	Job getJobMsg(Integer id);
	//修改职位名称
	void updateJobName(@Param("encodeJobname")String encodeJobname, @Param("id")Integer id);
	//根据部门id  得到部门名字和部门id
	Dept getDeptMsg4Depename(Integer id);
	//修改部门名称信息
	void updateDeptName(@Param("encodeDeptname")String encodeDeptname,@Param("id")Integer id);
	//统计员工信息总记录数
	int getTotalCountEmployeeMsg();
	//得到每页显示数据的集合数据
	List<Employee> getEmployeeMsgList(@Param("begin")int begin, @Param("pagesize")int pagesize);
	//根据id得到员工基本信息
	Employee getEmployeeMsgById(Integer id);
	//根据id删除员工
	void delEmployeeMsgById(Integer id);
	//更新员工信息(换岗之后的)
	void updateEmployeeMsg(@Param("did")Integer did,@Param("encodeJname")String encodeJname,@Param("id")Integer id);
	//根据职位名字得到职位id
	Job getJobMsgByName(String jobName);
	//根据部门名得到部门id
	Dept getDeptMsgByName(String deptName);
	//统计培训信息总记录数
	int getTotalCountTrainMsg();
	//得到每页显示培训数据集合
	List<Train> getTrainMsgList(@Param("begin")int begin,@Param("pagesize")int pagesize);
	//得到所有员工姓名集合
	List<Employee> getAllEmployeeMsg();
	//保存培训信息
	void saveTrainMsg(@Param("ttime")String ttime,@Param("tcontent")String tcontent,@Param("address")String address,@Param("eid")Integer eid,@Param("ename")String ename,@Param("ebossId")Integer ebossId,@Param("ebossName")String ebossName);
	//根据eid得到培训表信息
	 Train getTrainMsgByEid(Integer eid);
	//修改员工表中外键et_id=t_id
	void updateEmployeeMsgByTrain(@Param("tid")Integer tid,@Param("eid")Integer eid);
	//给员工添加培训信息时,判断是否已经添加了
	Employee isExistMsg(Integer eid);
	void delTrainMsgById(Integer id);
	//查询培训信息做修改时进行回显
	Train getTrainMsg(Integer id);
	//更新培训表里面的信息
	void updateTrainMsg(@Param("eid")Integer eid, @Param("ename")String ename, @Param("ttime")String ttime, @Param("encodeTcontent")String encodeTcontent, @Param("encodeTaddress")String encodeTaddress,
			@Param("id")Integer id);
	//统计奖惩表的总记录数
	int getTotalCountRepeMsg();
	//得到每页显示的奖惩信息数据的集合
	List<Repe> getRepeMsgList(@Param("begin")int begin,@Param("pagesize")int pagesize);
	//保存奖励信息到奖惩表中
	void saveRepeMsg(Repe repe);
	//保存惩罚信息到奖惩表中去
	void savePeReMsg(Repe repe);
	//删除奖惩相关信息
	void delRepeMsg(Integer id);
	//根据id得到奖惩表的相关信息,做修改操作
	Repe getAllRepeMsgById(Integer id);
	//更新奖惩表信息
	void updateRepeMsg(Repe repe);
	//每页显示的员工相关的考勤信息的数据集合
	List<Employee> getEmplAttenceMsgList(@Param("begin")int begin,@Param("pagesize")int pagesize);
	//统计考勤表中的总记录数
	int getTotalCountAttenceMsg(@Param("year")int year, @Param("month")int month, @Param("day")int day);
	//每页显示的考勤数据集合
	List<Attence> getAttenceMsgList(@Param("year")int year, @Param("month")int month, @Param("day")int day, @Param("begin")int begin, @Param("pagesize")int pagesize);
	//根据部门名字得到部门信息
	Dept getDeptMsgByname(String deptName);
	//根据部门id得到员工集合信息
	List<Employee> getListEmployeeMsgById(int did);
	//开除员工,修改员工的状态
	void updateEmployeeStatusById(@Param("id")Integer id,@Param("emplStatus")String emplStatus);
	List<EmployeeCusttom> getAllEmployeeMsgCustom();
	List<Attence> getAttenceMsgByidAndDate(@Param("eid")Integer eid,@Param("year")int year,@Param("month")int month);
    //保存员工薪资相关信息到薪资表中	
	void saveSalMsgYearAndMonth(EmployeeCusttom employeeCusttom);
	//一键结算工资时判断当月员工薪资信息是否已经插入到了薪资表中,如果已经插入了,则无需重复插入
	List<Salary> isExistSalMsgofEmplDate(@Param("eid")int eid,@Param("year")int year,@Param("month")int month);
	//得到异议信息总记录数
	int getTotalCountSalReviewMsg();
	//得到每页显示的异议信息数据集合
	List<SalReview> getSalReviewMsgList(@Param("begin")int begin,@Param("pagesize")int pagesize);
	//根据异议id拿到异议表中薪资id
	SalReview getSalReviewMsgById(Integer id);
	//根据sid得到对应的薪资信息
	Salary getSalaryMsgBySid(int sid);
	//更改薪资复议消息状态
	void updateSalReviewMsg(@Param("srid")Integer srid,@Param("srstatus")String srstatus);
	//验证管理员姓名密码是否正确
	Admin validateAdminMsg(Admin admin);
}
