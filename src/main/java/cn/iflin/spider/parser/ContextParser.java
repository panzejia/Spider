package cn.iflin.spider.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.iflin.spider.model.ArticleModel;
/**
 * 提取正文,时间,标题
 * @author Jaypan
 *
 */
public class ContextParser {
	public static ArticleModel getHtmlInfo(String html) {
		ArticleModel context = getHtmlInfo(html,"div");
		if(context==null){
			context = getHtmlInfo(html,"td");
		}
		return context;
	}
	
	private static ArticleModel getHtmlInfo(String html,String tag) {
		String title = "",starttime = "",content = "",stoptime = "";
		ArticleModel cm = new ArticleModel();
		Document doc = Jsoup.parse(html);
		Elements e = doc.getElementsByTag(tag);

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 添加
		for (int i = 0; i < e.size(); i++) {
			String s = SpiderParser.delTab(e.get(i).html());
			if (s.equals("")) {
				continue;
			}
			map.put(i, s.length());
		}
		// 添加结束

		// 从大到小排序
		List<Map.Entry<Integer, Integer>> list1 = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
		// 对sort进行设置为排序 类型
		Collections.sort(list1, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		//获取标题
		title = getTitle(e,list1);
		// 计算
		float f = 2.5f;
		for (int i = 0; i < list1.size() - 1; i++) {
			float value = list1.get(i).getValue() / list1.get(i + 1).getValue();
			if (value > f) {
				//获取时间
				if(i==0){
					starttime = getTime(SpiderParser.delTab(e.get(list1.get(i).getKey()).html()));
				}else{
					starttime = getTime(SpiderParser.delTab(e.get(list1.get(i-1).getKey()).html()));
				}
				//获取正文
				content = SpiderParser.delTab(e.get(list1.get(i).getKey()).html());
				break;
			}
		}
		//获取截止时间
		stoptime = getStopTime(content);
		if(starttime!=null&&stoptime!=null&content!=null){
			cm.setTitle(title);
			cm.setStarttime(starttime);
			cm.setStoptime(stoptime);
			cm.setContent(content);
			return cm;
		}
		return cm;
	}
	private static String getTitle(Document doc){
		String title = "";
		for(int i  = 0;i<6;i++){
			if(title.equals("")){
				title = doc.tagName("h"+i).text();
			}else{
				break;
			}
		}
		return title;
	}
	/**
	 * 获取标题，从小到大排序div大小，判断哪一段拥有标题。
	 * @param e
	 * @param list
	 * @return
	 */
	private static String getTitle(Elements e,List<Map.Entry<Integer, Integer>> list){
		String[] stopwords = {"申报","通知","重大","项目","指南","通告","招标","课题","征集","选题","重点","公告"};
		int flag = 0;
		for (int i = list.size()-1; i >= 0; i--) {
			flag=0;
			String s = SpiderParser.delTab(e.get(list.get(i).getKey()).html());
			for(String word : stopwords){
				if(s.contains(word)){
					flag++;
				}
				if(flag==2)return s;
			}
		}
		return "";
	}
	/**
	 * 获取发布时间
	 * @param doc
	 * @return
	 */
	private static String getTime(String doc) {
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
			startTime = "2018-"+startTime.replaceAll("月", "-").replaceAll("日", "");
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
		String regex_3 = "受理[^\\s]*。";
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
		while (matcher_3.find()) {
			stopTime = getTime(matcher_3.group());
		}
		return stopTime;
	}
}

