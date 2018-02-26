package cn.iflin.admin.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.admin.model.WordModel;
import cn.iflin.admin.model.DAO.WordSqlConfiguration;

/**
 * 设置页面控制器
 * @author Jaypan
 *
 */
@Controller
@RequestMapping ("/setter" )
public class SetterController {
	

	/**
	 * 获取选择词页面
	 * @param model
	 * @return
	 */
	@RequestMapping("getWordSetterPage")
	public String getWordSetterPage(Model model) {
		ArrayList<WordModel> stopWordList = WordSqlConfiguration.getStopword();
		ArrayList<WordModel> selectWordList = WordSqlConfiguration.getSelectword();
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

	/**
	 * 獲取過濾詞匯表格部分
	 * @param model
	 * @return
	 */
	@RequestMapping("getStopWordView")
	public String getStopWordView(Model model) {
		ArrayList<WordModel> stopWordList = WordSqlConfiguration.getStopword();
		model.addAttribute("words", stopWordList);
		model.addAttribute("tag", "StopWord");
		model.addAttribute("tagChinese", "停用词");
		return "setter/wordView";
	}

	/**
	 * 獲取過濾詞匯表格部分
	 * @param model
	 * @return
	 */
	@RequestMapping("getSelectWordView")
	public String getSelectWordView(Model model) {
		ArrayList<WordModel> selectWordList = WordSqlConfiguration.getSelectword();
		model.addAttribute("words", selectWordList);
		model.addAttribute("tag", "SelectWord");
		model.addAttribute("tagChinese", "选择词");
		return "setter/wordView";
	}

	/**
	 * 删除停用词
	 * @param wordId
	 */
	@RequestMapping(value = "/delStopWord", method = RequestMethod.GET)
	public void delStopWord(@RequestParam("wordId") String wordId) {
		WordSqlConfiguration.delStopword(wordId);
	}

	/**
	 * 删除选择词
	 * @param wordId
	 */
	@RequestMapping(value = "/delSelectWord", method = RequestMethod.GET)
	public void delSelectWord(@RequestParam("wordId") String wordId) {
		WordSqlConfiguration.delSelectword(wordId);
	}

	/**
	 * 保存停用词
	 * @param stopword
	 * @return
	 */
	@RequestMapping(value = "/saveStopWord", method = RequestMethod.POST)
	public String saveStopWord(@RequestParam("inputWord") String stopword) {
		WordSqlConfiguration.addStopword(stopword);
		return "spider/changeSuccess";
	}

	/**
	 * 保存选择词
	 * @param selectword
	 * @return
	 */
	@RequestMapping(value = "/saveSelectWord", method = RequestMethod.POST)
	public String saveSelectWord(@RequestParam("inputWord") String selectword) {
		WordSqlConfiguration.addSelectword(selectword);
		return "spider/changeSuccess";
	}
	/**
	 * 獲取關於界面
	 * @return
	 */
	@RequestMapping("getAboutPage")
	public String getAboutPage() {
		return "setter/about";
	}
}
