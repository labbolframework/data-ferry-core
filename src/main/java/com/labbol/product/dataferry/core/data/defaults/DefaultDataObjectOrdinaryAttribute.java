package com.labbol.product.dataferry.core.data.defaults;

import org.yelong.core.annotation.Nullable;

/**
 * 默认的数据对象普通属性
 */
public class DefaultDataObjectOrdinaryAttribute extends AbstractDataObjectOrdinaryAttribute {

	private Object value;

	private Class<?> valueType;

	private String reference;

	public DefaultDataObjectOrdinaryAttribute(String name, @Nullable Object value) {
		this(name, value, null);
	}

	public DefaultDataObjectOrdinaryAttribute(String name, @Nullable Object value, @Nullable Class<?> valueType) {
		super(name);
		this.value = value;
		this.valueType = valueType;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Class<?> getValueType() {
		if (null != valueType) {
			return valueType;
		}
		if (null != value) {
			return value.getClass();
		} else {
			return null;
		}
	}

	@Override
	public void setValueType(Class<?> valueType) {
		this.valueType = valueType;
	}

	@Override
	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String getReference() {
		return reference;
	}

}
