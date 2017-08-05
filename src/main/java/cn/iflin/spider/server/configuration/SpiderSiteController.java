package cn.iflin.spider.server.configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.spider.model.MysqlConnection;
import cn.iflin.spider.model.WordModel;

public class SpiderSiteController {
	/**
	 * 获取停用词
	 * 
	 * @return
	 */
	public static ArrayList<WordModel> getStopword() {
		ArrayList<WordModel> stopWordList = new ArrayList<WordModel>();
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		Statement stmt;
		ResultSet result = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM spider_stopword ");
			while (result.next()) {
				WordModel stopWord = new WordModel();
				stopWord.setWordId(result.getString("stopwordId"));
				stopWord.setWord(result.getString("stopword"));
				stopWordList.add(stopWord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stopWordList;
	}

	/**
	 * 添加停用词
	 * 
	 * @param stopword
	 * @return
	 */
	public static boolean addStopword(String stopword) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT spider_stopword VALUE(NULL,'" + stopword + "')";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除停用词
	 * 
	 * @param stopword
	 * @return
	 */
	public static void delStopword(String stopwordId) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "DELETE from spider_stopword  WHERE stopwordId='" + stopwordId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 修改停用词
	 * 
	 * @param stopword
	 * @return
	 */
	public static void changeStopword(String stopwordId, String stopword) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE spider_stopword set stopword='" + stopword + "' WHERE stopwordId='" + stopwordId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 获取选择词
	 * 
	 * @return
	 */
	public static ArrayList<WordModel> getSelectword() {
		ArrayList<WordModel> stopwordList = new ArrayList<WordModel>();
		Connection conn = MysqlConnection.getConnection();
		// 通过数据的连接操作数据库
		Statement stmt;
		ResultSet result = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM spider_selectword ");
			while (result.next()) {
				WordModel stopWord = new WordModel();
				stopWord.setWordId(result.getString("selectwordId"));
				stopWord.setWord(result.getString("selectword"));
				stopwordList.add(stopWord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stopwordList;
	}

	/**
	 * 添加选择词
	 * 
	 * @param stopword
	 * @return
	 */
	public static boolean addSelectword(String selectword) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT spider_selectword VALUE(NULL,'" + selectword + "')";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除选择词
	 * 
	 * @param stopword
	 * @return
	 */
	public static void delSelectword(String selectwordId) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "DELETE from spider_selectword  WHERE selectwordId='" + selectwordId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 修改选择词
	 * 
	 * @param stopword
	 * @return
	 */
	public static void changeSelectword(String selectwordId, String selectword) {
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE spider_selectword set selectword='" + selectword + "' WHERE selectwordId='" + selectwordId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
