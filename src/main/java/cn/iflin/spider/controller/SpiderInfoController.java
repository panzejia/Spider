package cn.iflin.spider.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.spider.model.TaskModel;
import cn.iflin.spider.server.configuration.ConfigurationController;
import cn.iflin.spider.server.configuration.Parser.UrlParser;
import cn.iflin.spider.server.configuration.ViewPaperProcessor;

@Controller
public class SpiderInfoController {
	// 保存一個新的任務
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public String doSave(@RequestParam("source") String source, @RequestParam("liUrl") String liUrl,
			@RequestParam("cssSeletor") String cssSeletor, @RequestParam("xpath") String xpath,
			@RequestParam("titleCSS") String titleCSS, @RequestParam("titleXpath") String titleXpath,
			@RequestParam("contentCSS") String contentCSS, @RequestParam("contentXpath") String contentXpath,
			@RequestParam("starttimeCSS") String starttimeCSS, @RequestParam("starttimeXpath") String starttimeXpath) {
		if (UrlParser.setUrlListConfiguration(source, liUrl, cssSeletor, xpath, titleCSS, titleXpath, contentCSS,
				contentXpath, starttimeCSS, starttimeXpath)) {
			return "spider/changeSuccess";
		}
		return "spider/changeFaild";
	}

	// 修改一個任務
	@RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
	public String changeInfo(@RequestParam("source") String source, @RequestParam("liUrl") String liUrl,
			@RequestParam("cssSeletor") String cssSeletor, @RequestParam("xpath") String xpath,
			@RequestParam("titleCSS") String titleCSS, @RequestParam("titleXpath") String titleXpath,
			@RequestParam("contentCSS") String contentCSS, @RequestParam("contentXpath") String contentXpath,
			@RequestParam("starttimeCSS") String starttimeCSS, @RequestParam("starttimeXpath") String starttimeXpath,
			@RequestParam("taskId") String taskId) {
		String sql = "";
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
		if (ConfigurationController.changeTaskInfo(sql)) {
			return "spider/changeSuccess";
		}
		return "spider/changeFaild";
	}

	// 删除任务
	@RequestMapping(value = "/delTask", method = RequestMethod.GET)
	public String delTask(@RequestParam("taskId") String taskId) {
		ConfigurationController.delTaskInfo(taskId);
		return "forward:index.html";
	}

	// 獲取任務詳情
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public String getTaskDetail(@RequestParam("taskId") String taskId, Model model) {
		HashMap<String, String> info = ConfigurationController.getDetailById(taskId);
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

	// 獲得預覽
	@RequestMapping(value = "/getPageCode", method = RequestMethod.GET)
	public String getPageCode(@RequestParam("url") String url, @RequestParam("cssSeletor") String cssSeletor,
			@RequestParam("xpath") String xpath, Model model) {
		model.addAttribute("view", ViewPaperProcessor.getViewContent(url, cssSeletor, xpath));
		return "spider/view";
	}

	// 获得已爬取的文章列表
	@RequestMapping(value = "/getSpiderArticle", method = RequestMethod.GET)
	public String getSpiderArticle(@RequestParam("source") String source, Model model) {
		model.addAttribute("articles", ConfigurationController.getSpiderArticle(source));
		return "spider/articleList";
	}

	// 获取文章内容
	@RequestMapping(value = "/getArticleInfo", method = RequestMethod.GET)
	public String getArticleInfo(@RequestParam("articleId") String articleId, Model model) {
		model.addAttribute("article", ConfigurationController.getArticleInfo(articleId));
		return "spider/articleInfo";
	}

	// 删除文章
	@RequestMapping(value = "/delArticle", method = RequestMethod.GET)
	public String delArticle(@RequestParam("articleId") String articleId, Model model) {
		ConfigurationController.delArticleInfo(articleId);
		return "forward:index.html";
	}
}
