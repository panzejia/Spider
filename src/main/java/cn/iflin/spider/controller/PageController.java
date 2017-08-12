package cn.iflin.spider.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.iflin.spider.model.TaskModel;
import cn.iflin.spider.server.configuration.ConfigurationController;

@Controller
public class PageController {
	@RequestMapping("/addTask")
	public String getAddTaskPage(){
		return "SpiderAddTask";
	}
	@RequestMapping("/taskDetail")
	public String getTaskDetailPage(){
		return "SpiderTaskDetail";
	}
	@RequestMapping("")
	public String index1(Model model){
		ArrayList<TaskModel> taskList = ConfigurationController.getTaskName();
		model.addAttribute("spiders", taskList);
		return "forward:SpiderControlPanel.jsp";
	}
	@RequestMapping("index.html")
	public String index2(Model model){
		ArrayList<TaskModel> taskList = ConfigurationController.getTaskName();
		model.addAttribute("spiders", taskList);
		return "forward:SpiderControlPanel.jsp";
	}
}
