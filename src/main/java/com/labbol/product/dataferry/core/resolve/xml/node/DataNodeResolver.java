/**
 * 
 */
package com.labbol.product.dataferry.core.resolve.xml.node;

import org.w3c.dom.Node;
import org.yelong.core.annotation.Nullable;

import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 数据节点解析器
 */
public interface DataNodeResolver {

	/**
	 * 解析数据节点为数据对象源
	 * 
	 * @param dataNode 数据节点
	 * @return 数据对象源
	 * @throws DataNodeResolveException 数据节点解析异常
	 */
	@Nullable
	DataObjectSource resolve(Node dataNode) throws DataNodeResolveException;

}
