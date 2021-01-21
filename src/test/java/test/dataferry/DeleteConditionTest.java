package test.dataferry;

import java.util.ArrayList;
import java.util.List;

import org.yelong.commons.util.PlaceholderUtilsE;
import org.yelong.commons.util.StringUtilsEE;

public class DeleteConditionTest {

	public static void main(String[] args) {
		String deleteCondition = "userId = #{12345} and creator = #{1a5s64df56}";
		List<String> placeholderValues = PlaceholderUtilsE.getPoundBraceContentAll(deleteCondition);
		String deleteConditionSql = deleteCondition;
		List<String> deleteConditionParams = new ArrayList<String>();
		for (String placeholderValue : placeholderValues) {
			deleteConditionSql = StringUtilsEE.replaceFirst(deleteConditionSql, "#{" + placeholderValue + "}", "?");
			deleteConditionParams.add(placeholderValue);
		}
		System.out.println(deleteConditionSql);
		System.out.println(deleteConditionParams);
	}

}
