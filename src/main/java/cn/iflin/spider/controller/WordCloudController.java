package cn.iflin.spider.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.model.WordModel;
import cn.iflin.spider.server.configuration.ArticleSqlConfiguration;
import cn.iflin.spider.server.wordcloud.WordCloud;

@Controller
public class WordCloudController {
	

	@RequestMapping(value = "/getWordFre", method = RequestMethod.GET)
	public String getWordFre(@RequestParam("articleId") String articleId, Model model) {
		ArticleModel article = ArticleSqlConfiguration.getArticleInfo(articleId);
		ArrayList<WordModel> wordList = WordCloud.getWordFre(article.getContent(), articleId,"chinese","");
		model.addAttribute("wordList", wordList);
		model.addAttribute("total", wordList.size());
		model.addAttribute("data", articleId);
		model.addAttribute("tag", "wordCloudBySql");
		return "wordcloud/freView";
	}

	@RequestMapping(value = "/getWordFreTemp", method = RequestMethod.POST)
	public String getWordFreTemp(@RequestParam("content") String content,@RequestParam("tag") String tag, Model model) {
		ArrayList<WordModel> wordList = WordCloud.getWordFre(content, "temp",tag,"");
		model.addAttribute("data", content);
		model.addAttribute("wordList", wordList);
		model.addAttribute("total", wordList.size());
		model.addAttribute("tag", "wordCloudByUser");
		return "wordcloud/freView";
	}

	@RequestMapping(value = "/getWordCloudDraw", method = RequestMethod.POST)
	public void getWordCloudDraw(HttpServletRequest request, HttpServletResponse response,@RequestParam("articleId") String articleId) {
		ArticleModel article = ArticleSqlConfiguration.getArticleInfo(articleId);
		ArrayList<WordModel> wordList = WordCloud.getWordFre(article.getContent(), articleId,"chinese","");
		String[] data = new String[20];
		WordModel word;
		if(wordList.size()>20){
			for (int i = 0; i < 20; i++) {
				word=wordList.get(i);
				data[i] = word.getWord();
			}
		}else{
			for (int i = 0; i < wordList.size(); i++) {
				word=wordList.get(i);
				data[i] = word.getWord();
			}
		}
		
		ResponseJsonUtils.json(response, data);  
	}
	@RequestMapping(value = "/getWordCloudDrawByUser", method = RequestMethod.POST)
	public void getWordCloudDrawByUser(HttpServletRequest request, HttpServletResponse response,@RequestParam("content") String content) {
		ArrayList<WordModel> wordList = WordCloud.getWordFre(content, "temp","chinese","");
		String[] data = new String[20];
		WordModel word;
		if(wordList.size()>20){
			for (int i = 0; i < 20; i++) {
				word=wordList.get(i);
				data[i] = word.getWord();
			}
		}else{
			for (int i = 0; i < wordList.size(); i++) {
				word=wordList.get(i);
				data[i] = word.getWord();
			}
		}
		ResponseJsonUtils.json(response, data);  
	}
}
