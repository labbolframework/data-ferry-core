package com.labbol.product.dataferry.core.data;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 数据对象源工具类
 * 
 * @author PengFei
 * @date 2021年1月26日下午10:06:50
 */
public final class DataObjectSourceUtils {

	private DataObjectSourceUtils() {
	}

	/**
	 * 所有数据对象源是否都不存在数据对象
	 * 
	 * @author PengFei
	 * @date 2021年1月26日下午10:08:14
	 * @param dataObjectSources 数据对象源集合
	 * @return <code>true</code> 所有数据对象源都不存在数据对象
	 */
	public static boolean isEmptyDataObject(List<? extends DataObjectSource> dataObjectSources) {
		if (CollectionUtils.isEmpty(dataObjectSources)) {
			return true;
		}
		for (DataObjectSource dataObjectSource : dataObjectSources) {
			if (!dataObjectSource.isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
