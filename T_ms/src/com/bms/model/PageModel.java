package com.bms.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类：装载某一种实体的集合，并进行分页属性的存储
 */
public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	// 成员变量
	// 某一种实体的集合
	private List<T> list;
	// 每页显示多少条记录
	private Integer pageSize;
	// 当前是第几页
	private Integer pageNo;
	// 总记录条数
	private Integer totalCount;

	// 成员方法
	// get/set方法
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	// 自定义成员方法
	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public Integer getTotalPages() {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize + 1;
		}
	}

	/**
	 * 获取首页
	 * 
	 * @return
	 */
	public Integer getFirstPage() {
		return 1;
	}

	/**
	 * 获取上一页
	 * 
	 * @return
	 */
	public Integer getPreviousPage() {
		if (pageNo <= 1) {
			return 1;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * 获取下一页
	 * @return
	 */
	public Integer getNextPage() {
		if (pageNo >= this.getTotalPages()) {
			return this.getTotalPages();
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 获取末页
	 * 
	 * @return
	 */
	public Integer getLastPage() {
		return this.getTotalPages();
	}
}