package cn.iflin.spider.server.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.model.MysqlConnection;

public class ArticleSqlConfiguration {
	/**
	 * 从数据库中获取所有文章
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ArticleModel> getAllArticle() {
		ArrayList<ArticleModel> articleList = new ArrayList<ArticleModel>();
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "SELECT * FROM context";
		Statement stmt;
		ResultSet result = null;
		String articleId, title, time, context, url,stopTime;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			if (result == null) {
				ArticleModel am = new ArticleModel();
				am.setTitle("暂无文章");
				articleList.add(am);
				return articleList;
			}
			while (result.next()) {
				articleId = result.getString("ArticleId");
				title = result.getString("Title");
				time = result.getString("Time");
				url = result.getString("Url");
				ArticleModel am = new ArticleModel();
				am.setArticleId(articleId);
				am.setTitle(title);
				am.setStarttime(time);
				am.setUrl(url);
				articleList.add(am);
			}
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
		Connection conn = MysqlConnection.getConnection();
		String sql = "SELECT context.ArticleId,context.Url FROM context WHERE " + "context.Source = '" + source
				+ "' ORDER BY context.ArticleId DESC";
		// 通过数据的连接操作数据库
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			String url = "";
			if (result.next()) {
				url = result.getString("Url");
				return url;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 获取已爬取的文章列表
	 */
	public static ArrayList<ArticleModel> getSpiderArticle(String source) {
		ArrayList<ArticleModel> articleList = new ArrayList<ArticleModel>();
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "SELECT * FROM context WHERE Source='" + source + "'";
		Statement stmt;
		ResultSet result = null;
		String articleId, title, time, context, url,stopTime;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			if (result == null) {
				ArticleModel am = new ArticleModel();
				am.setTitle("暂无文章");
				articleList.add(am);
				return articleList;
			}
			while (result.next()) {
				articleId = result.getString("ArticleId");
				title = result.getString("Title");
				time = result.getString("Time");
				context = result.getString("Context");
				url = result.getString("Url");
				stopTime = result.getString("StopTime");
				ArticleModel am = new ArticleModel();
				am.setArticleId(articleId);
				am.setTitle(title);
				am.setStarttime(time);
				am.setContent(context);
				am.setUrl(url);
				am.setStoptime(stopTime);
				articleList.add(am);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}
	/**
	 * 在数据库中添加一篇文章
	 */
	public static void addAticle(String title, String startTime,String stopTime ,String context, String contextNoCode, String url,
			String source) {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "INSERT INTO context(ArticleId,Title,Time,Source,Context,ContextNoCode,Url,StopTime) VALUES (NULL,?,?,?,?,?,?,?)";
		PreparedStatement preStmt;
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, title);
			preStmt.setString(2, startTime);
			preStmt.setString(3, source);
			preStmt.setString(4, context);
			preStmt.setString(5, contextNoCode);
			preStmt.setString(6, url);
			preStmt.setString(7, stopTime);
			preStmt.executeUpdate();
			// if(result!=1){
			// System.out.println("添加失败");
			// }else{
			// System.out.println("添加成功");
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取文章内容
	 */
	public static ArticleModel getArticleInfo(String articleId) {
		ArticleModel am = new ArticleModel();
		ResultSet result = null;
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM context where ArticleId='" + articleId + "'");
			while (result.next()) {
				am.setTitle(result.getString("Title"));
				am.setStarttime(result.getString("Time"));
				am.setStoptime(result.getString("StopTime"));
				am.setContent(result.getString("Context"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return am;
	}

	/**
	 * 刪除文章信息
	 */
	public static void delArticleInfo(String articleId) {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		PreparedStatement preStmt;
		try {
			String sql = "DELETE from context  WHERE ArticleId='" + articleId + "'";
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
