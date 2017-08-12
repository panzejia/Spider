package cn.iflin.spider.server.configuration;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 解析器
 * @author Jaypan
 *
 */
public class Parser {
	/**
	 * 对文章标题判断是否为项目申报信息
	 */
	public static class TitleParser{
		/**
		 * 判断是否为符合申报信息
		 * @param text
		 * @return boolean
		 */
		public static boolean projectJudgment(String text){
	        ArrayList<String> wordList = new ArrayList<String>();
			try {
				//创建分词对象  
				Analyzer anal=new IKAnalyzer(true);
		        if(text==null){
		        	anal.close();
		        	return false;
		        }
		        StringReader reader=new StringReader(text);  
		        //分词  
		        TokenStream ts;
				ts = anal.tokenStream("", reader);
				anal.close();
		        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
		        //遍历分词数据  
		        while(ts.incrementToken()){  
		        	wordList.add(term.toString());
		        }  
		        reader.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
	        int count =0;
	        for(int i =0;i<wordList.size();i++){
	//        	System.out.println(wordList.get(i));
	        	if(NoProjectWordJudgment(wordList.get(i))){
					return false;
	        	}
	        	if(projectWordJudgment(wordList.get(i))){
	        		count++;
	        	}
	        }
	        
	        if(count>=2){
	        	return true;
	        }
	        return false;
		}
		//申报关键词
		private static boolean projectWordJudgment(String word){
			String stopWordsList[] ={"申报","通知","重大","项目","指南","通告","招标","课题","征集","选题","重点"};//常用项目判定词
			for(int i=0;i<stopWordsList.length;++i)
	        {
				
	            if(word.equalsIgnoreCase(stopWordsList[i]))
	                return true;
	            
	        }
			return false;
		}
		//非申报关键词
		private static boolean NoProjectWordJudgment(String word){
			String stopWordsList[] ={"公示","名单","清除","清理","结项","情况","终止","少先队",
					"暑期","防范","造假","2016","2016年","检查","2015","2015年","结","结果","推荐",
					"中期","检查工作","调查","调查工作","少年","青少年","评选","宣讲","活动","撤项","2014","2014年"
					,"2012","2012年","2011","2011年","办法","调整","撤","会议","邀请","召开","评估"};//常用项目判定词
			for(int i=0;i<stopWordsList.length;++i)
	        {
				
	            if(word.equalsIgnoreCase(stopWordsList[i]))
	                return true;
	            
	        }
			return false;
		}
	}
	/**
	 * 时间解析，包括发布时间及截止时间
	 * @author Jaypan
	 *
	 */
	public static class TimeParser{
		public static String getTime(String doc){
			String startTime = "";
			String regex="[0-9]*-[0-9]*-[0-9]*";
			String regex_2="[0-9]*年[0-9]*月[0-9]*日";
			String regex_3="[0-9]*/[0-9]*/[0-9]*日";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(doc);
			Pattern pattern_2 = Pattern.compile(regex_2);
			Matcher matcher_2 = pattern_2.matcher(doc);
			Pattern pattern_3 = Pattern.compile(regex_3);
			Matcher matcher_3 = pattern_3.matcher(doc);
			while(matcher.find()){
				startTime = matcher.group();
			}
			while(matcher_2.find()) {
				startTime = matcher_2.group();
				startTime.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
			}
			while(matcher_3.find()) {
				startTime = matcher_2.group();
				startTime.replaceAll("/", "-").replaceAll("/", "-");
			}
			return startTime;
		}
	}
	/**
	 * 处理正文中的链接问题
	 */
	public static class ContentParser{
		public static String getNewContent(String content, List<String> list){
			String reg = "href=\"[^\\s]*\"";
			ArrayList<String> hrefList = new ArrayList<String>();
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()){
				hrefList.add(matcher.group());
			}
			for(int i =0;i<list.size();i++){
				String newHref = "href=\""+list.get(i)+"\"";
				content = content.replace(hrefList.get(i),newHref );
			}
			return content;
		}
	}
	public static class UrlParser{
		public static boolean checkUrl(String url){
			String reg = "[^\\s]*.htm*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(url);
			if(matcher.find()){
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
		public static boolean setUrlListConfiguration(String source,String liUrl,String cssSeletor,String xpath,
				String titleCSS,String titleXpath,
				String contentCSS,String contentXpath,String starttimeCSS,String starttimeXpath){
			if(cssSeletor.equals("")){
				cssSeletor = "0";
			}
			if(xpath.equals("")){
				xpath = "0";
			}
			if(titleCSS.equals("")){
				titleCSS = "0";
			}
			if(titleXpath.equals("")){
				titleXpath = "0";
			}
			if(contentCSS.equals("")){
				contentCSS = "0";
			}
			if(contentXpath.equals("")){
				contentXpath = "0";
			}
			if(starttimeCSS.equals("")){
				starttimeCSS = "0";
			}
			if(starttimeXpath.equals("")){
				starttimeXpath = "0";
			}
			if(ConfigurationController.addUrlConfiguration(source,liUrl,cssSeletor,xpath,
					titleCSS, titleXpath, contentCSS, contentXpath, starttimeCSS, starttimeXpath)){
				return true;
			}
			return false;
		}
	}
}
