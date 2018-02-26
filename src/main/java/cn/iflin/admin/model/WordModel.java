package cn.iflin.admin.model;
/**
 * 单词数据模型，
 * @author Panzejia
 *
 */
public class WordModel {
	private String word;
	private String wordId;
	private Integer wordFrequency;
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	/**
	 * @return the wordFrequency
	 * 出现的次数
	 */
	public Integer getWordFrequency() {
		return wordFrequency;
	}
	/**
	 * @param wordFrequency the wordFrequency to set
	 * 设置出现的次数
	 */
	public void setWordFrequency(Integer wordFrequency) {
		this.wordFrequency = wordFrequency;
	}
	/**
	 * @return the wordId
	 */
	public String getWordId() {
		return wordId;
	}
	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}
	/**
	 * @param wordId the wordId to set
	 */
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
}
