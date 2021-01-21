package com.labbol.product.dataferry.core.data;

/**
 * 数据对象操作类型
 */
public enum DataObjectOperationType {

	/** 新增 */
	INSERT,
	/** 新增或者修改.这取决于数据存在还是不存在 */
	INSERT_UPDATE,
	/** 删除后新增，根据指定的条件删除数据后在进行新增(目前仅用在从数据中，第一个主数据不会用到) */
	INSERT_DELETE,

}
