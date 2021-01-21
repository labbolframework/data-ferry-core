package com.labbol.product.dataferry.core.configuration.xml;

import org.springframework.context.annotation.Bean;
import org.yelong.core.data.string.StringDataTypeConvertorManager;

import com.labbol.product.dataferry.core.data.DataObjectSourceFactory;
import com.labbol.product.dataferry.core.generate.DataFileGenerator;
import com.labbol.product.dataferry.core.generate.xml.DefaultXMLDataFileGenerator;
import com.labbol.product.dataferry.core.resolve.xml.DefaultXMLDataFileResolver;
import com.labbol.product.dataferry.core.resolve.xml.XMLDataFileResolver;
import com.labbol.product.dataferry.core.resolve.xml.node.DataNodeResolver;
import com.labbol.product.dataferry.core.resolve.xml.node.DefaultDataNodeResolver;

public class XMLDataFerryConfig {

	// ==================================================数据文件解析器==================================================
	@Bean
	public DataNodeResolver dataNodeResolver(DataObjectSourceFactory dataObjectSourceFactory,
			StringDataTypeConvertorManager stringDataTypeConvertorManager) {
		return new DefaultDataNodeResolver(dataObjectSourceFactory, stringDataTypeConvertorManager);
	}

	@Bean
	public XMLDataFileResolver dataFileResolver(DataNodeResolver dataNodeResolver) {
		return new DefaultXMLDataFileResolver(dataNodeResolver);
	}

	// ==================================================数据文件生成器==================================================

	@Bean
	public DataFileGenerator dataFileGenerator(StringDataTypeConvertorManager stringDataTypeConvertorManager) {
		return new DefaultXMLDataFileGenerator(stringDataTypeConvertorManager);
	}

}
