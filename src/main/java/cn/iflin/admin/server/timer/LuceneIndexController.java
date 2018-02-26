package cn.iflin.admin.server.timer;

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
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import cn.iflin.admin.model.DAO.DAO;
import cn.iflin.admin.server.lucene.configuration.HanLPIndexAnalyzer;
import cn.iflin.admin.util.FileOp;

/**
 * 每隔一两小时添加索引
 * 
 * @author Jaypan
 *
 */
/**
public class LuceneIndexController extends QuartzJobBean {
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// 删除已有索引
		String path = "C:\\ktbl\\Lucene\\LuceneIndex";
		File file = new File(path);
		if (file.exists()) {
			// System.out.println("file exists");
			delAllFile(path);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置日期格式
		File logFile = new File("C:\\ktbl\\log\\AddLuceneLog\\"+df.format(new Date())+".txt");
		System.out.println("建立索引");
		String logStr = "";
		// 数据库取出文章
		MysqlConnection mysqlconn = new MysqlConnection();
		Connection conn = mysqlconn.getConnection();
		// 1.建立索引
		// 实例化对象来连接数据库，同时用sql语句调用数据库中指定的数据表
		String sql = "SELECT * FROM context";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SystemIndex.addAllIndex(rs.getString("ArticleId") + "", rs.getString("Title") + "",
						rs.getString("Time") + "", rs.getString("Context") + "", rs.getString("Url") + "",
						rs.getString("Source"));
				logStr += rs.getString("Source")+"_____"+rs.getString("Title")+"\r\n";
			}
			writeTxtFile(logStr,logFile);
		} catch (SQLException e) {
			System.out.println("在添加索引过程中出现sql错误");
			
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("在添加索引过程中出现读写错误");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 统一释放内存
			try {
				rs.close();
				pstmt.close();
				if (!conn.isClosed()) {
					//conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
*/

public class LuceneIndexController {
	
	public static void main(String[] args) throws IOException {
		addAllIndex();
//		FileOp.delAllFile("C:\\ktbl\\Lucene\\LuceneIndex");
	}
	
	public static void addAllIndex() throws IOException {
		// 1.建立索引
		// 实例化对象来连接数据库，同时用sql语句调用数据库中指定的数据表
		String sql = "SELECT * FROM articleview";
		ResultSet rs = null;
		Analyzer analyzerIKA = new HanLPIndexAnalyzer();
		IndexWriterConfig configfile = new IndexWriterConfig(analyzerIKA);
		configfile.setOpenMode(OpenMode.CREATE_OR_APPEND);

		File file = new File("C:\\ktbl\\Lucene\\LuceneSearch");
		Path path = file.toPath();

		// 删除已有索引
		if (file.exists()) {
			// System.out.println("file exists");
			FileOp.delAllFile("C:\\ktbl\\Lucene\\LuceneSearch");
		}
		String logStr = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");// 设置日期格式
		File logFile = new File("C:\\ktbl\\log\\AddLuceneLog\\" + df.format(new Date()) + ".txt");

		Directory fileindex = FSDirectory.open(path);
		IndexWriter filew = new IndexWriter(fileindex, configfile);
		try {
			rs = DAO.executableQuery(sql);
			while (rs.next()) {
				addDoc(filew, rs.getString("ArticleId"),rs.getString("Title") + "", rs.getString("Content_NoCode") + "", rs.getString("StartTime") + "",
						rs.getString("StopTime") + "", rs.getString("name"));
				logStr += rs.getString("Source") + "" + rs.getString("Title") + "\r\n";
			}
			FileOp.writeTxtFile(logStr, logFile);
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

	private static void addDoc(IndexWriter w, String articleId,String title, String content, String startTime, String stopTime,
			String area) throws IOException {
		Document doc = new Document();
		FieldType fieldType = new FieldType();
		fieldType.setStored(true);// set 是否存储
		fieldType.setTokenized(false);// set 是否分类
		FieldType articleIdType = new FieldType();
		articleIdType.setStored(true);
		articleIdType.setTokenized(false); // 不分词
		doc.add(new Field("ArticleId", articleId, fieldType));
		doc.add(new TextField("Title", title, Field.Store.YES));
		doc.add(new TextField("Content", content, Field.Store.YES));
		doc.add(new Field("StartTime", startTime, fieldType));
		doc.add(new Field("StopTime", stopTime, fieldType));
		doc.add(new Field("Area", area, fieldType));
		w.addDocument(doc);
	}
	

}