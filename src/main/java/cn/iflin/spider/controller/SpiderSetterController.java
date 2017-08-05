package cn.iflin.spider.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.spider.model.WordModel;
import cn.iflin.spider.server.configuration.SpiderSiteController;
import cn.iflin.spider.server.configuration.Parser.UrlParser;

/**
 * 设置页面控制器
 * 
 * @author Jaypan
 *
 */
@Controller
public class SpiderSetterController {
	// 獲取設置主頁面
	@RequestMapping("getSetter")
	public String getSetterPage() {
		return "setter";
	}

	// 獲取過濾詞匯頁面
	@RequestMapping("getWordSetterPage")
	public String getWordSetterPage(Model model) {
		ArrayList<WordModel> stopWordList = SpiderSiteController.getStopword();
		ArrayList<WordModel> selectWordList = SpiderSiteController.getSelectword();
		String stopWord = "", selectWord = "";
		for (WordModel wm : stopWordList) {
			stopWord += wm.getWord() + ",";
		}
		for (WordModel wm : selectWordList) {
			selectWord += wm.getWord() + ",";
		}
		model.addAttribute("stopWord", stopWord);
		model.addAttribute("selectWord", selectWord);
		return "setter/wordSetter";
	}

	// 獲取過濾詞匯表格部分
	@RequestMapping("getStopWordView")
	public String getStopWordView(Model model) {
		ArrayList<WordModel> stopWordList = SpiderSiteController.getStopword();
		model.addAttribute("words", stopWordList);
		model.addAttribute("tag", "StopWord");
		model.addAttribute("tagChinese", "停用词");
		return "setter/wordView";
	}

	// 獲取過濾詞匯表格部分
	@RequestMapping("getSelectWordView")
	public String getSelectWordView(Model model) {
		ArrayList<WordModel> selectWordList = SpiderSiteController.getSelectword();
		model.addAttribute("words", selectWordList);
		model.addAttribute("tag", "SelectWord");
		model.addAttribute("tagChinese", "选择词");
		return "setter/wordView";
	}

	// 删除停用词
	@RequestMapping(value = "/delStopWord", method = RequestMethod.GET)
	public void delStopWord(@RequestParam("wordId") String wordId) {
		SpiderSiteController.delStopword(wordId);
	}

	// 删除选择词
	@RequestMapping(value = "/delSelectWord", method = RequestMethod.GET)
	public void delSelectWord(@RequestParam("wordId") String wordId) {
		SpiderSiteController.delSelectword(wordId);
	}

	// 保存停用词
	@RequestMapping(value = "/saveStopWord", method = RequestMethod.POST)
	public String saveStopWord(@RequestParam("inputWord") String stopword) {
		SpiderSiteController.addStopword(stopword);
		return "spider/changeSuccess";
	}

	// 保存选择词
	@RequestMapping(value = "/saveSelectWord", method = RequestMethod.POST)
	public String saveSelectWord(@RequestParam("inputWord") String selectword) {
		SpiderSiteController.addSelectword(selectword);
		return "spider/changeSuccess";
	}
}
