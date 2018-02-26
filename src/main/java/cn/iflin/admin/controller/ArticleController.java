package cn.iflin.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.iflin.admin.model.ArticleModel;
import cn.iflin.admin.model.DAO.ArticleSqlConfiguration;
import cn.iflin.admin.server.aritcles.ArticleAnalysis;

/**
 * 文章管理接口
 * @author Panzejia
 *
 */
@Controller
@RequestMapping ("/article" )
public class ArticleController {
	/**
	 * 获取已经爬取的文章列表
	 * @param range 申报范围
	 * @param sortType 排序类型
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inquire", method = RequestMethod.GET)
	public String getAnalysis(@RequestParam("range") String range,@RequestParam("sort") String sortType,Model model) {
		ArrayList<ArticleModel> articles = ArticleAnalysis.getAllArticle(range,sortType);
		model.addAttribute("articles", articles);
		return "articles/articles";
	}
	
	/**
	 * 删除文章控制器
	 * @param articleId 文章id
	 * @return 删除成功或失败 json
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public  @ResponseBody HashMap<String,String>  delArticle(@RequestParam("articleId") String articleId) {
		HashMap<String,String> info = new HashMap<String,String> ();
		if(ArticleSqlConfiguration.delArticleInfo(articleId) ){
			info.put("info", "删除成功");
			
		}else{
			info.put("info", "删除失败");
		}
		return info;
	}
	
}
