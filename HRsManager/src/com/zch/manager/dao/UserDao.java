package com.zch.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zch.manager.bean.Resume;
import com.zch.manager.bean.User;

/**
*Created by ch.zhang on 2017年9月26日 下午7:03:12
*/
public interface UserDao {
	//添加注册的用户信息
	int addUser(User user);

	//判断要注册的用户名是否已被注册
	List<User> findUser(User user);
    
	User validateLogin(User user);

	int saveResume(Resume resume);

	User getUserMsg(String uname);

	List<Resume> getResumeMsg(String uname);

	List<User> getUserObjByUname(String uname);

	int updateUserMsg(User user);

	User getHasIdMsg(User user);

	Resume getUserResumeById(Integer id);

	int updateResumeMsg(Resume resume);

	void updateUserResumeMsg(@Param("aid")Integer aid, @Param("rid")Integer rid);

	void updateUserResumeMsg(Integer id);

	List<Resume> getResumeMsg2(Integer id);

	void updateUserResumeMsg2(Resume resume);

	Resume getResumeMsgByRid(Integer id);

	void updateUserMsgByUid(@Param("uid")Integer uid,@Param("admint")Integer admint);


}
