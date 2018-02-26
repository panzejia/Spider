package cn.iflin.admin.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.iflin.admin.model.TaskModel;
import cn.iflin.admin.model.DAO.TaskSqlConfiguration;
import cn.iflin.admin.model.DAO.UsermanagerSql;
/**
 * 用于功能界面切换
 * @author Panzejia
 *
 */
@Controller
public class PageController {
	/**
	 * 切换至主页
	 * @param model
	 * @return html page
	 */
	@RequestMapping("/index")
	public String getIndex(Model model) {
		ArrayList<TaskModel> taskList = TaskSqlConfiguration.getTaskName();
		model.addAttribute("spiders", taskList);
		return "index";
	}
	/**
	 * 切换至设置页面
	 * @return html page
	 */
	@RequestMapping("setter")
	public String getSetterPage() {
		return "setter";
	}
	/**
	 * 切换至爬虫页面
	 * @param model
	 * @return html page
	 */
	@RequestMapping("spider")
	public String getSpiderList(Model model) {
		ArrayList<TaskModel> taskList = TaskSqlConfiguration.getTask("all");
		model.addAttribute("spiders", taskList);
		return "spiderList";
	}
	
	/**
	 * 切换至用户管理页面
	 * @param model
	 * @return html page
	 */
	@RequestMapping("/usermanager")
	public String getUserManager(Model model){
		model.addAttribute("users", UsermanagerSql.getUsers());
		return "usermanager/users";
	}

	
	

}
