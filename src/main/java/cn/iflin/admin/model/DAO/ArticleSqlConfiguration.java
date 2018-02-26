package cn.iflin.admin.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.iflin.admin.model.ArticleModel;
import cn.iflin.admin.util.TimeUtil;
/**
 * 文章DAO类
 * @author Panzejia
 *
 */
public class ArticleSqlConfiguration {
	/**
	 * 获取数据库中共有多少文章
	 * @return
	 */
	public static String getArticleNum(){
		String sql = "select count(*) from content_info";
		ResultSet result = DAO.executableQuery(sql);
		try {

			while (result.next()) {
				return result.getString("count(*)");
			}
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 从数据库中获取所有文章
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ArticleModel> getAllArticle() {
		ArrayList<ArticleModel> articleList = new ArrayList<ArticleModel>();
		// 通过数据的连接操作数据库
		String sql = "SELECT * FROM article_info_view";
		ResultSet result = DAO.executableQuery(sql);
		String articleId, title, time, url;
		try {
			if (result == null) {
				ArticleModel am = new ArticleModel();
				am.setTitle("暂无文章");
				articleList.add(am);
				// conn.close();
				return articleList;
			}
			while (result.next()) {
				articleId = result.getString("ArticleId");
				title = result.getString("Title");
				time = result.getString("StartTime");
				url = result.getString("Url");
				ArticleModel am = new ArticleModel();
				am.setArticleId(articleId);
				am.setTitle(title);
				am.setStarttime(time);
				am.setUrl(url);
				am.setSource(result.getString("Source"));
				am.setCrawlTime(result.getString("CrawlTime"));
				articleList.add(am);
				
			}
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}

	/**
	 * 从数据库中获取到分类下的最新的一条文章
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLastUrl(String source) {
		String sql = "SELECT ArticleId,Url FROM article_info_view WHERE " + "Source = '" + source
				+ "' ORDER BY ArticleId DESC  limit 1";
		// 通过数据的连接操作数据库
		try {
			ResultSet result = DAO.executableQuery(sql);
			String url = "";
			if (result.next()) {
				url = result.getString("Url");
				// conn.close();
				return url;
			}
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}
	/**
	 * 从数据库中获取到分类下的所有已爬取的网址
	 * 
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,String> getAllUrlByCagetory(String source) {
		String sql = "SELECT Url FROM article_info_view WHERE Source = '" + source+ "'";
		HashMap<String,String> urlMap = new HashMap<String,String>();
		// 通过数据的连接操作数据库
		try {
			ResultSet result = DAO.executableQuery(sql);
			String url = "";
			while (result.next()) {
				url = result.getString("Url");
				// conn.close();
				urlMap.put(url, "");
			}
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return urlMap;
	}

	/**
	 * 获取已爬取的文章列表,需修改
	 */
	public static ArrayList<ArticleModel> getSpiderArticle(String source) {
		ArrayList<ArticleModel> articleList = new ArrayList<ArticleModel>();
		// 通过数据的连接操作数据库
		String sql = "SELECT * FROM article_info_view WHERE Source='" + source + "'";
		ResultSet result = DAO.executableQuery(sql);
		String articleId, title, time, url, stopTime;
		try {
			if (result == null) {
				ArticleModel am = new ArticleModel();
				am.setTitle("暂无文章");
				articleList.add(am);
				// conn.close();
				return articleList;
			}
			while (result.next()) {
				articleId = result.getString("ArticleId");
				title = result.getString("Title");
				time = result.getString("StartTime");
				url = result.getString("Url");
				stopTime = result.getString("StopTime");
				ArticleModel am = new ArticleModel();
				am.setArticleId(articleId);
				am.setTitle(title);
				am.setStarttime(time);
				am.setUrl(url);
				am.setStoptime(stopTime);
				am.setCrawlTime(result.getString("CrawlTime"));
				articleList.add(am);
			}
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}

	/**
	 * 在数据库中添加一篇文章
	 */
	public static void addAticle(String title, String startTime, String stopTime, String context, String contextNoCode,
			String url, String source, String areaId) {
		// 通过数据的连接操作数据库
		String time = TimeUtil.getNowTime();
		String sql = "INSERT INTO content_info(ArticleId,Title,Source,StartTime,StopTime,AreaId,CrawlTime) VALUES (NULL,'" + title
				+ "','" + source + "','" + startTime + "','" + stopTime + "','" + areaId + "','"+time+"');";
		if (DAO.executableSQL(sql)) {

			ResultSet result = DAO
					.executableQuery("SELECT ArticleId FROM content_info ORDER BY ArticleId DESC LIMIT 1");
			try {
				while (result.next()) {
					String id = result.getString("ArticleId");
					sql = "INSERT INTO content_text(ArticleId,Url,Content,Content_NoCode) VALUES ('" + id + "','" + url
							+ "','" + context + "','" + contextNoCode + "')";
					DAO.executableSQL(sql);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 获取文章内容
	 */
	public static ArticleModel getArticleInfo(String articleId) {
		ArticleModel am = new ArticleModel();
		String sql = "SELECT * FROM articleview where ArticleId='" + articleId + "'";
		ResultSet result = DAO.executableQuery(sql);
		try {
			while (result.next()) {
				am.setTitle(result.getString("Title"));
				am.setStarttime(result.getString("StartTime"));
				am.setStoptime(result.getString("StopTime"));
				am.setContent(result.getString("Context"));
			}
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return am;
	}

	/**
	 * 刪除文章信息,待修改
	 */
	public static boolean delArticleInfo(String articleId) {
		String sql = "DELETE from context  WHERE ArticleId='" + articleId + "'";
		if(DAO.executableSQL(sql)){
			return true;
		}else {
			return false;
		}

	}
}
