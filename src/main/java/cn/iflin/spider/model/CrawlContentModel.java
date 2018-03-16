package cn.iflin.spider.model;

import java.util.HashMap;

import cn.iflin.spider.model.factory.SpiderModel;

/**
 * 爬取文章模型
 * 
 * @author Panzejia
 */
public class CrawlContentModel implements SpiderModel {
	HashMap<String, String> map = new HashMap<String, String>();
	private String source;
	private String url;
	private String titleCSS;
	private String titleXpath;
	private String contentCSS;
	private String contentXpath;
	private String starttimeCSS;
	private String starttimeXpath;
	private String articleCount;
	private String status;
	private String taskId;

	public HashMap<String, String> getMap() {
		return this.map;
	}

	public String getArticleCount() {
		return articleCount;
	}

	public String getStatus() {
		return status;
	}

	private String nextTime;


	public String getNextTime() {
		return nextTime;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getSource() {
		return source;
	}

	public String getUrl() {
		return url;
	}

	public String getTitleCSS() {
		return titleCSS;
	}

	public String getTitleXpath() {
		return titleXpath;
	}

	public String getContentCSS() {
		return contentCSS;
	}

	public String getContentXpath() {
		return contentXpath;
	}

	public String getStarttimeCSS() {
		return starttimeCSS;
	}

	public String getStarttimeXpath() {
		return starttimeXpath;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitleCSS(String titleCSS) {
		if (titleCSS.equals("0") == false) {
			map.put("title", titleCSS);
		}
		this.titleCSS = titleCSS;
	}

	public void setTitleXpath(String titleXpath) {
		if (titleXpath.equals("0") == false) {
			map.put("title", titleXpath);
		}
		this.titleXpath = titleXpath;
	}

	public void setContentCSS(String contentCSS) {
		if (contentCSS.equals("0") == false) {
			map.put("content", contentCSS);
		}
		this.contentCSS = contentCSS;
	}

	public void setContentXpath(String contentXpath) {
		if (contentXpath.equals("0") == false) {
			map.put("content", contentXpath);
		}
		this.contentXpath = contentXpath;
	}

	public void setStarttimeCSS(String starttimeCSS) {
		if (starttimeCSS.equals("0") == false) {
			map.put("starttime", starttimeCSS);
		}
		this.starttimeCSS = starttimeCSS;
	}

	public void setStarttimeXpath(String starttimeXpath) {
		if (starttimeXpath.equals("0") == false) {
			map.put("starttime", starttimeXpath);
		}
		this.starttimeXpath = starttimeXpath;
	}
	
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}
}
