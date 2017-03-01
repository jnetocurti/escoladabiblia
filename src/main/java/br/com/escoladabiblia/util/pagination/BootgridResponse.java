package br.com.escoladabiblia.util.pagination;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public class BootgridResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int current;

	private final long rowCount;

	private final List<T> rows;

	private final long total;

	public BootgridResponse(Page<T> page) {

		this.current = page.getNumber() + 1;

		this.rowCount = page.getTotalElements();

		this.rows = page.getContent();

		this.total = page.getTotalElements();
	}

	public int getCurrent() {
		return current;
	}

	public long getRowCount() {
		return rowCount;
	}

	public List<T> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

}
