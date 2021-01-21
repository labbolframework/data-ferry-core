package com.labbol.product.dataferry.core.data.attribute;

import java.util.LinkedHashMap;
import java.util.Map;

import com.labbol.product.dataferry.core.data.DataObject;

/**
 * 数据对象组
 */
public class DataObjectGroup {

	private Map<String, DataObject> dataObjectMaps = new LinkedHashMap<>();

	public DataObjectGroup() {
	}

	public DataObjectGroup(DataObject dataObject) {
		addDataObject(dataObject);
	}

	public void addDataObject(DataObject dataObject) {
		dataObjectMaps.put(dataObject.getDeclaringDataObjectSource().getTableName(), dataObject);
	}

	public Map<String, DataObject> getDataObjectMaps() {
		return dataObjectMaps;
	}

	public DataObject getDataObject(String tableName) {
		return dataObjectMaps.get(tableName);
	}

}
