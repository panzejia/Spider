package cn.iflin.spider.server.lucene.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import cn.iflin.admin.model.DAO.DAO;
import cn.iflin.admin.server.lucene.configuration.HanLPIndexAnalyzer;
import cn.iflin.admin.util.FileOp;
import cn.iflin.recommend.configuration.Parser;

public class LuceneTest {
	public static void main(String[] args) {
		try {
			addAllIndex() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addAllIndex() throws IOException {
		// 1.建立索引
		// 实例化对象来连接数据库，同时用sql语句调用数据库中指定的数据表
		String sql = "SELECT * FROM articleview";
		ResultSet rs = null;
		Analyzer analyzerIKA = new HanLPIndexAnalyzer();
		IndexWriterConfig configfile = new IndexWriterConfig(analyzerIKA);
		configfile.setOpenMode(OpenMode.CREATE_OR_APPEND);

		File file = new File("C:\\ktbl\\Lucene\\LuceneTest");
		Path path = file.toPath();

		// 删除已有索引
		if (file.exists()) {
			// System.out.println("file exists");
			FileOp.delAllFile("C:\\ktbl\\Lucene\\LuceneTest");
		}
		String logStr = "";
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");// 设置日期格式
//		File logFile = new File("C:\\ktbl\\log\\AddLuceneLog\\" + df.format(new Date()) + ".txt");

		Directory fileindex = FSDirectory.open(path);
		IndexWriter filew = new IndexWriter(fileindex, configfile);
		try {
			rs = DAO.executableQuery(sql);
			while (rs.next()) {
				String content = Parser.getOnlyChinese(rs.getString("Content_NoCode"));
				addDoc(filew, rs.getString("ArticleId"),rs.getString("Title") + "", content + "", rs.getString("StartTime") + "",
						rs.getString("StopTime") + "", rs.getString("name"));
				logStr += rs.getString("Source") + "" + rs.getString("Title") + "\r\n";
			}
//			FileOp.writeTxtFile(logStr, logFile);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 统一释放内存
			try {
				filew.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void addDoc(IndexWriter w,String articleId, String title, String content, String startTime, String stopTime,
			String area) throws IOException {
		Document doc = new Document();
		FieldType type = new FieldType();
        // 索引时保存文档、词项频率、位置信息、偏移信息
        type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        type.setStored(true);// 原始字符串全部被保存在索引中
        type.setStoreTermVectors(true);// 存储词项量
        type.setTokenized(true);// 词条化
        Field field1 = new Field("Content", content, type);
        doc.add(field1);
		doc.add(new TextField("ArticleId",articleId, Field.Store.YES));
		doc.add(new Field("Name", title, type));
		doc.add(new TextField("StartTime", startTime, Field.Store.YES));
		doc.add(new TextField("StopTime", stopTime, Field.Store.YES));
		doc.add(new TextField("Area", area, Field.Store.YES));
		w.addDocument(doc);
	}
}
