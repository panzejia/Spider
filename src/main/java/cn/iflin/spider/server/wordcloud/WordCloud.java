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

import cn.iflin.server.englishwords.EnglishParser;
import cn.iflin.spider.model.WordModel;

/**
 * 计算词频
 * 
 * @author Jaypan
 *
 */
public class WordCloud {

	public static void main(String[] args) throws IOException {
		ArrayList<WordModel> wordList = getTF(
				"Could a hug a day keep the doctor away? The answer may be a resounding“yes!” 1 helping you feel close and 2 to people you care about, it turns out that hugs can bring a 3 of health benefits to your body and mind.Believe it or not, a warm embrace might even help you 4 getting sick thiswinter. In a recent study 5 over 400 health adults, researchers from Carnegie Mellon University in Pennsylvania examined the effects ofperceived social support and the receipt of hugs 6 the articipants’susceptibility to developing the common cold after being 7 to the virus.People who perceived greater social support were less likely to come 8with a cold ,and the researchers 9 that the stress-reducing effects of hugging 10 about 32 percent of that beneficial effect. 11 among those whogot a cold, the ones who felt greater social support and received morefrequent hugs had less severe 12 .“Hugging protects people who are understress from the 13 risk for colds that’s usually 14 with stress,” notes Sheldon Cohen, a professor of psychology at Carnegie. Hugging “is amarker of intimacy and helps 15 the feeling that others are there to help16 difficulty. Some experts 17 the stress-reducing , health-related benefits of hugging to the release of oxytocin, often called “the bonding hormone” 18 it promotes attachment in relationships, including that between mother and their newborn babies. Oxytocin is made primarily in the central lower part of the brain , and some of it is released into the bloodstream. But some of it 19 in the brain, where it 20 mood, behavior and physiology.”First two hours , now three hours—this is how far in advance authorities are recommending people show up to catch a domestic flight , at least at some major U.S. airports with increasingly massive security lines. Americans are willing to tolerate time-consuming security procedures in return for increased safety. The crash of Egypt Air Flight 804,which terrorists may have downed over the Mediterranean Sea ,provides another tragic reminder of why. But demanding too much of air travelers or providing too little security in return undermines public support for the process. And it should: Wasted time is a drag on Americans’ economic and private lives, not to mention infuriating. Last year, the Transportation Security Administration (TSA) found in a secret check that undercover investigators were able to sneak weapons---both fake and real—past airport security nearly every time they tried .Enhanced security measures since then, combined with a rise in airline travel due to the improving Chicago’s O’Hare International .It is not yet clear how much more effective airline security has become—but the lines are obvious. Part of the issue is that the government did not anticipate the steep increase in airline travel , so the TSA is now rushing to get new screeners on the line. Part of the issue is that airports have only so much room for screening lanes. Another factor may be that more people are trying to overpack their carry-on bags to avoid checked-baggage fees, though the airlines strongly dispute this. There is one step the TSA could take that would not require remodeling airports or rushing to hire: Enroll more people in the PreCheck program. PreCheck is supposed to be a win-win for travelers and the TSA. Passengers who pass a background check are eligible to use expedited screening lanes. This allows the TSA wants to enroll 25 million people in PreCheck. It has not gotten anywhere close to that, and one big reason is sticker shock. Passengers must pay $85 every five years to process their background checks. Since the beginning,this price tag has been PreCheck’s fatal flaw. Upcoming reforms might bring the price to a more reasonable level. But Congress should look into doing so directly, by helping to finance PreCheck enrollment or to cut costs in other ways. The TSA cannot continue diverting resources into underused PreCheck lanes while most of the traveling public suffers in unnecessary lines. It is long past time to make the program work.",
				"1", "english","");
		class SortByFre implements Comparator {
			public int compare(Object o1, Object o2) {
				WordModel s1 = (WordModel) o1;
				WordModel s2 = (WordModel) o2;
				return s2.getWordFrequency().compareTo(s1.getWordFrequency());
			}
		}
		System.out.println("---------------------");
		Collections.sort(wordList, new SortByFre());
		for (WordModel word : wordList) {
			System.out.println(word.getWord() + " " + word.getWordFrequency());
		}
		System.out.println("总共：" + wordList.size());
	}

	/**
	 * 返回排序后的词频
	 * 
	 * @param text
	 * @return
	 */
	public static ArrayList<WordModel> getWordFre(String text, String articleId, String tag, String englishClass) {
		ArrayList<WordModel> wordList = new ArrayList<WordModel>();
		try {
			wordList = getTF(text, articleId, tag,englishClass);
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
	private static ArrayList<WordModel> checkFile(String text, String articleId) {
		ArrayList<WordModel> wordList = new ArrayList<WordModel>();
		return wordList;
	}

	/**
	 * 建立索引之后计算词频
	 * 
	 * @param text
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<WordModel> getTF(String text, String articleId, String tag, String englishClass)
			throws IOException {
		ArrayList<WordModel> wordList = new ArrayList<WordModel>();
		File file = new File("C:\\Spider\\WordCloud_Lucene\\" + articleId);
		deleteDir(file);
		Analyzer analyzer = new IKAnalyzer(true);// 智能分词模式，如果构造函数参数为false，那么使用最细粒度分词。
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
						if (tag.equals("english")) {
							if (EnglishParser.checkEnglishWord(termText, englishClass)) {
								WordModel wm = new WordModel();
								wm.setWord(termText);
								wm.setWordFrequency(docsEnum.freq());
								wordList.add(wm);
							}
						}
						if (tag.equals("chinese")) {
							if (isChinese(termText) && termText.length() >= 2) {
								WordModel wm = new WordModel();
								wm.setWord(termText);
								wm.setWordFrequency(docsEnum.freq());
								wordList.add(wm);
							}
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

	// public static void main(String[] args) {
	// System.out.println(notEnglishStopWord("asdgsa"));
	// }
}
