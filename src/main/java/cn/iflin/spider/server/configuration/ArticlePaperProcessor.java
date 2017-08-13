package cn.iflin.spider.server.configuration;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.model.TaskModel;
import cn.iflin.spider.server.configuration.Parser.ContentParser;
import cn.iflin.spider.server.configuration.Parser.TimeParser;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 处理正文页面逻辑
 * 
 * @author Jaypan
 *
 */
public class ArticlePaperProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private String titleCSS = "0";
	private String titleXpath = "0";
	private String contentCSS = "0";
	private String contentXpath = "0";
	private String starttimeCSS = "0";
	private String starttimeXpath = "0";

	public ArticlePaperProcessor(TaskModel taskModel) {
		this.titleCSS = taskModel.getTitleCSS();
		this.titleXpath = taskModel.getTitleXpath();
		this.contentCSS = taskModel.getContentCSS();
		this.contentXpath = taskModel.getContentXpath();
		this.starttimeCSS = taskModel.getStarttimeCSS();
		this.starttimeXpath = taskModel.getStarttimeXpath();
	}

	/**
	 * 对传递过来的配置list解析
	 */
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if (this.titleCSS.equals("0")) {
			String title = page.getHtml().xpath(this.titleXpath).get();
			title = ContentParser.delTag(title);
			page.putField("title", title);
		}
		if (this.titleXpath.equals("0")) {
			String title = page.getHtml().css(this.titleCSS).get();
			title = ContentParser.delTag(title);
			page.putField("title", title);
		}
		if (this.contentXpath.equals("0")) {
			Selectable contentSelect = page.getHtml().css(this.contentCSS);
			String content = contentSelect.get();
			content = ContentParser.getNewContent(content, contentSelect.links().all());
			page.putField("content", content);
			String contentNoCode = ContentParser.delTag(content);
			page.putField("contentNoCode", contentNoCode);
			String stopTime = TimeParser.getStopTime(contentNoCode);
			page.putField("stopTime", stopTime);
		}
		if (this.contentCSS.equals("0")) {
			Selectable contentSelect = page.getHtml().xpath(this.contentXpath);
			String content = contentSelect.get();
			content = ContentParser.getNewContent(content, contentSelect.links().all());
			page.putField("content", content);
			String contentNoCode = ContentParser.delTag(content);
			page.putField("contentNoCode", contentNoCode);
			String stopTime = TimeParser.getStopTime(contentNoCode);
			page.putField("stopTime", stopTime);
		}
		if (this.starttimeXpath.equals("0")) {
			String time = page.getHtml().css(this.starttimeCSS).get();
			time = TimeParser.getTime(time);
			page.putField("starttime", time);
		}
		if (this.starttimeCSS.equals("0")) {
			String time = TimeParser.getTime(page.getHtml().xpath(this.starttimeXpath).get());
			time = TimeParser.getTime(time);
			page.putField("starttime", time);
		}
	}

	public Site getSite() {
		return site;
	}

	/**
	 * 该方法作用为获取文章的标题发布时间截止时间正文
	 */
	public static ArticleModel getArticle(TaskModel taskModel, String articleUrl) {
		ArticleModel model = new ArticleModel();
		Spider spider = Spider.create(new ArticlePaperProcessor(taskModel)).thread(2);
		ResultItems resultItems = spider.get(articleUrl);
		model.setTitle(resultItems.getAll().get("title").toString());
		model.setStarttime(resultItems.getAll().get("starttime").toString());
		model.setStoptime(resultItems.getAll().get("stopTime").toString());
		model.setContent(resultItems.getAll().get("content").toString());
		model.setContentNoCode(resultItems.getAll().get("contentNoCode").toString());
		spider.close();
		return model;
	}

}
