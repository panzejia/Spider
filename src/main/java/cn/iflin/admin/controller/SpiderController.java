package cn.iflin.admin.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.iflin.admin.model.DAO.ArticleSqlConfiguration;
import cn.iflin.admin.model.DAO.TaskSqlConfiguration;
import cn.iflin.admin.server.spider.configuration.Parser.UrlParser;
import cn.iflin.admin.server.spider.configuration.SpidersController;
import cn.iflin.admin.server.spider.configuration.ViewPaperProcessor;
import cn.iflin.admin.server.timer.LuceneIndexController;


/**
 * 爬虫相关操作设置控制器
 * @author Panzejia
 *
 */
@Controller
@RequestMapping ("/spider" )
public class SpiderController {
	
	
	/**
	 * 添加爬虫任务页面
	 * @return
	 */
	@RequestMapping("/addTask")
	public String getAddTaskPage() {
		return "spider/SpiderAddTask";
	}
	
	/**
	 * 修改已有爬虫任务页面
	 * @param taskId 爬虫任务ID
	 * @param model  爬虫任务数据模型
	 * @return
	 */
	@RequestMapping(value = "/getChangePage", method = RequestMethod.GET)
	public String changePage(@RequestParam("taskId") String taskId, Model model) {
		HashMap<String, String> info = TaskSqlConfiguration.getDetailById(taskId);
		model.addAttribute("source", info.get("source"));
		model.addAttribute("url", info.get("url"));
		model.addAttribute("cssSeletor", info.get("cssSeletor"));
		model.addAttribute("xpath", info.get("xpath"));
		model.addAttribute("titleCSS", info.get("titleCSS"));
		model.addAttribute("titleXpath", info.get("titleXpath"));
		model.addAttribute("contentCSS", info.get("contentCSS"));
		model.addAttribute("contentXpath", info.get("contentXpath"));
		model.addAttribute("starttimeCSS", info.get("starttimeCSS"));
		model.addAttribute("starttimeXpath", info.get("starttimeXpath"));
		model.addAttribute("nextTime", info.get("nextTime"));
		model.addAttribute("taskId", taskId);
		return "spider/ChangeInfo";
	}
	
	/**
	 * 获取单个爬虫任务详细信息
	 * @return
	 */
	@RequestMapping("/taskDetail")
	public String getTaskDetailPage() {
		return "spider/SpiderTaskDetail";
	}
	
