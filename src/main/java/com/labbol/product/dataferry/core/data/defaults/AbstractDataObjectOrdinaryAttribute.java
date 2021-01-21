package com.labbol.product.dataferry.core.data.defaults;

import org.yelong.commons.lang.Strings;

import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;

public abstract class AbstractDataObjectOrdinaryAttribute implements DataObjectOrdinaryAttribute {

	private final String name;

	public AbstractDataObjectOrdinaryAttribute(String name) {
		this.name = Strings.requireNonBlank(name);
	}

	@Override
	public String getName() {
		return name;
	}

}
