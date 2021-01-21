package com.labbol.product.dataferry.core.data.convert;

/**
 * 数据对象转换异常
 */
public class DataObjectConvertException extends Exception {

	private static final long serialVersionUID = -5679512657256078797L;

	public DataObjectConvertException() {
		super();
	}

	public DataObjectConvertException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataObjectConvertException(String message) {
		super(message);
	}

	public DataObjectConvertException(Throwable cause) {
		super(cause);
	}

}
