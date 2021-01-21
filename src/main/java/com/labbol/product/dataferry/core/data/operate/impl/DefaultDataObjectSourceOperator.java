package com.labbol.product.dataferry.core.data.operate.impl;

import org.yelong.commons.util.Dates;

import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectOperationType;
import com.labbol.product.dataferry.core.data.attribute.DataObjectGroup;
import com.labbol.product.dataferry.core.data.attribute.DataObjectOrdinaryAttributeManager;

import dream.first.base.model.DreamFirstBaseModelable;
import dream.first.base.utils.DreamFirstBaseModelIDGenerator;

/**
 * 默认数据对象源操作器
 */
public class DefaultDataObjectSourceOperator extends AbstractDataObjectSourceOperator {

	public DefaultDataObjectSourceOperator(DataObjectOrdinaryAttributeManager dataObjectOrdinaryAttributeManager) {
		super(dataObjectOrdinaryAttributeManager);
	}

	@Override
	protected void beforeInsert(DataObjectSourceOperateProperties dataObjectSourceOperateProperties,
			DataObjectGroup dataObjectGroup, DataObject dataObject) {
		// 如果处理类型是 INSERT 则强制修改 ID值。否则只有在ID值不存在时才进行设置
		DataObjectOperationType dataObjectOperationType = dataObject.getDeclaringDataObjectSource()
				.getDataObjectOperationType();
		if (dataObjectOperationType == DataObjectOperationType.INSERT) {
			dataObject.addOrdinaryAttribute(DEFAUL_PRIMARYKEY, DreamFirstBaseModelIDGenerator.getUUID());
		} else {
			Object id = dataObject.getOrdinaryAttributeValue(DEFAUL_PRIMARYKEY);
			if (null == id) {
				dataObject.addOrdinaryAttribute(DEFAUL_PRIMARYKEY, DreamFirstBaseModelIDGenerator.getUUID());
			}
		}
		Object createTime = dataObject.getOrdinaryAttributeValue(DreamFirstBaseModelable.CREATETIME);
		if (null == createTime) {
			dataObject.addOrdinaryAttribute(DreamFirstBaseModelable.CREATETIME, Dates.nowDate());
		}
	}

	@Override
	protected void beforeUpdate(DataObjectSourceOperateProperties dataObjectSourceOperateProperties,
			DataObjectGroup dataObjectGroup, DataObject dataObject) {
		Object updateTime = dataObject.getOrdinaryAttributeValue(DreamFirstBaseModelable.UPDATETIME);
		if (null == updateTime) {
			dataObject.addOrdinaryAttribute(DreamFirstBaseModelable.UPDATETIME, Dates.nowDate());
		}
	}

}
