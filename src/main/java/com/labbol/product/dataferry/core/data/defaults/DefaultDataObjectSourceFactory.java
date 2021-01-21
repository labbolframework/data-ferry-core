package com.labbol.product.dataferry.core.data.defaults;

import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.DataObjectSourceFactory;

/**
 * 默认的数据对象源建造者工厂
 */
public class DefaultDataObjectSourceFactory implements DataObjectSourceFactory {

	public static final DataObjectSourceFactory INSTANCE = new DefaultDataObjectSourceFactory();

	protected DefaultDataObjectSourceFactory() {
	}

	@Override
	public DataObjectSource create() {
		return new DefaultDataObjectSource();
	}

}
