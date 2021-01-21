package com.labbol.product.dataferry.core.data.model;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.model.Modelable;

import com.labbol.product.dataferry.core.data.DataObjectSourceFactory;

/**
 * 模型数据对象源工厂
 */
public interface ModelDataObjectSourceFactory extends DataObjectSourceFactory {

	/**
	 * @param <M>        model type
	 * @param modelClass 模型类型
	 * @return 模型数据对象源
	 */
	<M extends Modelable> ModelDataObjectSource<M> create(@Nullable Class<M> modelClass);

}
