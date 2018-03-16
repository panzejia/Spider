package cn.iflin.spider;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 搜索引擎数据爬虫配置
 * @author Jaypan
 *
 */
public class SearchEngineSpiderConfig  implements PageProcessor{
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
//    	System.out.println(page.getHtml());
//    	System.out.println(page.getHtml().css("#content_left").regex("<a .*>.*</a>"));
    	
        page.putField("text", page.getHtml().css("#content_left").css("a").all());
    }

    public Site getSite() {
        return site;
    }
    
    @SuppressWarnings("unchecked")
	public static List<String> getContent(String url) {
//    	String url ="http://www.seac.gov.cn/art/2017/4/1/art_144_298470.html";
    	Spider spider = Spider.create(new SearchEngineSpiderConfig()).addUrl(url).thread(5);
    	ResultItems resultItems = spider.get(url);
    	List<String>  content = new ArrayList<String>();
    	try{
			content = (List<String>) resultItems.getAll().get("text");
		}catch(NullPointerException e){
		}
		spider.close();
		return content ; 
    }
}
