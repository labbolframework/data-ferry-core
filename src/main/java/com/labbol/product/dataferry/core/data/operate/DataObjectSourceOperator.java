package com.labbol.product.dataferry.core.data.operate;

import org.yelong.core.model.service.SqlModelService;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 数据对象源操作器
 */
public interface DataObjectSourceOperator {

	/**
	 * 操作数据对象源.根据对象源的配置进行操作数据库
	 * 
	 * @param dataObjectSource 数据对象源
	 * @param modelService     模型业务
	 * @return 操作结果
	 * @throws DataObjectSourceOperateException 数据对象源操作异常
	 */
	DataObjectSourceOperateResult operate(DataObjectSource dataObjectSource, SqlModelService modelService)
			throws DataObjectSourceOperateException;

}
