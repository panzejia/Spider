package cn.iflin.spider.server.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.model.MysqlConnection;
import cn.iflin.spider.model.TaskModel;

/**
 * 这个类用来做对配置文件的处理 将网站的配置存放到txt文件中，使用的时候调用该txt 将里面的配置取出来，先进行第一个列表页的URL鉴别
 * 如果有新的url则对正文进行爬取
 * 
 * @author Jaypan
 *
 */
public class ConfigurationController {
	/**
	 * 建立一个可以存储简单列表页配置的方法<br>
	 * source:来自哪个网站，同时也将作为文件名<br>
	 * url:列表页的URL<br>
	 * cssSeletor:css选择器<br>
	 * xpath:xpath
	 */
	public static boolean addUrlConfiguration(String source, String url, String cssSeletor, String xpath,
			String titleCSS, String titleXpath, String contentCSS, String contentXpath, String starttimeCSS,
			String starttimeXpath) {
		HashMap<String, String> info = new HashMap<String, String>();
		info.put("url", url);
		info.put("cssSeletor", cssSeletor);
		info.put("xpth", xpath);
		info.put("titleCSS", titleCSS);
		info.put("titleXpath", titleXpath);
		info.put("contentCSS", contentCSS);
		info.put("contentXpath", contentXpath);
		info.put("starttimeCSS", starttimeCSS);
		info.put("starttimeXpath", starttimeXpath);
		if (outputConfigurationMysql(source, info)) {
			return true;
		}
		return false;
	}

