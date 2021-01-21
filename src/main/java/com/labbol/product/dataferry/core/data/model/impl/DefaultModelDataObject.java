package com.labbol.product.dataferry.core.data.model.impl;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.model.Modelable;

import com.labbol.product.dataferry.core.data.defaults.DefaultDataObject;
import com.labbol.product.dataferry.core.data.model.ModelDataObject;
import com.labbol.product.dataferry.core.data.model.ModelDataObjectSource;

/**
 * 默认的模型数据对象
 *
 * @param <M> model type
 */
public class DefaultModelDataObject<M extends Modelable> extends DefaultDataObject implements ModelDataObject<M> {

	@Nullable
	protected final M model;

	protected DefaultModelDataObject(ModelDataObjectSource<M> dataObjectSource) {
		this(dataObjectSource, null);
	}

	protected DefaultModelDataObject(ModelDataObjectSource<M> dataObjectSource, M model) {
		super(dataObjectSource);
		this.model = model;
	}

	@Override
	public M getModel() {
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelDataObjectSource<M> getDeclaringDataObjectSource() {
		return (ModelDataObjectSource<M>) super.getDeclaringDataObjectSource();
	}

}
