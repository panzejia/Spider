package cn.iflin.spider;

import java.util.HashMap;
import java.util.Set;

import cn.iflin.spider.model.factory.SpiderModel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
/**
 * 实现webmagic的PageProcessor的接口方法
 * @author Jaypan
 *
 */
public class SpiderProcessor  implements PageProcessor{
	private static SpiderModel spiderModel; //使用静态方法并且有子类的时候一定要在子类使用setSpiderModel
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
    	HashMap<String,String> map = spiderModel.getMap();
    	Set<String> keys = map.keySet();
    	for(String key : keys){
    		Selectable content = page.getHtml().css(map.get(key));
    		if(content==null){
    			content = page.getHtml().xpath(map.get(key));
    		}
    		page.putField(key, content);
    	}
    }

    public Site getSite() {
        return site;
    }
    
    public void setSpiderModel(SpiderModel model){
    	spiderModel = model;
    }
}
