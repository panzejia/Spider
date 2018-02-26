package cn.iflin.admin.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.admin.model.MysqlConnection;

/**
 * 数据访问层
 * @author Panzejia
 *
 */
public class DAO {
	/**
	 * 执行sql语句，返回boolean类型
	 * 主要使用方面：增删改
	 */
	public static boolean executableSQL(String sql){
		MysqlConnection mysqlconn = new MysqlConnection();
		Connection conn = mysqlconn.getConnection();
		// 通过数据的连接操作数据库
		PreparedStatement preStmt;
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 执行sql语句，返回ResultSet
	 * 主要使用：查询数据
	 */
	public static ResultSet executableQuery(String sql){
		MysqlConnection mysqlconn = new MysqlConnection();
		Connection conn = mysqlconn.getConnection();
		ResultSet result = null;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		ArrayList<String> list = new ArrayList<String>();
		list.add("null");
		list.add("test");
		list.add("test2");
		dao.addRecording("area", list);
	}
	/** 不加 影响性能
	 * 获取数据库中表的列名
	 * @param conn 数据库连接
	 * @param tableName 表名
	 * @return
	 
	private static ArrayList<String> getColumName(Connection conn , String tableName){
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select * from "+tableName;
		PreparedStatement stmt ;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData data = result.getMetaData();
			String columName ="";
			for(int i =1 ; i <= data.getColumnCount();i++){
				columName  = data.getColumnName(i);
				list.add(columName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	*/
	/**
	 * 添加一条记录
	 * @param tableName 表名
	 * @param list 列表，按顺序的
	 */
	public void addRecording(String tableName,ArrayList<String> list){
		String sql = "INSERT  "+tableName+" VALUE(";
		MysqlConnection mysqlconn = new MysqlConnection();
		Connection conn = mysqlconn.getConnection();
		PreparedStatement preStmt;
		for(String s : list){
			sql += "'"+s+"',";
		}
		sql = sql.substring(0, sql.lastIndexOf(","))+")";
		System.out.println(sql);
		try {
			preStmt = conn.prepareStatement(sql);
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
