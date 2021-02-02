package com.labbol.product.dataferry.core.data;

import java.util.List;

import org.yelong.core.annotation.Nullable;

import com.labbol.product.dataferry.core.constants.DefaultPropertyPool;

/**
 * 数据对象源
 */
public interface DataObjectSource {

	/**
	 * @return 数据对象对应的表名称
	 */
	@Nullable
	String getTableName();

	/**
	 * @param tableName 表名称
	 */
	void setTableName(String tableName);

	/**
	 * @return 对象的主键
	 */
	@Nullable
	String getPrimaryKey();

	/**
	 * @param primaryKey 主键名称
	 */
	void setPrimaryKey(String primaryKey);

	/**
	 * 没有设置该属性时返回 {@link DefaultPropertyPool#dataObjectOperationType}
	 * 
	 * @return 数据对象操作类型
	 * @see DefaultPropertyPool#dataObjectOperationType
	 */
	DataObjectOperationType getDataObjectOperationType();

	/**
	 * @param dataObjectOperationType 数据对象操作类型
	 */
	void setDataObjectOperationType(DataObjectOperationType dataObjectOperationType);

	/**
	 * @param dataObjectOperationType 数据对象操作类型字符串
	 */
	default void setDataObjectOperationType(String dataObjectOperationType) {
		setDataObjectOperationType(DataObjectOperationType.valueOf(dataObjectOperationType.toUpperCase()));
	}

	/**
	 * 
	 * @date 2021年1月20日下午3:13:55
	 * @return
	 */
	DataObjectSourceProperties getDataObjectSourceProperties();

	/**
	 * @date 2021年1月20日下午3:13:55
	 * @param dataObjectSourceProperties 数据对象源属性
	 */
	void setDataObjectSourceProperties(DataObjectSourceProperties dataObjectSourceProperties);

	// ==================================================数据对象==================================================

	/**
	 * 添加数据对象
	 * 
	 * @param dataObject 数据对象
	 */
	void addDataObject(DataObject dataObject);

	/**
	 * 添加数据对象
	 * 
	 * @param dataObjects 数据对象集合
	 */
	default void addDataObjects(List<? extends DataObject> dataObjects) {
		dataObjects.forEach(this::addDataObject);
	}

	/**
	 * 创建数据对象。这仅会创建一个数据对象，且数据对象的数据对象源为当前对象，而不会添加到当前数据对象源的数据对象集合中
	 * 
	 * @return 数据对象
	 */
	DataObject createDataObject();

	/**
	 * @return 对象所有的属性集合
	 */
	List<? extends DataObject> getDataObjects();

	/**
	 * 是否不存在数据对象
	 * 
	 * @author PengFei
	 * @date 2021年1月26日下午10:05:00
	 * @return <code>true</code> 不存在数据对象
	 */
	boolean isEmpty();

}
