package com.labbol.product.dataferry.core.data.model;

import java.util.List;

import org.yelong.core.model.Modelable;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 模型数据对象源
 *
 * @param <M> model type
 */
public interface ModelDataObjectSource<M extends Modelable> extends DataObjectSource {

	/**
	 * @return 模型类型
	 */
	Class<M> getModelClass();

	@Override
	List<? extends ModelDataObject<M>> getDataObjects();

	/**
	 * 创建模型数据对象
	 * 
	 * @param model 模型对象
	 * @return 模型数据对象
	 */
	ModelDataObject<M> createModelDataObject(M model);

}
