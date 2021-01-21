package com.labbol.product.dataferry.core.resolve;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 数据文件解析器
 */
public interface DataFileResolver {

	/**
	 * 解析数据文件
	 * 
	 * @param dataFile 数据文件
	 * @return 数据对象源集合
	 * @throws DataFileResolveException 数据文件解析异常
	 */
	default List<DataObjectSource> resolve(File dataFile) throws DataFileResolveException {
		try {
			return resolve(new FileInputStream(dataFile));
		} catch (FileNotFoundException e) {
			throw new DataFileResolveException(e);
		}
	}

	/**
	 * 解析数据
	 * 
	 * @param data 数据字节流
	 * @return 数据对象源集合
	 * @throws DataFileResolveException 数据文件解析异常
	 */
	default List<DataObjectSource> resolve(byte[] data) throws DataFileResolveException {
		return resolve(new ByteArrayInputStream(data));
	}

	/**
	 * 解析数据
	 * 
	 * @param dateInputStream 数据流
	 * @return 数据对象源集合
	 * @throws DataFileResolveException 数据文件解析异常
	 */
	List<DataObjectSource> resolve(InputStream dateInputStream) throws DataFileResolveException;

}
