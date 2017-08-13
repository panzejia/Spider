package cn.iflin.spider.server.wordcloud;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.iflin.spider.model.WordModel;

/**
 * 计算词频
 * 
 * @author Jaypan
 *
 */
public class WordCloud {

	public static void main(String[] args) throws IOException {
//		ArrayList<WordModel> wordList = getTF(
//				"中新网3月12日电 据中国中国中国中国中国中国中国中国中国中国政府网消息，3月12日上午10时15分，李克强总理参加完政协闭幕会后来到国务院应急指挥中心，与前方中国搜救船长通话，了解马航MH370失联客机搜救最新进展情况。李克强要求各有关部门调集一切可能力量，加大搜救密度和力度，不放弃任何一线希望。 ");
		// for (WordModel word : wordList) {
		// System.out.println(word.getWord() + " " + word.getWordFrequency());
		// }
//		class SortByFre implements Comparator {
//			public int compare(Object o1, Object o2) {
//				WordModel s1 = (WordModel) o1;
//				WordModel s2 = (WordModel) o2;
//				return s2.getWordFrequency().compareTo(s1.getWordFrequency());
//			}
//		}
//		System.out.println("---------------------");
//		Collections.sort(wordList, new SortByFre());
//		for (WordModel word : wordList) {
//			System.out.println(word.getWord() + "    " + word.getWordFrequency());
//		}
	}

	/**
	 * 返回排序后的词频
	 * 
	 * @param text
	 * @return
	 */
	public static ArrayList<WordModel> getWordFre(String text,String articleId) {
		ArrayList<WordModel> wordList = new ArrayList<WordModel>();
		try {
			wordList = getTF(text,articleId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 定义排序规则
		class SortByFre implements Comparator {
			public int compare(Object o1, Object o2) {
				WordModel s1 = (WordModel) o1;
				WordModel s2 = (WordModel) o2;
				return s2.getWordFrequency().compareTo(s1.getWordFrequency());
			}
		}
		Collections.sort(wordList, new SortByFre());
		return wordList;
	}
	/**
	 * 判断文件夹是否存在之后调用索引计算词频
	 */
	private static  ArrayList<WordModel> checkFile(String text,String articleId){
		ArrayList<WordModel> wordList = new ArrayList<WordModel>();
		return wordList;
	}
	/**
	 * 建立索引之后计算词频
	 * @param text
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<WordModel> getTF(String text,String articleId) throws IOException {
		ArrayList<WordModel> wordList = new ArrayList<WordModel>();
		File file = new File("C:\\Spider\\WordCloud_Lucene\\"+articleId);
		deleteDir(file);
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_47, analyzer);// 创建索引的配置信息
		Directory fileindex;
		fileindex = FSDirectory.open(file);
		IndexWriter filew = new IndexWriter(fileindex, configfile);
		try {
			addDoc(filew, text);
		} finally {
			// 统一释放内存
			filew.close();
		}
		try {
			IndexReader reader = DirectoryReader.open(fileindex);
			for (int i = 0; i < reader.numDocs(); i++) {
				int docId = i;
				Terms terms = reader.getTermVector(docId, "text");
				if (terms == null)
					continue;
				TermsEnum termsEnum = terms.iterator(null);
				BytesRef thisTerm = null;
				while ((thisTerm = termsEnum.next()) != null) {
					String termText = thisTerm.utf8ToString();
					DocsEnum docsEnum = termsEnum.docs(null, null);
					while ((docsEnum.nextDoc()) != DocIdSetIterator.NO_MORE_DOCS) {
						if (isChinese(termText) && termText.length() >= 2) {
							WordModel wm = new WordModel();
							wm.setWord(termText);
							wm.setWordFrequency(docsEnum.freq());
							wordList.add(wm);
						}
					}
				}
			}
			reader.close();
			fileindex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}

	private static void addDoc(IndexWriter w, String text) throws IOException {
		Document doc = new Document();
		FieldType ft = new FieldType();
		ft.setIndexed(true);// 存储
		ft.setStored(true);// 索引
		ft.setStoreTermVectors(true);
		ft.setTokenized(true);
		ft.setStoreTermVectorPositions(true);// 存储位置
		ft.setStoreTermVectorOffsets(true);// 存储偏移量
		doc.add(new Field("text", text, ft));
		w.addDocument(doc);
	}

	/**
	 * 删除文件
	 * 
	 * @param dir
	 * @return
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	// 判断一个字符是否是中文
	private static boolean isChinese(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
	}

	// 判断一个字符串是否含有中文
	private static boolean isChinese(String str) {
		if (str == null)
			return false;
		for (char c : str.toCharArray()) {
			if (isChinese(c))
				return true;// 有一个中文字符就返回
		}
		return false;
	}
}
