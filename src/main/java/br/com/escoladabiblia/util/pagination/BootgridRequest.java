package br.com.escoladabiblia.util.pagination;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;

public class BootgridRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int current;

	private int rowCount;

	private String searchPhrase;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public PageRequest getPageRequest() {
		return new PageRequest(current - 1, rowCount);
	}

}
