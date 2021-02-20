package com.labbol.product.dataferry.core.data.operate;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.jdbc.DataBaseOperationType;

import com.labbol.product.dataferry.core.data.DataObject;

/**
 * 数据对象源操作实体
 * 
 * @author PengFei
 * @date 2021年2月19日上午9:59:49
 */
public class DataObjectOperateResultEntry {

	private final DataObject dataObject;

	@Nullable
	private DataBaseOperationType dataBaseOperationType;

	@Nullable
	private Object primaryValue;

	public DataObjectOperateResultEntry(DataObject dataObject) {
		this.dataObject = dataObject;
	}

	public DataObject getDataObject() {
		return dataObject;
	}

	public DataBaseOperationType getDataBaseOperationType() {
		return dataBaseOperationType;
	}

	public void setDataBaseOperationType(DataBaseOperationType dataBaseOperationType) {
		this.dataBaseOperationType = dataBaseOperationType;
	}

	public Object getPrimaryValue() {
		return primaryValue;
	}

	public void setPrimaryValue(Object primaryValue) {
		this.primaryValue = primaryValue;
	}

}