	/**
	 * 存储到数据库
	 */
	private static boolean outputConfigurationMysql(String source, HashMap<String, String> texts) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT spider_taskinfo VALUE(NULL,'" + source + "','" + texts.get("url") + "','"
					+ texts.get("cssSeletor") + "'" + ",'" + texts.get("xpth") + "','" + texts.get("titleCSS") + "','"
					+ texts.get("titleXpath") + "','" + texts.get("contentCSS") + "','" + texts.get("contentXpath")
					+ "','" + texts.get("starttimeCSS") + "','" + texts.get("starttimeXpath") + "',NULL)";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取某个任务的详细信息
	 */
	public static HashMap<String, String> getDetailById(String taskId) {
		HashMap<String, String> detail = new HashMap<String, String>();
		ResultSet result = null;
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM spider_taskinfo where taskId='" + taskId + "'");
			while (result.next()) {
				detail.put("source", result.getString("source"));
				detail.put("url", result.getString("url"));
				detail.put("cssSeletor", result.getString("cssSeletor"));
				detail.put("xpath", result.getString("xpath"));
				detail.put("titleCSS", result.getString("titleCSS"));
				detail.put("titleXpath", result.getString("titleXpath"));
				detail.put("contentCSS", result.getString("contentCSS"));
				detail.put("contentXpath", result.getString("contentXpath"));
				detail.put("starttimeCSS", result.getString("starttimeCSS"));
				detail.put("starttimeXpath", result.getString("starttimeXpath"));
				detail.put("nextTime", result.getString("nextTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detail;
	}
	
	/**
	 * 修改任務信息
	 */
	public static boolean changeTaskInfo(String sql){
		if(sql.equals("")){
			return false;
		}
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		PreparedStatement preStmt;
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 刪除任務信息
	 */
	public static void delTaskInfo(String taskId){
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		PreparedStatement preStmt;
		try {
			String sql = "DELETE from spider_taskinfo  WHERE taskId='"+taskId+"'";
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库中的任务名称<br>
	 * 主要用在首页的任务获取
	 */
	public static ArrayList<TaskModel> getTaskName() {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		Statement stmt;
		ArrayList<TaskModel> taskList = new ArrayList<TaskModel>();
		ResultSet result = null;
		String source, taskId,url,nextTime;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT taskId,source,url,nextTime FROM spider_taskinfo ");
			if (result == null) {
				TaskModel sm = new TaskModel();
				sm.setSource("请新建一个任务");
				sm.setTaskId("#");
				taskList.add(sm);
				return taskList;
			}
			while (result.next()) {
				source = result.getString("source");
				taskId = result.getString("taskId");
				url = result.getString("url");
				nextTime = result.getString("nextTime");
				TaskModel sm = new TaskModel();
				sm.setSource(source);
				sm.setTaskId(taskId);
				sm.setUrl(url);
				sm.setNextTime(nextTime);
				taskList.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}

	/**
	 * 获取数据库中的任务
	 */
	public static ArrayList<TaskModel> getTask() {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		Statement stmt;
		ArrayList<TaskModel> taskList = new ArrayList<TaskModel>();
		ResultSet result = null;
		String source, taskId, url, cssSeletor, xpath, titleCSS, titleXpath, contentCSS, contentXpath, starttimeCSS,
				starttimeXpath,nextTime;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM spider_taskinfo");
			if (result == null) {
				TaskModel sm = new TaskModel();
				sm.setSource("请新建一个任务");
				sm.setTaskId("#");
				taskList.add(sm);
				return taskList;
			}
			while (result.next()) {
				source = result.getString("source");
				taskId = result.getString("taskId");
				url = result.getString("url");
				cssSeletor = result.getString("cssSeletor");
				xpath = result.getString("xpath");
				titleCSS = result.getString("titleCSS");
				titleXpath = result.getString("titleXpath");
				contentCSS = result.getString("contentCSS");
				contentXpath = result.getString("contentXpath");
				starttimeCSS = result.getString("starttimeCSS");
				starttimeXpath = result.getString("starttimeXpath");
				nextTime = result.getString("nextTime");
				TaskModel sm = new TaskModel();
				sm.setSource(source);
				sm.setTaskId(taskId);
				sm.setCssSeletor(cssSeletor);
				sm.setContentCSS(contentCSS);
				sm.setContentXpath(contentXpath);
				sm.setSource(source);
				sm.setStarttimeCSS(starttimeCSS);
				sm.setStarttimeXpath(starttimeXpath);
				sm.setTitleCSS(titleCSS);
				sm.setTitleXpath(titleXpath);
				sm.setUrl(url);
				sm.setXpath(starttimeXpath);
				sm.setNextTime(nextTime);
				taskList.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}

	/**
	 * 从数据库中获取到分类下的最新的一条通知
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLastUrl(String source) {
		Connection conn = MysqlConnection.getConnection();
		String sql = "SELECT context.AticleId,context.Url FROM context WHERE " + "context.Source = '" + source
				+ "' ORDER BY context.AticleId DESC";
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
	 * 在数据库中添加一篇文章
	 */
	public static void addAticle(String title, String time, String context, String contextNoCode, String url,
			String source) {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "INSERT INTO context(AticleId,Title,Time,Source,Context,ContextNoCode,Url) VALUES (NULL,?,?,?,?,?,?)";
		PreparedStatement preStmt;
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, title);
			preStmt.setString(2, time);
			preStmt.setString(3, source);
			preStmt.setString(4, context);
			preStmt.setString(5, contextNoCode);
			preStmt.setString(6, url);
			preStmt.executeUpdate();
			// if(result!=1){
//			 System.out.println("添加失败");
//			 }else{
//			 System.out.println("添加成功");
//			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 设置下一次执行时间
	 */
	public static void setNextTime(String nextTime){
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "UPDATE spider_taskinfo set nextTime='"+nextTime+"'";
		PreparedStatement preStmt;
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取已爬取的文章列表
	 */
	public static ArrayList<ArticleModel> getSpiderArticle(String source){
		ArrayList<ArticleModel> articleList = new ArrayList<ArticleModel>();
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "SELECT ArticleId,Title,Time,Context,Url FROM context WHERE Source='"+source+"'";
		Statement stmt;
		ResultSet result = null;
		String articleId,title,time,context,url;
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
				ArticleModel am = new ArticleModel();
				am.setArticleId(articleId);
				am.setTitle(title);
				am.setStarttime(time);
				am.setContent(context);
				am.setUrl(url);
				articleList.add(am);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}
	/**
	 * 获取文章内容
	 */
	public static ArticleModel getArticleInfo(String articleId){
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
	public static void delArticleInfo(String articleId){
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		PreparedStatement preStmt;
		try {
			String sql = "DELETE from context  WHERE ArticleId='"+articleId+"'";
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
