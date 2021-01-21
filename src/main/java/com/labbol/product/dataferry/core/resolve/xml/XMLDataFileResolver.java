package com.labbol.product.dataferry.core.resolve.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.yelong.support.xml.dom.Documents;

import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.resolve.DataFileResolveException;
import com.labbol.product.dataferry.core.resolve.DataFileResolver;

/**
 * XML数据文件解析器
 */
public interface XMLDataFileResolver extends DataFileResolver {

	@Override
	default List<DataObjectSource> resolve(InputStream dateInputStream) throws DataFileResolveException {
		try {
			return resolve(Documents.parseDocument(dateInputStream));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DataFileResolveException(e);
		}
	}

	/**
	 * 解析数据
	 * 
	 * @param document 文档
	 * @return 数据对象源集合
	 * @throws DataFileResolveException 数据文件解析异常
	 */
	List<DataObjectSource> resolve(Document document) throws DataFileResolveException;

}
