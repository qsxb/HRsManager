package com.zch.manager.service;

import java.util.List;

import com.zch.manager.bean.Advertise;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年9月26日 下午5:18:24
 */
public interface IndexService {

	List<Advertise> getAdvertiseMsg();

	// 分页显示招聘信息
	PageBean<Advertise> getAdvertiseMsgByPage(Integer page);

}
