package cn.iflin.spider.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.iflin.dataprocessing.sql.DAO;
import cn.iflin.spider.model.CrawlContentModel;
import cn.iflin.spider.model.CrawlListModel;


public class IktblDAO {
	/**
	 * 获取数据库中共有多少爬虫任务
	 * @return
	 */
	public static String getSpiderTaskNum(){
		String sql = "select count(*) from spider_taskinfo";
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
	 * 存储到数据库，要修改
	 */
	private static boolean outputConfigurationMysql(String source, HashMap<String, String> texts) {
		String nextTime = "", sql = "SELECT nextTime FROM spider_info_view where status='Action'";
		ResultSet result = DAO.executableQuery(sql);
					try {
						while (result.next()) {
							nextTime = result.getString("nextTime");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					sql = "INSERT spider_taskinfo VALUE(NULL,'" + source + "','" + texts.get("url") + "','"
							+ texts.get("cssSeletor") + "'" + ",'" + texts.get("xpth") + "','" + texts.get("titleCSS") + "','"
							+ texts.get("titleXpath") + "','" + texts.get("contentCSS") + "','" + texts.get("contentXpath")
							+ "','" + texts.get("starttimeCSS") + "','" + texts.get("starttimeXpath") + "','"+nextTime +"','Action');";
					DAO.executableSQL(sql);
					////conn.close();
					return true;
	}

	/**
	 * 获取某个任务的详细信息
	 */
	public static HashMap<String, String> getDetailById(String taskId) {
		HashMap<String, String> detail = new HashMap<String, String>();
		String sql = "SELECT * FROM spider_info_view where taskId='" + taskId + "'";
		ResultSet result = DAO.executableQuery(sql);
		try {
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
			//////conn.close();
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
		DAO.executableSQL(sql);
		return false;
	}

	/**
	 * 刪除任務信息
	 */
	public static void delTaskInfo(String taskId) {
		String sql = "DELETE from spider_taskinfo  WHERE taskId='" + taskId + "';";
		DAO.executableSQL(sql);
	}
	/**
	 * 获取数据库中的任务
	 * 如果要获取所有的任务，task的值为all
	 * @param task
	 * @return
	 */
	public static ArrayList<CrawlListModel> getTaskForList(String task) {
		// 通过数据的连接操作数据库
		ArrayList<CrawlListModel> taskList = new ArrayList<CrawlListModel>();
		String sql ="SELECT * FROM spider_info_view";
		if(task.equals("all")==false){
			sql = "SELECT * FROM spider_info_view where taskId = '"+task+"'";
		}
		ResultSet result = DAO.executableQuery(sql);
		try {
			while (result.next()) {
				CrawlListModel clm = new CrawlListModel();
				clm.setUrl(result.getString("url"));
				clm.setCssSeletor(result.getString("cssSeletor"));
				clm.setXpath(result.getString("xpath"));
				clm.setTaskId(result.getString("taskId"));
				taskList.add(clm);
			}
			////conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	/**
	 * 获取数据库中的任务
	 * 如果要获取所有的任务，task的值为all
	 * @param task
	 * @return
	 */
	public static ArrayList<CrawlContentModel> getTaskForArticle(String task) {
		// 通过数据的连接操作数据库
		ArrayList<CrawlContentModel> taskList = new ArrayList<CrawlContentModel>();
		String sql ="SELECT * FROM spider_info_view";
		if(task.equals("all")==false){
			sql = "SELECT * FROM spider_info_view where taskId = '"+task+"'";
		}
		ResultSet result = DAO.executableQuery(sql);
		try {
			if (result == null) {
				CrawlContentModel sm = new CrawlContentModel();
				sm.setSource("请新建一个任务");
				sm.setTaskId("#");
				taskList.add(sm);
				////conn.close();
				return taskList;
			}
			while (result.next()) {
				CrawlContentModel sm = new CrawlContentModel();
				sm.setSource(result.getString("source"));
				sm.setTaskId(result.getString("taskId"));
				sm.setContentCSS(result.getString("contentCSS"));
				sm.setContentXpath(result.getString("contentXpath"));
				sm.setStarttimeCSS(result.getString("starttimeCSS"));
				sm.setStarttimeXpath(result.getString("starttimeXpath"));
				sm.setTitleCSS(result.getString("titleCSS"));
				sm.setTitleXpath(result.getString("titleXpath"));
				sm.setNextTime(result.getString("nextTime"));
				if (sm.getNextTime().equals("1900-01-01 00:00:00.0")) {
					sm.setNextTime("Stop");
				}
				sm.setStatus(result.getString("status"));
				taskList.add(sm);
			}
			////conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
	/**
	 * 设置下一次执行时间
	 */
	public static void setNextTime(String nextTime) {
		String sql = "UPDATE spider_taskinfo set nextTime='" + nextTime + "'";
		DAO.executableSQL(sql);

	}

	/**
	 * 修改任务状态，要修改
	 */
	public static void changeTaskStatus(String taskId, String status) {
		ResultSet result = null;
		// 通过数据的连接操作数据库
		String sql = "UPDATE spider_taskinfo set status='" + status + "',nextTime='1900/1/1' WHERE taskId='" + taskId
				+ "'";
		String nextTime = "", getNextTime = "SELECT nextTime FROM spider_taskinfo where status='Action'";
		try {
			result = DAO.executableQuery(getNextTime);
			while (result.next()) {
				nextTime = result.getString("nextTime");
			}
			if (status.equals("Action")) {
				sql = "UPDATE spider_taskinfo set status='" + status + "',nextTime='" + nextTime + "' WHERE taskId='"
						+ taskId + "'";
			}
			DAO.executableSQL(sql);
			////conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

}
