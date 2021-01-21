package com.labbol.product.dataferry.core.data;

import org.yelong.core.annotation.Nullable;

/**
 * 数据对象属性
 */
public interface DataObjectOrdinaryAttribute {

	/**
	 * @return 属性名称
	 */
	String getName();

	/**
	 * @return 属性值
	 */
	@Nullable
	Object getValue();

	/**
	 * @param value 属性值
	 */
	void setValue(Object value);

	/**
	 * @return 属性的数据类型。这个是值得java数据类型
	 */
	@Nullable
	Class<?> getValueType();

	/**
	 * @param valueType 屬性的数据类型。这个是值得java数据类型
	 */
	void setValueType(Class<?> valueType);

	/**
	 * 该属性值是引用的其他的值
	 * 
	 * @return 引用
	 */
	String getReference();

	/**
	 * @param reference 引用
	 */
	void setReference(String reference);

}
