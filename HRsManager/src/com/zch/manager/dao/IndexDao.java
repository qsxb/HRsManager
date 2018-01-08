package com.zch.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zch.manager.bean.Advertise;

/**
*Created by ch.zhang on 2017年9月26日 下午5:18:13
*/
public interface IndexDao {

	List<Advertise> getAdvertiseMsg1();

	int getTotalCountAdvertiseMsg();

	List<Advertise> getAdvertisetMsgList(@Param("begin")int begin,@Param("pagesize")int pagesize);

}
