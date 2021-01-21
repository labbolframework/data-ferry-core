package com.labbol.product.dataferry.core.data;

/**
 * 数据对象源属性
 * 
 * @author PengFei
 * @date 2021年1月20日下午3:10:51
 */
public class DataObjectSourceProperties {

	/**
	 * 删除条件。这只会在操作类型为 INSERT_DELETE 时起作用. 该属性使用 #{} 时表示该值就是一个值。使用${}表示值为一个引用值 <br/>
	 * userId = #{123456789} <br/>
	 * userId = ${co_user.id} 。目前不支持使用${}
	 */
	private String deleteCondition;

	public String getDeleteCondition() {
		return deleteCondition;
	}

	public void setDeleteCondition(String deleteCondition) {
		this.deleteCondition = deleteCondition;
	}

}
