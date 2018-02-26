package cn.iflin.admin.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.iflin.admin.model.AreaModel;
/**
 * 地区DAO类
 * @author Panzejia
 *
 */
public class AreaSqlConfiguration {
	/**
	 * 根据父地区编号获取子地区信息
	 * @param parentId 父地区编号
	 * @return
	 */
	public static ArrayList<AreaModel> getAreaInfo(String parentId){
		ArrayList<AreaModel> info = new ArrayList<AreaModel>();
		AreaModel area = new AreaModel();
		// 通过数据的连接操作数据库
		String sql = "SELECT * from area  WHERE parentId = '"+parentId+"'";
		ResultSet result = DAO.executableQuery(sql);
		try {
			if (result == null) {
				return null;
			}
			while (result.next()) {
				area.setId(result.getString("id"));
				area.setName(result.getString("name"));
				area.setParentId(result.getString("parentId"));
				info.add(area);
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * 根据地区名获取地区编号
	 * @param name 地区名
	 * @return areaId
	 */
	public static String getAreaId(String name){
		// 通过数据的连接操作数据库
		String sql = "SELECT id from area  WHERE `name` LIKE  '"+name+"%'";
		ResultSet result = DAO.executableQuery(sql);
		try {
			if (result == null) {
				return null;
			}
			while (result.next()) {
				return result.getString("id");
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据名字获取AreaModel
	 * @param name 地区名
	 * @return
	 */
	public static AreaModel getAreaModel(String name){
		AreaModel areaModel = new AreaModel();
		String sql = "SELECT * from area  WHERE `name` LIKE  '"+name+"%'";
		ResultSet result = DAO.executableQuery(sql);
		try {
//			if (result.last()==false) {
//				areaModel.setName("全国");
//				return areaModel;
//			}
			while (result.next()) {
				areaModel.setId(result.getString("id"));
				areaModel.setName(result.getString("name"));
				areaModel.setParentId(result.getString("parentId"));
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaModel;
	}
}
