package com.zch.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zch.manager.bean.Advertise;
import com.zch.manager.bean.Resume;
import com.zch.manager.bean.User;
import com.zch.manager.service.AdminService;
import com.zch.manager.service.UserService;
import com.zch.manager.util.MailUitls;

/**
 * Created by ch.zhang on 2017年9月26日 下午7:02:49
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;

	/*
	 * 新用户注册部分(游客注册只需要用户名密码即可)
	 */
	@RequestMapping("/nowRegist")
	public String nowRegist(HttpServletRequest req, Map<String, Object> map,
			@RequestParam(name = "registName") String registName, @RequestParam(name = "registPwd") String registPwd,
			@RequestParam(name = "email") String email, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		String encodeRegistName = "";
		encodeRegistName = new String(registName.getBytes("ISO-8859-1"), "utf-8");
		User user = new User(encodeRegistName, registPwd);
		user.setUstate(0);
		user.setUemail(email);
		// 先判断用户名是否被注册
		boolean isExistflag = userService.isExistUser(user);
		System.out.println("isExistflag" + isExistflag);
		if (isExistflag == false) {
			map.put("key_existUserError", "用户名已被注册");
			return "forward:loadIndex";
		}
		boolean flag = userService.registUser(user);
		if (flag == true) {
			req.getSession().getServletContext().setAttribute("application_username", user.getUname());
			MailUitls.sendMail(user.getUemail(), 0);
			// 注册成功
			// resp.getWriter().println("注册成功");
			map.put("regist_success_msg", "注册成功");
			return "forward:msg.jsp";
		}
		return null;
		// return "forward:loadIndex";
	}
	/* 用户登录部分,判断用户名密码是否正确即可 */

	@RequestMapping("/nowLogin")
	public String nowLogin(Map<String, Object> map, @RequestParam(name = "loginName") String loginName,
			@RequestParam(name = "loginPwd") String loginPwd, HttpServletRequest req)
					throws UnsupportedEncodingException {
		String encodLoginName = "";
		encodLoginName = new String(loginName.getBytes("ISO-8859-1"), "utf-8");
		User user = new User(encodLoginName, loginPwd);
		req.getSession().setAttribute("userName_in_session", user.getUname());
		boolean flag = userService.validateLogin(user);
		if (flag == true) {
			System.out.println("登录成功!");
			User u = userService.getHasIdMsg(user);
			req.getSession().setAttribute("user_in_session", u);
			map.put("login_success_msg", "登录成功!");
			return "forward:loginSuccessMsg.jsp";
		}
		if (flag == false) {
			return "forward:loadIndex";
		}
		return null;
	}

	/*
	 * 游客注册完后可以填写简历,可投递简历,接受面试邀请信息,确认面试则成为了员工
	 */
	// 续加功能部分,因为具体的招聘信息是管理员发布的,求职者点击申请职位即可填写简历并投递出去
	// 只要某个具体的招聘信息被点击申请了职位就把招聘表中招聘信息的a_sataus状态的值+1
	@RequestMapping("/applyFor")
	public String applyFor(Map<String, Object> map, HttpServletRequest req, HttpServletResponse resp,
			@RequestParam(name = "id") Integer id) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		System.out.println("我点了申请职位");
		// 首先得判断游客(用户有没有登录,只有登陆了才能点击申请职位)
		User user = (User) req.getSession().getAttribute("user_in_session");
		System.out.println("user信息---" + user);
		PrintWriter out = resp.getWriter();
		PrintWriter out1 = resp.getWriter();
		// 跳转到填写简历信息页面中并且提交就相当于申请了职位
		if (user == null) {
			out.print("<script language='javascript'>alert('请先登录');window.location.href='loadIndex';</script>");
		}
		if (user != null && user.getUstate() == 0) {
			out1.print(
					"<script language='javascript'>alert('你还未激活,请去邮箱激活');window.location.href='loadIndex';</script>");
		}
		if (user != null && user.getUstate() == 1) {
			System.out.println("我点了");
			// 在可以申请职位的前提下设置招聘信息的状态看是否已经被投递了
			// 这里做修改招聘信息的状态通过招聘信息的id
			// 根据id信息得到招聘信息再把新的字段属性更新进数据库中
			Advertise advertise = adminService.getAdvertiseMsgById(id);
			System.out.println("advertise的状态----" + advertise);
			// 把申请点击的招聘信息中的部门信息,职位信息加载到简历表中
			map.put("key_advertise", advertise);
			return "writeResume";
		}
		// return "forward:loadIndex";
		return null;
	}

	/* 用户填写简历并保存到数据库中 */
	@RequestMapping("/saveResume")
	public String saveResume(Map<String, Object> map, @RequestParam(name = "username") String username,
			@RequestParam(name = "age") int age, @RequestParam(name = "gender") String sex,
			@RequestParam(name = "education") String education, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email, @RequestParam(name = "polstatus") String polstatus,
			@RequestParam(name = "deptName") String deptName, @RequestParam(name = "jobName") String jobName,
			@RequestParam(name = "rsalary") double rsalary) throws UnsupportedEncodingException {
		String encodeUsername = new String(username.getBytes("ISO-8859-1"), "utf-8");
		String encodeSex = new String(sex.getBytes("ISO-8859-1"), "utf-8");
		String encodeEducation = new String(education.getBytes("ISO-8859-1"), "utf-8");
		String encodePolstatus = new String(polstatus.getBytes("ISO-8859-1"), "utf-8");
		String encodeDeptName = new String(deptName.getBytes("ISO-8859-1"), "utf-8");
		String encodeJobName = new String(jobName.getBytes("ISO-8859-1"), "utf-8");
		// 根据用户名查找用户信息(得到用户id)
		User user = userService.getUserMsg(encodeUsername);
		System.out.println("user--" + user);
		// 保存简历信息到简历表中,我是一保存就相当于提交的,所以设置读取状态为未读状态
		Resume resume = new Resume(encodeUsername, age, encodeSex, encodeEducation, tel, email, encodePolstatus, user);
		System.out.println("resume的信息" + resume.getRreadStatus() + "--" + resume.getRfaceStatus());
		resume.setDeptName(encodeDeptName);
		resume.setJobName(encodeJobName);
		resume.setRsalary(rsalary);
		boolean flag = userService.saveResume(resume);
		System.out.println("简历表的flag--" + flag);
		if (flag == true) {
			// 申请职位(填写简历完成,跳转界面)
			return "user/userMsg";
		}
		if (flag == false) {
			return "forward:loadIndex";
		}
		return null;
	}
	/*
	 * //个人查看建立的时候还没有填写就查看信息(报异常,在此解决)
	 * 
	 * @RequestMapping("/nowWriteResume") public String nowWriteResume(){ return
	 * "redirect:applyFor"; }
	 */

	// 首页上点游客信息进入
	@RequestMapping("/intoMyMsg")
	public String intoMyMsg() {
		return "user/userMsg";
	}

	// 这里我准备做一个用户申请简历之后进入我的个人中心页面
	// 程序加载frameset top界面,因为配置了视图渲染器,加上jsp文件放在web-inf下面
	// 必须经过controller跳转
	// 加载frame top
	@RequestMapping("/userloadTop")
	public String loadTop() {
		return "user/top";
	}

	// 加载frame left
	@RequestMapping("/userloadLeft")
	public String loadLeft() {
		return "user/left";
	}

	// 加载frame welcome
	@RequestMapping("/userloadWelcome")
	public String loadWelcome() {
		return "user/welcome";
	}

	// 个人中心那一处点击我的基本信息进行数据显示
	@RequestMapping("/userloadMsg")
	public String userloadMsg(Map<String, Object> map, @RequestParam(name = "uname") String uname)
			throws UnsupportedEncodingException {
		// 获取数据库中我的简历的基本信息
		String encodUname = new String(uname.getBytes("ISO-8859-1"), "utf-8");
		System.out.println("uname--" + encodUname);
		User user = userService.getUserMsg(encodUname);
		System.out.println("查出来的user信息---" + user);
		map.put("key_user", user);
		return "user/userlist";
	}

	// 个人中心那一处点击我的简历信息进行数据显示
	@RequestMapping("/userloadResumeMsg")
	public String userloadResumeMsg(Map<String, Object> map, @RequestParam(name = "uname") String uname)
			throws UnsupportedEncodingException {
		String encodeUname = new String(uname.getBytes("ISO-8859-1"), "UTF-8");
		// 获取数据库中的简历信息(前提是当前用户登陆的简历信息)
		Resume resume = null;
		List<Resume> list = userService.getResumeMsg(encodeUname);
		if (list.size() != 0) {
			resume = list.get(0);
		}
		// 这里还要做一个有没有填写简历,简历信息存不存在的判断
		System.out.println("我的简历信息---" + resume);
		if (resume == null) {
			return "user/userEmptyResumelist";
		}
		map.put("key_resume", resume);
		return "user/userResumelist";
	}
	// 个人中心那一处点击我的消息通知功能

	// 用户激活的方法,根据激活码进行用户查询,如果不为空修改用户状态为1,反之code被篡改
	@RequestMapping("/userActivation")
	public String userActivation(HttpServletRequest req, Map<String, Object> map) {
		// 获取激活状态
		String uname = (String) req.getServletContext().getAttribute("application_username");
		System.out.println("uname----" + uname);
		// 通过这个名字查找数据库,得到user对象.然后通过它设置激活状态
		List<User> list = userService.getUserObjByUname(uname);
		User user = list.get(0);
		user.setUstate(1);
		System.out.println("激活后的user信息--" + user);
		// 然后再修改user表中的状态属性
		boolean flag = userService.updateUserMsg(user);
		System.out.println("激活状态flag--" + flag);
		if (flag == true) {
			// 修改状态成功,激活成功
			map.put("key_activationSuccess", "激活成功,请前去主页登录");
			return "forward:userActivationSuccess.jsp";
		}
		if (flag == false) {
			// 激活失败
			return "forward:loadIndex";
		}
		return null;
	}

	// 用户修改自己的简历信息(根据id来修改)
	@RequestMapping("/userResumeEdit")
	public String userResumeEdit(@RequestParam(name = "id") Integer id, Map<String, Object> map) {
		// 跳转到user/userResumeEdit.jsp页面进行信息回显,做修改操作(根据id来查询数据库)
		Resume resume = userService.modifyUserResumeById(id);
		System.out.println("拿到修改回显的简历信息--" + resume);
		map.put("key_recyle_resumeMsg", resume);
		return "user/userResumeEdit";
	}

	// 用户修改简历的操作
	@RequestMapping("/updateUserResume")
	public String updateUserResume(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name,
			@RequestParam(name = "age") int age, @RequestParam(name = "sex") String sex,
			@RequestParam(name = "education") String education, @RequestParam(name = "phone") String phone,
			@RequestParam(name = "email") String email, @RequestParam(name = "polstatus") String polstatus)
					throws UnsupportedEncodingException {
		String encodeName = new String(name.getBytes("ISO-8859-1"), "utf-8");
		String encodeSex = new String(sex.getBytes("ISO-8859-1"), "utf-8");
		String encodeEducation = new String(education.getBytes("ISO-8859-1"), "utf-8");
		String encodePolstatus = new String(polstatus.getBytes("ISO-8859-1"), "utf-8");
		Resume resume = new Resume(id, encodeName, age, encodeSex, encodeEducation, phone, email, encodePolstatus);
		boolean flag = userService.updateUserResumeMsg(resume);
		System.out.println("修改后的flag--" + flag);
		/*
		 * if(flag == true){ //修改成功,跳转到显示我的简历信息页面 return
		 * "forward:userloadResumeMsg?uname="+encodeName; }
		 */
		return "user/updateResumeMsgSuccess";
	}

}
