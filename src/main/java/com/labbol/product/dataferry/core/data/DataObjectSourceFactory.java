package com.labbol.product.dataferry.core.data;

/**
 * 数据对象源工厂
 */
public interface DataObjectSourceFactory {

	/**
	 * @return 数据对象源
	 */
	DataObjectSource create();

	/**
	 * @param tableName  表名称
	 * @param primaryKey 主键
	 * @return 数据对象源
	 */
	default DataObjectSource create(String tableName, String primaryKey) {
		DataObjectSource dataObjectSource = create();
		dataObjectSource.setTableName(tableName);
		dataObjectSource.setPrimaryKey(primaryKey);
		return dataObjectSource;
	}

}
