package cn.iflin.admin.server.aritcles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.iflin.admin.model.ArticleModel;
import cn.iflin.admin.model.DAO.DAO;

public class ArticleAnalysis {
	/**
	 * 根据地区名查询该地区下的申报信息
	 * @param range 地区
	 * @return
	 */
	public static ArrayList<ArticleModel> getAllArticle(String range,String sortType) {
		ArrayList<ArticleModel> articles = new ArrayList<ArticleModel>();
		
		String sql = "SELECT * FROM article_info_view WHERE name = ANY(SELECT name FROM area where parentId = (SELECT id FROM area where name LIKE '"+range+"%')) OR  name LIKE '"+range+"%' GROUP BY "+sortType+" DESC";
		ResultSet result = DAO.executableQuery(sql);
		if(result==null){
			return articles;
		}
		try {
			while(result.next()){
				ArticleModel am = new ArticleModel();
				if(result.getString("StartTime")==null||result.getString("StopTime")==null){
					continue;
				}
				am.setArticleId(result.getString("ArticleId"));
				am.setSource(result.getString("Source"));;
				am.setUrl(result.getString("Url"));
				am.setTitle(result.getString("Title"));;
				am.setStarttime(result.getString("StartTime"));;
				am.setStoptime(result.getString("StopTime"));
				am.setArea(result.getString("name"));
				am.setCrawlTime(result.getString("CrawlTime"));
				articles.add(am);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
}
