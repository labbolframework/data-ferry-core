package com.labbol.product.dataferry.core.data.convert;

import java.util.ArrayList;
import java.util.List;

import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.model.convert.ModelDataObjectConvertException;

/**
 * 数据对象转换器
 */
public interface DataObjectConvertor {

	/**
	 * 根据对象、数据对象源创建模型数据对象
	 * 
	 * @param model            模型对象
	 * @param dataObjectSource 数据对象源
	 * @return 模型数据对象
	 * @throws ModelDataObjectConvertException 转换异常
	 */
	DataObject toDataObject(Object model, DataObjectSource dataObjectSource) throws DataObjectConvertException;

	/**
	 * 根据对象、数据对象源创建模型数据对象
	 * 
	 * @param models           模型对象集合
	 * @param dataObjectSource 数据对象源
	 * @return 模型数据对象集合
	 * @throws DataObjectConvertException 转换异常
	 */
	default List<DataObject> toDataObjects(List<?> models, DataObjectSource dataObjectSource)
			throws DataObjectConvertException {
		List<DataObject> dataObjects = new ArrayList<>(models.size());
		for (Object model : models) {
			dataObjects.add(toDataObject(model, dataObjectSource));
		}
		return dataObjects;
	}

}
