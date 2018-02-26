package PaperTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import cn.iflin.admin.model.DAO.DAO;

/**
 * 用于对剩余时间的测试
 * @author Panzejia
 *
 */
public class TimeTest {
	public static void main(String[] args) {
		String sql = "select ArticleId,StartTime,StopTime from content_info";
		ResultSet result = DAO.executableQuery(sql);
		int nullTime =0,nullDay=0,count=0;
		HashMap<String,Double> article = new HashMap<String,Double>();
		try {
			while(result.next()){
				count++;
				String start = result.getString("StartTime").replaceAll(" ", "");
				String stop = result.getString("StopTime").replaceAll(" ", "");
				if(stop==null||stop.equals("")){
					nullTime++;
					continue;
				}
				
				double day = getRT(start,stop);
				if(day<0){
					nullDay++;
					continue;
				}
				System.out.println("剩余时间"+day);
				double score = getScore(day*1.62/30);
				System.out.println(score);
				
				article.put(result.getString("ArticleId"), score);
			}
			System.out.println("没有时间的："+nullTime+"时间为负的："+nullDay+"总共有："+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static double getRT(String start,String stop){
		double time = 0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date1 = null,date2 = null;
		try {
			date1 = sdf.parse(start);
			date2 = sdf.parse(stop);  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        long l = date2.getTime() - date1.getTime();  
        long day = l / (24 * 60 * 60 * 1000);
        time = day;
        
        return time;
	}
	public static double getScore(double time){
		double score = 0.0;
		score = Math.pow(((Math.pow(5, 0.5)+1)/2)-time, 3)+Math.pow(((Math.pow(5, 0.5)+1)/2)-time, 2)-Math.pow(((Math.pow(5, 0.5)+1)/2)-time, 4);
		return score;
	}
}
