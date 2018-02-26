package cn.iflin.recommend.configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	/**
	 * 只保留中文
	 * @param text
	 * @return
	 */
	public static String getOnlyChinese(String text){
		String reg = "[\u4e00-\u9fa5]*";
		String t = "";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(reg);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(text);
		while (m.find()) {
			t += m.group();
		}
		return t;
	}
}
