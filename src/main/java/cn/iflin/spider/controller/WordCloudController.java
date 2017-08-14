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
	@RequestMapping("/getWordCloud")
	public String getTaskDetailPage(Model model) {
		model.addAttribute("articles", ArticleSqlConfiguration.getAllArticle());
		return "wordcloud/articleList";
	}

	@RequestMapping(value = "/getWordFre", method = RequestMethod.GET)
	public String getWordFre(@RequestParam("articleId") String articleId, Model model) {
		ArticleModel article = ArticleSqlConfiguration.getArticleInfo(articleId);
		ArrayList<WordModel> wordList = WordCloud.getWordFre(article.getContent(), articleId);
		model.addAttribute("wordList", wordList);
		model.addAttribute("total", wordList.size());
		model.addAttribute("articleId", articleId);
		return "wordcloud/freView";
	}

	@RequestMapping(value = "/getWordFreTemp", method = RequestMethod.GET)
	public String getWordFreTemp(@RequestParam("content") String content, Model model) {
		ArrayList<WordModel> wordList = WordCloud.getWordFre(content, "temp");
		model.addAttribute("wordList", wordList);
		model.addAttribute("total", wordList.size());
		return "wordcloud/freView";
	}

	@RequestMapping(value = "/getWordCloudDraw", method = RequestMethod.POST)
	public void getWordCloud(HttpServletRequest request, HttpServletResponse response,@RequestParam("articleId") String articleId) {
		System.out.println(articleId);
		ArticleModel article = ArticleSqlConfiguration.getArticleInfo(articleId);
		ArrayList<WordModel> wordList = WordCloud.getWordFre(article.getContent(), articleId);
		String[] data = new String[20];
		WordModel word;
		for (int i = 0; i < 20; i++) {
			word=wordList.get(i);
			data[i] = word.getWord();
		}
		ResponseJsonUtils.json(response, data);  
	}
}
