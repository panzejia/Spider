package cn.iflin.admin.server.spider.configuration;


import java.util.List;

import cn.iflin.admin.model.TaskModel;
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
	private static String cssSeletor="0" ;
	private static String xpath="0" ;

	public UrlListPaperProcessor() {


	}


	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if (cssSeletor.equals("0")) {
			System.out.println(xpath);
			page.putField("url", page.getHtml().xpath(xpath).links().all());
		}
		if (xpath.equals("0")) {
			page.putField("url", page.getHtml().css(cssSeletor).links().all());
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
		if (taskModel.getXpath().equals("0")) {
			cssSeletor = taskModel.getCssSeletor();
			xpath = "0";	//因为静态数据是共享的 所以需要将这里重新设置为0，否则会调用上一个爬虫任务使用的数据
		}
		if (taskModel.getCssSeletor().equals("0")) {
			xpath = taskModel.getXpath();
			cssSeletor = "0";
		}
		Spider spider = Spider.create(new UrlListPaperProcessor()).thread(3);
		ResultItems resultItems = spider.get(taskModel.getUrl());
		List<String> urlList = (List<String>) resultItems.getAll().get("url");
		spider.close();
		return urlList;
	}
	
//	  public static void main(String[] args) { 
//		  TaskModel tm = new TaskModel();
//		  tm.setUrl("http://www.gdpplgopss.gov.cn/tzgg");
//		  tm.setCssSeletor("body > div.cm > div.nlist > ul");
//		  tm.setXpath("0");
//		  List<String> urlList = getUrlList(tm);
//		  for (String url :urlList){
//			  System.out.println(url);
//		  }
//	  }
	 
}
