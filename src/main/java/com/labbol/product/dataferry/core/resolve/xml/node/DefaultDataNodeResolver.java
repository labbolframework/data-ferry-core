package com.labbol.product.dataferry.core.resolve.xml.node;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yelong.commons.lang.EnumUtilsE;
import org.yelong.core.annotation.Nullable;
import org.yelong.core.data.DataTypeConvertException;
import org.yelong.core.data.DataTypeConvertor;
import org.yelong.core.data.string.StringDataTypeConvertorManager;

import com.labbol.product.dataferry.core.constants.NodeNameTool;
import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;
import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.DataObjectSourceFactory;
import com.labbol.product.dataferry.core.data.DataObjectSourceProperties;
import com.labbol.product.dataferry.core.data.attribute.DataObjectAttributeType;

/**
 * 默认的数据节点解析器
 */
public class DefaultDataNodeResolver implements DataNodeResolver {

	protected DataObjectSourceFactory dataObjectSourceFactory;

	protected StringDataTypeConvertorManager stringDataTypeConvertorManager;

	public DefaultDataNodeResolver(DataObjectSourceFactory dataObjectSourceFactory,
			StringDataTypeConvertorManager stringDataTypeConvertorManager) {
		this.dataObjectSourceFactory = dataObjectSourceFactory;
		this.stringDataTypeConvertorManager = stringDataTypeConvertorManager;
	}

	@Override
	public DataObjectSource resolve(Node dataNode) throws DataNodeResolveException {
		// 子节点只有为元素时才会解析
		if (Node.ELEMENT_NODE != dataNode.getNodeType()) {
			return null;
		}
		DataObjectSource dataObjectSource = dataObjectSourceFactory.create();

		Element dataElement = (Element) dataNode;

		// 对象表名
		dataObjectSource.setTableName(dataElement.getNodeName());
		// 对象主键
		dataObjectSource.setPrimaryKey(dataElement.getAttribute(NodeNameTool.ATTR_PRIMARYKEY));
		// 删除条件
		DataObjectSourceProperties dataObjectSourceProperties = dataObjectSource.getDataObjectSourceProperties();
		dataObjectSourceProperties.setDeleteCondition(dataElement.getAttribute(NodeNameTool.ATTR_DELETECONDITION));

		// 数据操作类型
		String dataObjectOperationType = dataElement.getAttribute(NodeNameTool.ATTR_DATAOPERATIONTYPE);
		try {
			// 这里转换为枚举可能会出现异常
			dataObjectSource.setDataObjectOperationType(dataObjectOperationType);
		} catch (Exception e) {
			throw new DataNodeResolveException("未知的数据操作类型：" + dataObjectOperationType, e);
		}

		// 解析所有的Row节点为数据对象
		NodeList dataElementChildNodes = dataElement.getChildNodes();
		for (int i = 0; i < dataElementChildNodes.getLength(); i++) {
			DataObject dataObject = resolveRowNode(dataElementChildNodes.item(i), dataObjectSource);
			if (null != dataObject) {
				dataObjectSource.addDataObject(dataObject);
			}
		}

		return dataObjectSource;
	}

	/**
	 * 解析行节点
	 * 
	 * @param rowNode          行节点
	 * @param dataObjectSource 数据对象源
	 * @return 数据对象
	 * @throws DataNodeResolveException 数据节点解析异常
	 */
	@Nullable
	public DataObject resolveRowNode(Node rowNode, DataObjectSource dataObjectSource) throws DataNodeResolveException {
		// 子节点只有为元素时才会解析
		if (Node.ELEMENT_NODE != rowNode.getNodeType()) {
			return null;
		}
		DataObject dataObject = dataObjectSource.createDataObject();

		// 行节点中所有子元素分为 对象元素和属性元素。这个由元素的类型区分
		NodeList rowChildNodes = rowNode.getChildNodes();
		for (int i = 0; i < rowChildNodes.getLength(); i++) {
			Node node = rowChildNodes.item(i);
			// 只有是一个元素的时候才能解析
			if (Node.ELEMENT_NODE != node.getNodeType()) {
				continue;
			}
			Element nodeElement = (Element) node;
			DataObjectAttributeType dataObjectAttributeType = getDataObjectAttributeType(nodeElement);
			switch (dataObjectAttributeType) {
			case ORDINARY:// 普通属性
				addOrdinaryAttribute(dataObject, nodeElement);
				break;
			case DATA_OBJECT_SOURCE:// 数据对象源
				DataObjectSource childDataObjectSource = resolve(node);
				if (null != childDataObjectSource) {
					dataObject.addDataObjectSourceAttribute(childDataObjectSource);
				}
				break;
			}
		}
		return dataObject;
	}

