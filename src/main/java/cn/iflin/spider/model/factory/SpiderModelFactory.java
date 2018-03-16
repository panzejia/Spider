package cn.iflin.spider.model.factory;

import cn.iflin.spider.model.CrawlContentModel;

/**
 * 具体工厂类
 * @author Panzejia
 *
 */
public class SpiderModelFactory extends AbstractSpiderModelFactory{

	
	@Override
	public SpiderModel getIktblContentSpiderModel() {
		return new CrawlContentModel();
	}

}
