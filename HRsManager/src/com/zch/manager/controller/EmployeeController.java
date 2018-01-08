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

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zch.manager.bean.Dept;
import com.zch.manager.bean.Employee;
import com.zch.manager.bean.Repe;
import com.zch.manager.bean.SalReview;
import com.zch.manager.bean.Salary;
import com.zch.manager.bean.Train;
import com.zch.manager.service.EmployeeService;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年10月6日 下午1:25:06
 */
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/employeeLogin")
	public String employeeLogin() {
		return "employee/Login";
	}

	// ajax验证验证码是否正确
	@RequestMapping("/validateCode")
	public void validateCode(HttpServletResponse response, String randomCode, HttpServletRequest request)
			throws IOException {
		// 1.获取输入框的验证码 2.获取图片中的验证码 3. 忽略大小写的进行比较
		String vCode = (String) request.getSession().getAttribute("vCode");
		boolean b = randomCode.equalsIgnoreCase(vCode);
		response.getWriter().println(b);
	}

	@RequestMapping("/validateEmplLogin")
	// 员工登录,验证用户名和密码是否正确
	public String validateEmplLogin(HttpServletRequest req, Map<String, Object> map,
			@RequestParam(name = "username") String username, @RequestParam(name = "pwd") String pwd,
			@RequestParam(name = "randomCode") String randomCode) throws UnsupportedEncodingException {
		String encodeName = new String(username.getBytes("ISO-8859-1"), "utf-8");
		Employee employee = new Employee(encodeName, pwd);
		boolean flag = employeeService.validateEmplLogin(employee);
		if (flag == true) {
			// 验证成功
			req.getSession().setAttribute("session_employee_msg", employee);
			System.out.println(employee.getEname());
			return "employee/index";
		}
		if (flag == false) {
			// 验证失败,重新登录
			map.put("key_msg", "<script>alert(用户名字或密码错误)</script>");
			return "forward:employeeLogin";
		}
		return null;
	}

	// 员工界面显示top.jsp
	@RequestMapping("/loadEmplTop")
	public String loadTop() {
		return "employee/top";
	}

	// 员工界面显示left.jsp
	@RequestMapping("/loadEmplLeft")
	public String loadLeft() {
		return "employee/left";
	}

	// 员工界面显示welcome.jsp
	@RequestMapping("/loadEmplWelcome")
	public String loadWelcome() {
		return "employee/welcome";
	}

	// 员工退出功能
	@RequestMapping("/employeeExist")
	public String employeeExist(HttpServletRequest req, Map<String, Object> map) {
		req.getSession().invalidate();
		map.put("key_exist_msg", "你已退出,请重新登录!");
		return "employee/Login";
	}

	// 员工修改密码
	// 员工点击部门职位,查看所有的部门以及部门下的职位,还可以查看职位下的员工姓名
	@RequestMapping("/deptJobEmployeeMsg")
	public String deptJobEmployeeMsg(Map<String, Object> map, Integer page) {
		if (page == null) {
			page = 1;
		}
		PageBean<Dept> pb = employeeService.getDeptMsgByPage(page);
		System.out.println(pb);
		List<Dept> list = pb.getList();
		System.out.println("list信息为--" + list);
		for (Dept dept : list) {
			System.out.println(dept.getJob());
		}
		map.put("key_deptMsg_list", list);
		map.put("key_deptMsg_pb", pb);
		return "employee/deptJobMsg/list";
	}
	// 管理员在部门显示中二级联动部门职位,职位员工.(部门-职位-一对多 职位--员工 一对多)
	@RequestMapping("/emplGetEmployeeMsgByjobId")
	public String getEmployeeMsgByjobId(Integer jobId, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		// 根据职位id查找该职位下对应的所有的员工
		List<Employee> listEmployee = employeeService.findAllEmployeeMsgByJobId(jobId);
		JSONObject json = new JSONObject();
		JSON jj = (JSON) json.toJSON(listEmployee);
		System.out.println(jj.toJSONString());
		out.println(jj.toJSONString());
		return null;
	}

	// 员工点击培训信息的一系列操作(收到关于培训的通知,并能查看培训的相关内容)
	@RequestMapping("/trainMsg")
	public String trainMsg(HttpServletRequest req, Map<String, Object> map) {
		// 根据保存在session中的登录的用户名找到et_id,进而通过培训t_id获取到培训相关信息
		Employee employee = (Employee) req.getSession().getAttribute("session_employee_msg");
		// 根据员工名字找到员工id,因为做了操作,姓名是不会相同的
		Employee empl = employeeService.getEmployeeMsgByName(employee.getEname());
		Employee newEmployee = employeeService.getEmployeeMsgById(empl.getEid());
		System.out.println("newEmployee" + newEmployee.getEid());
		// 下面注释的这行如果员工没有培训消息,可能出现空指针异常,下面是解决方案
		// 根据员工id查找et_id,判断是否为空
		Integer et_id = employeeService.getEmployeeEtidByid(newEmployee.getEid());
		System.out.println("et_id--" + et_id);
		// int tid = newEmployee.getTrain().getTid();
		// 有为空的状况
		if (et_id != null) {
			Train train = employeeService.getTrainMsg(et_id);
			System.out.println("train--" + train);
			map.put("key_train_msg", train);
			return "employee/peixun/list";
		}
		{
			map.put("key_msg", "<script>alert('你还没有相关的培训消息~')</script>");
			return "employee/peixun/list";
		}
	}

	// 员工点击考勤打卡显示的界面,(加载morning12,evening12四张图片)
	@RequestMapping("/signInMsg")
	public String signInMsg() {
		return "employee/daka/list";
	}

	// 员工点击奖惩,查看和自己相关的奖惩情况
	@RequestMapping("/repeMsg")
	public String repeMsg(Integer page, Map<String, Object> map, HttpServletRequest req) {
		if (page == null) {
			page = 1;
		}
		Employee employee = (Employee) req.getSession().getAttribute("session_employee_msg");
		System.out.println("员工姓名为--" + employee.getEname());
		// 根据员工姓名得到员工id
		Employee empl = employeeService.getEmployeeMsgByName(employee.getEname());
		PageBean<Repe> pb = employeeService.getRepeMsgBypage(page, empl.getEid());
		System.out.println("pb--" + pb);
		List<Repe> list = pb.getList();
		System.out.println("list--" + list);
		map.put("key_repeMsg_pb", pb);
		map.put("key_repe_list", list);
		return "employee/repe/list";
	}

	// 员工点击上班打卡的操作
	@RequestMapping("/signIn")
	public String signIn(Map<String, Object> map, HttpServletRequest req) {
		// 一天(24h)只能打一次上班卡,一次下班卡,如果打完上班卡之后刷新网页后,看到的应该是上班卡灰色,没打下班卡有颜色
		// 把系统时间改变时候相当于重新进来一次
		HttpSession session = req.getSession();
		session.setAttribute("session_signIn", "今天打卡上班");
		if (session.getAttribute("session_signIn") != "") {
			map.put("key_msg", "<script>alert('今日你已打卡!!')</script>");
		}
		// 获取哪个员工 打的卡的时间记录到数据库中,方便管理员查看考勤记录计算工资
		Employee employee = (Employee) req.getSession().getAttribute("session_employee_msg");
		// 得到的是员工姓名,根据员工姓名得到对应的员工id
		Employee empl = employeeService.getEmployeeMsgByName(employee.getEname());
		// 得到员工id,前面还得到了员工姓名
		int eid = empl.getEid();
		// 获取打卡上班时间
		Date date = new Date();
		employeeService.saveAttenceMsg(date, eid, empl.getEname());
		return "employee/daka/list";
	}

	// 员工点击下班打卡的操作
	@RequestMapping("/signOut")
	public String signout(Map<String, Object> map, HttpServletRequest req) {
		Employee employee = (Employee) req.getSession().getAttribute("session_employee_msg");
		// 得到的是员工姓名,根据员工姓名得到对应的员工id
		Employee empl = employeeService.getEmployeeMsgByName(employee.getEname());
		// 得到员工id,前面还得到了员工姓名
		int eid = empl.getEid();
		HttpSession session = req.getSession();
		session.setAttribute("session_signOut", "今天打卡下班");
		// 获取打卡下班时间
		Date date = new Date();
		// 根据用户id更新考勤记录
		employeeService.updateAttenceMsg(date, eid, empl.getEname());
		return "employee/daka/list";
	}

	// 员工点击我的消息查看面试通知
	@RequestMapping("/emplMsg")
	public String emplMsg() {
		return "employee/msg/list";
	}

	// 面试官(员工)点击录用做一系列的操作
	@RequestMapping("/admin")
	public String admin(HttpServletRequest req, int status) {
		System.out.println("---status" + status);
		if (status == 1) {
			req.getSession().setAttribute("session_admin", 1);
		}
		return "forward:loadIndex";
	}

	// 面试官(员工)点击不录用做一系列的操作
	@RequestMapping("/notAdmin")
	public String notAdmin(HttpServletRequest req, int status) {
		if (status == 0) {
			req.getSession().setAttribute("session_admin", 0);
		}
		return "forward:loadIndex";
	}

	// 员工点击我的薪资进行查看操作
	@RequestMapping("/lookMySal")
	public String lookMySal(HttpServletRequest req, Map<String, Object> map) {
		// 根据登录的seession中的用户名得到用户id,根据id查找t_salary薪资表,
		// 员工可以根据年份和月份来查看自己当月的工资状态,工资每月20号发放,
		// 每月20之前员工点击都有弹框提示,该月工资还未发放,请等待财务..,20好之后员工可以查看自己以往的(比如上个月的薪资)
		// 信息,如果有疑问可以申请薪资复议
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Employee empl = (Employee) req.getSession().getAttribute("session_employee_msg");
		String ename = empl.getEname();
		// 根据员工姓名找到员工id,用id做操作更加精准
		Employee employee = employeeService.getEmployeeMsgByName(ename);
		int eid = employee.getEid();// 得到员工id
		if (day >= 20) {
			String strDate = sdf.format(date);
			map.put("key_date_msg", strDate);
			// 得到员工该月的薪资信息
			Salary salary = employeeService.getSalMsgDate(eid, year, month);
			map.put("key_salary_msg", salary);
			return "employee/salary/mysal";
		}
		return "employee/salary/nomysal";
	}

	// 员工切换年月来查看我的当前年月薪资记录
	@RequestMapping("/myWhichMonth")
	public String myWhichMonth(String strDate, Map<String, Object> map, HttpServletRequest req) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Employee empl = (Employee) req.getSession().getAttribute("session_employee_msg");
		String ename = empl.getEname();
		// 根据员工姓名找到员工id,用id做操作更加精准
		Employee employee = employeeService.getEmployeeMsgByName(ename);
		int eid = employee.getEid();// 得到员工id
		if (day >= 20) {
			map.put("key_date_msg", strDate);
			// 得到员工该月的薪资信息
			Salary salary = employeeService.getSalMsgDate(eid, year, month);
			map.put("key_salary_msg", salary);
			return "employee/salary/mysal";
		}
		return "employee/salary/nomysal";
	}

	// 员工点击异议操作,表示对该月薪资有疑问,把疑问相关信息保存在薪资异议表中去,让管理员看得见
	// 传入参数sid薪资表的id(里面有年月信息),eid员工的id,谁提出的异议
	@RequestMapping("/salReview")
	public String salReview(Integer eid, Integer sid, Map<String, Object> map) {
		map.put("key_eid", eid);
		map.put("key_sid", sid);
		return "employee/salary/addReason";
	}

	// 员工写好原因后提交操作(吧相关信息保存到复议表中去)
	// 传入参数sid薪资表的id(里面有年月信息),eid员工的id,谁提出的异议
	@RequestMapping("/saveSalReviewReason")
	public String saveSalReviewReason(Map<String, Object> map, @RequestParam(name = "reason") String reason,
			Integer eid, Integer sid) throws UnsupportedEncodingException {
		String encodeReason = new String(reason.getBytes("ISO-8859-1"), "utf-8");
		map.put("key_msg", "<script>alert('异议提交成功,等待审核,如有出入,请注意下月薪资查看!!')</script>");
		// 根据sid,eid分别得到薪资类,员工类对象,不然设置使用mybatis设置对象时会出现空指针状况
		Employee empl = employeeService.getEmployeeMsgById(eid);
		Salary salary = employeeService.getSalMsgById(sid);
		// 插入相应数据到异议表中
		SalReview salReview = new SalReview();
		salReview.setSrreason(encodeReason);
		salReview.setEmployee(empl);
		salReview.setSrstatus("等待处理");
		// salary对象封装了异议原因,哪个员工,哪条薪资(里面有年月信息)
		salReview.setSalary(salary);
		employeeService.saveSalReview(salReview);
		return "forward:lookMySal";
	}

	// 员工点击查看我的个人信息
	@RequestMapping("/loadMyMsgList")
	public String loadMyMsgList(HttpServletRequest req, Map<String, Object> map) {
		// 返回一个个人中心的信息界面,根据session中的登陆的用户名得到用户id,再得到员工相关信息
		Employee employee = (Employee) req.getSession().getAttribute("session_employee_msg");
		Employee empl = employeeService.getEmployeeMsgByName(employee.getEname());
		// 得到员工的基本信息
		Employee em = employeeService.getEmployeeMsgById(empl.getEid());
		map.put("key_em_msg", em);
		return "employee/myMsg/list";
	}

	// 员工修改姓名,联系方式
	@RequestMapping("/modifyEmployeeMsg")
	public String modifyEmployeeMsg(Integer id, Map<String, Object> map) {
		Employee empl = employeeService.getEmployeeMsgById(id);
		map.put("key_employee_msg", empl);
		return "employee/myMsg/edit";
	}

	// 员工修改个人基本信息
	@RequestMapping("/myUpdateEmployeeMsg")
	public String myUpdateEmployeeMsg(Integer id, Map<String, Object> map, @RequestParam(name = "pwd") String pwd,
			@RequestParam(name = "phone") String phone) throws UnsupportedEncodingException {
		Employee employee = new Employee();
		employee.setEpwd(pwd);
		employee.setEtel(phone);
		employee.setEid(id);
		employeeService.updateEmployeeMsg(employee);
		map.put("key_msg", "<script>alert('修改成功!')</script>");
		return "forward:loadMyMsgList";
	}
}
