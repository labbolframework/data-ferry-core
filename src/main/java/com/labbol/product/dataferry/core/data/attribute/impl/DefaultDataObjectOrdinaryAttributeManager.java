package com.labbol.product.dataferry.core.data.attribute.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;
import com.labbol.product.dataferry.core.data.attribute.DataObjectGroup;
import com.labbol.product.dataferry.core.data.attribute.DataObjectOrdinaryAttributeManager;
import com.labbol.product.dataferry.core.data.attribute.OrdinaryAttributeReferenceException;

import dream.first.base.model.DreamFirstBaseModelable;

public class DefaultDataObjectOrdinaryAttributeManager implements DataObjectOrdinaryAttributeManager {

	public static final String DEFAULT_REF_ATTRNAME = DreamFirstBaseModelable.ID;

	@Override
	public Object getAttributeReferenceValue(DataObjectGroup dataObjectGroup,
			DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute) throws OrdinaryAttributeReferenceException {
		String reference = dataObjectOrdinaryAttribute.getReference();
		// 引用一般格式为 tableName.attrName
		if (StringUtils.isBlank(reference)) {
			throw new OrdinaryAttributeReferenceException(dataObjectOrdinaryAttribute.getReference() + "引用不能为空");
		}
		String[] referenceSplit = reference.split("[.]");
		if (ArrayUtils.isEmpty(referenceSplit)) {
			throw new OrdinaryAttributeReferenceException(dataObjectOrdinaryAttribute.getReference() + "引用不能为空");
		}
		String tableName = referenceSplit[0];

		String attrName = null;
		if (referenceSplit.length > 1) {
			attrName = referenceSplit[1];
		} else {
			attrName = DEFAULT_REF_ATTRNAME;
		}

		DataObject dataObject = dataObjectGroup.getDataObject(tableName);
		if (null == dataObject) {
			throw new OrdinaryAttributeReferenceException("设置属性“" + dataObjectOrdinaryAttribute.getName() + "”错误！解析引用“"
					+ reference + "”异常，没有找到“" + tableName + "”引用");
		}
		DataObjectOrdinaryAttribute ordinaryAttribute = dataObject.getOrdinaryAttribute(attrName);
		return getAttributeValue(dataObjectGroup, ordinaryAttribute);
	}

}
