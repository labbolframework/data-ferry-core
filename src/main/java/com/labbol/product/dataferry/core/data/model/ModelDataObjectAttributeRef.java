/**
 * 
 */
package com.labbol.product.dataferry.core.data.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/**
 * 模型数据对象属性引用。指定该属性的数据引用
 * 
 * @see DataObjectOrdinaryAttributeManager
 */
public @interface ModelDataObjectAttributeRef {

	/**
	 * @return 属性的引用规则
	 */
	String value() default "";

}
