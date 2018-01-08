package com.zch.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zch.manager.bean.Resume;
import com.zch.manager.bean.User;
import com.zch.manager.dao.UserDao;
import com.zch.manager.service.UserService;

/**
 * Created by ch.zhang on 2017年9月26日 下午7:03:36
 */
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public boolean registUser(User user) {
		int result = userDao.addUser(user);
		if (result == 0) {
			return false;
		}
		return true;
	}

	public boolean isExistUser(User user) {
		List<User> list = userDao.findUser(user);
		System.out.println(list);
		if (list.size() != 0) {
			return false;
		}
		return true;
	}

	public boolean validateLogin(User user) {
		User u = userDao.validateLogin(user);
		if (u == null) {
			return false;
		}
		return true;
	}

	public boolean saveResume(Resume resume) {
		resume.setRreadStatus("未读");
		resume.setRfaceStatus("未面试");
		int result = userDao.saveResume(resume);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public User getUserMsg(String uname) {

		return userDao.getUserMsg(uname);
	}

	public List<Resume> getResumeMsg(String encodeUname) {

		return userDao.getResumeMsg(encodeUname);
	}

	public List<User> getUserObjByUname(String uname) {
		return userDao.getUserObjByUname(uname);
	}

	public boolean updateUserMsg(User user) {
		int result = userDao.updateUserMsg(user);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public User getHasIdMsg(User user) {
		return userDao.getHasIdMsg(user);
	}

	public Resume modifyUserResumeById(Integer id) {
		Resume resume = userDao.getUserResumeById(id);
		return resume;
	}

	public boolean updateUserResumeMsg(Resume resume) {
		int result = userDao.updateResumeMsg(resume);
		if (result != 0) {
			return true;
		}
		return false;
	}

	public void updateUserResumeMsg(Integer aid, Integer rid) {
		userDao.updateUserResumeMsg(aid, rid);
	}

	public void updateUserResumeMsg(Integer id) {
		userDao.updateUserResumeMsg(id);
	}

	public List<Resume> getResumeMsg(Integer id) {
		return userDao.getResumeMsg2(id);
	}

	public void updateUserResumeMsg2(Resume resume) {
		resume.setRreadStatus("已读");
		userDao.updateUserResumeMsg2(resume);
	}

	public Resume getResumeMsgByRid(Integer id) {
		return userDao.getResumeMsgByRid(id);
	}

	public void updateUserMsgByUid(int uid) {
		int admint = 1;
		userDao.updateUserMsgByUid(uid, admint);
	}
}
