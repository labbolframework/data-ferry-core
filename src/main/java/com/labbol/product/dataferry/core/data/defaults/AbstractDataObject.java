package com.labbol.product.dataferry.core.data.defaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectOrdinaryAttribute;
import com.labbol.product.dataferry.core.data.DataObjectSource;

/**
 * 抽象的数据对象
 */
public abstract class AbstractDataObject implements DataObject {

	protected Map<String, DataObjectOrdinaryAttribute> ordinaryAttributes = new LinkedHashMap<>();

	protected List<DataObjectSource> dataObjectSourceAttributes = new ArrayList<DataObjectSource>();

	@Override
	public DataObjectOrdinaryAttribute addOrdinaryAttribute(String name) {
		return addOrdinaryAttribute(name, null, null);
	}

	@Override
	public DataObjectOrdinaryAttribute addOrdinaryAttribute(String name, Object value) {
		return addOrdinaryAttribute(name, value, null);
	}

	@Override
	public DataObjectOrdinaryAttribute addOrdinaryAttribute(String name, Object value, Class<?> valueType) {
		DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute = new DefaultDataObjectOrdinaryAttribute(name, value,
				valueType);
		addOrdinaryAttribute(dataObjectOrdinaryAttribute);
		return dataObjectOrdinaryAttribute;
	}

	@Override
	public void addOrdinaryAttribute(DataObjectOrdinaryAttribute attribute) {
		ordinaryAttributes.put(attribute.getName(), attribute);
	}

	@Override
	public void removeOrdinaryAttribute(String... names) {
		if (null != names) {
			for (String name : names) {
				ordinaryAttributes.remove(name);
			}
		}
	}

	@Override
	public void removeOrdinaryAttribute(List<String> names) {
		names.forEach(ordinaryAttributes::remove);
	}

	@Override
	public Map<String, Object> getOrdinaryAttributeMaps() {
		Map<String, Object> ordinaryAttributeMaps = new HashMap<String, Object>();
		ordinaryAttributes.forEach((k, v) -> {
			ordinaryAttributeMaps.put(k, v.getValue());
		});
		return ordinaryAttributeMaps;
	}

	@Override
	public List<DataObjectOrdinaryAttribute> getOrdinaryAttributes() {
		return new ArrayList<DataObjectOrdinaryAttribute>(ordinaryAttributes.values());
	}

	@Override
	public boolean isEmptyOrdinaryAttribute() {
		return ordinaryAttributes.isEmpty();
	}

	@Override
	public DataObjectOrdinaryAttribute getOrdinaryAttribute(String name) {
		return ordinaryAttributes.get(name);
	}

	@Override
	public Object getOrdinaryAttributeValue(String name) {
		DataObjectOrdinaryAttribute dataObjectOrdinaryAttribute = getOrdinaryAttribute(name);
		if (null == dataObjectOrdinaryAttribute) {
			return null;
		}
		return dataObjectOrdinaryAttribute.getValue();
	}

	@Override
	public void addDataObjectSourceAttribute(DataObjectSource dataObjectSource) {
		dataObjectSourceAttributes.add(dataObjectSource);
	}

	@Override
	public List<? extends DataObjectSource> getDataObjectSourceAttributes() {
		return dataObjectSourceAttributes;
	}

	@Override
	public boolean isEmptyDataObjectSourceAttribute() {
		return dataObjectSourceAttributes.isEmpty();
	}

}
