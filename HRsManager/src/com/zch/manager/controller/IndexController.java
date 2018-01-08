package com.zch.manager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zch.manager.bean.Advertise;
import com.zch.manager.service.IndexService;
import com.zch.manager.util.PageBean;

/**
 * Created by ch.zhang on 2017年9月26日 下午5:14:56
 */
@Controller
public class IndexController {
	@Autowired
	private IndexService indexService;

	// 我要让主页面一加载进显示招聘信息在左边 loadIndex
	@RequestMapping("/loadIndex")
	public String loadIndex(Map<String, Object> map, HttpServletResponse resp, Integer page) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		System.out.println("我进来了.....");
		if (page == null) {
			page = 1;
		}
		List<Advertise> list = indexService.getAdvertiseMsg();
		map.put("key_advertiseMsg", list);
		return "main";
	}
}
