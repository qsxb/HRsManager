package com.zch.manager.service;

import java.util.List;

import com.zch.manager.bean.Resume;
import com.zch.manager.bean.User;

/**
 * Created by ch.zhang on 2017年9月26日 下午7:03:23
 */
public interface UserService {
	// 用户注册
	boolean registUser(User user);

	// 判断用户名是否注册
	boolean isExistUser(User user);

	// 判断用户名登录成功页面
	boolean validateLogin(User user);

	// 用户填写简历并保存到数据库中
	boolean saveResume(Resume resume);

	// 用户查看个人信息的操作
	User getUserMsg(String uname);

	// 拿到当前登录用户的简历信息
	List<Resume> getResumeMsg(String encodeUname);

	// 激活邮件的时候通过名字得到user对象来重新设置激活状态值
	List<User> getUserObjByUname(String uname);

	// 更新user表中的属性状态来判断是否激活成功
	boolean updateUserMsg(User user);

	// 得到带有id 名字的 用户信息
	User getHasIdMsg(User user);

	// 根据id进行修改信息回显
	Resume modifyUserResumeById(Integer id);

	// 修改用户简历信息
	boolean updateUserResumeMsg(Resume resume);

	void updateUserResumeMsg(Integer aid, Integer rid);

	// 管理员阅读了简历,把该简历信息读取状态更改为已读状态
	void updateUserResumeMsg(Integer id);

	// 用户和简历是一对一的关系,通过用户id拿到简历表的信息
	List<Resume> getResumeMsg(Integer id);

	// 更新简历表中被管理员读取的状态
	void updateUserResumeMsg2(Resume resume);

	Resume getResumeMsgByRid(Integer id);

	// 用户接受面试,并修改admint的状态为1
	void updateUserMsgByUid(int uid);

}
