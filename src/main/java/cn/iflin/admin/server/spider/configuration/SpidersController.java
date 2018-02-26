package cn.iflin.admin.server.spider.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import cn.iflin.admin.model.ArticleModel;
import cn.iflin.admin.model.TaskModel;
import cn.iflin.admin.model.DAO.AreaSqlConfiguration;
import cn.iflin.admin.model.DAO.ArticleSqlConfiguration;
import cn.iflin.admin.model.DAO.TaskSqlConfiguration;
import cn.iflin.admin.server.spider.configuration.Parser.AreaPaser;
import cn.iflin.admin.server.spider.configuration.Parser.TitleParser;
import cn.iflin.admin.server.spider.configuration.Parser.UrlParser;
import cn.iflin.admin.server.timer.LuceneIndexController;


/**
 * 爬虫模块核心逻辑算法
 * @author Panzejia
 *
 */
public class SpidersController {
	/**
	 * 爬取所有数据库中爬虫任务
	 */
	public static void actionAllSpider() {
		spiderFrame("all");
	}
	
	/**
	 * 运行单个爬虫任务
	 */
	public static void actionSingleSpider(String taskId){
		spiderFrame(taskId);
	}
	
	/**
	 * 爬虫核心逻辑，task为“all”时将开启数据库所有的爬虫任务
	 * @param task
	 */
	private static void spiderFrame(String task){
		ArrayList<TaskModel> taskList = TaskSqlConfiguration.getTask(task);
		//遍历所有的爬虫任务
		for (TaskModel taskModel : taskList) {
			// 如果状态为Stop继续下一个循环
			if (taskModel.getStatus().equals("Stop")) { 
				System.out.println(taskModel.getSource());
				continue;
			}
			// 获取已爬取的URL列表
			List<String> urlList = UrlListPaperProcessor.getUrlList(taskModel);
			// 返回一个没有爬取的URL栈
			Stack<String> newUrlList = UrlParser.getArticleNoExistedList(urlList, taskModel.getSource());
			//需本次要爬取的条数
			int count = newUrlList.size(); 
			System.out.println("共有" + count + "条需要爬取");
			//将本次的爬取条数信息记录到数据库中的spider_run_info表中
			
			//获取需要爬取的页面内容
			for (int i = 0; i < count; i++) {
				String articleUrl = newUrlList.pop();
				// String articleUrl = urlList.get(i);
				if (UrlParser.checkUrl(articleUrl)) {
					ArticleModel article = ArticlePaperProcessor.getArticle(taskModel, articleUrl);
					if (article.getTitle().equals("NoContet")) { // 防止爬虫无法爬取到内容报错
						continue;
					}
					if (TitleParser.projectJudgment(article.getTitle())) {
						String areaId = AreaSqlConfiguration.getAreaId(AreaPaser.getArea(article.getTitle()));
						if (areaId.equals("") == false) {
							ArticleSqlConfiguration.addAticle(article.getTitle(), article.getStarttime(),
									article.getStoptime(), article.getContent(), article.getContentNoCode(), articleUrl,
									taskModel.getSource(),areaId);
						}
					}
				}
			}
			// 计时，如果超过二十分钟没有爬取就
			System.out.println(taskModel.getSource() + "结束");
		}
		// 添加索引
		try {
			System.out.println("建立索引");
			LuceneIndexController.addAllIndex();
			System.out.println("建立完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
