/**
 * 
 */
package com.labbol.product.dataferry.core.data.model;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.labbol.product.dataferry.core.data.DataObjectOperationType;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
/**
 * 标注在模型上面，可以指定模型转换为数据对象的操作类型。这个注解一般在 ModelDataObjectConvertor 中使用
 * @see ModelDataObjectConvertor
 */
public @interface ModelDataObjectOperationType {

	/**
	 * @return 数据对象操作类型
	 */
	DataObjectOperationType value() default DataObjectOperationType.INSERT_UPDATE;

}
