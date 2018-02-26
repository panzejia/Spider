package cn.iflin.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.iflin.admin.server.dataanalysis.DateAnalysis;

/**
 * 提供数据分析功能接口
 * @author Panzejia
 *
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController {
	/**
	 * 用于呈现系统主页数据显示
	 * @return json
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,ArrayList<String>> getAnalysis() {
		HashMap<String,ArrayList<String>> info = new HashMap<String,ArrayList<String>>();
		ArrayList<String> statusList = DateAnalysis.getIndexChart();
		info.put("num", statusList);
		return info;
	}
	/**
	 * 获取最近爬取文章内容趋势
	 * @param day 天数
	 * @param source 来源（所有或某个爬虫任务）
	 * @return json
	 */
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public @ResponseBody HashMap<String,ArrayList<String>> getArticle(@RequestParam("day") Integer day,@RequestParam("source") String source) {
		HashMap<String,ArrayList<String>> info = new HashMap<String,ArrayList<String>>();
		ArrayList<String> statusList = DateAnalysis.getSpiderStatusByDayNum(day, source);
		info.put("num", statusList);
		return info;
	}
	/**
	 * 获取文章分析页面
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public String getArticles() {
		return "analysis/articles";
	}
}
