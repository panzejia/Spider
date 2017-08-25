package cn.iflin.server.englishwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EnglishParser {
	public static void main(String[] args) {
		ArrayList<String> words = getEnglishWords("kaoyan");
		for(String word : words){
			System.out.println(word);
		}
	}
	public static boolean checkEnglishWord(String checkWord,String englishClass){
		ArrayList<String> words = getEnglishWords(englishClass);
		for(String word : words){
			if(word.equals(checkWord)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取单词列表
	 * @param sourceName 单词级别
	 * @return 单词列表
	 */
	private static ArrayList<String> getEnglishWords(String sourceName){
		ArrayList<String> words = new ArrayList<String>();
		String filePath = "C:\\Spider\\EnglishWords\\"+sourceName+".txt";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if(file.isFile()&&file.exists()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader br = new BufferedReader(read);
				String lineText = null;
				while((lineText = br.readLine())!=null){
					words.add(lineText);
				}
				read.close();
			}else{
				System.out.println("找不到指定文件");
			}
		}catch (Exception e){
			System.out.println("读取文件出错");
			e.printStackTrace();
		}
		return words;
		
	}
}
