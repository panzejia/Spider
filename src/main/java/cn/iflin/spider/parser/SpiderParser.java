package cn.iflin.spider.parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 爬虫分析器
 * @author Jaypan
 *
 */
public class SpiderParser {
	// 创建CookieStore实例
		static CookieStore cookieStore = null;
		static HttpClientContext context = null;
		/**
		 * 下载html源代码
		 * @param url
		 * @return
		 */
		public static String getHtml(String url) {
			// 直接创建client
			CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse get;
			String responseString = null;
			HttpEntity entity;
			try {
				get = client.execute(httpGet, context);
				entity = get.getEntity();
				 responseString = EntityUtils.toString(entity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return responseString;
		}
		/**
		 * 删除Html所有标签
		 * @throws Exception
		 */
		public static String delTab(String html){			
			String regEx_a = "<a[^>]*?>[\\s\\S]*?<\\/a>"; // 定义a标签的正则表达式    
			String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式    
	        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式    
	        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式    
	        String regEx_anno = "<!--[\\s\\S]*?-->"; //html注释  
	        html = html.replaceAll(regEx_a, "");  
	        html = html.replaceAll(regEx_script, "");  
	        html = html.replaceAll(regEx_style, "");  
	        html = html.replaceAll(regEx_html, "");  
	        html = html.replace(regEx_anno, "");  
	        html = html.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");//去除空白行  
	        html = html.replaceAll("    +| +|　+", ""); //去除空白  
	        return html.replaceAll("&nbsp;", "");
		}
		/**
		 * 获取根网址
		 */
		public static String getRootUrl(String oldUrl){
			String url = "";
			String reg1 = "[a-zA-z]+://[^\\s]*";
			String reg2 = "[a-zA-z].*/";
			Pattern p1 = Pattern.compile(reg1);
			Matcher m1 = p1.matcher(oldUrl);
			Pattern p2 = Pattern.compile(reg2);
			Matcher m2 = p2.matcher(oldUrl);
			if(m1.find()){
				url = m1.group(0);
				return url.substring(0, url.lastIndexOf('/'));
			}
			if(m2.find()){
				url = m2.group(0);
				if(url.contains("/")){
					return url.substring(0, url.indexOf('/'));
				}
			}
			return url;
		}
		/**
		 * 过滤掉特殊符号
		 * @param s
		 * @return
		 */
		public static String delSpecialSymbol(String s){
			String reg = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(s);
			return m.replaceAll("").trim();
		}
}
