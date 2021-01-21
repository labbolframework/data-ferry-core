package com.labbol.product.dataferry.core.data.model.impl;

import java.util.List;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.model.Modelable;

import com.labbol.product.dataferry.core.data.defaults.DefaultDataObjectSource;
import com.labbol.product.dataferry.core.data.model.ModelDataObject;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectSource;

/**
 * 默认
 * 
 * @param <M> model type
 */
public class DefaultModelDataObjectSource<M extends Modelable> extends DefaultDataObjectSource
		implements ModelDataObjectSource<M> {

	@Nullable
	protected final Class<M> modelClass;

	public DefaultModelDataObjectSource() {
		this(null);
	}

	public DefaultModelDataObjectSource(Class<M> modelClass) {
		this.modelClass = modelClass;
	}

	@Override
	public Class<M> getModelClass() {
		return modelClass;
	}

	@SuppressWarnings("unchecked")
	public List<? extends ModelDataObject<M>> getDataObjects() {
		return (List<? extends ModelDataObject<M>>) super.getDataObjects();
	}

	@Override
	public ModelDataObject<M> createDataObject() {
		return new DefaultModelDataObject<>(this);
	}

	@Override
	public ModelDataObject<M> createModelDataObject(M model) {
		return new DefaultModelDataObject<>(this, model);
	}

}
