package com.labbol.product.dataferry.core.data;

import java.util.ArrayList;
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

	/**
	 * 获取对象数据源中所有的数据对象(递归，包含子数据对象中的数据对象源)
	 * 
	 * @author PengFei
	 * @date 2021年2月19日上午11:07:15
	 * @param dataObjectSource 数据对象源
	 * @return 数据对象源中所有的数据对象
	 */
	public static List<DataObject> getDataObjectAll(DataObjectSource dataObjectSource) {
		List<DataObject> dataObjectAll = new ArrayList<>();
		List<? extends DataObject> dataObjects = dataObjectSource.getDataObjects();
		for (DataObject dataObject : dataObjects) {
			dataObjectAll.add(dataObject);
			if (dataObject.isEmptyDataObjectSourceAttribute()) {
				continue;
			}
			List<? extends DataObjectSource> dataObjectSourceAttributes = dataObject.getDataObjectSourceAttributes();
			for (DataObjectSource childDataObjectSource : dataObjectSourceAttributes) {
				dataObjectAll.addAll(getDataObjectAll(childDataObjectSource));
			}
		}
		return dataObjectAll;
	}

	/**
	 * 获取所有对象数据源中所有的数据对象(递归，包含子数据对象中的数据对象源)
	 * 
	 * @author PengFei
	 * @date 2021年2月19日上午11:07:15
	 * @param dataObjectSources 数据对象源集合
	 * @return 所有数据对象源中所有的数据对象
	 */
	public static List<DataObject> getDataObjectAll(List<? extends DataObjectSource> dataObjectSources) {
		List<DataObject> dataObjectAll = new ArrayList<>();
		for (DataObjectSource dataObjectSource : dataObjectSources) {
			dataObjectAll.addAll(getDataObjectAll(dataObjectSource));
		}
		return dataObjectAll;
	}

}
