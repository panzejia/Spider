package cn.iflin.spider.server.configuration;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.iflin.spider.model.WordModel;

/**
 * 解析器
 * 
 * @author Jaypan
 *
 */
public class Parser {
	/**
	 * 对文章标题判断是否为项目申报信息
	 */
	public static class TitleParser {
		public static void main(String[] args) {
			String text = "广东省哲学社会科学“十三五”规划2016年度项目申报通知";
			System.out.println(projectJudgment(text));
		}
		/**
		 * 判断是否为符合申报信息
		 * 
		 * @param text
		 * @return boolean
		 */
		public static boolean projectJudgment(String text) {
			ArrayList<String> wordList = new ArrayList<String>();
			try {
				// 创建分词对象
				Analyzer anal = new IKAnalyzer(true);
				if (text == null) {
					anal.close();
					return false;
				}
				StringReader reader = new StringReader(text);
				// 分词
				TokenStream ts;
				ts = anal.tokenStream("", reader);
				anal.close();
				CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
				// 遍历分词数据
				while (ts.incrementToken()) {
					wordList.add(term.toString());
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int count = 0;
			for (int i = 0; i < wordList.size(); i++) {
				// System.out.println(wordList.get(i));
				if (NoProjectWordJudgment(wordList.get(i))) {
					return false;
				}
				if (projectWordJudgment(wordList.get(i))) {
					count++;
				}
			}

			if (count >= 2) {
				return true;
			}
			return false;
		}

		// 申报关键词
		private static boolean projectWordJudgment(String word) {
//			String stopWordsList[] = { "申报", "通知", "重大", "项目", "指南", "通告", "招标", "课题", "征集", "选题", "重点" };// 常用项目判定词
			ArrayList<WordModel> selectWordList = SpiderSiteController.getSelectword();
			for (WordModel wm : selectWordList) {
				if (word.equals(wm.getWord())){
					return true;
				}
			}
			return false;
		}

		// 非申报关键词
		private static boolean NoProjectWordJudgment(String word) {
//			String stopWordsList[] = { "公示", "名单", "清除", "清理", "结项", "情况", "终止", "少先队", "暑期", "防范", "造假", "2016",
//					"2016年", "检查", "2015", "2015年", "结", "结果", "推荐", "中期", "检查工作", "调查", "调查工作", "少年", "青少年", "评选",
//					"宣讲", "活动", "撤项", "2014", "2014年", "2012", "2012年", "2011", "2011年", "办法", "调整", "撤", "会议", "邀请",
//					"召开", "评估" };// 常用项目判定词
			ArrayList<WordModel> stopWordList = SpiderSiteController.getStopword();
			for (WordModel wm : stopWordList) {
				if (word.equals(wm.getWord())){
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 时间解析，包括发布时间及截止时间
	 * 
	 * @author Jaypan
	 *
	 */
	public static class TimeParser {
		/**
		 * 1.获取单句中的时间。
		 * 2.获取发布时间
		 */
		public static String getTime(String doc) {
			String startTime = "";
			String regex = "[\\s]*[0-9]*[\\s]*[-/][\\s]*[0-9]*[\\s]*[-/][\\s]*[0-9]*[\\s]*";
			String regex_2 = "[\\s]*[0-9]*[\\s]*年[\\s]*[0-9]*[\\s]*月[\\s]*[0-9]*[\\s]*日";
			String regex_3 = "[\\s]*[0-9]*[\\s]*月[\\s]*[0-9]*[\\s]*日";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(doc);
			Pattern pattern_2 = Pattern.compile(regex_2);
			Matcher matcher_2 = pattern_2.matcher(doc);
			Pattern pattern_3 = Pattern.compile(regex_3);
			Matcher matcher_3 = pattern_3.matcher(doc);
			while (matcher.find()) {
				startTime = matcher.group().replaceAll("/", "-").replaceAll("/", "-");
			}
			while (matcher_3.find()) {
				startTime = matcher_3.group();
				startTime = "2017-"+startTime.replaceAll("月", "-").replaceAll("日", "");
			}
			while (matcher_2.find()) {
				startTime = matcher_2.group();
				startTime = startTime.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
			}
			
			return startTime;
		}
		/**
		 * 获取截止时间
		 */
		public static String getStopTime(String doc) {
			String stopTime = "";
			String regex_1 = "截止[^\\s]*。";
			String regex_3 = "截止[^\\s]*。";
			String regex_2 = "[\\s]*[0-9]*[\\s]*年[\\s]*[0-9]*[\\s]*月[\\s]*[0-9]*[\\s]*日[^\\s]*前[^\\。]*";
			String regex_5 = "[^\\s]*[\\s]*[0-9]*[\\s]*月[\\s]*[0-9]*[\\s]*日[^\\s]*前[^\\。]*";
			Pattern pattern_1 = Pattern.compile(regex_1);
			Matcher matcher_1 = pattern_1.matcher(doc);
			Pattern pattern_2 = Pattern.compile(regex_2);
			Matcher matcher_2 = pattern_2.matcher(doc);
			Pattern pattern_3 = Pattern.compile(regex_3);
			Matcher matcher_3 = pattern_3.matcher(doc);
			Pattern pattern_5 = Pattern.compile(regex_5);
			Matcher matcher_5 = pattern_5.matcher(doc);
			while (matcher_2.find()) {
				stopTime = getTime(matcher_2.group());
			}
			while (matcher_5.find()) {
				stopTime = getTime(matcher_5.group());
			}
			while (matcher_1.find()) {
				stopTime = getTime(matcher_1.group());
			}
//			while (matcher_3.find()) {
//				stopTime = getTime(matcher_3.group());
//			}
			return stopTime;
		}
	}

	/**
	 * 处理正文中的链接问题
	 */
	public static class ContentParser {
		public static String getNewContent(String content, List<String> list) {
			String reg = "href=\"[^\\s]*\"";
			ArrayList<String> hrefList = new ArrayList<String>();
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()) {
				hrefList.add(matcher.group());
			}
			for (int i = 0; i < list.size(); i++) {
				String newHref = "href=\"" + list.get(i) + "\"";
				content = content.replace(hrefList.get(i), newHref);
			}
			return content;
		}

		private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
		private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";// 定义所有w标签

		/**
		 * 過濾html標簽
		 */
		public static String delTag(String htmlStr) {
			Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
			Matcher m_w = p_w.matcher(htmlStr);
			htmlStr = m_w.replaceAll(""); // 过滤script标签

			Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
			Matcher m_space = p_space.matcher(htmlStr);
			htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

			htmlStr = htmlStr.replaceAll(" ", ""); // 过滤
			htmlStr = htmlStr.replaceAll("&nbsp;", "");
			htmlStr = htmlStr.replaceAll("&nbsp", "");
			return htmlStr.trim(); // 返回文本字符串
		}
	}

	public static class UrlParser {
		public static boolean checkUrl(String url) {
			String reg = "[^\\s]*.htm*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				return true;
			}
			return false;
		}

		/**
		 * 
		 * @param source:来源网站名称
		 * @param cssSeletor：CSS选择器
		 * @param xpath：Xpath
		 * @return
		 */
		public static boolean setUrlListConfiguration(String source, String liUrl, String cssSeletor, String xpath,
				String titleCSS, String titleXpath, String contentCSS, String contentXpath, String starttimeCSS,
				String starttimeXpath) {
			if (cssSeletor.equals("")) {
				cssSeletor = "0";
			}
			if (xpath.equals("")) {
				xpath = "0";
			}
			if (titleCSS.equals("")) {
				titleCSS = "0";
			}
			if (titleXpath.equals("")) {
				titleXpath = "0";
			}
			if (contentCSS.equals("")) {
				contentCSS = "0";
			}
			if (contentXpath.equals("")) {
				contentXpath = "0";
			}
			if (starttimeCSS.equals("")) {
				starttimeCSS = "0";
			}
			if (starttimeXpath.equals("")) {
				starttimeXpath = "0";
			}
			if (TaskSqlConfiguration.addUrlConfiguration(source, liUrl, cssSeletor, xpath, titleCSS, titleXpath,
					contentCSS, contentXpath, starttimeCSS, starttimeXpath)) {
				return true;
			}
			return false;
		}

		/**
		 * 获取没有爬取的列表
		 * 使用栈存放，排第一个的文章最后一个存放到数据库中。
		 */
		public static Stack<String> getArticleNoExistedList(List<String> oldUrlList, String source) {
			String url = "";
			url = ArticleSqlConfiguration.getLastUrl(source);
			Stack<String> urlList = new Stack<String>();
			for (int i = 0; i < oldUrlList.size(); i++) {
				if (url.equals(oldUrlList.get(i))) {
					break;
				} else {
					urlList.push(oldUrlList.get(i));
				}
			}
			return urlList;
		}

	}
}
