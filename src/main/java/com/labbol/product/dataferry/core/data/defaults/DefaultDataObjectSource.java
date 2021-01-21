package com.labbol.product.dataferry.core.data.defaults;

import com.labbol.product.dataferry.core.constants.DefaultPropertyPool;
import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectOperationType;

/**
 * 默认的数据对象源
 */
public class DefaultDataObjectSource extends AbstractDataObjectSource {

	private String tableName;

	private String primaryKey;

	private DataObjectOperationType dataObjectOperationType;

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public DataObjectOperationType getDataObjectOperationType() {
		if (null == dataObjectOperationType) {
			return DefaultPropertyPool.dataObjectOperationType;
		}
		return dataObjectOperationType;
	}

	@Override
	public void setDataObjectOperationType(DataObjectOperationType dataObjectOperationType) {
		this.dataObjectOperationType = dataObjectOperationType;
	}

	@Override
	public DataObject createDataObject() {
		return new DefaultDataObject(this);
	}

}
