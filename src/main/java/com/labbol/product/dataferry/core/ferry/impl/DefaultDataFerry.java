package com.labbol.product.dataferry.core.ferry.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yelong.core.model.service.SqlModelService;

import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperateException;
import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperateResult;
import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperator;
import com.labbol.product.dataferry.core.ferry.DataFerry;
import com.labbol.product.dataferry.core.ferry.DataFerryResult;
import com.labbol.product.dataferry.core.resolve.DataFileResolveException;
import com.labbol.product.dataferry.core.resolve.DataFileResolver;

/**
 * 默认的数据库摆渡实现
 */
public class DefaultDataFerry implements DataFerry {

	protected DataFileResolver dataFileResolver;

	protected DataObjectSourceOperator dataObjectSourceOperator;

	public DefaultDataFerry(DataFileResolver dataFileResolver, DataObjectSourceOperator dataObjectSourceOperator) {
		this.dataFileResolver = dataFileResolver;
		this.dataObjectSourceOperator = dataObjectSourceOperator;
	}

	@Override
	public DataFerryResult ferry(InputStream inputStream, SqlModelService modelService)
			throws DataFileResolveException, DataObjectSourceOperateException {
		List<DataObjectSource> dataObjectSources = dataFileResolver.resolve(inputStream);
		List<DataObjectSourceOperateResult> dataObjectSourceOperateResults = new ArrayList<>(dataObjectSources.size());
		// 一个文件具有事务功能
		modelService.doOperation(() -> {
			for (DataObjectSource dataObjectSource : dataObjectSources) {
				DataObjectSourceOperateResult dataObjectSourceOperateResult = dataObjectSourceOperator
						.operate(dataObjectSource, modelService);
				dataObjectSourceOperateResults.add(dataObjectSourceOperateResult);
			}
		});
		DataFerryResult dataFerryResult = new DataFerryResult(dataObjectSources);
		dataFerryResult.setDataObjectSourceOperateResults(dataObjectSourceOperateResults);
		return dataFerryResult;
	}

}
