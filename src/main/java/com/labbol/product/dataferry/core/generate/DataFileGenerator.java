/**
 * 
 */
package com.labbol.product.dataferry.core.generate;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 数据文件生成器
 */
public interface DataFileGenerator {

	/**
	 * 根据数据源集合生成数据文件内容,并将内容写入到指定文件内
	 * 
	 * @param dataObjectSource 数据对象源
	 * @param dateFile         生成后存放的数据文件
	 * @throws DataFileGenerateException 数据对象源集合
	 */
	default void generate(DataObjectSource dataObjectSource, File dateFile) throws DataFileGenerateException {
		generate(Arrays.asList(dataObjectSource), dateFile);
	}

	/**
	 * 根据数据源集合生成数据文件内容,并将内容写入到指定文件内
	 * 
	 * @param dataObjectSources 数据对象源集合
	 * @param dateFile          生成后存放的数据文件
	 * @throws DataFileGenerateException 数据对象源集合
	 */
	void generate(List<? extends DataObjectSource> dataObjectSources, File dateFile) throws DataFileGenerateException;

	/**
	 * 根据数据源集合生成数据文件内容
	 * 
	 * @param dataObjectSource 数据对象源
	 * @return 数据文件 byte 数组
	 * @throws DataFileGenerateException 数据对象源集合
	 */
	default byte[] generateBytes(DataObjectSource dataObjectSource) throws DataFileGenerateException {
		return generateBytes(Arrays.asList(dataObjectSource));
	}

	/**
	 * 根据数据源集合生成数据文件内容
	 * 
	 * @param dataObjectSources 数据对象源集合
	 * @return 数据文件 byte 数组
	 * @throws DataFileGenerateException 数据对象源集合
	 */
	byte[] generateBytes(List<? extends DataObjectSource> dataObjectSources) throws DataFileGenerateException;

}
