package com.labbol.product.dataferry.core.ferry;

import java.util.List;

import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperateResult;

public class DataFerryResult {

	private final List<DataObjectSource> dataObjectSources;

	private List<DataObjectSourceOperateResult> dataObjectSourceOperateResults;

	public DataFerryResult(List<DataObjectSource> dataObjectSources) {
		this.dataObjectSources = dataObjectSources;
	}

	public List<DataObjectSource> getDataObjectSources() {
		return dataObjectSources;
	}

	public List<DataObjectSourceOperateResult> getDataObjectSourceOperateResults() {
		return dataObjectSourceOperateResults;
	}

	public void setDataObjectSourceOperateResults(List<DataObjectSourceOperateResult> dataObjectSourceOperateResults) {
		this.dataObjectSourceOperateResults = dataObjectSourceOperateResults;
	}

}
