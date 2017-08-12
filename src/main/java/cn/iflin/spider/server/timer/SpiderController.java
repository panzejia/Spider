package cn.iflin.spider.server.timer;

import java.util.List;

import cn.iflin.spider.model.ArticleModel;
import cn.iflin.spider.server.configuration.ArticlePaperProcessor;
import cn.iflin.spider.server.configuration.UrlListPaperProcessor;
import cn.iflin.spider.server.configuration.Parser.UrlParser;

/**
 * 该类预计将会作为列表页中链接到正文内容的控制器
 * @author Jaypan
 */
public class SpiderController {
	
	public static void main(String[] args) {
		
		String source = "科学技术司";
    	String url = "http://www.moe.edu.cn/s78/A16/s8213/index.html";
//    	status = setUrlListConfiguration(source,"div.siju_common_center","0");
//    	System.out.println(status);
//    	status = setContentConfigration(source,"#content_body > h1","0","#content_body_txt > div",
//    			"0","#content_body_txt > div","0");
//    	System.out.println(status);
    	
    	List<String> urlList = UrlListPaperProcessor.getUrlList(source, url);
    	for(String article : urlList){
    		if(article.equals("")||article==null){
    			continue;
    		}
    		if(UrlParser.checkUrl(article)){
    			ArticleModel model = ArticlePaperProcessor.getArticle(source, article);
    			System.out.println(model.getTitle());
    		}else{
    			continue;
    		}
    	}
	}
}
