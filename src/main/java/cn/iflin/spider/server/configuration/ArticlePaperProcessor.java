package cn.iflin.spider.server.configuration;

import java.util.ArrayList;
import java.util.HashMap;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.server.configuration.Parser.ContentParser;
import cn.iflin.spider.server.configuration.Parser.TimeParser;
import cn.iflin.spider.server.configuration.Parser.TitleParser;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
/**
 * 处理正文页面逻辑
 * @author Jaypan
 *
 */
public class ArticlePaperProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private HashMap<String,String> configure;
	
	public ArticlePaperProcessor(String source){
//		ArrayList<String> info =ConfigurationController
//				.readTxt(source);
//		this.configure = configureParser(info);
	}
	/**
	 * 对传递过来的配置list解析
	 */
	public  HashMap<String,String> configureParser(ArrayList<String> info){
		this.configure = new HashMap<String,String>();
		for(String s : info ){
			if(s.indexOf("titleCSS:")== 0){
				this.configure.put("titleCSS", s.split("titleCSS:")[1]);
			}
			if(s.indexOf("titleXpath:")==0){
				this.configure.put("titleXpath", s.split("titleXpath:")[1]);
			}
			if(s.indexOf("contentCSS:")==0){
				this.configure.put("contentCSS", s.split("contentCSS:")[1]);
			}
			if(s.indexOf("contentXpath:")==0){
				this.configure.put("contentXpath", s.split("contentXpath:")[1]);
			}
			if(s.indexOf("starttimeCSS:")==0){
				this.configure.put("starttimeCSS", s.split("starttimeCSS:")[1]);
			}
			if(s.indexOf("starttimeXpath:")==0){
				this.configure.put("starttimeXpath", s.split("starttimeXpath:")[1]);
			}
			else{
				continue;
			}
		}
		return this.configure;
	}
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page){
		if(this.configure.get("titleCSS").equals("0")){
			page.putField("title", page.getHtml().xpath(this.configure.get("titleXpath")).get());
		}
		if(this.configure.get("titleXpath").equals("0")){
			page.putField("title", page.getHtml().css(this.configure.get("titleCSS")).get());
		}
		if(this.configure.get("contentXpath").equals("0")){
			Selectable contentSelect = page.getHtml().css(this.configure.get("contentCSS"));
			String content = contentSelect.get();
			content = ContentParser.getNewContent(content,contentSelect.links().all());
			page.putField("content", content);
		}
		if(this.configure.get("contentCSS").equals("0")){
			page.putField("content", page.getHtml().xpath(this.configure.get("contentXpath")).get());
		}
		if(this.configure.get("starttimeXpath").equals("0")){
			String time = page.getHtml().css(this.configure.get("starttimeCSS")).get();
			time = TimeParser.getTime(time);
			page.putField("starttime", time);
		}
		if(this.configure.get("starttimeCSS").equals("0")){
			String time = TimeParser.getTime(page.getHtml().xpath(this.configure.get("starttimeXpath")).get());
			page.putField("starttime", time );
		}
	}

	public Site getSite() {
		return site;
	}
	
	/**
	 * 该方法作用为获取文章的标题发布时间截止时间正文
	 */
	public static ArticleModel getArticle(String source,String url){
		ArticleModel model = new ArticleModel();
		Spider spider = Spider.create(new ArticlePaperProcessor(source)).thread(2);
		ResultItems resultItems = spider.get(url);
		model.setTitle(resultItems.getAll().get("title").toString());
		model.setStarttime(resultItems.getAll().get("starttime").toString());
		model.setContent(resultItems.getAll().get("content").toString());
		spider.close();
		return model;
	}
	public static void main(String[] args) {
		String source = "科学技术司";
    	String urlTemplate = "http://www.moe.edu.cn/s78/A16/s8213/A16_sjhj/201706/t20170630_308397.html";
    	Spider spider = Spider.create(new ArticlePaperProcessor(source)).thread(2);
    	ResultItems resultItems = spider.get(urlTemplate);
    	String title = resultItems.getAll().get("title").toString();
        System.out.println(title+TitleParser.projectJudgment(title));
        spider.close();
	}
}
