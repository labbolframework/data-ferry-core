package com.labbol.product.dataferry.core.data.model.convert;

/**
 * 模型与数据对象的转换异常
 */
public class ModelDataObjectConvertException extends Exception {

	private static final long serialVersionUID = -7461710355112224934L;

	public ModelDataObjectConvertException() {
		super();
	}

	public ModelDataObjectConvertException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelDataObjectConvertException(String message) {
		super(message);
	}

	public ModelDataObjectConvertException(Throwable cause) {
		super(cause);
	}

}
