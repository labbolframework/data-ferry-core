package com.labbol.product.dataferry.core.data.defaults;

import java.util.ArrayList;
import java.util.List;

import com.labbol.product.dataferry.core.data.DataObject;
import com.labbol.product.dataferry.core.data.DataObjectSource;
import com.labbol.product.dataferry.core.data.DataObjectSourceProperties;

/**
 * 抽象的数据对象源
 */
public abstract class AbstractDataObjectSource implements DataObjectSource {

	protected final List<DataObject> dataObjects = new ArrayList<>();

	protected DataObjectSourceProperties dataObjectSourceProperties = new DataObjectSourceProperties();;

	@Override
	public void addDataObject(DataObject dataObject) {
		if (dataObject.getDeclaringDataObjectSource() != this) {
			throw new UnsupportedOperationException("无法将“" + dataObject + "”放入到“" + this + "”，因为它属于“"
					+ dataObject.getDeclaringDataObjectSource() + "”");
		}
		dataObjects.add(dataObject);
	}

	@Override
	public List<? extends DataObject> getDataObjects() {
		return dataObjects;
	}

	@Override
	public boolean isEmpty() {
		return dataObjects.isEmpty();
	}

	@Override
	public DataObjectSourceProperties getDataObjectSourceProperties() {
		return dataObjectSourceProperties;
	}

	@Override
	public void setDataObjectSourceProperties(DataObjectSourceProperties dataObjectSourceProperties) {
		this.dataObjectSourceProperties = dataObjectSourceProperties;
	}

}
