package cn.iflin.admin.server.dataanalysis;

import java.util.ArrayList;

import cn.iflin.admin.model.ArticleModel;
import cn.iflin.admin.model.DAO.ArticleSqlConfiguration;
import cn.iflin.admin.model.DAO.TaskSqlConfiguration;
import cn.iflin.admin.model.DAO.UsermanagerSql;
import cn.iflin.admin.util.TimeUtil;

/**
 * 分析爬虫任务状态
 * @author Panzejia
 *
 */
public class DateAnalysis {
	public static ArrayList<String> getIndexChart(){
		ArrayList<String> statusList = new ArrayList<String>();
		statusList.add(ArticleSqlConfiguration.getArticleNum());
		statusList.add(TaskSqlConfiguration.getSpiderTaskNum());
		statusList.add(UsermanagerSql.getUserNum());
		return statusList;
	}

	/**
	 * 获取N天每个任务的爬取状态
	 * @param dayNum N天
	 * @param source 爬虫任务
	 * @return
	 */
	public static ArrayList<String> getSpiderStatusByDayNum(int dayNum,String source){
		ArrayList<String> statusList = new ArrayList<String>();
		ArrayList<ArticleModel> articles =new ArrayList<ArticleModel>();
		if(source.equals("all")){
			articles = ArticleSqlConfiguration.getAllArticle();
		}else{
			articles = ArticleSqlConfiguration.getSpiderArticle(source);
		}
		for(int i =1;i<=dayNum;i++){
			int num = 0;
			for(ArticleModel am : articles){
				if(am.getCrawlTime()==null||am.getCrawlTime().equals("")){
					continue;
				}
				if(TimeUtil.isInRangeDay(am.getCrawlTime(), i)){
					num++;
				}
			}
			statusList.add(String.valueOf(num));
			
		}

		return statusList;
	}
}
