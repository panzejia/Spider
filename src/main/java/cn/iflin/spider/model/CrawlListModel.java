package cn.iflin.spider.model;

import java.util.HashMap;

import cn.iflin.spider.model.factory.SpiderModel;
/**
 * 爬取列表页链接模型
 * @author Panzejia
 *
 */
public class CrawlListModel implements SpiderModel{
	private String url;
	private String cssSeletor;
	private String xpath;
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public HashMap<String, String> getMap(){
		return map;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCssSeletor() {
		return cssSeletor;
	}

	public String getXpath() {
		return xpath;
	}

	public void setCssSeletor(String cssSeletor) {
		if (cssSeletor.equals("0") == false) {
			map.put("url", cssSeletor);
		}
		this.cssSeletor = cssSeletor;
	}

	public void setXpath(String xpath) {
		if (xpath.equals("0") == false) {
			map.put("url", xpath);
		}
		this.xpath = xpath;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	

}
