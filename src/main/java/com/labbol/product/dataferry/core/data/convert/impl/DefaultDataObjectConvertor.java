package com.labbol.product.dataferry.core.data.convert.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.convert.DataObjectConvertException;
import com.labbol.product.dataferry.core.data.convert.DataObjectConvertor;

/**
 * 默认的数据对象转换器
 */
public class DefaultDataObjectConvertor implements DataObjectConvertor {

	@SuppressWarnings("unchecked")
	@Override
	public DataObject toDataObject(Object model, DataObjectSource dataObjectSource) throws DataObjectConvertException {
		DataObject dataObject = dataObjectSource.createDataObject();
		Objects.requireNonNull(model);
		try {
			if (model instanceof Map) {
				addOrdinaryAttributeByMap(dataObject, (Map<String, Object>) model);
			} else {
				addOrdinaryAttributeByPOJO(dataObject, model);
			}
		} catch (Exception e) {
			throw new DataObjectConvertException(e);
		}
		return dataObject;
	}

	/**
	 * 根据POJO添加属性
	 * 
	 * @param dataObject 数据对象
	 * @param pojo       POJO对象
	 * @throws IllegalAccessException 反射获取值异常
	 */
	protected void addOrdinaryAttributeByPOJO(DataObject dataObject, Object pojo) throws IllegalAccessException {
		Class<? extends Object> pojoClass = pojo.getClass();
		List<Field> fields = FieldUtils.getAllFieldsList(pojoClass);
		for (Field field : fields) {
			Object value = FieldUtils.readField(field, pojo);
			dataObject.addOrdinaryAttribute(field.getName(), value, field.getClass());
		}
	}

	/**
	 * 根据Map添加属性
	 * 
	 * @param dataObject 数据对象
	 * @param map        map
	 */
	protected void addOrdinaryAttributeByMap(DataObject dataObject, Map<String, Object> map) {
		dataObject.addOrdinaryAttributes(map);
	}

}
