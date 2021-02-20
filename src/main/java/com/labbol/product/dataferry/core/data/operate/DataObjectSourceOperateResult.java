package com.labbol.product.dataferry.core.data.operate;

import java.util.ArrayList;
import java.util.List;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 数据对象源操作结果
 * 
 * @author PengFei
 * @date 2021年2月19日上午9:57:36
 */
public class DataObjectSourceOperateResult {

	private final DataObjectSource dataObjectSource;

	/**
	 * 数据对象操作结果实体
	 */
	private List<DataObjectOperateResultEntry> dataObjectOperateResultEntrys = new ArrayList<>();

	/**
	 * 数据对象源操作结果
	 */
	private List<DataObjectSourceOperateResult> dataObjectSourceOperateResults = new ArrayList<>();

	public DataObjectSourceOperateResult(DataObjectSource dataObjectSource) {
		this.dataObjectSource = dataObjectSource;
	}

	// ==================================================数据对象==================================================

	public void addDataObjectOperateResultEntry(DataObjectOperateResultEntry dataObjectOperateResultEntry) {
		this.dataObjectOperateResultEntrys.add(dataObjectOperateResultEntry);
	}

	public void addDataObjectOperateResultEntrys(List<DataObjectOperateResultEntry> dataObjectOperateResultEntrys) {
		this.dataObjectOperateResultEntrys.addAll(dataObjectOperateResultEntrys);
	}

	public List<DataObjectOperateResultEntry> getDataObjectOperateResultEntrys() {
		return dataObjectOperateResultEntrys;
	}

	// ==================================================数据对象源==================================================

	public void addDataObjectSourceOperateResult(DataObjectSourceOperateResult dataObjectSourceOperateResult) {
		this.dataObjectSourceOperateResults.add(dataObjectSourceOperateResult);
	}

	public void addDataObjectSourceOperateResults(List<DataObjectSourceOperateResult> dataObjectSourceOperateResults) {
		this.dataObjectSourceOperateResults.addAll(dataObjectSourceOperateResults);
	}

	public List<DataObjectSourceOperateResult> getDataObjectSourceOperateResults() {
		return dataObjectSourceOperateResults;
	}

	// ==================================================get/set==================================================

	public DataObjectSource getDataObjectSource() {
		return dataObjectSource;
	}

}
