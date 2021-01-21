package com.labbol.product.dataferry.core.data.operate.impl;

import java.util.Objects;

import org.yelong.core.jdbc.dialect.Dialect;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.service.SqlModelService;
import org.yelong.core.model.sql.ModelSqlFragmentFactory;

import com.labbol.product.dataferry.core.data.DataObjectOperationType;
import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 数据对象源操作配置
 */
public class DataObjectSourceOperateProperties {

	private final DataObjectSource dataObjectSource;

	private final SqlModelService modelService;

	public DataObjectSourceOperateProperties(DataObjectSource dataObjectSource, SqlModelService modelService) {
		this.dataObjectSource = Objects.requireNonNull(dataObjectSource);
		this.modelService = Objects.requireNonNull(modelService);
	}

	public DataObjectSource getDataObjectSource() {
		return dataObjectSource;
	}

	public SqlModelService getModelService() {
		return modelService;
	}

	public ModelSqlFragmentFactory getModelSqlFragmentFactory() {
		return getModelConfiguration().getModelSqlFragmentFactory();
	}

	public ModelConfiguration getModelConfiguration() {
		return modelService.getModelConfiguration();
	}

	public String getTableName() {
		return dataObjectSource.getTableName();
	}

	public String getPrimaryKey() {
		return dataObjectSource.getPrimaryKey();
	}

	public DataObjectOperationType getDataObjectOperationType() {
		return dataObjectSource.getDataObjectOperationType();
	}

	public Dialect getDialect() {
		return getModelConfiguration().getDialect();
	}

}
