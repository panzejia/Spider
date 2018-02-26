package cn.iflin.recommend;

import java.util.HashMap;

public class ScoreTest {
	public static void main(String[] args) {
//		ArrayList<TaskModel> taskList = TaskSqlConfiguration.getTask("all");
//
//		for (TaskModel taskModel : taskList) {
//
//			if (taskModel.getStatus().equals("Stop")) { // 如果状态为Stop继续下一个循环
//				System.out.println(taskModel.getSource());
//				continue;
//			}
//			List<String> urlList = UrlListPaperProcessor.getUrlList(taskModel);// 获取已爬取的URL列表
//			Stack<String> newUrlList = UrlParser.getArticleNoExistedList(urlList, taskModel.getSource());// 返回一个没有爬取的URL栈
//			}
//		if(ArticleSqlConfiguration.getAllUrlByCagetory("国家自然科学基金委员会").get("htp://www.nsfc.gov.cn/publish/portal0/tab442/info71364.htm") != null){
//			System.out.println("ok");
//		}else{
//			System.out.println("no");
//		}
//		System.out.println(AreaPaser.getArea("福建省教育厅关于做好2017年大学生创新创业优秀项目推荐申报工作的通知.txt=6.6867575104612955"));
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("1502020050","hongpp");
		System.out.println("hongpp".hashCode());
	}
}
