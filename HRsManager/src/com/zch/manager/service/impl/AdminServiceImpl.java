package com.zch.manager.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.zch.manager.dao.AdminDao;
import com.zch.manager.dao.UserDao;
import com.zch.manager.service.AdminService;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年9月26日 下午1:00:19
 */
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	public List<Dept> getDeptMsg() {

		return adminDao.getDeptMsg();
	}

	public PageBean<Dept> getDeptMsgByPage(int page) {
		PageBean<Dept> pb = new PageBean<Dept>();
		// 设置当前页
		pb.setPage(page);
		// 设置总记录数
		int totalCount = adminDao.getTotalCountDeptMsg();
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
		List<Dept> list = adminDao.getDeptMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public PageBean<Advertise> getAdvertiseMsgByPage(Integer page) {
		PageBean<Advertise> pb = new PageBean<Advertise>();
		pb.setPage(page);
		int pagesize = 3;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountAdvertisetMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Advertise> list = adminDao.getDeptAdvertiseList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public List<Dept> getAllDeptMsg() {

		return adminDao.getAllDeptMsg();
	}

	public List<Job> findAllJobMsgByDeptId(Integer deptId) {
		return adminDao.getAllJobMsgByDeptId(deptId);
	}

	public boolean saveAdvertiseMsg(Advertise advertise) {
		int result = adminDao.saveAdvertiseMsg(advertise);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public List<Dept> findAllJobMsgByDeptName(String deptName) {
		return adminDao.findDeptMsgByDeptName(deptName);
	}

	public boolean delAdvertiseById(Integer id) {
		int result = adminDao.delAdvertiseById(id);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public Advertise getAdvertiseMsgById(Integer id) {
		return adminDao.getAdvertiseMsgById(id);
	}

	public boolean updateAdvertise(Advertise advertise) {
		int result = adminDao.updateAdvertise(advertise);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public PageBean<Resume> getAllResumeMsgByPage(Integer page) {
		PageBean<Resume> pb = new PageBean<Resume>();
		pb.setPage(page);
		int pagesize = 3;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountResumetMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Resume> list = adminDao.getResumeMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public void updateResumeFaceStatusByRid(int rid) {
		String rfaceStatus = "录取";
		adminDao.updateResumeFaceStatusByRid(rid, rfaceStatus);
	}

	public boolean saveDeptName(String encodeDeptName) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createTime = sdf.format(date);
		int result = adminDao.saveDeptName(encodeDeptName, createTime);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public boolean isExistDeptByDeptName(String encodeDeptName) {
		Dept dept = adminDao.isExistDeptByDeptName(encodeDeptName);
		if (dept == null) {
			return true;
		}
		return false;
	}

	public void delDeptMsg(Integer id) {
		// 根据部门id满足需求的进行删除
		adminDao.delDeptMsg(id);
	}

	public Resume getResumeMsg(Integer rid) {
		return adminDao.getResumeMsg(rid);
	}

	public void saveResumeMsgToEmployee(Resume resume, String upwd) {
		System.out.println("resumt----" + resume);
		String name = resume.getRname();
		int age = resume.getRage();
		String sex = resume.getRsex();
		String education = resume.getReducation();
		String tel = resume.getRtel();
		String email = resume.getRemail();
		String polstatus = resume.getRpolstatus();
		String jobName = resume.getJobName();
		System.out.println("jobName" + jobName);
		// 根据职位名得到职位id,后面写到的时候才发现的漏洞
		Job job = adminDao.getJobMsgByName(jobName);
		int ej_id = job.getJid();
		double rsalary = resume.getRsalary();
		String deptName = resume.getDeptName();
		System.out.println("deptName--" + deptName);
		// 根据部门名得到职位id,后面写到的时候才发现的漏洞
		Dept dept = adminDao.getDeptMsgByName(deptName);
		System.out.println("dept---" + dept);
		int ed_id = dept.getDid();
		String estatus = "实习";
		adminDao.getResumeMsgToEmployee(name, age, sex, education, tel, email, polstatus, jobName, rsalary, upwd,
				estatus, ej_id, ed_id);
	}

	public User getUserMsg(int getuid) {
		return adminDao.getUserMsg(getuid);
	}

	public PageBean<Job> getJobMsgByPage(Integer page) {
		PageBean<Job> pb = new PageBean<Job>();
		pb.setPage(page);
		int pagesize = 5;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountJobMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Job> list = adminDao.getJobMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public void saveJobMsg(String encodeJobname, Integer id) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = sdf.format(date);
		adminDao.saveJobMsg(encodeJobname, id, strDate);
	}

	public List<Employee> findAllEmployeeMsgByJobId(Integer jobId) {
		return adminDao.findAllEmployeeMsgByJobId(jobId);
	}

	// 这里的参数id是部门id
	public boolean isExistEmployeeDeptJobEmployee(Integer id) {
		List<Employee> employeeList = adminDao.isExistEmployee(id);
		if (employeeList.size() != 0) {
			// 存在,不能进行删除
			return true;
		}
		return false;
	}

	public boolean isExistEmployee(Integer id) {
		List<Employee> employeeList = adminDao.isExistEmployeeForJob(id);
		if (employeeList.size() != 0) {
			return true;
		}
		return false;
	}

	public void delJobById(Integer id) {
		adminDao.delJobById(id);
	}

	public Job getJobMsg(Integer id) {
		return adminDao.getJobMsg(id);
	}

	public void updateJobName(String encodeJobname, Integer id) {
		adminDao.updateJobName(encodeJobname, id);
	}

	public Dept getDeptMsg4Depename(Integer id) {
		return adminDao.getDeptMsg4Depename(id);
	}

	public void updateDeptName(String encodeDeptname, Integer id) {
		adminDao.updateDeptName(encodeDeptname, id);
	}

	public PageBean<Employee> getEmployeeMsgByPage(Integer page) {
		PageBean<Employee> pb = new PageBean<Employee>();
		pb.setPage(page);
		int pagesize = 5;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountEmployeeMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Employee> list = adminDao.getEmployeeMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public Employee getEmployeeMsgById(Integer id) {
		return adminDao.getEmployeeMsgById(id);
	}

	public void delEmployeeMsgById(Integer id) {
		adminDao.delEmployeeMsgById(id);
	}

	public void updateEmployeeMsg(Integer did, String encodeJname, Integer id) {
		adminDao.updateEmployeeMsg(did, encodeJname, id);
	}

	public PageBean<Train> getTrainMsgByPage(Integer page) {
		PageBean<Train> pb = new PageBean<Train>();
		pb.setPage(page);
		int pagesize = 2;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountTrainMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Train> list = adminDao.getTrainMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public List<Employee> getAllEmployeeMsg() {
		return adminDao.getAllEmployeeMsg();
	}

	public void saveTrainMsg(Train train, Integer eid, String ename, Integer ebossId, String ebossName) {
		// java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String ttime = train.getTtime();
		String tcontent = train.getTcontent();
		String address = train.getTaddress();
		adminDao.saveTrainMsg(ttime, tcontent, address, eid, ename, ebossId, ebossName);
	}

	public Train getTrainMsgByEid(Integer eid) {

		return adminDao.getTrainMsgByEid(eid);
	}

	public void updateEmployeeMsgByTrain(Integer tid, Integer eid) {
		adminDao.updateEmployeeMsgByTrain(tid, eid);
	}

	public boolean isExistMsg(Integer eid) {
		Employee employee = adminDao.isExistMsg(eid);
		System.out.println(employee);
		if (employee == null) {
			return true;
		}
		return false;
	}

	public void delTrainMsgById(Integer id) {
		adminDao.delTrainMsgById(id);
	}

	public Train getTrainMsg(Integer id) {
		return adminDao.getTrainMsg(id);
	}

	public void updateTrainMsg(Integer eid, String ename, String ttime, String encodeTcontent, String encodeTaddress,
			Integer id) {
		adminDao.updateTrainMsg(eid, ename, ttime, encodeTcontent, encodeTaddress, id);
	}

	public PageBean<Repe> getRepeMsgByPage(Integer page) {
		PageBean<Repe> pb = new PageBean<Repe>();
		pb.setPage(page);
		int pagesize = 3;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountRepeMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Repe> list = adminDao.getRepeMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public void saveRepeMsg(Repe repe) throws UnsupportedEncodingException {
		// 转码方式
		String encodeRereason = new String(repe.getRereason().getBytes("ISO-8859-1"), "utf-8");
		repe.setRereason(encodeRereason);
		adminDao.saveRepeMsg(repe);
	}

	public void savePeReMsg(Repe repe) throws UnsupportedEncodingException {
		// 转码方式
		String encodePereason = new String(repe.getPereason().getBytes("ISO-8859-1"), "utf-8");
		repe.setPereason(encodePereason);
		adminDao.savePeReMsg(repe);
	}

	public void delRepeMsg(Integer id) {
		adminDao.delRepeMsg(id);
	}

	public Repe getAllRepeMsgById(Integer id) {
		return adminDao.getAllRepeMsgById(id);
	}

	public void updateRepeMsg(Repe repe) {
		adminDao.updateRepeMsg(repe);
	}

	public PageBean<Attence> getAttenceBydateAndPage(Date d, Integer page) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(year + "-" + month + "-" + day);
		PageBean<Attence> pb = new PageBean<Attence>();
		pb.setPage(page);
		int pagesize = 3;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountAttenceMsg(year, month, day);
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<Attence> list = adminDao.getAttenceMsgList(year, month, day, begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public int getEmployeeCount() {
		return adminDao.getTotalCountEmployeeMsg();
	}

	public int getEmployeeAttenceCount(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return adminDao.getTotalCountAttenceMsg(year, month, day);
	}

	public Dept getDeptMsgByname(String deptName) {
		return adminDao.getDeptMsgByname(deptName);
	}

	public List<Employee> getListEmployeeMsgById(int did) {

		return adminDao.getListEmployeeMsgById(did);
	}

	public void updateEmployeeStatusById(Integer id) {
		String emplStatus = "离职";
		adminDao.updateEmployeeStatusById(id, emplStatus);
	}

	public List<EmployeeCusttom> getAllEmployeeMsgCustom() {
		return adminDao.getAllEmployeeMsgCustom();
	}

	public List<Attence> getAttenceMsgByidAndDate(Integer eid, int year, int month) {
		return adminDao.getAttenceMsgByidAndDate(eid, year, month);
	}

	public void saveSalMsgYearAndMonth(EmployeeCusttom employeeCusttom) {
		int eid = employeeCusttom.getEid();
		int year = employeeCusttom.getYear();
		int month = employeeCusttom.getMonth();
		// 插入当月薪资记录前判断是不是已经插入了,若有则不再进行插入,不然的话数据真的是太多了
		List<Salary> salaryList = adminDao.isExistSalMsgofEmplDate(eid, year, month);
		if (salaryList.size() == 0) {
			adminDao.saveSalMsgYearAndMonth(employeeCusttom);
		}
	}

	public PageBean<SalReview> getSalReviewMsgByPage(Integer page) {
		PageBean<SalReview> pb = new PageBean<SalReview>();
		pb.setPage(page);
		int pagesize = 5;
		pb.setPagesize(pagesize);
		int totalCount = adminDao.getTotalCountSalReviewMsg();
		pb.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? totalCount / pagesize : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		int begin = (page - 1) * pagesize;
		List<SalReview> list = adminDao.getSalReviewMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}

	public SalReview getSalReviewMsgById(Integer id) {
		return adminDao.getSalReviewMsgById(id);
	}

	public Salary getSalaryMsgBySid(int sid) {
		return adminDao.getSalaryMsgBySid(sid);
	}

	public void updateSalReviewMsg(Integer srid) {
		String srstatus = "已操作";
		adminDao.updateSalReviewMsg(srid, srstatus);
	}

	public boolean validateAdminMsg(Admin admin) {
		Admin admin1 = adminDao.validateAdminMsg(admin);
		if (admin1 != null) {
			return true;
		}
		return false;
	}
}
