package com.labbol.product.dataferry.core.data.attribute;

import org.apache.commons.lang3.StringUtils;

import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;

/**
 * 数据对象属性管理器
 */
public interface DataObjectOrdinaryAttributeManager {

	/**
	 * 获取属性的引用值。如果属性没有引用规则则返回 null
	 * 
	 * @param dataObjectGroup             数据对象组
	 * @param dataObjectOrdinaryAttribute 数据对象属性
	 * @return 通过引用规则获取的值。如果没有引用规则则返回null
	 * @throws OrdinaryAttributeReferenceException 引用规则不符合规范
	 */
	Object getAttributeReferenceValue(DataObjectGroup dataObjectGroup,
			DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute) throws OrdinaryAttributeReferenceException;

	/**
	 * 获取属性的值。如果属性存在引用规则，则通过引用规则获取值，否则直接返回属性的值
	 * 
	 * @param dataObjectGroup             数据对象组
	 * @param dataObjectOrdinaryAttribute 数据对象属性
	 * @return 通过引用规则获取的值。如果没有引用规则则返回null
	 * @throws OrdinaryAttributeReferenceException 引用规则不符合规范
	 */
	default Object getAttributeValue(DataObjectGroup dataObjectGroup,
			DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute) throws OrdinaryAttributeReferenceException {
		String reference = dataObjectOrdinaryAttribute.getReference();
		if (StringUtils.isBlank(reference)) {
			return dataObjectOrdinaryAttribute.getValue();
		}
		return getAttributeReferenceValue(dataObjectGroup, dataObjectOrdinaryAttribute);
	}

}