	/**
	 * 数据对象根据元素添加一个普通属性
	 * 
	 * @param dataObject               数据对象
	 * @param ordinaryAttributeElement 普通属性元素
	 * @throws DataNodeResolveException
	 */
	protected void addOrdinaryAttribute(DataObject dataObject, Element attributeElement)
			throws DataNodeResolveException {
		String attrName = attributeElement.getNodeName();
		DataObjectOrdinaryAttribute attribute = dataObject.addOrdinaryAttribute(attrName);
		// 获取引用值
		String reference = attributeElement.getAttribute(NodeNameTool.ATTR_REF);
		attribute.setReference(reference);

		// 解析数值值
		String textContent = attributeElement.getTextContent();
		String javaTypeStr = attributeElement.getAttribute(NodeNameTool.ATTR_JAVATYPE);
		Class<?> javaType = null;
		if (StringUtils.isBlank(javaTypeStr)) {
			javaType = String.class;
		} else {
			try {
				javaType = ClassUtils.getClass(javaTypeStr);
			} catch (ClassNotFoundException e) {
				throw new DataNodeResolveException("属性元素“" + attrName + "”指定的JavaType(" + javaTypeStr + ")不符合规范", e);
			}
		}
		// 如果属性值为空字符且属性类型不是String则修改值为null
		if (StringUtils.isEmpty(textContent)) {
			if (javaType != String.class) {
				textContent = null;
			}
		}

		Object value;
		DataTypeConvertor<String, ?> dataTypeConvertor = stringDataTypeConvertorManager.getDataTypeConvertor(javaType);
		if (null == dataTypeConvertor) {
			value = textContent;
		} else {
			try {
				value = dataTypeConvertor.convert(textContent);
			} catch (DataTypeConvertException e) {
				throw new DataNodeResolveException(
						"属性元素“" + attrName + "”值(" + textContent + ")转换为JavaType(" + javaType + ")异常", e);
			}
		}
		attribute.setValue(value);
		attribute.setValueType(javaType);
	}

	/**
	 * 获取属性的类型
	 * 
	 * @param attrElement 属性元素
	 * @return 属性类型
	 */
	protected DataObjectAttributeType getDataObjectAttributeType(Element attrElement) throws DataNodeResolveException {
		String attribute = attrElement.getAttribute(NodeNameTool.ATTR_ATTRTYPE);
		if (StringUtils.isBlank(attribute)) {
			// 包含子元素则为对象元素
			if (childNodeContainElementNode(attrElement)) {
				return DataObjectAttributeType.DATA_OBJECT_SOURCE;
			} else {
				return DataObjectAttributeType.ORDINARY;
			}
		}
		DataObjectAttributeType dataObjectAttributeType = EnumUtilsE.valueOf(DataObjectAttributeType.class,
				attribute.toUpperCase());
		if (null == dataObjectAttributeType) {
			throw new DataNodeResolveException("属性元素“" + attrElement.getNodeName() + "”类型(" + attribute + ")不符合规范。规范值："
					+ DataObjectAttributeType.values());
		}
		return dataObjectAttributeType;
	}

	/**
	 * 节点的子节点中是否存在元素节点
	 * 
	 * @param node 节点
	 * @return <code>true</code> 节点的子节点中存在元素节点
	 */
	protected boolean childNodeContainElementNode(Node node) {
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (Node.ELEMENT_NODE == childNode.getNodeType()) {
				return true;
			}
		}
		return false;
	}

}
