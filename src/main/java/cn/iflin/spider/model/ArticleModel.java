package cn.iflin.spider.model;

public class ArticleModel {
	private String title;
	private String starttime;
	private String stoptime;
	private String content;
	private String contentNoCode;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getStoptime() {
		return stoptime;
	}
	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentNoCode() {
		return contentNoCode;
	}
	public void setContentNoCode(String contentNoCode) {
		this.contentNoCode = contentNoCode;
	}
}
