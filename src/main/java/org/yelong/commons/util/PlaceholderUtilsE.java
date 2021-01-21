package org.yelong.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 占位符工具类
 */
public final class PlaceholderUtilsE {

	private PlaceholderUtilsE() {
	}

	/**
	 * 获取第一个${} 里面的内容。不支持嵌套
	 * 
	 * @param str 字符串
	 * @return ${}里面的内容。如果整个字符串中不含有${} 则返回 <code>null</code>
	 */
	public static String getDollarBraceContent(String str) {
		Pattern regex = Pattern.compile("\\$\\{([^}]*)\\}");
		Matcher matcher = regex.matcher(str);
		while (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	/**
	 * 获取所有 ${} 里面的内容。不支持嵌套
	 * 
	 * @param str 字符串
	 * @return 所有${}里面的内容。如果整个字符串中不含有${} 则返回空集合
	 */
	public static List<String> getDollarBraceContentAll(String str) {
		Pattern regex = Pattern.compile("\\$\\{([^}]*)\\}");
		Matcher matcher = regex.matcher(str);
		List<String> valus = new ArrayList<>();
		while (matcher.find()) {
			valus.add(matcher.group(1));
		}
		return valus;
	}

	/**
	 * 获取第一个#{} 里面的内容。不支持嵌套
	 * 
	 * @param str 字符串
	 * @return #{}里面的内容。如果整个字符串中不含有#{} 则返回 <code>null</code>
	 */
	public static String getPoundBraceContent(String str) {
		Pattern regex = Pattern.compile("\\#\\{([^}]*)\\}");
		Matcher matcher = regex.matcher(str);
		while (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	/**
	 * 获取所有 #{} 里面的内容。不支持嵌套
	 * 
	 * @param str 字符串
	 * @return 所有#{}里面的内容。如果整个字符串中不含有#{} 则返回空集合
	 */
	public static List<String> getPoundBraceContentAll(String str) {
		Pattern regex = Pattern.compile("\\#\\{([^}]*)\\}");
		Matcher matcher = regex.matcher(str);
		List<String> valus = new ArrayList<>();
		while (matcher.find()) {
			valus.add(matcher.group(1));
		}
		return valus;
	}

}
