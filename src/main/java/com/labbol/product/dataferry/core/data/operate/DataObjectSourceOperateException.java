package com.labbol.product.dataferry.core.data.operate;

/**
 * 数据对象源操作异常
 */
public class DataObjectSourceOperateException extends RuntimeException {

	private static final long serialVersionUID = -899933751925894524L;

	public DataObjectSourceOperateException() {
		super();
	}

	public DataObjectSourceOperateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataObjectSourceOperateException(String message) {
		super(message);
	}

	public DataObjectSourceOperateException(Throwable cause) {
		super(cause);
	}

}
