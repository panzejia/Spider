package cn.iflin.admin.server.spider.configuration;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 该类用来预览
 * @author Jaypan
 *
 */
public class ViewPaperProcessor implements PageProcessor{
	 // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等s
		private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
		private String css;
		private String xpath;
		public ViewPaperProcessor(String css,String xpath){
			this.css = css;
			this.xpath=xpath;
		}
		// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
		public void process(Page page){
			if(this.xpath.equals("0")||this.xpath.equals("")){
				page.putField("content", page.getHtml().css(this.css).toString());
			}
			if(this.css.equals("0")||this.css.equals("")){
				page.putField("content", page.getHtml().xpath(this.xpath).toString());
			}
		}

		public Site getSite() {
			return site;
		}
		
		/**
		 * 获取到预览的内容
		 */
		public static String getViewContent(String url,String css,String xpath){
			if(css.equals("")){
				css = "0";
			}
			if(xpath.equals("")){
				xpath = "0";
			}
			Spider spider = Spider.create(new ViewPaperProcessor(css,xpath)).thread(2);
			ResultItems resultItems = spider.get(url);
			String content = (String) resultItems.getAll().get("content");
			spider.close();
			return content;
		}
		/*
	    public static void main(String[] args) {
	    	String css = "body";
	    	String xpath = "";
	    	Spider spider = Spider.create(new ViewPaperProcessor(css,xpath)).thread(2);
	    	String urlTemplate = "http://www.moe.edu.cn/s78/A16/s8213/A16_sjhj/201706/t20170630_308397.html";
	    	ResultItems resultItems = spider.get(urlTemplate);
	        System.out.println(resultItems);
	        spider.close();
	    }
	    */
}