	/**
	 * 立即执行爬虫
	 * @return
	 */
	@RequestMapping("/doSpidersTask")
	public String doSpidersTask() {
		System.out.println("管理员点击“立即开始爬虫”");
		SpidersController.actionAllSpider();
		return "spiderList";
	}
	/**
	 * 一键添加索引
	 * @return
	 */
	@RequestMapping("/addIndex")
	public String addIndex() {
		System.out.println("管理员点击“添加索引”");
		try {
			LuceneIndexController.addAllIndex();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "spiderList";
	}
	/**
	 * 保存一個新的任務
	 * @param source
	 * @param liUrl
	 * @param cssSeletor
	 * @param xpath
	 * @param titleCSS
	 * @param titleXpath
	 * @param contentCSS
	 * @param contentXpath
	 * @param starttimeCSS
	 * @param starttimeXpath
	 * @return
	 */
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,String> doSave(@RequestParam("source") String source, @RequestParam("liUrl") String liUrl,
			@RequestParam("cssSeletor") String cssSeletor, @RequestParam("xpath") String xpath,
			@RequestParam("titleCSS") String titleCSS, @RequestParam("titleXpath") String titleXpath,
			@RequestParam("contentCSS") String contentCSS, @RequestParam("contentXpath") String contentXpath,
			@RequestParam("starttimeCSS") String starttimeCSS, @RequestParam("starttimeXpath") String starttimeXpath) {
		HashMap<String,String> info = new HashMap<String,String>();
		if (UrlParser.setUrlListConfiguration(source, liUrl, cssSeletor, xpath, titleCSS, titleXpath, contentCSS,
				contentXpath, starttimeCSS, starttimeXpath)) {
			info.put("info", "保存成功");
			return info;
		}
		info.put("info", "保存失败");
		return info;
	}

	/**
	 * 修改一個任務
	 * @param source
	 * @param liUrl
	 * @param cssSeletor
	 * @param xpath
	 * @param titleCSS
	 * @param titleXpath
	 * @param contentCSS
	 * @param contentXpath
	 * @param starttimeCSS
	 * @param starttimeXpath
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
	public @ResponseBody  HashMap<String,String>  changeInfo(@RequestParam("source") String source, @RequestParam("liUrl") String liUrl,
			@RequestParam("cssSeletor") String cssSeletor, @RequestParam("xpath") String xpath,
			@RequestParam("titleCSS") String titleCSS, @RequestParam("titleXpath") String titleXpath,
			@RequestParam("contentCSS") String contentCSS, @RequestParam("contentXpath") String contentXpath,
			@RequestParam("starttimeCSS") String starttimeCSS, @RequestParam("starttimeXpath") String starttimeXpath,
			@RequestParam("taskId") String taskId) {
		String sql = "";
		HashMap<String,String> info = new HashMap<String,String>();
		System.out.println(source);
		if (source.equals("") == false) {
			sql = "UPDATE spidertaskinfo set source='" + source + "' WHERE taskId='" + taskId + "';";
		}
		if (liUrl.equals("") == false) {
			sql = "UPDATE spidertaskinfo set url='" + liUrl + "' WHERE taskId='" + taskId + "';";
		}
		if (cssSeletor.equals("") == false) {
			sql = "UPDATE spidertaskinfo set cssSeletor='" + cssSeletor + "' WHERE taskId='" + taskId + "';";
		}
		if (xpath.equals("") == false) {
			sql = "UPDATE spidertaskinfo set xpath='" + xpath + "' WHERE taskId='" + taskId + "';";
		}
		if (titleCSS.equals("") == false) {
			sql = "UPDATE spidertaskinfo set titleCSS='" + titleCSS + "' WHERE taskId='" + taskId + "';";
		}
		if (titleXpath.equals("") == false) {
			sql = "UPDATE spidertaskinfo set titleXpath='" + titleXpath + "' WHERE taskId='" + taskId + "';";
		}
		if (contentCSS.equals("") == false) {
			sql = "UPDATE spidertaskinfo set contentCSS='" + contentCSS + "' WHERE taskId='" + taskId + "';";
		}
		if (contentXpath.equals("") == false) {
			sql = "UPDATE spidertaskinfo set contentXpath='" + contentXpath + "' WHERE taskId='" + taskId + "';";
		}
		if (starttimeCSS.equals("") == false) {
			sql = "UPDATE spidertaskinfo set starttimeCSS='" + starttimeCSS + "' WHERE taskId='" + taskId + "';";
		}
		if (starttimeXpath.equals("") == false) {
			sql = "UPDATE spidertaskinfo set contentXpath='" + starttimeXpath + "' WHERE taskId='" + taskId + "';";
		}
		if (TaskSqlConfiguration.changeTaskInfo(sql)) {
			info.put("info", "保存成功");
			return info;
		}
		info.put("info", "保存失败");
		return info;
	}

	/**
	 * 删除任务
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/delTask", method = RequestMethod.GET)
	public String delTask(@RequestParam("taskId") String taskId) {
		TaskSqlConfiguration.delTaskInfo(taskId);
		return "index";
	}

	/**
	 * 獲取任務詳情
	 * @param taskId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public String getTaskDetail(@RequestParam("taskId") String taskId, Model model) {
		HashMap<String, String> info = TaskSqlConfiguration.getDetailById(taskId);
		model.addAttribute("source", info.get("source"));
		model.addAttribute("url", info.get("url"));
		model.addAttribute("cssSeletor", info.get("cssSeletor"));
		model.addAttribute("xpath", info.get("xpath"));
		model.addAttribute("titleCSS", info.get("titleCSS"));
		model.addAttribute("titleXpath", info.get("titleXpath"));
		model.addAttribute("contentCSS", info.get("contentCSS"));
		model.addAttribute("contentXpath", info.get("contentXpath"));
		model.addAttribute("starttimeCSS", info.get("starttimeCSS"));
		model.addAttribute("starttimeXpath", info.get("starttimeXpath"));
		model.addAttribute("nextTime", info.get("nextTime"));
		model.addAttribute("taskId", taskId);
		return "spider/SpiderTaskDetail";
	}

	/**
	 * 獲得預覽
	 * @param url
	 * @param cssSeletor
	 * @param xpath
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getPageCode", method = RequestMethod.GET)
	public String getPageCode(@RequestParam("url") String url, @RequestParam("cssSeletor") String cssSeletor,
			@RequestParam("xpath") String xpath, Model model) {
		model.addAttribute("view", ViewPaperProcessor.getViewContent(url, cssSeletor, xpath));
		return "spider/view";
	}

	/**
	 *  修改任务状态
	 * @param taskId
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeTaskStatus", method = RequestMethod.GET)
	public String changeTaskStatus(@RequestParam("taskId") String taskId, @RequestParam("status") String status,
			Model model) {
		if (status.equals("Action")) {
			TaskSqlConfiguration.changeTaskStatus(taskId, "Stop");
		}
		if (status.equals("Stop")) {
			TaskSqlConfiguration.changeTaskStatus(taskId, "Action");
		}
		return "forward:getSpiderList";
	}

	/**
	 *  获得已爬取的文章列表
	 * @param source
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getSpiderArticle", method = RequestMethod.GET)
	public String getSpiderArticle(@RequestParam("source") String source, Model model) {
		model.addAttribute("articles", ArticleSqlConfiguration.getSpiderArticle(source));
		return "spider/articleList";
	}

	/**
	 * 获取文章内容
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getArticleInfo", method = RequestMethod.GET)
	public String getArticleInfo(@RequestParam("articleId") String articleId, Model model) {
		model.addAttribute("article", ArticleSqlConfiguration.getArticleInfo(articleId));
		return "spider/articleInfo";
	}

	/**
	 *  删除文章
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delArticle", method = RequestMethod.GET)
	public String delArticle(@RequestParam("articleId") String articleId, Model model) {
		ArticleSqlConfiguration.delArticleInfo(articleId);
		return "spider";
	}
}
