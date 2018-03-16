package cn.iflin.spider.model;

import java.util.HashMap;
/**
 * 文章模型
 * @author Panzejia
 *
 */
public class ArticleModel implements Model{
	private String title;
	private String starttime;
	private String source;
	private String stoptime;
	private String content;
	
	public HashMap<String, String> getMap() {
		return map;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStarttime() {
		return starttime;
	}
	public String getSource() {
		return source;
	}
	public void setStarttime(String starttime) {
		map.put("starttime", starttime);
		this.starttime = starttime;
	}

	public void setSource(String source) {
		map.put("source", source);
		this.source = source;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		map.put("stoptime", stoptime);
		this.stoptime = stoptime;
	}

	public void setContent(String content) {
		map.put("content", content);
		this.content = content;
	}





}
