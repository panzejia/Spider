package cn.iflin.spider.model.factory;

import cn.iflin.spider.model.Model;
/**
 * 继承Model接口
 * @author Panzejia
 *
 */
public interface SpiderModel extends Model{
	/**
	 * 获取Url
	 * @return String url
	 */
	public String getUrl();
	/**
	 * 设置url
	 * @param url
	 */
	public void setUrl(String url);
}
