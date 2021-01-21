package com.labbol.product.dataferry.core.data.attribute;

/**
 * 属性引用异常
 */
public class OrdinaryAttributeReferenceException extends RuntimeException {

	private static final long serialVersionUID = -5396676684928045642L;

	public OrdinaryAttributeReferenceException() {
		super();
	}

	public OrdinaryAttributeReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrdinaryAttributeReferenceException(String message) {
		super(message);
	}

	public OrdinaryAttributeReferenceException(Throwable cause) {
		super(cause);
	}

}
