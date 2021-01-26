package org.yelong.core.data.string;

import java.sql.Date;
import java.text.ParseException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.yelong.commons.lang.ArrayUtilsE;
import org.yelong.commons.lang.Strings;
import org.yelong.commons.util.Dates;
import org.yelong.core.data.DataTypeConvertException;

/**
 * oracle.sql.TIMESTAMP
 * 
 * @author PengFei
 * @date 2021年1月25日下午6:40:35
 */
public class StringOracleTIMESTAMPDataTypeConvertor implements StringDataTypeConvertor<Object> {

	private final String[] parsePatterns;

	private final String pattern;

	public StringOracleTIMESTAMPDataTypeConvertor() {
		this(ArrayUtils.toArray(Dates.YYYY_MM_DD_BAR, Dates.YYYY_MM_DD_HH_MM_SS), Dates.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * @param parsePatterns 解析的日期格式
	 * @param pattern       格式话的日期格式
	 */
	public StringOracleTIMESTAMPDataTypeConvertor(String[] parsePatterns, String pattern) {
		this.parsePatterns = ArrayUtilsE.requireNonEmpty(parsePatterns);
		this.pattern = Strings.requireNonBlank(pattern);
	}

	@Override
	public Object convert(String sourceObject) throws DataTypeConvertException {
		if (null == sourceObject) {
			return null;
		}
		try {
			return DateUtils.parseDate(sourceObject, parsePatterns);
		} catch (ParseException e) {
			throw new DataTypeConvertException(e);
		}
	}

	@Override
	public String reverseConvert(Object requiredObject) throws DataTypeConvertException {
		if (null == requiredObject) {
			return null;
		}
		Date date;
		try {
			date = (Date) MethodUtils.invokeMethod(requiredObject, true, "dateValue");
		} catch (Exception e) {
			throw new DataTypeConvertException(e);
		}
		return DateFormatUtils.format(date, pattern);
	}

}
