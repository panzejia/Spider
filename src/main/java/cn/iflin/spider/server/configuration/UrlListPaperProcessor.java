package cn.iflin.spider.server.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.iflin.spider.model.TaskModel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 该方法用来传递列表页中的配置信息并对其进行解析
 * 
 * @author Jaypan
 *
 */
public class UrlListPaperProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private String cssSeletor ="0";
	private String xpath ="0";

	public UrlListPaperProcessor(TaskModel taskModel) {
		if (taskModel.getXpath().equals("0")) {
			this.cssSeletor = taskModel.getCssSeletor();
		}
		if (taskModel.getCssSeletor().equals("0")) {
			this.xpath = taskModel.getXpath();
		}

	}


	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if (this.cssSeletor.equals("0")) {
			page.putField("url", page.getHtml().xpath(this.xpath).links().all());
		}
		if (this.xpath.equals("0")) {
			page.putField("url", page.getHtml().css(this.cssSeletor).links().all());
		}
		
	}

	public Site getSite() {
		return site;
	}

	/**
	 * 该方法作为传递过来一个列表页的网址做参数，然后返回一个列表页中链接
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUrlList(TaskModel taskModel) {
		Spider spider = Spider.create(new UrlListPaperProcessor(taskModel)).thread(2);
		ResultItems resultItems = spider.get(taskModel.getUrl());
		List<String> urlList = (List<String>) resultItems.getAll().get("url");
		spider.close();
		return urlList;
	}
	/*
	 * public static void main(String[] args) { String source = "广东社科规划网";
	 * String urlTemplate = "http://www.gdpplgopss.gov.cn/tzgg/"; Spider spider
	 * = Spider.create(new UrlListPaperProcessor(source)).thread(2); ResultItems
	 * resultItems = spider.get(urlTemplate); System.out.println(resultItems);
	 * spider.close(); }
	 */
}
