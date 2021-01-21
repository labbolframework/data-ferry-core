/**
 * 
 */
package org.yelong.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类拓展。
 * 
 * @see StringUtils
 * @since 1.2
 */
public final class StringUtilsEE {

	private StringUtilsEE() {
	}

	/**
	 * 下划线转驼峰 yelong_long ==> yelongLong
	 * 
	 * @param str 需要转换的字符串
	 * @return 下划线转换为驼峰后的字符串
	 */
	public static String underscoreToCamelCase(String str) {
		// 利用正则删除下划线，把下划线后一位改成大写
		Pattern pattern = Pattern.compile("_(\\w)");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer(str);
		if (matcher.find()) {
			sb = new StringBuffer();
			// 将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
			// 正则之前的字符和被替换的字符
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
			// 把之后的也添加到StringBuffer对象里
			matcher.appendTail(sb);
		} else {
			return sb.toString();
		}
		return underscoreToCamelCase(sb.toString());
	}

	/**
	 * 驼峰转下划线 yelongLong ==> yelong_long
	 * 
	 * @param str 需要转换的字符串
	 * @return 驼峰转换为下划线后的字符串
	 */
	public static String camelCaseToUnderscore(String str) {
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer(str);
		if (matcher.find()) {
			sb = new StringBuffer();
			// 将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
			// 正则之前的字符和被替换的字符
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
			// 把之后的也添加到StringBuffer对象里
			matcher.appendTail(sb);
		} else {
			return sb.toString();
		}
		return camelCaseToUnderscore(sb.toString());
	}

	/**
	 * 字符串开始添加斜杠。如果字符串开始字符为斜杠则不会追加
	 * 
	 * @param str 需要添加斜杠的字符串
	 * @return 添加斜杠后的字符串
	 * @since 2.1.3
	 */
	public static String appendStartsSlash(String str) {
		if (null == str) {
			return null;
		}
		if (str.startsWith("/")) {
			return str;
		}
		return "/" + str;
	}

	/**
	 * 字符串结尾添加斜杠。如果字符串结尾字符为斜杠则不会追加
	 * 
	 * @param str 需要添加斜杠的字符串
	 * @return 添加斜杠后的字符串
	 * @since 2.1.3
	 */
	public static String appendEndsSlash(String str) {
		if (null == str) {
			return null;
		}
		if (str.endsWith("/")) {
			return str;
		}
		return str + "/";
	}

	/**
	 * 字符串开始删除斜杠
	 * 
	 * @param str 需要删除斜杠的字符串
	 * @return 删除斜杠后的字符串
	 * @since 2.1.3
	 */
	public static String deleteStartsSlash(String str) {
		if (null == str) {
			return null;
		}
		if (!str.endsWith("/")) {
			return str;
		}
		return str.substring(1);
	}

	/**
	 * 字符串结尾删除斜杠
	 * 
	 * @param str 需要删除斜杠的字符串
	 * @return 删除斜杠后的字符串
	 * @since 2.1.3
	 */
	public static String deleteEndsSlash(String str) {
		if (null == str) {
			return null;
		}
		if (!str.endsWith("/")) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}

	/**
	 * s第二个字符串在第一个字符串中出现的次数 <br/>
	 * str1 = "abcdefc" str2 = "c" return 2
	 * 
	 * @param str1 第一个字符串
	 * @param str2 第二个字符串
	 * @return 第二个字符串在第一个字符串中出现的次数
	 * @since 2.1.3
	 */
	public static int containCount(String str1, String str2) {
		int count = 0;
		if (!str1.contains(str2)) {
			return count;
		}
		while (str1.indexOf(str2) != -1) {
			count++;
			str1 = str1.substring(str1.indexOf(str2) + str2.length());
		}
		return count;
	}

	/**
	 * 字符串替换，左边第一个。这个不适用正则。
	 * 
	 * @param str    源字符串
	 * @param oldStr 目标字符串
	 * @param newStr 替换字符串
	 * @return 替换后的字符串
	 */
	public static String replaceFirst(String str, String oldStr, String newStr) {
		int i = str.indexOf(oldStr);
		if (i == -1)
			return str;
		str = str.substring(0, i) + newStr + str.substring(i + oldStr.length());
		return str;
	}

	/**
	 * 字符串替换，从头到尾查询一次，替换后的字符串不检查。这个不适用正则。
	 * 
	 * @param str    源字符串
	 * @param oldStr 目标字符串
	 * @param newStr 替换字符串
	 * @return 替换后的字符串
	 */
	public static String replaceAll(String str, String oldStr, String newStr) {
		int i = str.indexOf(oldStr);
		while (i != -1) {
			str = str.substring(0, i) + newStr + str.substring(i + oldStr.length());
			i = str.indexOf(oldStr, i + newStr.length());
		}
		return str;
	}

}
