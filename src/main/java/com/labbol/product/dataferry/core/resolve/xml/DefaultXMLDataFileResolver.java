package com.labbol.product.dataferry.core.resolve.xml;

import org.w3c.dom.Node;

import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.resolve.DataFileResolveException;
import com.labbol.product.dataferry.core.resolve.xml.node.DataNodeResolver;

/**
 * 默认的数据文件解析器
 */
public class DefaultXMLDataFileResolver extends AbstractXMLDataFileResolver {

	protected final DataNodeResolver dataNodeResolver;

	public DefaultXMLDataFileResolver(DataNodeResolver dataNodeResolver) {
		this.dataNodeResolver = dataNodeResolver;
	}

	@Override
	protected DataObjectSource resolveToDataObjectSource(Node node) throws DataFileResolveException {
		return dataNodeResolver.resolve(node);
	}

}
