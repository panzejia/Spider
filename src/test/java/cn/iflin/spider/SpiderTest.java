package cn.iflin.spider;

import java.util.ArrayList;
import java.util.List;

import cn.iflin.spider.model.CrawlContentModel;
import cn.iflin.spider.model.CrawlListModel;
import cn.iflin.spider.parser.SpiderParser;
import cn.iflin.spider.sql.IktblDAO;

public class SpiderTest {
	public static void main(String[] args) {
		String taskId = "10";
		
		ArrayList<CrawlListModel> list = IktblDAO.getTaskForList(taskId);
		ListSpider listSpider = new ListSpider();
		ContentSpider contentSpider = new ContentSpider();
		for(CrawlListModel task:list){
			List<String> urllist = listSpider.getListUrl(task);
			for(String url : urllist){
				ArrayList<CrawlContentModel> tasks = IktblDAO.getTaskForArticle(task.getTaskId());
				for(CrawlContentModel ccm : tasks){
					ccm.setUrl(url);
					String title = contentSpider.getContent(ccm).getTitle();
					if(title==null) continue;
					System.out.println(SpiderParser.delTab(contentSpider.getContent(ccm).getTitle()));
				}
			}
		}
	}
}
