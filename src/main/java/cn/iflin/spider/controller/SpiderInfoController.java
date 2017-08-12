package cn.iflin.spider.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.spider.server.configuration.ConfigurationController;
import cn.iflin.spider.server.configuration.Parser.UrlParser;
import cn.iflin.spider.server.configuration.ViewPaperProcessor;
import cn.iflin.spider.server.timer.SpiderController;

@Controller
public class SpiderInfoController {
	@RequestMapping(value="/addTaskInfo",method=RequestMethod.GET)
	public String addConfiguration(@RequestParam("source")String source,
			@RequestParam("liUrl")String liUrl,@RequestParam("cssSeletor")String cssSeletor,
			@RequestParam("xpath")String xpath,@RequestParam("titleCSS")String titleCSS,
			@RequestParam("titleXpath")String titleXpath,@RequestParam("contentCSS")String contentCSS,
			@RequestParam("contentXpath")String contentXpath,@RequestParam("starttimeCSS")String starttimeCSS,
			@RequestParam("starttimeXpath")String starttimeXpath){
		if(UrlParser.setUrlListConfiguration(source,liUrl, cssSeletor, xpath
				, titleCSS, titleXpath, contentCSS, contentXpath, starttimeCSS, starttimeXpath)){
			return "changeSuccess";
		}
		return "changeFaild";
	}
	
	@RequestMapping(value="/getDetail",method=RequestMethod.GET)
	public String getTaskDetail(@RequestParam("taskId")String taskId,Model model){
		HashMap<String,String> info = ConfigurationController.getDetail(taskId);
		model.addAttribute("source",info.get("source"));
		model.addAttribute("url", info.get("url"));
		model.addAttribute("cssSeletor", info.get("cssSeletor"));
		model.addAttribute("xpath", info.get("xpath"));
		model.addAttribute("titleCSS", info.get("titleCSS"));
		model.addAttribute("titleXpath", info.get("titleXpath"));
		model.addAttribute("contentCSS", info.get("contentCSS"));
		model.addAttribute("contentXpath", info.get("contentXpath"));
		model.addAttribute("starttimeCSS", info.get("starttimeCSS"));
		model.addAttribute("starttimeXpath", info.get("starttimeXpath"));
		return "SpiderTaskDetail";
	}
	
	@RequestMapping(value="/getPageCode",method=RequestMethod.GET)
	public String getPageCode(@RequestParam("url")String url,
			@RequestParam("cssSeletor")String cssSeletor,@RequestParam("xpath")String xpath,Model model){
		model.addAttribute("view", ViewPaperProcessor.getViewContent(url, cssSeletor, xpath));
		return "view";
	}
	
}
