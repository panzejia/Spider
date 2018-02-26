package cn.iflin.admin.server.timer;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


import cn.iflin.admin.model.DAO.TaskSqlConfiguration;

import cn.iflin.admin.server.spider.configuration.SpidersController;


/**
 * 该类预计将会作为列表页中链接到正文内容的控制器
 * 
 * @author Jaypan
 */
public class SpiderController extends QuartzJobBean {
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

	@SuppressWarnings("static-access")
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		/*设定下一次爬虫时间*/
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
		TaskSqlConfiguration.setNextTime(sf.format(date));
		/*
		 * 运行所有的爬虫任务
		 */
		SpidersController.actionAllSpider();
		System.out.println("下次運行" + sf.format(date));
	}

}
