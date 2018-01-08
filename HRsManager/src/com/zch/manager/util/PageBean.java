package com.zch.manager.util;

import java.util.List;

/**
 * Created by ch.zhang on 2017年9月26日 下午2:45:53
 */
public class PageBean<T> {
	private int page; // 页数
	private int totalCount;// 总记录数
	private int totalPage;// 总页数
	private int pagesize;// 每页显示的记录数
	private List<T> list;// 每页显示的数据集合

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", pagesize="
				+ pagesize + ", list=" + list + "]";
	}

}
