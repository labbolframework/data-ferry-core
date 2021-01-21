package com.labbol.product.dataferry.core.data.model.convert.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import org.yelong.commons.lang.annotation.AnnotationUtilsE;
import org.yelong.core.model.Modelable;
import org.yelong.core.model.manage.FieldAndColumn;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.core.model.manage.ModelManager;
import org.yelong.core.model.property.ModelProperty;

import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;
import com.labbol.product.dataferry.core.data.convert.impl.DefaultDataObjectConvertor;
import com.labbol.product.dataferry.core.data.model.ModelDataObject;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectAttributeRef;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectSource;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectSourceFactory;
import com.labbol.product.dataferry.core.data.model.convert.ModelDataObjectConvertException;
import com.labbol.product.dataferry.core.data.model.convert.ModelDataObjectConvertor;

/**
 * 模型数据对象转换器
 */
public class DefaultModelDataObjectConvertor extends DefaultDataObjectConvertor implements ModelDataObjectConvertor {

	private ModelManager modelManager;

	private ModelProperty modelProperty;

	private ModelDataObjectSourceFactory modelDataObjectSourceFactory;

	public DefaultModelDataObjectConvertor(ModelManager modelManager, ModelProperty modelProperty,
			ModelDataObjectSourceFactory modelDataObjectSourceFactory) {
		this.modelManager = Objects.requireNonNull(modelManager);
		this.modelDataObjectSourceFactory = Objects.requireNonNull(modelDataObjectSourceFactory);
		this.modelProperty = Objects.requireNonNull(modelProperty);
	}

	@Override
	public <M extends Modelable> ModelDataObjectSource<M> toDataObjectSource(Class<M> modelClass)
			throws ModelDataObjectConvertException {
		return modelDataObjectSourceFactory.create(modelClass);
	}

	@Override
	public <M extends Modelable> ModelDataObject<M> toDataObject(M model, ModelDataObjectSource<M> dataObjectSource)
			throws ModelDataObjectConvertException {
		ModelDataObject<M> modelDataObject = dataObjectSource.createModelDataObject(model);
		ModelAndTable modelAndTable = modelManager.getModelAndTable(model.getClass());
		List<FieldAndColumn> normalFieldAndColumns = modelAndTable.getNormalFieldAndColumns();
		for (FieldAndColumn fieldAndColumn : normalFieldAndColumns) {
			Object value = modelProperty.get(model, fieldAndColumn.getFieldName());
			Class<?> valueType = null;
			if (null == value) {
				valueType = fieldAndColumn.getFieldType();
			} else {
				valueType = value.getClass();
			}
			DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute = modelDataObject
					.addOrdinaryAttribute(fieldAndColumn.getColumn(), value, valueType);
			// 获取是否指定的数据引用
			Field field = fieldAndColumn.getField();
			if (null != field) {
				ModelDataObjectAttributeRef modelDataObjectAttributeRef = AnnotationUtilsE.getAnnotation(field,
						ModelDataObjectAttributeRef.class);
				if (null != modelDataObjectAttributeRef) {
					dataObjectOrdinaryAttribute.setReference(modelDataObjectAttributeRef.value());
				}
			}

		}
		return modelDataObject;
	}

}
