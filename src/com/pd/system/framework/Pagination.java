package com.pd.system.framework;

import java.util.List;

//封装页面显示逻辑
public class Pagination {

	private int total;// 总共的数据量

	private int pageSize;// 每页显示多少条

	private int totalPage;// 共有多少页

	private int index;// 当前是第几页

	private String path;// 连接路径
	
	private List pagelist;//分页数据

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getIndex() {
		return index;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return (this.total + this.pageSize - 1) / this.pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getPageDisplay() {
		StringBuilder displayInfo = new StringBuilder("<div>");
		if (index == 0 || pageSize == 0 || total == 0) {
			displayInfo.append("<span>没有分页的信息</span>");
		} else {
			displayInfo.append("第&nbsp;<span>").append(index).append("</span>&nbsp;页");
			displayInfo.append("&nbsp;/&nbsp;");
			displayInfo.append("共&nbsp;<span>")	.append(totalPage).append("</span>&nbsp;页");
			displayInfo.append("&nbsp;	");
			displayInfo.append("【共&nbsp;<span>").append(total).append("</span>&nbsp;条，每页&nbsp;<span>")	.append(pageSize).append("</span>&nbsp;条】");
			
			displayInfo.append("<a ").append(index <= 1 ? "class='page_current'":"");
			if (index > 1) {
				displayInfo.append("onclick='javascript:page_query(1)'");
			}
			displayInfo.append(">&lt;&lt;首页</a>&nbsp;");
			displayInfo.append("<a ").append(index <= 1 ? "class='page_current'":"");
			if (index > 1) {
				displayInfo.append("onclick='javascript:page_query(").append(index-1).append(")'");
			}
			displayInfo.append(">&lt;上一页</a>&nbsp;");
			displayInfo.append("<a ").append(index >= totalPage ? "class='page_current'":"");
			if (index < totalPage) {
				displayInfo.append("onclick='javascript:page_query(").append(index+1).append(")'");
			}
			displayInfo.append(">下一页&gt;</a>&nbsp;");
			displayInfo.append("<a ").append(index >= totalPage ? "class='page_current'":"");
			if (index < totalPage) {
				displayInfo.append("onclick='javascript:page_query(").append(totalPage).append(")'");
			}
			displayInfo.append(">尾页&gt;&gt;</a>");
//			displayInfo.append("转到<input class=\"page_query_input\" onkeyup=\"pageCheckNumber(this)\" maxlength=\"5\" />页<a onclick=\"javascript:pageQueryGo(this,${page.index},${page.beginPage},${page.totalPage})\">GO</a>");
			displayInfo.append("<span>&nbsp;&nbsp;&nbsp;</span>");
		}
		displayInfo.append("</div>");
		return displayInfo.toString();
	}

	/**
	 * 分页
	 * 
	 * @param total
	 *            总记录数
	 * @param pageSize
	 *            每页多少条
	 * @param index
	 *            当前页
	 * @param path
	 *            action路径
	 * 
	 * @author wangwei
	 */
	public Pagination(int total, int pageSize, int index, String path) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.index = index;
		this.path = path;
		this.totalPage=getTotalPage();
	}

	/**
	 * 
	 * 分页对象
	 * 
	 * @param total
	 *            总记录数
	 * @param pageSize
	 * @param index
	 *            当前页
	 * 
	 * @author wangwei
	 * @modify wzm
	 */
	public Pagination(int total, int pageSize, int index) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.index = index;
		this.totalPage=getTotalPage();
	}

	/**
	 * 
	 * 分页对象
	 * 
	 * @param total
	 *            总记录数
	 * @param index
	 *            当前页
	 * 
	 * @author wangwei
	 */
	public Pagination(int total, int index) {
		super();
		this.total = total;
		this.pageSize = 20;
		this.index = index;
		this.totalPage=getTotalPage();
	}

	public Pagination() {
		super();
	}

	public List getPagelist() {
		return pagelist;
	}

	public void setPagelist(List pagelist) {
		this.pagelist = pagelist;
	}

}
