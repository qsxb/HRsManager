package com.zch.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zch.manager.bean.Advertise;
import com.zch.manager.bean.Dept;
import com.zch.manager.dao.IndexDao;
import com.zch.manager.service.IndexService;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年9月26日 下午5:18:35
 */
public class IndexServiceImpl implements IndexService {
	@Autowired
	private IndexDao indexDao;

	public List<Advertise> getAdvertiseMsg() {
		return indexDao.getAdvertiseMsg1();
	}

	public PageBean<Advertise> getAdvertiseMsgByPage(Integer page) {
		PageBean<Advertise> pb = new PageBean<Advertise>();
		// 设置当前页
		pb.setPage(page);
		// 设置总记录数
		int totalCount = indexDao.getTotalCountAdvertiseMsg();
		pb.setTotalCount(totalCount);
		// 设置每页显示条数
		int pagesize = 3;
		pb.setPagesize(pagesize);
		// 设置总页数
		int totalPage = 0;
		totalPage = totalCount % pagesize == 0 ? (totalCount / pagesize) : (totalCount / pagesize) + 1;
		pb.setTotalPage(totalPage);
		// 设置每页显示的数据集合
		int begin = (page - 1) * pagesize;
		List<Advertise> list = indexDao.getAdvertisetMsgList(begin, pagesize);
		pb.setList(list);
		return pb;
	}
}
