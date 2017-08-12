package cn.iflin.spider.server.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 该方法用来传递列表页中的配置信息并对其进行解析
 * @author Jaypan
 *
 */
public class UrlListPaperProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private HashMap<String,String> configure;
	
	public UrlListPaperProcessor(String source){
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
			if(s.indexOf("cssSeletor:")==0){
				this.configure.put("cssSeletor", s.split("cssSeletor:")[1]);
			}
			if(s.indexOf("xpth:")==0){
				this.configure.put("xpath", s.split("xpth:")[1]);
			}
			else{
				continue;
			}
		}
		return this.configure;
	}
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page){
		
		if(this.configure.get("cssSeletor").equals("0")){
			page.putField("url", page.getHtml().xpath(this.configure.get("xpath")).links().all());
		}
		if(this.configure.get("xpath").equals("0")){
			page.putField("url", page.getHtml().css(this.configure.get("cssSeletor")).links().all());
		}
	}

	public Site getSite() {
		return site;
	}
	
	/**
	 * 该方法作为传递过来一个列表页的网址做参数，然后返回一个列表页中链接
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUrlList(String source,String url){
		Spider spider = Spider.create(new UrlListPaperProcessor(source)).thread(2);
    	ResultItems resultItems = spider.get(url);
    	List<String> urlList = (List<String>) resultItems.getAll().get("url");
    	spider.close();
    	return urlList;
	}
	/*
    public static void main(String[] args) {
    	String source = "广东社科规划网";
    	String urlTemplate = "http://www.gdpplgopss.gov.cn/tzgg/";
    	Spider spider = Spider.create(new UrlListPaperProcessor(source)).thread(2);
    	ResultItems resultItems = spider.get(urlTemplate);
        System.out.println(resultItems);
        spider.close();
    }*/
}
