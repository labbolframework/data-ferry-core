package com.labbol.product.dataferry.core.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author PengFei
 * @date 2021年1月26日下午10:11:12
 */
public class DataObjectSources {

	private List<DataObjectSource> dataObjectSources = new ArrayList<>();

	protected DataObjectSources() {

	}

	/**
	 * 添加一个数据对象源
	 * 
	 * @author PengFei
	 * @date 2021年1月26日下午10:13:17
	 * @param dataObjectSource
	 */
	public void add(DataObjectSource dataObjectSource) {
		if (null == dataObjectSources) {
			return;
		}
		dataObjectSources.add(dataObjectSource);
	}

	/**
	 * @author PengFei 
	 * @date 2021年1月26日下午10:15:13
	 * @see DataObjectSourceUtils#isEmptyDataObject(List)
	 */
	public boolean isEmptyDataObject() {
		return DataObjectSourceUtils.isEmptyDataObject(dataObjectSources);
	}

	public List<DataObjectSource> getDataObjectSources() {
		return dataObjectSources;
	}

	public static DataObjectSources create() {
		return new DataObjectSources();
	}

}
