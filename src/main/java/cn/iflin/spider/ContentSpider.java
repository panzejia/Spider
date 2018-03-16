package cn.iflin.spider;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.model.CrawlContentModel;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Selectable;
/**
 * 文章内容爬取
 * @author Panzejia
 *
 */
public class ContentSpider extends SpiderProcessor{
	/**
	 * 继承 SpiderProcessor 实现getContnet方法
	 * @param model 爬取文章模型
	 * @param Selectable 可编辑的selectable
	 * @return ArticleModel 文章模型
	 */
    public ArticleModel getContent(CrawlContentModel model) {
    	this.setSpiderModel(model);
    	Spider spider = Spider.create(new SpiderProcessor()).addUrl(model.getUrl()).thread(5);
    	ResultItems resultItems = spider.get(model.getUrl());
    	ArticleModel article = new ArticleModel();
    	try{
    		Selectable select = (Selectable) resultItems.getAll().get("title");
    		article.setTitle(select.get());
    		select = (Selectable) resultItems.getAll().get("starttime");
    		article.setStarttime(select.get());
    		article.setSource(model.getSource());
    		select = (Selectable) resultItems.getAll().get("content");
    		article.setContent(select.get());
		}catch(NullPointerException e){
		}
		spider.close();
		return article ; 
    }
}
