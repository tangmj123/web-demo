package com.tangmj.demo.base.example;

public class BaseExample {

	private String pageClause;
	/**
	 * 设置分页参数
	 * @param currentPage
	 * @param pageSize
	 */
	protected void setPageCause(int currentPage, int pageSize){
		if(currentPage <= 0) currentPage = 1;
		if(   pageSize <= 0) pageSize    = 10;
		pageClause = "limit " + (currentPage-1)*pageSize +" , " +pageSize;
	}

	public String getPageClause() {
		return pageClause;
	}

	public void setPageClause(String pageClause) {
		this.pageClause = pageClause;
	}
}
