package cn.iflin.spider.server.timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.model.TaskModel;
import cn.iflin.spider.server.configuration.ArticlePaperProcessor;
import cn.iflin.spider.server.configuration.ArticleSqlConfiguration;
import cn.iflin.spider.server.configuration.TaskSqlConfiguration;
import cn.iflin.spider.server.configuration.Parser.TitleParser;
import cn.iflin.spider.server.configuration.Parser.UrlParser;
import cn.iflin.spider.server.configuration.UrlListPaperProcessor;

/**
 * 该类预计将会作为列表页中链接到正文内容的控制器
 * 
 * @author Jaypan
 */
public class SpiderController extends QuartzJobBean {

	// public static void main(String[] args) {
	// ArrayList<TaskModel> taskList = ConfigurationController.getTask();
	// List<String> urlList = UrlListPaperProcessor.getUrlList(taskList.get(0));
	// Stack<String> newUrlList = UrlParser.getArticleNoExistedList(urlList,
	// taskList.get(0).getSource());
	// int count = newUrlList.size();
	// System.out.println("運行");
	// for (int i = 0; i < count; i++) {
	// String articleUrl = newUrlList.pop();
	// if (UrlParser.checkUrl(articleUrl)) {
	// ArticleModel article = ArticlePaperProcessor.getArticle(taskList.get(0),
	// articleUrl);
	// if (TitleParser.projectJudgment(article.getTitle())) {
	// ConfigurationController.addAticle(article.getTitle(),
	// article.getStarttime(), article.getContent(),
	// article.getContentNoCode(), articleUrl, taskList.get(0).getSource());
	// }
	// }
	// }
	// }
	private int hour;
	private int minutes;

	/**
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (hour != 0) {
			calendar.add(calendar.HOUR, hour);// 把日期往后增加一天.整数往后推,负数往前移动
		}
		if (minutes != 0) {
			calendar.add(calendar.MINUTE, minutes);// 把日期往后增加一天.整数往后推,负数往前移动
		}
		date = calendar.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("下次運行" + sf.format(date));
		ArrayList<TaskModel> taskList = TaskSqlConfiguration.getTask();
		TaskSqlConfiguration.setNextTime(sf.format(date));
		for (TaskModel taskModel : taskList) {
			if (taskModel.getStatus().equals("Stop")) { // 如果状态为Stop继续下一个循环
				continue;
			}
			System.out.println(taskModel.getSource()+"开始");
			List<String> urlList = UrlListPaperProcessor.getUrlList(taskModel);// 获取已爬取的URL列表
			Stack<String> newUrlList = UrlParser.getArticleNoExistedList(urlList, taskModel.getSource());// 返回一个没有爬取的URL栈
			int count = newUrlList.size();
			for (int i = 0; i < count; i++) {
				String articleUrl = newUrlList.pop();
				if (UrlParser.checkUrl(articleUrl)) {
					ArticleModel article = ArticlePaperProcessor.getArticle(taskModel, articleUrl);
					if (TitleParser.projectJudgment(article.getTitle())) {
						ArticleSqlConfiguration.addAticle(article.getTitle(), article.getStarttime(),
								article.getStoptime(), article.getContent(), article.getContentNoCode(), articleUrl,
								taskList.get(0).getSource());
					}
				}
			}
			System.out.println(taskModel.getSource()+"结束");
		}
	}

	public static void main(String[] args) {

		ArrayList<TaskModel> taskList = TaskSqlConfiguration.getTask();
		for (TaskModel taskModel : taskList) {
			if (taskModel.getStatus().equals("Stop")) { // 如果状态为Stop继续下一个循环
				System.out.println(taskModel.getSource());
				continue;
			}
			List<String> urlList = UrlListPaperProcessor.getUrlList(taskModel);// 获取已爬取的URL列表
			Stack<String> newUrlList = UrlParser.getArticleNoExistedList(urlList, taskModel.getSource());// 返回一个没有爬取的URL栈
			int count = newUrlList.size();
			for (int i = 0; i < count; i++) {
				String articleUrl = newUrlList.pop();
				
				if (UrlParser.checkUrl(articleUrl)) {
					ArticleModel article = ArticlePaperProcessor.getArticle(taskModel, articleUrl);
					System.out.println(article.getTitle());
					if (TitleParser.projectJudgment(article.getTitle())) {
						
					}
				}
			}
			System.out.println(taskModel.getSource()+"结束");
		}
	}
}
