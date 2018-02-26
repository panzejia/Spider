package cn.iflin.admin.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.admin.model.MysqlConnection;
import cn.iflin.admin.model.UserModel;

/***
 * 用于对用户管理数据库操作
 * @author Jaypan
 *
 */
public class UsermanagerSql {
	/**
	 * 获取数据库中共有多少用户
	 * @return
	 */
	public static String getUserNum(){
		String sql = "select count(*) from userinformation";
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
	 * 获取所有用户信息
	 * @return
	 */
	public static ArrayList<UserModel> getUsers(){
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		
		ResultSet result = null;
		MysqlConnection mysqlconn = new MysqlConnection();
		Connection conn = mysqlconn.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM userinformation");
			while (result.next()) {
				UserModel um = new UserModel();
				um.setId(result.getString("id"));
				um.setInfo(result.getString("info"));
				um.setMail(result.getString("email"));
				um.setName(result.getString("realname"));
				um.setPhone(result.getString("phone"));
				um.setWorkspace(result.getString("workspace"));
				um.setStatus(result.getString("status"));
				users.add(um);
			}
			//////conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
		
	}
	public static UserModel getUser(String id){
		UserModel um = new UserModel();
		ResultSet result = null;
		MysqlConnection mysqlconn = new MysqlConnection();
		Connection conn = mysqlconn.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM userinformation where id='"+id+"'");
			while (result.next()) {
				um.setId(result.getString("id"));
				um.setInfo(result.getString("info"));
				um.setMail(result.getString("email"));
				um.setName(result.getString("realname"));
				um.setPhone(result.getString("phone"));
				um.setWorkspace(result.getString("workspace"));
				um.setStatus(result.getString("status"));
			}
			//////conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return um;
	}
}
