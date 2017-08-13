package cn.iflin.spider.server.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import cn.iflin.spider.model.MysqlConnection;
import cn.iflin.spider.model.TaskModel;

/**
 * 这个类用来做对配置文件的处理 将网站的配置存放到txt文件中，使用的时候调用该txt 将里面的配置取出来，先进行第一个列表页的URL鉴别
 * 如果有新的url则对正文进行爬取
 * 
 * @author Jaypan
 *
 */
public class TaskSqlConfiguration {
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
		ResultSet result = null;
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		String nextTime="",getNextTime = "SELECT nextTime FROM spider_taskinfo where status='Action'";
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(getNextTime);
			while (result.next()) {
				nextTime = result.getString("nextTime");
			}
			String sql = "INSERT spider_taskinfo VALUE(NULL,'" + source + "','" + texts.get("url") + "','"
					+ texts.get("cssSeletor") + "'" + ",'" + texts.get("xpth") + "','" + texts.get("titleCSS") + "','"
					+ texts.get("titleXpath") + "','" + texts.get("contentCSS") + "','" + texts.get("contentXpath")
					+ "','" + texts.get("starttimeCSS") + "','" + texts.get("starttimeXpath") + "','"+nextTime+"','Action')";
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
	public static boolean changeTaskInfo(String sql) {
		if (sql.equals("")) {
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
	public static void delTaskInfo(String taskId) {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		PreparedStatement preStmt;
		try {
			String sql = "DELETE from spider_taskinfo  WHERE taskId='" + taskId + "'";
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
		String source, taskId, url, nextTime;
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
				starttimeXpath, nextTime, status;
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
				status = result.getString("status");
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
				sm.setXpath(xpath);
				sm.setNextTime(nextTime);
				if(nextTime.equals("1900-01-01 00:00:00.0")){
					sm.setNextTime("Stop");
				}
				sm.setStatus(status);
				taskList.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}



	/**
	 * 设置下一次执行时间
	 */
	public static void setNextTime(String nextTime) {
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		String sql = "UPDATE spider_taskinfo set nextTime='" + nextTime + "'";
		PreparedStatement preStmt;
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	/**
	 * 修改任务状态
	 */
	public static void changeTaskStatus(String taskId, String status) {
		ResultSet result = null;
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		// 通过数据的连接操作数据库
		String sql = "UPDATE spider_taskinfo set status='" + status + "',nextTime='1900/1/1' WHERE taskId='" + taskId + "'";
		String nextTime="",getNextTime = "SELECT nextTime FROM spider_taskinfo where status='Action'";
		PreparedStatement preStmt;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(getNextTime);
			while (result.next()) {
				nextTime = result.getString("nextTime");
			}
			if(status.equals("Action")){
				sql = "UPDATE spider_taskinfo set status='" + status + "',nextTime='"+nextTime+"' WHERE taskId='" + taskId + "'";
			}
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取任务已有的文章数量
	 */
}
