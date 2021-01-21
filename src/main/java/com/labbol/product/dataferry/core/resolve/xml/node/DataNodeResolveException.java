package com.labbol.product.dataferry.core.resolve.xml.node;

import com.labbol.product.dataferry.core.resolve.DataFileResolveException;

/**
 * 数据节点解析异常
 */
public class DataNodeResolveException extends DataFileResolveException {

	private static final long serialVersionUID = -1245845467746994756L;

	public DataNodeResolveException() {
		super();
	}

	public DataNodeResolveException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNodeResolveException(String message) {
		super(message);
	}

	public DataNodeResolveException(Throwable cause) {
		super(cause);
	}

}
