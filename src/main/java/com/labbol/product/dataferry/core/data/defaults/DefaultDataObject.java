package com.labbol.product.dataferry.core.data.defaults;

import org.yelong.commons.util.map.CaseInsensitiveMapUtils;
import org.yelong.commons.util.map.CaseInsensitiveMapUtils.KeyStoreMode;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 默认的数据对象
 */
public class DefaultDataObject extends AbstractDataObject {

	private final DataObjectSource dataObjectSource;

	protected DefaultDataObject(DataObjectSource dataObjectSource) {
		this.dataObjectSource = dataObjectSource;
		// 属性键值默认忽略大小写
		super.ordinaryAttributes = CaseInsensitiveMapUtils.createCaseInsensitiveMap(KeyStoreMode.LOWER);
	}

	@Override
	public DataObjectSource getDeclaringDataObjectSource() {
		return dataObjectSource;
	}

}
