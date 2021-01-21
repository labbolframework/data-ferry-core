package com.labbol.product.dataferry.core.data.model;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.model.Modelable;

import com.labbol.product.dataferry.core.data.DataObject;

/**
 * 模型数据对象
 *
 * @param <M> model type
 */
public interface ModelDataObject<M extends Modelable> extends DataObject {

	/**
	 * @return 模型对象
	 */
	@Nullable
	M getModel();

	@Override
	ModelDataObjectSource<M> getDeclaringDataObjectSource();

}
