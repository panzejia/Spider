package cn.iflin.server.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.iflin.admin.model.ArticleModel;
import cn.iflin.admin.model.DAO.DAO;
import cn.iflin.admin.server.spider.configuration.Parser.TimeParser;


public class ParserTest {
	public static void main(String[] args) {
		ResultSet result =DAO.executableQuery("select * from articleview");
		String content="",title="";
//		try {
//			while(result.next()){
//				content = result.getString("Content_NoCode");
//				title = result.getString("Title");
//				
//				System.out.println(title +"    "+TimeParser.getStopTime(content));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		System.out.println(TimeParser.getStopTime("关于做好2017年国家社科基金中华学术外译项目申报工作的通知2017-08-28粤社科规划办通[2017]23号各单位科研管理部门：　　全国社科规划办《2017年国家社科基金中华学术外译项目申报公告》已经发布（以下简称《公告》）。2017年国家社科基金中华学术外译项目继续实行集中受理申报，一年评审一次。为认真做好我省2017年国家社科基金中华外译项目申报工作，请严格按照《公告》的要求，以高度负责的精神认真组织做好项目申报工作。现将有关事项通知如下：　　1、我省集中申报受理时间为8月4日至10月6日，请各单位在规定时间内将书面材料报送我办。逾期不予受理。　　2、申报材料包括：　　（1）《申请书》（2017年7月修订版）用计算机填写。一式7份，A3纸双面打印、中缝装订。　　（2）著作类成果应提供所翻译原著、译稿或翻译样章各6份（译文须为2万字以上核心章节，用外文直接写作的须提交已完成稿全文），与国外学术出版机构所签订的出版合同、国外出版机构法律证明文件等有关文本的复印件及中文译稿、原著著作权人授权证明各2份。另外，也可附上反映原著学术水平及其影响的相关材料。　　（3）期刊类成果应提供近一年内出版的样刊一式6份，期刊出版许可证副本复印件2份；反映本期刊学术水平及其社会影响的相关材料；编委会成员名单及工作单位（国际编委含国籍）。　　（4）项目申请书及翻译样章须附电子版光盘2张。　　（5）《国家社科基金中华学术外译项目申报信息汇总表》电子版发至指定邮箱（109600525@qq.com）。　　附件：1．2017年国家社科基金中华学术外译项目申报公告　　　　　2．国家社科基金中华学术外译项目申请书　　　　　3．国家社科基金中华学术外译项目申请书（2）　　　　　4．2017年国家社科基金中华学术外译项目申报信息汇总表　　　　　5．国家社科基金项目申报数据代码表　　　　　6．国家社科基金中华学术外译项目推荐选题目录（2017）　　　　　7．国家社科基金中华学术外译项目国外出版机构指导目录（2017）　　　　　　　　　　　　　　　　　　　　　广东省哲学社会科学规划领导小组办公室　　　　　　　　　　　　　　　　　　　　　　　　　　　2017年8月28日"));
	}
}
