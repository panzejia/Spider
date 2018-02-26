package cn.iflin.admin.model;
/**
 * 文章数据模型
 * @author Panzejia
 *
 */
public class ArticleModel {
	private String title;
	private String starttime;
	private String stoptime;
	private String content;
	private String source;
	private String area;
	private String crawlTime;
	/**
	 * @return the crawlTime
	 */
	public String getCrawlTime() {
		return crawlTime;
	}
	/**
	 * @param crawlTime the crawlTime to set
	 */
	public void setCrawlTime(String crawlTime) {
		this.crawlTime = crawlTime;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	private String contentNoCode;
	private String articleId;
	private String url;
	/**
	 * @return the articleId
	 */
	public String getArticleId() {
		return articleId;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
