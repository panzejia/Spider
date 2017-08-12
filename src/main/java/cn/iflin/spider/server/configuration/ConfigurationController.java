package cn.iflin.spider.server.configuration;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import cn.iflin.spider.model.MysqlConnection;
import cn.iflin.spider.model.TaskModel;

/**
 * 这个类用来做对配置文件的处理
 * 将网站的配置存放到txt文件中，使用的时候调用该txt
 * 将里面的配置取出来，先进行第一个列表页的URL鉴别
 * 如果有新的url则对正文进行爬取
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
	public static boolean addUrlConfiguration(String source,String url,String cssSeletor,String xpath,
			String titleCSS,String titleXpath,
			String contentCSS,String contentXpath,String starttimeCSS,String starttimeXpath){
		HashMap<String,String> info = new HashMap<String,String>();
		info.put("url",url);
		info.put("cssSeletor",cssSeletor);
		info.put("xpth",xpath);
		info.put("titleCSS",titleCSS);
		info.put("titleXpath",titleXpath);
		info.put("contentCSS",contentCSS);
		info.put("contentXpath",contentXpath);
		info.put("starttimeCSS",starttimeCSS);
		info.put("starttimeXpath",starttimeXpath);
		if(outputConfigurationMysql(source,info)){
			return true;
		}
		return false;
	}
	/**
	 * 存储到数据库
	 */
	private static boolean outputConfigurationMysql(String source,HashMap<String,String> texts){
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try{
			stmt = conn.createStatement();
            String sql = "INSERT spiderTaskInfo VALUE(NULL,'"+source+"','"+texts.get("url")+"','"
			+texts.get("cssSeletor")+"'"+ ",'"+texts.get("xpth")+"','"+texts.get("titleCSS")+"','"
            +texts.get("titleXpath")+"','"+texts.get("contentCSS")+"','"+texts.get("contentXpath")+"','"
			+texts.get("starttimeCSS")+ "','"+texts.get("starttimeXpath")+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            return true;
            }catch(SQLException e1) {    
                e1.printStackTrace();   
            } 
		return false;
	}
	/**
	 * 获取某个任务的详细信息
	 */
	public static HashMap<String,String> getDetail(String taskId){
		HashMap<String,String> detail = new HashMap<String,String>();
		ResultSet result=null;
		Connection conn = MysqlConnection.getConnection();
		Statement stmt;
		try
        {
			stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM spiderTaskInfo where taskId='"+taskId+"'");
            while(result.next()){
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
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
		return detail;
	}
	/**
	 * 获取数据库中的任务
	 */
	public static ArrayList<TaskModel> getTaskName(){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		ArrayList<TaskModel> taskList = new ArrayList<TaskModel>();
        ResultSet result = null;
        String source,taskId;
        try
        {
        	stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT taskId,source FROM spiderTaskInfo");
            if(result==null){
            	TaskModel sm = new TaskModel();
            	sm.setSource("请新建一个任务");
            	sm.setTaskId("#");
            	taskList.add(sm);
            	return taskList;
            }
            while(result.next()){
            	source=result.getString("source");
            	taskId=result.getString("taskId");
            	TaskModel sm = new TaskModel();
            	sm.setSource(source);
            	sm.setTaskId(taskId);
            	taskList.add(sm);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
		return taskList;
	}
}
