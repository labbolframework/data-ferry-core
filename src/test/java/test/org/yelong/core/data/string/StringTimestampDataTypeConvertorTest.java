package test.org.yelong.core.data.string;

import java.sql.Timestamp;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.yelong.commons.util.Dates;
import org.yelong.core.data.DataTypeConvertException;
import org.yelong.core.data.string.StringTimestampDataTypeConvertor;

public class StringTimestampDataTypeConvertorTest {

	public static void main(String[] args) throws DataTypeConvertException {
		StringTimestampDataTypeConvertor stringTimestampDataTypeConvertor = new StringTimestampDataTypeConvertor();
		String string = stringTimestampDataTypeConvertor.reverseConvert(new Timestamp(Dates.nowDate().getTime()));
		Timestamp timestamp = stringTimestampDataTypeConvertor.convert(string);
		System.out.println(DateFormatUtils.format(timestamp, Dates.YYYY_MM_DD_HH_MM_SS));
		System.out.println(timestamp);
	}
	
}
