package cn.iflin.spider;

import cn.iflin.spider.util.TextFilePipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 全站数据爬虫配置 再修改
 * @author Jaypan
 *
 */
public class FullSiteSpiderConfig implements PageProcessor {
	// 如果下一个爬虫使用的是上一个爬虫的网址，说明这里静态变量出错
	private static String url;
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
	/**
	 * 实现PageProcessor接口的方法
	 */
	public void process(Page page) {
		//过滤掉非本站的网址
		page.addTargetRequests(page.getHtml().links().regex(url + "[^\\s]*").all());
		
		page.putField("text", page.getHtml().toString());
	}
	public Site getSite() {
		return site;
	}
	
	public static void get(String crwalUrl) {
		url = crwalUrl;
		//addpipeline 可以保存这个爬虫的結果
		Spider.create(new FullSiteSpiderConfig()).thread(5).addPipeline(new TextFilePipeline("D:\\webmagic\\"))
				.addUrl(url).start();
	}

}
