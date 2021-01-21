package com.labbol.product.dataferry.core.ferry.impl;

import java.io.InputStream;
import java.util.List;

import org.yelong.core.model.service.SqlModelService;

import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperateException;
import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperator;
import com.labbol.product.dataferry.core.ferry.DataFerry;
import com.labbol.product.dataferry.core.ferry.DataFerryResult;
import com.labbol.product.dataferry.core.resolve.DataFileResolveException;
import com.labbol.product.dataferry.core.resolve.DataFileResolver;

/**
 * 默认的数据库摆渡实现
 */
public class DefaultDataFerry implements DataFerry {

	private DataFileResolver dataFileResolver;

	private DataObjectSourceOperator dataObjectSourceOperator;

	public DefaultDataFerry(DataFileResolver dataFileResolver, DataObjectSourceOperator dataObjectSourceOperator) {
		this.dataFileResolver = dataFileResolver;
		this.dataObjectSourceOperator = dataObjectSourceOperator;
	}

	@Override
	public DataFerryResult ferry(InputStream inputStream, SqlModelService modelService)
			throws DataFileResolveException, DataObjectSourceOperateException {
		List<DataObjectSource> dataObjectSources = dataFileResolver.resolve(inputStream);
		// 一个文件具有事务功能
		modelService.doOperation(() -> {
			for (DataObjectSource dataObjectSource : dataObjectSources) {
				dataObjectSourceOperator.operate(dataObjectSource, modelService);
			}
		});
		return new DataFerryResult(dataObjectSources);
	}

}
