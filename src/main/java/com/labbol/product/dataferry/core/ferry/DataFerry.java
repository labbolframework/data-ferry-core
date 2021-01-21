package com.labbol.product.dataferry.core.ferry;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.yelong.core.model.service.SqlModelService;

import com.labbol.product.dataferry.core.data.operate.DataObjectSourceOperateException;
import com.labbol.product.dataferry.core.resolve.DataFileResolveException;

/**
 * 数据摆渡
 */
public interface DataFerry {

	/**
	 * 数据摆渡
	 * 
	 * @param dataFile     数据文件
	 * @param modelService 操作的数据库服务
	 * @throws DataFileResolveException         数据文件解析异常
	 * @throws DataObjectSourceOperateException 数据库操作异常
	 * @return 数据摆渡结果
	 */
	default DataFerryResult ferry(File dataFile, SqlModelService modelService)
			throws DataFileResolveException, DataObjectSourceOperateException {
		try {
			return ferry(new FileInputStream(dataFile), modelService);
		} catch (FileNotFoundException e) {
			throw new DataFileResolveException(e);
		}
	}

	/**
	 * 数据摆渡
	 * 
	 * @param data         数据二进制流
	 * @param modelService 操作的数据库服务
	 * @throws DataFileResolveException         数据文件解析异常
	 * @throws DataObjectSourceOperateException 数据库操作异常
	 * @return 数据摆渡结果
	 */
	default DataFerryResult ferry(byte[] data, SqlModelService modelService)
			throws DataFileResolveException, DataObjectSourceOperateException {
		return ferry(new ByteArrayInputStream(data), modelService);
	}

	/**
	 * 数据摆渡
	 * 
	 * @param inputStream  数据二进制流
	 * @param modelService 操作的数据库服务
	 * @throws DataFileResolveException         数据文件解析异常
	 * @throws DataObjectSourceOperateException 数据库操作异常
	 * @return 数据摆渡结果
	 */
	DataFerryResult ferry(InputStream inputStream, SqlModelService modelService)
			throws DataFileResolveException, DataObjectSourceOperateException;

}
