package cn.iflin.admin.server.lucene.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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

/**
 * Lucene设置 2.为查询添加索引
 * 
 * @author Jaypan
 *
 */
public class LuceneSite {
	/**
	 * 当有新的文章时在C：\\ktbl\\ScoreIndex中建立临时索引
	 * 
	 * @author Jaypan
	 *
	 */
	public static class ScoreIndex {
		public static void addIndex(String content) throws IOException {
			String path = "C:\\ktbl\\Lucene\\ScoreTemp";
			File file = new File(path);
			if (file.exists()) {
				// System.out.println("file exists");
				delAllFile(path);
			}
			Analyzer analyzerIKA = new HanLPIndexAnalyzer();
			IndexWriterConfig configfile = new IndexWriterConfig(analyzerIKA);
			Directory fileindex;
			Path filePath = file.toPath();
			fileindex = FSDirectory.open(filePath);
			IndexWriter filew = new IndexWriter(fileindex, configfile);
			// System.out.println(fileindex);
			try {
				addDoc(filew, content);
			} finally {
				// 统一释放内存
				filew.close();
			}
		}

		private static void addDoc(IndexWriter w, String context) throws IOException {
			Document doc = new Document();
			doc.add(new TextField("Context", context, Field.Store.YES));
			w.addDocument(doc);
		}

		private static boolean delAllFile(String path) {
			boolean flag = false;
			File file = new File(path);
			if (!file.exists()) {
				return flag;
			}
			if (!file.isDirectory()) {
				return flag;
			}
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
				if (temp.isDirectory()) {
					delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
					flag = true;
				}
			}
			return flag;
		}
	}

	/**
	 * 2.为查询添加索引
	 * 
	 * @author Jaypan
	 *
	 */
	public static class SystemIndex {
		public static void addAllIndex(String articleId, String title, String time, String context, String url,
				String sourceName) throws IOException {
			// 1.建立索引
			// 实例化对象来连接数据库，同时用sql语句调用数据库中指定的数据表
			Analyzer analyzerIKA = new HanLPIndexAnalyzer();
			IndexWriterConfig configfile = new IndexWriterConfig(analyzerIKA);
			configfile.setOpenMode(OpenMode.CREATE_OR_APPEND);

			File file = new File("C:\\ktbl\\Lucene\\LuceneIndex");
			Path path = file.toPath();
			Directory fileindex = FSDirectory.open(path);
			IndexWriter filew = new IndexWriter(fileindex, configfile);
			addDoc(filew, articleId, title, time, context, url, sourceName);
			// 统一释放内存
			filew.close();
		}

		private static void addDoc(IndexWriter w, String articleId, String title, String time, String context,
				String url, String sourceName) throws IOException {
			Document doc = new Document();
			FieldType fieldType = new FieldType();
			fieldType.setStored(true);// set 是否存储
			fieldType.setTokenized(false);// set 是否分类
			fieldType.setTokenized(false);
			FieldType articleIdType = new FieldType();
			articleIdType.setStored(true);
			articleIdType.setTokenized(false); // 不分词
			doc.add(new Field("ArticleId", articleId, articleIdType));
			doc.add(new TextField("Title", title, Field.Store.YES));
			doc.add(new TextField("Time", time, Field.Store.YES));
			doc.add(new TextField("Context", context, Field.Store.YES));
			doc.add(new TextField("Url", url, Field.Store.YES));
			doc.add(new Field("Source", sourceName, articleIdType));
			w.addDocument(doc);
		}
	}
}
