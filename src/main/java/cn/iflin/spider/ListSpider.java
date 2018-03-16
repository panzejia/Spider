package cn.iflin.spider;

import java.util.ArrayList;
import java.util.List;

import cn.iflin.spider.model.CrawlListModel;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 文章列表页的链接爬取
 * @author Panzejia
 *
 */
public class ListSpider extends SpiderProcessor{
	/**
	 * 继承 SpiderProcessor 实现getListUrl方法
	 * @param model 爬取列表模型
	 * @param Selectable 可编辑的selectable
	 * @return List<String> 链接列表
	 */
	public List<String> getListUrl(CrawlListModel model) {
		//设置spiderModel的静态变量
    	this.setSpiderModel(model);
    	Spider spider = Spider.create(new ListSpider()).thread(1);
    	ResultItems resultItems = spider.get(model.getUrl());
    	List<String> list = new ArrayList<String>();
    	try{
    		Selectable select = (Selectable) resultItems.getAll().get("url");
    		list =  select.links().all() ;
		}catch(NullPointerException e){
		}
		spider.close();
		return list ; 
    }
}
