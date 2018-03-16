package cn.iflin.spider;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.iflin.spider.parser.SpiderParser;
/**
 * 搜索引擎探索爬虫
 * @author Jaypan
 *
 */
public class SearchEngineSpider {
	public static void doSearchEngineSpider(String keywords){
		UrlSpiderModel usm = new UrlSpiderModel();
		for(int i = 0;i<30;i++){
			usm.pushQueue("http://www.baidu.com/s?wd="+keywords+"&pn="+String.valueOf(i*10));
		}
		Thread t1 = new Thread(new UrlSpiderTask(usm,"1"));
		Thread t2 = new Thread(new UrlSpiderTask(usm,"2"));
		Thread t3 = new Thread(new UrlSpiderTask(usm,"3"));
		t1.start();
		t2.start();
		t3.start();
		
	}
}

class UrlSpiderTask implements Runnable{
	UrlSpiderModel usm ;
	String id;
	public UrlSpiderTask(UrlSpiderModel usm1,String id){
		this.id = id;
		usm = usm1;
	}
	public void run() {
		while(usm.isQueue()){
			String page = usm.popQueue();
			String url = page;
//			System.out.println("当前线程"+id+"当前第"+page+","+url);
			List<String> list = SearchEngineSpiderConfig.getContent(url);
			for(String s : list){
				Document d  = Jsoup.parse(s);
				String result = d.text();
				if(result.contains("gov.cn")||result.contains("edu.cn")){
//					System.out.println(SpiderParser.getRootUrl(result));
					usm.getUrls().put(SpiderParser.getRootUrl(result), "");
					System.out.println(usm.getUrls().size());
				}
			}
		}
	}
}

class UrlSpiderModel {
	private static HashMap<String,String> urls = new HashMap<String,String>();

	public HashMap<String,String>getUrls() {
		return urls;
	}

	public static Queue<String> queue = new LinkedList<String>();
	public  void pushQueue(String s){
		queue.add(s);
	}
	public  String popQueue(){
		String result = queue.poll();
		return result;
	}
	public boolean isQueue(){
		if(queue.size()!=0)return true;
		return false;
	}
}