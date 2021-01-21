package com.labbol.product.dataferry.core.data;

import java.util.List;
import java.util.Map;

import org.yelong.core.annotation.Nullable;

/**
 * 数据对象属性
 */
public interface DataObject {

	/**
	 * @return 声明的数据对象源
	 */
	DataObjectSource getDeclaringDataObjectSource();

	// ==================================================普通属性==================================================

	/**
	 * 添加普通属性
	 * 
	 * @param name  属性名称
	 */
	DataObjectOrdinaryAttribute addOrdinaryAttribute(String name);

	/**
	 * 添加普通属性
	 * 
	 * @param name  属性名称
	 * @param value 属性值
	 */
	DataObjectOrdinaryAttribute addOrdinaryAttribute(String name, @Nullable Object value);

	/**
	 * 添加普通属性
	 * 
	 * @param name      属性名称
	 * @param value     属性值
	 * @param valueType 属性值类型
	 */
	DataObjectOrdinaryAttribute addOrdinaryAttribute(String name, @Nullable Object value, @Nullable Class<?> valueType);

	/**
	 * 添加普通属性
	 * 
	 * @param attribute 属性
	 */
	void addOrdinaryAttribute(DataObjectOrdinaryAttribute attribute);

	/**
	 * 添加普通属性
	 * 
	 * @param attributes 属性映射
	 */
	default void addOrdinaryAttributes(Map<String, Object> attributes) {
		attributes.forEach(this::addOrdinaryAttribute);
	}

	/**
	 * 移除普通属性
	 * 
	 * @param names 属性名称数组
	 */
	void removeOrdinaryAttribute(String... names);

	/**
	 * 移除普通属性
	 * 
	 * @param names 属性名称集合
	 */
	void removeOrdinaryAttribute(List<String> names);

	/**
	 * @return 数据对象属性的普通属性。这些属性指的是该对象的普通属性，而不是特殊定义的属性
	 */
	Map<String, Object> getOrdinaryAttributeMaps();

	/**
	 * @return 数据对象属性的普通属性。这些属性指的是该对象的普通属性，而不是特殊定义的属性
	 */
	List<DataObjectOrdinaryAttribute> getOrdinaryAttributes();

	/**
	 * 获取属性值
	 * 
	 * @param name 属性名称
	 * @return 属性值
	 */
	@Nullable
	Object getOrdinaryAttributeValue(String name);

	/**
	 * 获取属性
	 * 
	 * @param name 属性名称
	 * @return 属性
	 */
	@Nullable
	DataObjectOrdinaryAttribute getOrdinaryAttribute(String name);

	/**
	 * 普通属性是否是空的
	 * 
	 * @return <code>true</code> 普通属性是空的
	 */
	boolean isEmptyOrdinaryAttribute();

	// ==================================================数据对象源属性==================================================

	/**
	 * 添加数据对象源
	 * 
	 * @param dataObjectSource 数据对象源
	 */
	void addDataObjectSourceAttribute(DataObjectSource dataObjectSource);

	/**
	 * 添加数据对象源
	 * 
	 * @param dataObjectSources 数据对象源集合
	 */
	default void addDataObjectSourceAttributes(List<? extends DataObjectSource> dataObjectSources) {
		dataObjectSources.forEach(this::addDataObjectSourceAttribute);
	}

	/**
	 * @return 数据对象属性的数据对象源类型属性。
	 */
	List<? extends DataObjectSource> getDataObjectSourceAttributes();

	/**
	 * 数据对象源是否是空的
	 * 
	 * @return <code>true</code>数据对象源是空的
	 */
	boolean isEmptyDataObjectSourceAttribute();

}
