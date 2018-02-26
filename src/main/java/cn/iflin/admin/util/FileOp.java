package cn.iflin.admin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
/**
 * 用于对文件进行操作
 * 1.写入文件
 * 2.删除文件夹中所有文件
 * @author Panzejia
 *
 */
public class FileOp {
	// 建立txt文件存储每次建立索引信息
	public static boolean writeTxtFile(String content,File  file)throws Exception{  
		RandomAccessFile mm=null;  
		boolean flag=false;  
		FileOutputStream o=null;  
		try {  
			o = new FileOutputStream(file);  
			o.write(content.getBytes("UTF-8"));  
			o.close();  
		//   mm=new RandomAccessFile(fileName,"rw");  
		//   mm.writeBytes(content);  
			flag=true;  
		} catch (Exception e) {  
		   e.printStackTrace();  
		}finally{  
			if(mm!=null){  
				mm.close();  
			}  
		}  
		return flag;  
	}

	// 删除所有文件夹函数
	public static boolean delAllFile(String path) {
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
	/***
	 * 获取txt文件，将每一行的数据转成double类型后存入到ArrayList中
	 * @param path
	 * @return
	 */
	@SuppressWarnings("resource")
	public static ArrayList<String> readTextToList(String path){
        File filename = new File(path); // 要读取以上路径的input。txt文件  
        ArrayList<String> list = new ArrayList<String>();
        InputStreamReader reader;
		try {
			reader = new InputStreamReader(  
			        new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        String line = "";  
	        line = br.readLine();  
	        while (line != null) {  
	        	line = br.readLine(); // 一次读入一行数据  
	            if(line!=null){
		            list.add(line);
	            }
	        }  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
	}
}
