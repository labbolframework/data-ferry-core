package com.labbol.product.dataferry.core.data.model.impl;

import java.util.Objects;

import org.yelong.commons.lang.annotation.AnnotationUtilsE;
import org.yelong.core.model.Modelable;
import org.yelong.core.model.manage.FieldAndColumn;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.core.model.manage.ModelManager;
import org.yelong.core.model.manage.exception.PrimaryKeyException;

import com.labbol.product.dataferry.core.data.defaults.DefaultDataObjectSourceFactory;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectOperationType;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectSource;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectSourceFactory;

/**
 * 默认的模型对象源工厂
 */
public class DefaultModelDataObjectSourceFactory extends DefaultDataObjectSourceFactory
		implements ModelDataObjectSourceFactory {

	private ModelManager modelManager;

	public DefaultModelDataObjectSourceFactory(ModelManager modelManager) {
		this.modelManager = Objects.requireNonNull(modelManager);
	}

	@Override
	public <M extends Modelable> ModelDataObjectSource<M> create(Class<M> modelClass) {
		ModelDataObjectSource<M> modelDataObjectSource = new DefaultModelDataObjectSource<M>(modelClass);
		if (null == modelClass) {
			return modelDataObjectSource;
		}
		ModelAndTable modelAndTable = modelManager.getModelAndTable(modelClass);
		try {
			FieldAndColumn primaryKey = modelAndTable.getOnlyPrimaryKey();
			modelDataObjectSource.setPrimaryKey(primaryKey.getColumn());
		} catch (PrimaryKeyException e) {
			// ignore
		}
		modelDataObjectSource.setTableName(modelAndTable.getTableName());
		ModelDataObjectOperationType modelDataObjectOperationType = AnnotationUtilsE.getAnnotation(modelClass,
				ModelDataObjectOperationType.class, true);
		if (null != modelDataObjectOperationType) {
			modelDataObjectSource.setDataObjectOperationType(modelDataObjectOperationType.value());
		}
		return modelDataObjectSource;
	}

}
