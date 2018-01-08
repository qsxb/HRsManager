package com.zch.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.zch.manager.service.AdminService;
import com.zch.manager.service.UserService;
import com.zch.manager.util.MailUitls;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年9月26日 上午10:45:22
 */
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	// 程序加载frameset top界面,因为配置了视图渲染器,加上jsp文件放在web-inf下面
	// 必须经过controller跳转
	// 加载frame top
	@RequestMapping("/loadTop")
	public String loadTop() {

		return "admin/top";
	}

	// 加载frame left
	@RequestMapping("/loadLeft")
	public String loadLeft() {
		return "admin/left";
	}

	// 加载frame welcome
	@RequestMapping("/loadWelcome")
	public String loadWelcome() {
		return "admin/welcome";
	}

	// 加载 部门文件夹下的list.jsp,把数据库中的部门与职位信息加载出来
	@RequestMapping("/loadDeptList")
	public String loadDeptList(Map<String, Object> map, Integer page) {
		// List<Dept> list = adminService.getDeptMsg();
		if (page == null) {
			page = 1;
		}
		PageBean<Dept> pb = adminService.getDeptMsgByPage(page);
		System.out.println(pb);
		List<Dept> list = pb.getList();
		System.out.println("list信息为--" + list);
		for (Dept dept : list) {
			System.out.println(dept.getJob());
		}
		map.put("key_deptMsg_list", list);
		map.put("key_deptMsg_pb", pb);
		return "admin/dept/list";
	}

	// 加载 招聘文件夹下的list.jsp,这里要把数据库中存在的招聘信息加载出来
	@RequestMapping("/loadAdvertiseList")
	public String loadAdvertiseList(Map<String, Object> map, Integer page) {
		if (page == null) {
			page = 1;
		}
		PageBean<Advertise> pb = adminService.getAdvertiseMsgByPage(page);
		List<Advertise> list = pb.getList();
		System.out.println("招聘信息---" + list);
		map.put("key_advertiseMsg_list", list);
		map.put("key_advertiseMsg_pb", pb);
		return "admin/advertise/list";
	}

	// 管理员删除部门操作,需做到如果该部门存在在职员工的话则不能进行删除
	@RequestMapping("/delDept")
	public String afterdelShowDeptList(Integer id, Map<String, Object> map) throws IOException {
		// 根据部门id三表联合查找到,if该部门中不存在了在职员工则可以进行删除
		boolean flag = adminService.isExistEmployeeDeptJobEmployee(id);
		if (flag == false) {
			adminService.delDeptMsg(id);
			map.put("key_msg", "<script language='javascript'>alert('删除成功!')</script>");
			return "forward:loadDeptList";
		}
		map.put("key_msg", "<script language='javascript'>alert('还有在职员工,无法删除!')</script>");
		return "forward:loadDeptList";
	}

	// main.jsp上点击管理员登录跳转
	@RequestMapping("/adminLogin")
	public String adminLogin() {
		return "admin/AdminLogin";
	}

	// 验证管理员姓名密码是否正确,分别跳转的页面
	@RequestMapping("/validateAdminEnter")
	public String validateAdminEnter(HttpServletRequest req, Map<String, Object> map,
			@RequestParam(name = "username") String username, @RequestParam(name = "password") String pwd)
					throws UnsupportedEncodingException {
		String encodeUsername = new String(username.getBytes("ISo-8859-1"), "utf-8");
		Admin admin = new Admin(encodeUsername, pwd);
		boolean flag = adminService.validateAdminMsg(admin);
		if (flag == true) {
			// 验证成功,跳转显示界面
			// map.put("key_adminMsg", admin);
			req.getSession().setAttribute("session_adminname", admin.getAname());
			return "admin/index";
		}
		if (flag == false) {
			// 验证失败,重新登录,跳转相应的页面
			map.put("key_msg", "<script>alert('用户名或密码错误!请重新登入~')</script>");
			map.put("key_adminname_msg", admin.getAname());
			map.put("key_adminpwd_msg", admin.getApwd());
			return "admin/AdminLogin";
		}
		return null;
	}

	// 管理员点击增加招聘信息跳转的jsp页面
	@RequestMapping("/adminAddAdvertise")
	public String adminAddAdvertise(Map<String, Object> map) {
		// 这里把从数据库中已有的部门和其对应的所有职位可供选择 eg:财务部下有三个职位可发布出去
		// 部门和职位是一对多的关系,得到了部门,部门实体类有List<job> job属性
		List<Dept> list = adminService.getAllDeptMsg();
		for (Dept dept2 : list) {
			System.out.println("查出的数据库中所有部门信息--" + dept2);
		}
		map.put("key_dept_list", list);
		return "admin/advertise/add";
	}

	// 管理员添加招聘信息的二级联动操作
	@RequestMapping("/findJob")
	public String findJob(@RequestParam(name = "deptName") String deptName, HttpServletResponse resp,
			Map<String, Object> map) throws IOException {
		// -------->根据部门id进行查找职位相对应的信息
		// 这是之前的做法,发现添加发布信息的时候再次显示的是id而不是那么,那么我通过名字查找到id,然后再进行操作
		resp.setContentType("text/html;charset=utf-8");
		// 通过部门名字找到部门id,虽然多此一举,但还是有必要的,因为后期自己的操作可能会有多个同名字的部门而又没有
		// 控制,然后我只取get(0)的一个.这样在我的测试阶段至少不会报错
		List<Dept> list4deptName = adminService.findAllJobMsgByDeptName(deptName);
		int deptId = list4deptName.get(0).getDid();
		System.out.println("deptId---" + deptId);
		List<Job> list = adminService.findAllJobMsgByDeptId(deptId);
		JSONObject json = new JSONObject();
		JSON jj = (JSON) json.toJSON(list);
		System.out.println(jj.toJSONString());
		PrintWriter out = resp.getWriter();
		out.println(jj.toJSONString());
		return null;
	}

	// 管理员完成添加招聘信息并保存到招聘信息表中去
	@RequestMapping("/saveAdvertiseMsg")
	public String saveAdvertiseMsg(@RequestParam(name = "deptName") String deptName,
			@RequestParam(name = "jobName") String jobName, @RequestParam(name = "salary") double salary,
			@RequestParam(name = "number") Integer number) throws UnsupportedEncodingException {
		String encodeDeptName = new String(deptName.getBytes("ISO-8859-1"), "utf-8");
		String encodeJobName = new String(jobName.getBytes("ISO-8859-1"), "utf-8");
		Advertise advertise = new Advertise(encodeDeptName, encodeJobName, salary, number);
		boolean flag = adminService.saveAdvertiseMsg(advertise);
		if (flag == true) {
			// 发布招聘信息成功
			return "admin/advertise/saveAdvertiseSuccess";
		}
		return null;
	}

	// 管理员对招聘信息的删除 操作
	@RequestMapping("/delAdvertiseMsg")
	public String delAdvertiseMsg(@RequestParam(name = "id") Integer id) {
		// 根据管理员点击的招聘信息的id进行删除操作
		boolean flag = adminService.delAdvertiseById(id);
		if (flag == true) {
			// 删除成功
			return "admin/advertise/delAdvertiseSuccess";
		}
		return null;
	}

	// 管理员对招聘信息跳到修改界面,并带有信息的回显
	@RequestMapping("/modifyAdvertiseMsg")
	public String modifyAdvertiseMsg(Map<String, Object> map, @RequestParam(name = "id") Integer id) {
		// 根据id查找数据库进行修改招聘数据信息的回显
		Advertise advertise = adminService.getAdvertiseMsgById(id);
		// 做一个级联操作,还要把部门表中所有的信息带过去,
		List<Dept> list = adminService.getAllDeptMsg();
		System.out.println("list----" + list + "-------" + list.size());
		map.put("key_deptMsg", list);
		map.put("key_advertise", advertise);
		return "admin/advertise/advertiseMsgEdit";
	}

	@RequestMapping("/updateFindJob")
	public String updateFindJob(@RequestParam(name = "deptName") String deptName, HttpServletResponse resp,
			Map<String, Object> map) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		List<Dept> list4deptName = adminService.findAllJobMsgByDeptName(deptName);
		int deptId = list4deptName.get(0).getDid();
		System.out.println("deptId---" + deptId);
		List<Job> list = adminService.findAllJobMsgByDeptId(deptId);
		JSONObject json = new JSONObject();
		JSON jj = (JSON) json.toJSON(list);
		System.out.println(jj.toJSONString());
		PrintWriter out = resp.getWriter();
		out.println(jj.toJSONString());
		return null;
	}

	// 管理员确定好修改的招聘信息,并更新数据库中招聘表的信息
	@RequestMapping("/updateAdvertise")
	public String updateAdvertise(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "selectDeptName") String deptName,
			@RequestParam(name = "selectJobName") String jobName, @RequestParam(name = "selectSalary") double salary,
			@RequestParam(name = "number") int number) throws UnsupportedEncodingException {
		String encodeDeptName = new String(deptName.getBytes("ISO-8859-1"), "utf-8");
		String encodeJobName = new String(jobName.getBytes("ISO-8859-1"), "utf-8");
		Advertise advertise = new Advertise(id, encodeDeptName, encodeJobName, salary, number);
		boolean flag = adminService.updateAdvertise(advertise);
		if (flag == true) {
			// 跳转一个修改成功界面显示一下
			return "admin/advertise/updateAdvertiseSuccess";
		}
		return null;
	}

	// 管理员查看投递的简历信息
	@RequestMapping("/loadResumeList")
	public String loadResumeList(Map<String, Object> map, Integer page) {
		// 通过查找简历表找到投递的简历信息,分页拿到数据;
		if (page == null) {
			page = 1;
		}
		PageBean<Resume> pb = adminService.getAllResumeMsgByPage(page);
		List<Resume> list = pb.getList();
		System.out.println("list----" + list);
		map.put("key_resumeMsg", list);
		map.put("key_resumeMsgPb", pb);
		return "admin/advertise/resumelist";
	}

	// 管理员点击读简历读取取详情,进入一个界面,设置面试时间,做标记,下次则是已读状态并且安排人面试
	@RequestMapping("/loadauditionDesc")
	public String loadauditionDesc(Integer id, Map<String, Object> map) {
		// 根据用户id,拿到该用户的简历信息,更改简历读取状态为已读
		List<Resume> resumelist = userService.getResumeMsg(id);
		Resume resume = resumelist.get(0);
		userService.updateUserResumeMsg2(resume);
		map.put("key_resume", resume);
		// 面试的哪个部门就让哪个部门的任何一个人去面试
		String deptName = resume.getDeptName(); // 得到部门名字
		System.out.println("deptName" + deptName);
		// 根据部门名字得到部门id
		Dept dept = adminService.getDeptMsgByname(deptName);
		System.out.println("dept" + dept);
		int did = dept.getDid();// 根据部门id获取该部门员工的信息供管理员选择去面试
		List<Employee> listEmpl = adminService.getListEmployeeMsgById(did);
		System.out.println("listEmpl---" + listEmpl);
		map.put("key_depe_msg", dept);
		map.put("key_listEmpl_msg", listEmpl);
		return "admin/advertise/descresumelist";
	}

	// 管理员给面试者发邮箱消息,还有通知面试者部门里的人去面试
	@RequestMapping("/sendMsgToemail")
	public String sendMsgToemail(@RequestParam(name = "whoGoFaceId") Integer eid, HttpServletRequest req, Integer id,
			@RequestParam(name = "faceTime") String faceTime) {
		System.out.println("id信息为--" + id);
		// 根据id给邮件发消息
		Resume resume = userService.getResumeMsgByRid(id);
		// 得到用户表的id
		int uid = resume.getUser().getUid();
		System.out.println("uid===" + uid);
		int rid = resume.getRid();
		// 根据eid通知该员工去面试 ,给该员工发送邮件信息通知其有面试消息
		// 根据eid得到enamel
		System.out.println("----eid--" + eid);
		Employee employee = adminService.getEmployeeMsgById(eid);
		String ename = employee.getEname();
		String eemail = employee.getEmail();
		// 参数为 员工邮箱 面试时间
		MailUitls.sendMailInfoEmpl(eemail, faceTime, eid, ename, resume);
		MailUitls.sendMailAboutFaceTime(resume.getRemail(), resume.getRname(), faceTime, uid, rid);
		return "admin/advertise/sendFaceMsg";
	}

	// 面试官点击邮件模拟面试
	@RequestMapping("/mockInterview")
	public String mockInterview(Integer eid) {
		// 根据员工id进入现实界面
		return "employee/msg/list";
	}

	// 用户点了前去面试的链接之后,管理员需要把该建立的面试状态改为已面试
	@RequestMapping("/userKnowFaceTime")
	public String userKnowFaceTime(HttpServletRequest req, Integer uid, Integer rid) {
		int statue = (Integer) req.getSession().getAttribute("session_admin");
		System.out.println("----管理员的statue" + statue);
		if (statue == 1) {
			System.out.println("进来了");
			System.out.println("uid--" + uid);
			// 判断面试官是否录用,如果录用才做以下信息
			// 根据uid修改用户表中的admint为1(已录取)
			userService.updateUserMsgByUid(uid);
			// 管理员根据简历表id修改faceStatus状态为录用
			System.out.println("rid==" + rid);
			adminService.updateResumeFaceStatusByRid(rid);
			// 管理员把该录用的简历相关信息加载到员工表里面
			// 通过rid取出录取的相应的简历信息
			Resume resume = adminService.getResumeMsg(rid);
			System.out.println(resume.getDeptName());
			System.out.println("resum---" + resume);
			int getuid = resume.getUser().getUid(); // 得到用户id,再得到密码
			System.out.println("getuid----" + getuid);
			User user = adminService.getUserMsg(getuid);
			adminService.saveResumeMsgToEmployee(resume, user.getUpwd());
			return "user/faceMsgSuccess";
		}
		return "user/faceMsgError";
	}

	// 管理员添加部门信息,要有创建新部门的时间
	@RequestMapping("/addDept")
	public String addDept() {
		return "admin/dept/add";
	}

	// 保存新增部门到数据库中,并且部门要有创建的时间,而且不能创建数据库中已有的部门名字
	@RequestMapping("/saveDeptName")
	public String saveDeptName(Map<String, Object> map, HttpServletResponse resp,
			@RequestParam(name = "deptname") String deptName) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String encodeDeptName = new String(deptName.getBytes("ISO-8859-1"), "utf-8");
		// 先遍历数据库看新增加的部门名是否存在
		boolean existDeptFlag = adminService.isExistDeptByDeptName(encodeDeptName);
		if (existDeptFlag == true) {
			boolean flag = adminService.saveDeptName(encodeDeptName);
			if (flag == true) {
				// 添加成功界面
				map.put("key_msg", "<script>alert('添加成功')</script>");
				return "forward:loadDeptList";
				// out.print("<script
				// language='javascript'>alert('添加成功');</script>");
			}
		}
		if (existDeptFlag == false) {
			map.put("key_msg", "<script>alert(已存在该部门,不能重复添加!')</script");
			return "forward:loadDeptList";
		}
		return null;
	}

	// 管理员查看职位信息,可查看该职位下的员工,创建职位要有创建时间,并标明是哪个部门下创建的,删除该职位是如果存在员工不能进行删除
	@RequestMapping("/loadJobList")
	public String loadJobList(Integer page, Map<String, Object> map) {
		// 从数据库中取出job表中的信息,员工表和职位表该是一个一对一的表结构关系,一个员工有一份工作
		if (page == null) {
			page = 1;
		}
		PageBean<Job> pb = adminService.getJobMsgByPage(page);
		List<Job> list = pb.getList();
		for (Job job : list) {
			System.out.println("job---" + job);
		}
		map.put("key_jobMsg_list", list);
		map.put("key_jobMsg_pb", pb);
		return "admin/job/list";
	}

	// 管理员对职位的一个添加操作,这里需要指明添加的时间,在那个部门下创建
	@RequestMapping("/addJob")
	public String addJob(Map<String, Object> map) {
		// 遍历部门表,把已经建立的部门名用map存在request雨中,在add.jsp中取出来
		List<Dept> deptList = adminService.getDeptMsg();
		for (Dept dept : deptList) {
			System.out.println("dept======" + dept.getDname());
		}
		map.put("key_deptListMsg", deptList);
		return "admin/job/add";
	}

	// 管理员对职位信息的添加操作(指定部门名字下添加)
	@RequestMapping("/saveJobName")
	public String saveJobName(Map<String, Object> map, @RequestParam(name = "jobname") String jobname,
			@RequestParam(name = "selectDeptName") Integer id) throws UnsupportedEncodingException {
		String encodeJobname = new String(jobname.getBytes("ISO-8859-1"), "utf-8");
		// 传入职位名称,和职位对应的部门id
		adminService.saveJobMsg(encodeJobname, id);
		map.put("key_msg", "添加职位成功!");
		return "forward:loadJobList";
	}

	// 管理员在部门显示中二级联动部门职位,职位员工.(部门-职位-一对多 职位--员工 一对多)
	@RequestMapping("/getEmployeeMsgByjobId")
	public String getEmployeeMsgByjobId(Integer jobId, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		// 根据职位id查找该职位下对应的所有的员工
		List<Employee> listEmployee = adminService.findAllEmployeeMsgByJobId(jobId);
		JSONObject json = new JSONObject();
		JSON jj = (JSON) json.toJSON(listEmployee);
		System.out.println(jj.toJSONString());
		out.println(jj.toJSONString());
		return null;
	}

	// 管理员对职位的删除操作,需要注意的是(该职位存在员工,则不能进行删除)
	@RequestMapping("/delJob")
	public String delJob(Integer id, Map<String, Object> map) {
		// 参数id为职位id,根据职位id判断该职位是否存在员工
		boolean flag = adminService.isExistEmployee(id);
		System.out.println(flag);
		if (flag == true) {
			map.put("key_msg", "<script>alert('该职位存在员工信息,不能进行删除!')</script>");
			return "forward:loadJobList";
		}
		if (flag == false) {
			// 不存在,则进行删除操作
			adminService.delJobById(id);
			map.put("key_msg", "<script>alert('删除成功!')</script>");
			return "forward:loadJobList";
		}
		return null;
	}

	// 管理员对职位的修改操作,参数id是职位相对应的id
	@RequestMapping("/modifyJob")
	public String modifyJob(Integer id, Map<String, Object> map) {
		// 修改需要id拿到相应的信息回显到页面上进行修改
		Job job = adminService.getJobMsg(id);
		map.put("key_job_msg", job);
		return "admin/job/edit";
	}

	// 管理员对职位名称的一个修改操作
	@RequestMapping("/updateJobName")
	public String updateJobName(Map<String, Object> map, @RequestParam(name = "jobname") String jobname, Integer id)
			throws UnsupportedEncodingException {
		String encodeJobname = new String(jobname.getBytes("ISO-8859-1"), "utf-8");
		adminService.updateJobName(encodeJobname, id);
		map.put("key_msg", "<script>alert('修改成功!')</script>");
		return "forward:loadJobList";
	}

	// 管理员对部门名进行修改,有时候公司的部门需要改名字,但这都是很少见的事情,但是我们还是得做
	// 参数id是部门id
	@RequestMapping("/modifyDept")
	public String modifyDept(Integer id, Map<String, Object> map) {
		// 修改需要id拿到相应的信息回显到页面上进行修改
		Dept dept = adminService.getDeptMsg4Depename(id);
		map.put("key_dept_msg", dept);
		return "admin/dept/edit";
	}

	// 管理员对部门名字的修改操作
	@RequestMapping("/updateDeptName")
	public String updateDeptName(@RequestParam(name = "deptname") String deptname, Integer id, Map<String, Object> map)
			throws UnsupportedEncodingException {
		String encodeDeptname = new String(deptname.getBytes("ISO-8859-1"), "utf-8");
		adminService.updateDeptName(encodeDeptname, id);
		map.put("key_msg", "<script>alert('修改成功!')</script>");
		return "forward:loadDeptList";
	}

	// 点击left显示员工信息
	@RequestMapping("/loadEmployeeList")
	public String loadEmployeeList(Map<String, Object> map, Integer page) {
		// 查询员工表,把数据保存在request域中
		if (page == null) {
			page = 1;
		}
		PageBean<Employee> pb = adminService.getEmployeeMsgByPage(page);
		List<Employee> list = pb.getList();
		map.put("key_employeeMsg_pb", pb);
		map.put("key_employeeMsg_list", list);
		return "admin/employee/list";
	}

	@RequestMapping("/kaichu")
	public String kaichu(Integer id) {
		// 根据员工id进行开除,把员工的状态改为离职
		adminService.updateEmployeeStatusById(id);
		return "forward:loadEmployeeList";
	}

	// 管理员删除员工操作(看员工的在职状态(离职,在职,试用期)),要有原因说明删除该员工,删除不是真的删除,把其状态改为离职
	@RequestMapping("/delEmployee")
	public String delEmployee(Integer id, Map<String, Object> map) throws IOException {
		// 根据员工id来做删除操作,先查找出员工的状态,
		Employee employee = adminService.getEmployeeMsgById(id);
		System.out.println(employee.getEstatus());
		if ("离职".equals(employee.getEstatus())) {
			map.put("key_msg", "<script>alert('删除成功!')</script>");
			adminService.delEmployeeMsgById(employee.getEid());
			return "forward:loadEmployeeList";
		} else {
			map.put("key_msg", "<script>alert('删除失败!')</script>");
			return "forward:loadEmployeeList";
		}
	}

	// 管理员对员工进行换岗操作
	@RequestMapping("/modifyEmployee")
	public String modifyEmployee(Integer id, Map<String, Object> map) {
		// 根据员工id找到其对应的部门和职位关系
		Employee employee = adminService.getEmployeeMsgById(id);
		map.put("key_employee_msg", employee);
		// 遍历部门表和职位表,查出所有,供给管理员进行换岗操作时使用
		List<Dept> listDept = adminService.getAllDeptMsg();
		map.put("key_listDept_msg", listDept);
		return "admin/employee/edit";
	}

	// 管理员进行换岗的二级联动
	@RequestMapping("/changeDeptAndJob")
	public String changeDeptAndJob(@RequestParam(name = "deptId") Integer deptId, HttpServletResponse resp,
			Map<String, Object> map) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		List<Job> list = adminService.findAllJobMsgByDeptId(deptId);
		JSONObject json = new JSONObject();
		JSON jj = (JSON) json.toJSON(list);
		PrintWriter out = resp.getWriter();
		out.println(jj.toJSONString());
		return null;
	}

	// 换好岗之后,更新员工表中部门和职位信息
	@RequestMapping("/updateEmployeeMsg")
	public String updateEmployeeMsg(Map<String, Object> map, Integer id,
			@RequestParam(name = "selectDeptName") Integer did, @RequestParam(name = "selectJobName") String jname)
					throws UnsupportedEncodingException {
		String encodeJname = new String(jname.getBytes("ISO-8859-1"), "utf-8");
		adminService.updateEmployeeMsg(did, encodeJname, id);
		map.put("key_msg", "<script>alert('换岗成功!')</script>");
		return "forward:loadEmployeeList";
	}

	// 管理员点击培训信息显示的功能操作
	@RequestMapping("/loadTrainList")
	public String loadTrainList(Map<String, Object> map, Integer page, HttpServletRequest req) {
		// 查询培训信息表,并带有数据的显示在list.jsp中(培训id,培训时间,培训内容,给哪些人培训,选择培训官)
		if (page == null) {
			page = 1;
		}
		PageBean<Train> pb = adminService.getTrainMsgByPage(page);
		List<Train> list = pb.getList();
		for (Train train : list) {
			System.out.println("培训信息内容---" + train);
		}
		map.put("key_trainMsg_list", list);
		map.put("key_trainMsg_pb", pb);
		return "admin/train/list";
	}

	// 管理员添加培训信息,注明给哪些员工培训,注明后员工能看到培训信息,点击进去能看到培训详情
	@RequestMapping("/adminAddTrain")
	public String adminAddTrain(HttpServletRequest req) {
		// 还得查询一遍员工表,把所有员工姓名集合存到session中,可供管理员添加培训时选择
		System.out.println("进来了");
		List<Employee> listEmplName = adminService.getAllEmployeeMsg();
		System.out.println("listEmplName" + listEmplName);
		req.getSession().setAttribute("session_listEmplName", listEmplName);
		return "admin/train/add";
	}

	// 保存管理员添加的培训信息(其中还要员工到时候点击培训信息时能看到)
	@RequestMapping("/saveTrainMsg")
	public String saveTrainMsg(Map<String, Object> map, @RequestParam(name = "ttime") String ttime,
			@RequestParam(name = "tcontent") String tcontent, @RequestParam(name = "taddress") String taddress,
			@RequestParam(name = "trainEmplName") Integer eid, @RequestParam(name = "trainBossName") Integer bosseid)
					throws UnsupportedEncodingException, ParseException {
		String encodeTcontent = new String(tcontent.getBytes("ISO-8859-1"), "UTF-8");
		String encodeTaddress = new String(taddress.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("ttime--" + ttime);
		String newTtime = ttime.replace("T", " ");
		System.out.println("newTtime--" + newTtime);
		// 根据员工id得到员工信息
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// Date date = sdf.parse(newTtime);
		Employee employee = adminService.getEmployeeMsgById(eid);
		Employee emplBoss = adminService.getEmployeeMsgById(bosseid);
		Train train = new Train();
		train.setTtime(newTtime);
		train.setTcontent(encodeTcontent);
		train.setTaddress(encodeTaddress);
		// 为单个员工添加培训信息之前,判断这个用户有没有被添加,如果添加则不能再重复添加培训了
		boolean flag = adminService.isExistMsg(employee.getEid());
		// 根据员工id得到员工信息(姓名)
		Employee empl = adminService.getEmployeeMsgById(employee.getEid());
		// 根据bosseid选择培训官id得到其对应的姓名
		if (flag == true) {
			adminService.saveTrainMsg(train, employee.getEid(), empl.getEname(), emplBoss.getEid(),
					emplBoss.getEname());
			// 根据培训表中e_id得到培训表的id,然后设置员工表中的et_id=培训表id
			Train trainMsg = adminService.getTrainMsgByEid(employee.getEid());
			System.out.println("trainMsg.getTid()" + trainMsg.getTid());
			adminService.updateEmployeeMsgByTrain(trainMsg.getTid(), employee.getEid());
			return "forward:loadTrainList";
		}
		if (flag == false) {
			map.put("key_msg", "<script>alert('该员工已有培训安排,不能重复安排')</script>");
			return "forward:loadTrainList";
		}
		return null;
	}

	// 管理员删除培训信息
	@RequestMapping("/delTrainMsg")
	public String delTrainMsg(Integer id, Map<String, Object> map) {
		// 根据培训信息id删除信息
		adminService.delTrainMsgById(id);
		map.put("key_msg", "<script>alert('删除成功!')</script>");
		return "forward:loadTrainList";
	}

	// 管理员修改培训信息
	@RequestMapping("/modifyTrainMsg")
	public String modifyTrainMsg(Integer id, Map<String, Object> map) {
		// 根据培训表的id拿到数据到修改页面上进行回显
		Train train = adminService.getTrainMsg(id);
		System.out.println("train---" + train);
		// 得到所有的员工姓名存进集合,供修改的时候选择
		List<Employee> list = adminService.getAllEmployeeMsg();
		map.put("key_emplName_msg", list);
		map.put("key_train_msg", train);
		return "admin/train/update";
	}

	// 更新培训信息(update)
	@RequestMapping("/updateTrainMsg")
	public String updateTrainMsg(Map<String, Object> map, Integer id, @RequestParam(name = "ttime") String ttime,
			@RequestParam(name = "tcontent") String tcontent, @RequestParam(name = "taddress") String taddress,
			@RequestParam(name = "selectTrainEmployeeName") Integer eid) throws UnsupportedEncodingException {
		// 根据id来修改培训 信息,因为培训表里面有员工id,和员工name,所以也要做一个及时的更改
		String encodeTcontent = new String(tcontent.getBytes("ISO-8859-1"), "utf-8");
		String encodeTaddress = new String(taddress.getBytes("ISO-8859-1"), "utf-8");
		// 根据eid拿到对应的enamel
		Employee employee = adminService.getEmployeeMsgById(eid);
		String ename = employee.getEname();
		// 更新培训表里面的内容
		adminService.updateTrainMsg(eid, ename, ttime, encodeTcontent, encodeTaddress, id);
		map.put("key_msg", "<script>alert('修改成功!')</script>");
		return "forward:loadTrainList";
	}

	// 管理员点击奖惩信息管理的操作(对奖惩信息的crud操作)
	@RequestMapping("/loadRepeList")
	public String loadRepeList(Integer page, Map<String, Object> map) {
		if (page == null) {
			page = 1;
		}
		PageBean<Repe> pb = adminService.getRepeMsgByPage(page);
		List<Repe> list = pb.getList();
		map.put("key_repeMsg_pb", pb);
		map.put("key_repeMsg_list", list);
		return "admin/repe/list";
	}

	// 管理员增加奖励操作进入页面
	@RequestMapping("/adminAddRe")
	public String adminAddRe(Map<String, Object> map) {
		// 查询员工表获得员工信息,把所有的员工可供选择的给管理员做奖惩操作
		List<Employee> list = adminService.getAllEmployeeMsg();
		map.put("key_employee_list", list);
		return "admin/repe/addRe";
	}

	// 管理员实现增加奖励操作
	@RequestMapping("/saveReMsg")
	public String saveReMsg(Map<String, Object> map, @RequestParam(name = "retime") String retime,
			@RequestParam(name = "resal") double resal, @RequestParam(name = "rereason") String rereason,
			@RequestParam(name = "chooseEmplName") Integer eid) throws UnsupportedEncodingException {
		// 把填写的奖励有关的数据保存到数据库中,方便起见,奖惩表中有员工id,员工姓名信息,干脆把他们也一并保存进去
		// 根据员工id获得到员工信息
		Employee employee = adminService.getEmployeeMsgById(eid);
		String ename = employee.getEname();
		// 保存奖惩的数据到数据表中
		Repe repe = new Repe();
		repe.setRetime(retime);
		repe.setResal(resal);
		repe.setRereason(rereason);
		// 这里出现的空指针,通过eid获取到employee对象然后再进行设置,就不会报空指针了
		Employee empl = adminService.getEmployeeMsgById(eid);
		repe.setEmployee(empl);
		repe.getEmployee().setEid(eid);
		repe.getEmployee().setEname(ename);
		adminService.saveRepeMsg(repe);
		map.put("key_msg", "<script>alert('奖励完成,你就让他偷着乐吧!')</script>");
		return "forward:loadRepeList";
	}

	// 管理员增加惩罚操作进入页面
	@RequestMapping("/adminAddPe")
	public String adminAddPe(Map<String, Object> map) {
		List<Employee> list = adminService.getAllEmployeeMsg();
		map.put("key_employee_list", list);
		return "admin/repe/addPe";
	}

	// 管理员实现惩罚操作
	@RequestMapping("/savePeMsg")
	public String savePeMsg(Map<String, Object> map, @RequestParam(name = "petime") String petime,
			@RequestParam(name = "pesal") double pesal, @RequestParam(name = "pereason") String pereason,
			@RequestParam(name = "chooseEmplName") Integer eid) throws UnsupportedEncodingException {
		// 把填写的惩罚有关的数据保存到数据库中,方便起见,奖惩表中有员工id,员工姓名信息,干脆把他们也一并保存进去
		// 根据员工id获得到员工信息
		Employee employee = adminService.getEmployeeMsgById(eid);
		String ename = employee.getEname();
		Repe repe = new Repe();
		repe.setPetime(petime);
		repe.setPesal(pesal);
		repe.setPereason(pereason);
		// 这里出现的空指针,通过eid获取到employee对象然后再进行设置,就不会报空指针了
		Employee empl = adminService.getEmployeeMsgById(eid);
		repe.setEmployee(empl);
		repe.getEmployee().setEid(eid);
		repe.getEmployee().setEname(ename);
		adminService.savePeReMsg(repe);
		// 保存惩罚的数据到数据表中
		map.put("key_msg", "<script>alert('惩罚成功,他下次会注意的!')</script>");
		return "forward:loadRepeList";
	}

	// 管理员根据奖惩的id进行奖惩的删除,考虑到后期的薪资那块等会再做
	@RequestMapping("/delRepeMsg")
	public String delRepeMsg(Integer id, Map<String, Object> map) {
		adminService.delRepeMsg(id);
		// 这里删除奖惩相关信息,后序可以要把员工工资对应的相加减一下
		map.put("key_msg", "<script>alert('删除成功!')</script>");
		return "forward:loadRepeList";
	}

	// 修改奖惩信息跳转的界面
	@RequestMapping("/modifyRepeMsg")
	public String modifyRepeMsg(Integer id, Map<String, Object> map) {
		// 带数据的回显修改信息,根据id等到奖惩表中的信息
		Repe repe = adminService.getAllRepeMsgById(id);
		map.put("key_repe_msg", repe);
		// 得到所有的员工姓名可供选择
		List<Employee> list = adminService.getAllEmployeeMsg();
		map.put("key_employee_list", list);
		return "admin/repe/update";
	}

	// 更新奖惩信息
	@RequestMapping("/updateRepeMsg")
	public String updateRepeMsg(Integer id, Map<String, Object> map, @RequestParam(name = "retime") String retime,
			@RequestParam(name = "resal") double resal, @RequestParam(name = "rereason") String rereason,
			@RequestParam(name = "petime") String petime, @RequestParam(name = "pesal") double pesal,
			@RequestParam(name = "pereason") String pereason,
			@RequestParam(name = "selectRepeEmployeeName") Integer eid) throws UnsupportedEncodingException {
		String encodeRereason = new String(rereason.getBytes("ISO-8859-1"), "utf-8");
		String encodePereason = new String(pereason.getBytes("ISO-8859-1"), "utf-8");
		Repe repe = new Repe(resal, retime, encodeRereason, pesal, petime, encodePereason);
		// 根据eid获得enamel
		repe.setRpid(id);
		Employee employee = adminService.getEmployeeMsgById(eid);
		repe.setEmployee(employee);
		repe.getEmployee().setEid(employee.getEid());
		repe.getEmployee().setEname(employee.getEname());
		System.out.println("----" + repe);
		adminService.updateRepeMsg(repe);
		map.put("key_msg", "<script>alert('修改成功!')</script>");
		return "forward:loadRepeList";
	}

	// 管理员查看考勤信息,全体员工的考勤状况
	@RequestMapping("/loadAttenceList")
	public String loadAttenceList(Map<String, Object> map, Integer page) {
		// 管理员查询员工表,分页显示员工的考勤记录,一个员工有多个考勤记录
		// 得到所有的员工姓名给管理员点击查看其对应的考勤记录
		if (page == null) {
			page = 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// 根据年月日来查询当天的考勤情况信息(包括-员工的上班时间,下班时间,迟到,早退状态)
		PageBean<Attence> pb = adminService.getAttenceBydateAndPage(date, page);
		List<Attence> list = pb.getList();
		String strDate = sdf.format(date);
		// 1.获得总员工数 2.获取考勤了的员工数
		int countEmployee = adminService.getEmployeeCount();
		int attenceEmployee = adminService.getEmployeeAttenceCount(date);
		map.put("key_countEmployee", countEmployee);
		map.put("key_attenceEmployee", attenceEmployee);
		map.put("key_date_msg", strDate);
		map.put("key_attence_list", list);
		map.put("key_attence_pb", pb);
		return "admin/attence/list";
	}

	// 管理员选择日期进行这一天员工的考勤状况,传入参数 String strDate
	@RequestMapping("/whichDay")
	public String whichDay(String strDate, Integer page, Map<String, Object> map) {
		if (page == null) {
			page = 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PageBean<Attence> pb = adminService.getAttenceBydateAndPage(date, page);
		List<Attence> list = pb.getList();
		for (Attence attence : list) {
			System.out.println("---" + attence);
		}
		String strDate1 = sdf.format(date);
		// 1.获得总员工数 2.获取考勤了的员工数
		int countEmployee = adminService.getEmployeeCount();
		int attenceEmployee = adminService.getEmployeeAttenceCount(date);
		map.put("key_countEmployee", countEmployee);
		map.put("key_attenceEmployee", attenceEmployee);
		map.put("key_date_msg", strDate1);
		map.put("key_attence_list", list);
		map.put("key_attence_pb", pb);
		return "admin/attence/list";
	}

	// 管理员薪资结算操作
	/*
	 * 20号才能结算工资,判断当前时间是不是本月20号,如果是才能进入工资结算界面,如果没到日期则不能进入
	 */
	/*
	 * @RequestMapping("/loadSalaryList") public String
	 * loadSalaryList(Map<String, Object>map){ //获取当前计算机的时间,只有每月20号之后才能进入结算工资界面
	 * Date date = new Date(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); String strDate = sdf.format(date);
	 * map.put("key_date_msg", strDate);
	 * //判断当前日期是不是20号之后,是则进入salary/list界面,不是则弹框未到结算工资日期 Calendar cal =
	 * Calendar.getInstance(); cal.setTime(date); int day =
	 * cal.get(Calendar.DAY_OF_MONTH); //得到该月哪一天 if(day >= 20){
	 * //得到员工信息(id,name,职位,部门,本月旷工天数,迟到天数,早退天数,加班天数) return "admin/salary/list";
	 * }else{ return "admin/salary/nolist"; } }
	 */
	@RequestMapping("/loadSalaryList")
	public String loadEmployeeAttenceMsgList(Map<String, Object> map) {
		// 进入查看员工考勤信息界面,查看当月相应的考勤信息给出相应的绩效工资
		// 在扩展类中设置旷工天数
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		map.put("key_date_msg", strDate);
		// 判断当前日期是不是20号之后,是则进入salary/list界面,不是则弹框未到结算工资日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH); // 得到该月哪一天
		if (day >= 20) {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// Calendar cal = Calendar.getInstance();
			// Date date = new Date();
			// cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			// String strDate = sdf.format(date);
			map.put("key_date_msg", strDate);
			// 根据当前月份得到该月对应的天数
			int totalDay = 0;
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				totalDay = 31;
				break;
			case 2:
				totalDay = 28;
				break;
			default:
				totalDay = 30;
				break;
			}
			// 根据年份,月份查看所有员工当月的考勤情况
			List<EmployeeCusttom> list = adminService.getAllEmployeeMsgCustom();
			for (EmployeeCusttom employeeCusttom : list) {
				List<Attence> listAtten = adminService.getAttenceMsgByidAndDate(employeeCusttom.getEid(), year, month);
				if (listAtten != null && !listAtten.isEmpty()) {
					int absance = 0; // 统计旷工天数
					int comeLate = 0;// 统计迟到天数
					int leftEarly = 0;// 统计早退天数
					for (Attence attence : listAtten) {
						if ("旷工".equals(attence.getLeftearlystatus()) || "旷工".equals(attence.getComelatestatus())) {
							absance = absance + 1;
						}
						if ("迟到".equals(attence.getComelatestatus()) && "正常".equals(attence.getLeftearlystatus())) {
							// 统计迟到天数
							comeLate = comeLate + 1;
						}
						if ("正常".equals(attence.getComelatestatus()) && "早退".equals(attence.getLeftearlystatus())) {
							leftEarly = leftEarly + 1;
						}
					}
					System.out.println("考勤记录:" + listAtten.size());
					employeeCusttom.setKgDay(totalDay - listAtten.size() + absance);
					employeeCusttom.setCdDay(comeLate);
					employeeCusttom.setZtDay(leftEarly);
					if (employeeCusttom.getKgDay() == 0 && employeeCusttom.getCdDay() == 0
							&& employeeCusttom.getZtDay() == 0) {
						// 满勤状态下该员工每月获得500绩效奖金
						employeeCusttom.setJxsal(500);
					}
					if (!"离职".equals(employeeCusttom.getEstatus())) {
						employeeCusttom.setSbsal(200);
					}
					employeeCusttom.setPesal(employeeCusttom.getKgDay() * 50 + employeeCusttom.getCdDay() * 20
							+ employeeCusttom.getZtDay() * 20);
					double totalSal = employeeCusttom.getEsalary() - employeeCusttom.getPesal()
							- employeeCusttom.getSbsal();
					employeeCusttom.setTotalSal(totalSal);
					employeeCusttom.setYear(year);
					employeeCusttom.setMonth(month);
				} else {
					// 该员工记录为空时,整月旷工天数
					employeeCusttom.setKgDay(totalDay);
					employeeCusttom.setCdDay(0);
					employeeCusttom.setZtDay(0);
					employeeCusttom.setJxsal(0);
					employeeCusttom.setSbsal(0);
					employeeCusttom.setResal(0);
					employeeCusttom.setPesal(0);
					employeeCusttom.setYear(year);
					employeeCusttom.setMonth(month);
				}
				System.out.println("---" + employeeCusttom);
				map.put("key_employeeCusttom_list", list);
				// 结算当前计算机获得的月份对应的工资完工资,把这些信息存进薪资表中
				adminService.saveSalMsgYearAndMonth(employeeCusttom);
			}
			return "admin/salary/list";
		} else {
			return "admin/salary/nolist";
		}
	}

	// 管理员查看所有员工该月考勤的相关记录(旷工天数,迟到天数,早退天数)
	@RequestMapping("/whichMonth")
	public String whichMonth(String strDate, Map<String, Object> map) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String strDate1 = sdf.format(date);
		map.put("key_date_msg", strDate1);
		// 根据当前月份得到该月对应的天数
		int totalDay = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			totalDay = 31;
			break;
		case 2:
			totalDay = 28;
			break;
		default:
			totalDay = 30;
			break;
		}
		// 根据年份,月份查看所有员工当月的考勤情况
		List<EmployeeCusttom> list = adminService.getAllEmployeeMsgCustom();
		for (EmployeeCusttom employeeCusttom : list) {
			List<Attence> listAtten = adminService.getAttenceMsgByidAndDate(employeeCusttom.getEid(), year, month);
			if (listAtten != null && !listAtten.isEmpty()) {
				int absance = 0; // 统计旷工天数
				int comeLate = 0;// 统计迟到天数
				int leftEarly = 0;// 统计早退天数
				for (Attence attence : listAtten) {
					if ("旷工".equals(attence.getLeftearlystatus()) || "旷工".equals(attence.getComelatestatus())) {
						absance = absance + 1;
					}
					if ("迟到".equals(attence.getComelatestatus()) && "正常".equals(attence.getLeftearlystatus())) {
						// 统计迟到天数
						comeLate = comeLate + 1;
					}
					if ("正常".equals(attence.getComelatestatus()) && "早退".equals(attence.getLeftearlystatus())) {
						leftEarly = leftEarly + 1;
					}
					System.out.println("考勤记录:" + listAtten.size());
					employeeCusttom.setKgDay(totalDay - listAtten.size() + absance);
					employeeCusttom.setCdDay(comeLate);
					employeeCusttom.setZtDay(leftEarly);
					if (employeeCusttom.getKgDay() == 0 && employeeCusttom.getCdDay() == 0
							&& employeeCusttom.getZtDay() == 0) {
						// 满勤状态下该员工每月获得500绩效奖金
						employeeCusttom.setJxsal(500);
					}
					if (!"离职".equals(employeeCusttom.getEstatus())) {
						employeeCusttom.setSbsal(200);
					}
					employeeCusttom.setPesal(employeeCusttom.getKgDay() * 50 + employeeCusttom.getCdDay() * 20
							+ employeeCusttom.getZtDay() * 20);
					double totalSal = employeeCusttom.getEsalary() - employeeCusttom.getPesal()
							- employeeCusttom.getSbsal();
					employeeCusttom.setTotalSal(totalSal);
					employeeCusttom.setYear(year);
					employeeCusttom.setMonth(month);

				}
			} else {
				// 该员工记录为空时,整月旷工天数
				employeeCusttom.setKgDay(totalDay);
				employeeCusttom.setCdDay(0);
				employeeCusttom.setZtDay(0);
				employeeCusttom.setJxsal(0);
				employeeCusttom.setSbsal(200);
				employeeCusttom.setResal(0);
				employeeCusttom.setPesal(0);
				employeeCusttom.setTotalSal(0);
				employeeCusttom.setYear(year);
				employeeCusttom.setMonth(month);
			}
			System.out.println("---" + employeeCusttom);
			map.put("key_employeeCusttom_list", list);
			// 结算当前计算机获得的月份对应的工资完工资,把这些信息存进薪资表中
			adminService.saveSalMsgYearAndMonth(employeeCusttom);
		}
		return "admin/salary/list";
	}

	// 管理员对薪资复议的操作,查看异议信息记录,做相应的处理
	@RequestMapping("/loadReviewList")
	public String loadReviewList(Map<String, Object> map, Integer page) {
		// 分页查询薪资异议表中的数据显示在页面上
		if (page == null) {
			page = 1;
		}
		PageBean<SalReview> pb = adminService.getSalReviewMsgByPage(page);
		List<SalReview> list = pb.getList();
		map.put("key_salReview_pb_msg", pb);
		map.put("key_list_msg", list);
		return "admin/salary/salReview";
	}

	// 管理员点击眼睛图片对异议信息查看,根据异议信息id 获取该月供异议的月工资信息
	@RequestMapping("/lookSalReviewMsg")
	public String lookSalReviewMsg(Integer id, Map<String, Object> map) {
		// 根据id获得员工id,name,薪资表对应的详细信息
		SalReview salReview = adminService.getSalReviewMsgById(id);
		int srid = salReview.getSrid();
		int sid = salReview.getSalary().getSid();
		// 根据sid得到薪资表信息
		Salary salary = adminService.getSalaryMsgBySid(sid);
		map.put("key_salary", salary);
		map.put("key_srid_msg", srid);
		return "admin/salary/lookEmplSalMsg";
	}

	// 管理员查看的这条薪资驳回操作,修改操作状态为已处理
	@RequestMapping("/rejectyy")
	public String rejectyy(Integer srid) {
		adminService.updateSalReviewMsg(srid);
		return "forward:loadReviewList";
	}

	// 重新计算工资一遍
	@RequestMapping("/recyle")
	public String recyle(Integer sid, Map<String, Object> map) {
		Salary salary = adminService.getSalaryMsgBySid(sid);
		map.put("key_salary", salary);
		return "admin/salary/recyleSal";
	}

	// 工资有出入的操作,自动生成一条奖惩记录
	@RequestMapping("/reissue")
	public String reissue(Integer eid, int month, Integer srid) throws UnsupportedEncodingException {
		// 参数是员工id,month是下个月的月份,给该员工下个月生成一条奖励记录,补发到下月工资里面
		Employee empl = adminService.getEmployeeMsgById(eid);
		Repe repe = new Repe();
		repe.setEmployee(empl);
		// "上月漏发工资,写死给(赏500元)";
		double resal = 500;
		repe.setResal(resal);
		String strreason = "月结算错误补发";
		String FencodeStrreason = new String(strreason.getBytes("utf-8"), "ISO-8859-1");
		repe.setRereason((month - 1) + FencodeStrreason);
		String retime = "2017-" + month;
		repe.setRetime(retime);
		adminService.saveRepeMsg(repe);
		adminService.updateSalReviewMsg(srid);
		return "forward:loadReviewList";
	}
}
