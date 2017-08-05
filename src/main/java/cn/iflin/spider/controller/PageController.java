package cn.iflin.spider.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.spider.model.TaskModel;
import cn.iflin.spider.server.configuration.ConfigurationController;

@Controller
public class PageController {
	@RequestMapping("/addTask")
	public String getAddTaskPage() {
		return "SpiderAddTask";
	}

	@RequestMapping("/taskDetail")
	public String getTaskDetailPage() {
		return "SpiderTaskDetail";
	}

	@RequestMapping("")
	public String index1(Model model) {
		ArrayList<TaskModel> taskList = ConfigurationController.getTaskName();
		model.addAttribute("spiders", taskList);
		return "forward:index.jsp";
	}

	@RequestMapping("index.html")
	public String index2(Model model) {
		ArrayList<TaskModel> taskList = ConfigurationController.getTaskName();
		model.addAttribute("spiders", taskList);
		return "forward:index.jsp";
	}

	@RequestMapping("getSpiderList")
	public String getSpiderList(Model model) {
		ArrayList<TaskModel> taskList = ConfigurationController.getTask();
		model.addAttribute("spiders", taskList);
		return "spider/spiderList";
	}

	@RequestMapping(value = "/getChangePage", method = RequestMethod.GET)
	public String changePage(@RequestParam("taskId") String taskId, Model model) {
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
		return "ChangeInfo";
	}
}
