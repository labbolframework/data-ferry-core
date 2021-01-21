package com.labbol.product.dataferry.core.generate.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.yelong.core.data.DataTypeConvertException;
import org.yelong.core.data.DataTypeConvertor;
import org.yelong.core.data.string.StringDataTypeConvertorManager;

import com.labbol.product.dataferry.core.constants.NodeNameTool;
import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectOperationType;
import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;
import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.attribute.DataObjectAttributeType;
import com.labbol.product.dataferry.core.generate.DataFileGenerateException;
import com.labbol.product.dataferry.core.generate.DataFileGenerator;

/**
 * XML数据文件生成器
 */
public class DefaultXMLDataFileGenerator implements DataFileGenerator {

	private StringDataTypeConvertorManager stringDataTypeConvertorManager;

	public DefaultXMLDataFileGenerator(StringDataTypeConvertorManager stringDataTypeConvertorManager) {
		this.stringDataTypeConvertorManager = stringDataTypeConvertorManager;
	}

	@Override
	public void generate(List<? extends DataObjectSource> dataObjectSources, File dateFile)
			throws DataFileGenerateException {
		Document document = genereateDocument(dataObjectSources);
		// 接下来将Element对象转换成xml文档
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer();
			tf.transform(new DOMSource(document), new StreamResult(dateFile));
		} catch (Exception e) {
			throw new DataFileGenerateException(e);
		}
	}

	@Override
	public byte[] generateBytes(List<? extends DataObjectSource> dataObjectSources) throws DataFileGenerateException {
		Document document = genereateDocument(dataObjectSources);
		// 接下来将Element对象转换成xml文档
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			tf.transform(new DOMSource(document), new StreamResult(byteArrayOutputStream));
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw new DataFileGenerateException(e);
		}
	}

	/**
	 * 生成文档
	 * 
	 * @param dataObjectSources 数据对象源
	 * @return 文档
	 * @throws DataFileGenerateException 数据文件生成异常
	 */
	protected Document genereateDocument(List<? extends DataObjectSource> dataObjectSources)
			throws DataFileGenerateException {
		// 创建DocumentBuilderFactory对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder docBulider;
		try {
			docBulider = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new DataFileGenerateException(e);
		}
		// 创建Document对象，Document对象表示整个 HTML 或 XML 文档
		Document document = docBulider.newDocument();
		Element data = document.createElement(NodeNameTool.ROOT_ELEMENT_NAME);
		document.appendChild(data);

		for (DataObjectSource dataObjectSource : dataObjectSources) {
			data.appendChild(dataObjectSourceToElement(dataObjectSource, document));
		}
		return document;
	}

	/**
	 * 数据对象源转换为XML元素
	 * 
	 * @param dataObjectSource 数据对象源
	 * @param document         文档
	 * @return XMl元素
	 */
	public Element dataObjectSourceToElement(DataObjectSource dataObjectSource, Document document)
			throws DataFileGenerateException {
		String tableName = dataObjectSource.getTableName();
		Element tableElement = document.createElement(tableName);
		tableElement.setAttribute(NodeNameTool.ATTR_DATAOPERATIONTYPE,
				dataObjectSource.getDataObjectOperationType().name());
		tableElement.setAttribute(NodeNameTool.ATTR_PRIMARYKEY, dataObjectSource.getPrimaryKey());
		tableElement.setAttribute(NodeNameTool.ATTR_ATTRTYPE, DataObjectAttributeType.DATA_OBJECT_SOURCE.name());
		if (dataObjectSource.getDataObjectOperationType() == DataObjectOperationType.INSERT_DELETE) {
			tableElement.setAttribute(NodeNameTool.ATTR_DELETECONDITION,
					dataObjectSource.getDataObjectSourceProperties().getDeleteCondition());
		}
		List<? extends DataObject> dataObjects = dataObjectSource.getDataObjects();
		if (!dataObjects.isEmpty()) {
			for (DataObject dataObject : dataObjects) {
				Element rowElement = document.createElement(NodeNameTool.ROW);
				tableElement.appendChild(rowElement);
				List<DataObjectOrdinaryAttribute> ordinaryAttributes = dataObject.getOrdinaryAttributes();
				for (DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute : ordinaryAttributes) {
					Element attrElement = buildAttrElement(dataObjectOrdinaryAttribute, document);
					rowElement.appendChild(attrElement);
				}
				List<? extends DataObjectSource> dataObjectSourceAttributes = dataObject
						.getDataObjectSourceAttributes();
				for (DataObjectSource dataObjectSourceAttribute : dataObjectSourceAttributes) {
					rowElement.appendChild(dataObjectSourceToElement(dataObjectSourceAttribute, document));
				}
			}
		}
		return tableElement;
	}

	/**
	 * 构建一个属性元素
	 * 
	 * @param dataObjectOrdinaryAttribute 数据对象属性
	 * @param document                    文档
	 * @return 属性元素
	 * @throws DataFileGenerateException 数据文件生成异常
	 */
	@SuppressWarnings("unchecked")
	protected Element buildAttrElement(DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute, Document document)
			throws DataFileGenerateException {
		String attrName = dataObjectOrdinaryAttribute.getName();
		Element attrElement = document.createElement(attrName);

		// 元素类型。默认就是普通属性，不用展示在标签中了
//		attrElement.setAttribute(NodeNameTool.ATTR_ATTRTYPE, DataObjectAttributeType.ORDINARY.name());

		// 设置数据引用。如果存在数据引用则不用设置属性值
		String reference = dataObjectOrdinaryAttribute.getReference();
		if (StringUtils.isNotBlank(reference)) {
			attrElement.setAttribute(NodeNameTool.ATTR_REF, reference);
			return attrElement;
		}
		// 设置值。将值转换为字符串
		Object value = dataObjectOrdinaryAttribute.getValue();
		String reverseValue = null;
		// 根据值类型转换数据格式
		Class<?> valueType = dataObjectOrdinaryAttribute.getValueType();
		if (null != valueType) {
			attrElement.setAttribute(NodeNameTool.ATTR_JAVATYPE, valueType.getName());
			DataTypeConvertor<String, Object> dataTypeConvertor = (DataTypeConvertor<String, Object>) stringDataTypeConvertorManager
					.getDataTypeConvertor(valueType);
			if (null != dataTypeConvertor) {
				try {
					reverseValue = dataTypeConvertor.reverseConvert(value);
				} catch (DataTypeConvertException e) {
					throw new DataFileGenerateException("属性元素“" + attrName + "”值(" + value + ")转换为(String)异常", e);
				}
			}
		}
		if (null == reverseValue) {
			if (null == value) {
				reverseValue = null;
			} else {
				reverseValue = value.toString();
			}
		}

		attrElement.setTextContent(reverseValue);
		return attrElement;
	}

}
