package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.conn.ConnectTimeoutException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Spider {
	
	
	
	private static ArrayList<String> load(String id) throws IOException{
		
		ArrayList<String> comments = new ArrayList<String>();
		ArrayList<String> urls = setUrl(id);
		for(String url:urls) {
			try {
			String unparseString = request(url);
			comments.addAll(parse(unparseString));
			}catch(IOException e1) {
				throw new IOException();
			}
		}		
		return comments;
	}
	
	/*根据不同的url爬取并清洗数据，保存到ArrayList中，最后返回.
	**
	**@param id 股票id
	*/
	private static ArrayList<String> setUrl(String id){
		ArrayList<String> urls = new ArrayList<String>();
		String url = "https://xueqiu.com/query/v1/symbol/search/status?u=471618903549175&uuid=1384416939497107456&count=10&comment=0&symbol=&hl=0&source=all&sort=time&page=&q=&type=11&last_id=177653139&session_token=null&access_token=520e7bca78673752ed71e19b8820b5eb854123af";
		url = url.replaceAll("symbol=.*?&", "symbol="+id+"&");
		for(int page=1;page<=50;page++) {
			url = url.replaceAll("page=.*?&", "page="+page+"&");
			urls.add(url);
		}
		return urls;
	}
	
	
	
	
	/*伪造成浏览器访问并返回网站数据
	**
	**@param url 爬取的网址
	*/
	private static String request(String url) throws IOException{
		String result = "";
		
		BufferedReader in = null;
		try {
			 HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			 connection.setRequestProperty("accept","*/*");
			 connection.setRequestProperty("connection","Keep-Alive");
			 connection.setRequestProperty("user-agent",
					 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			 connection.connect();
			 
			 in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			 String line;
			 while((line = in.readLine())!=null) {
				 result+=line;
			 }
			 
			 
		} catch (IOException e) {
			throw new IOException();
		}finally {
			try {
				if(in != null) {
					in.close();
				}
			}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		
		return result;
	}
	
	
	
	
	/*清洗网站数据并返回评论。
	**
	**@param unparseString 原始的未经过清洗的网站数据
	*/
	private static ArrayList<String> parse(String unparseString) throws ConnectTimeoutException{
		ArrayList<String> comments = new ArrayList<String>();
		//将String转换为json
		JSONObject jsonObject = JSONObject.parseObject(unparseString);
		//取出其中的List(String)并转换为JSONArray
		JSONArray lists = (JSONArray) jsonObject.get("list");
		
//		int status = (Integer) jsonObject.get("count");
//		if(status==0) {throw new ConnectTimeoutException();
//		}
		
		if(lists==null) {return comments;}
		//层层剥落直到取到评论值
		for (int i=0; i<lists.size(); i++) {
			JSONObject list = (JSONObject)lists.get(i);
			String comment = list.getString("text");
			comments.add(replace(comment));
			
		}
		return comments;
	}
	
	
	/*取出html标签
	**
	**@param content 未取出html标签的评论内容
	*/
	private static String replace(String content) {
		content = content.replaceAll("&nbsp;", "");
		content = content.replaceAll("<p.*?>", "");
		content = content.replaceAll("</p.*?>", "");
		content = content.replaceAll("<a.*?>", "");
		content = content.replaceAll("</a.*?>", "");
		content = content.replaceAll("<br/.*?>", "");
		content = content.replaceAll("<img.*?>", "");
		content = content.replaceAll("</img.*?>", "");
		content = content.replaceAll("<b.*?>", "");
		content = content.replaceAll("</b.*?>", "");
		return content;
	}
	
	
	
	
	//输入错误的股票id但不含错误符号，网站会处理并返回200但是是空的json信息。这个问题还没处理。
	
	
	public static ArrayList<String> Load(String id) {
		
		try {
			ArrayList<String> comments = Spider.load(id);
			return comments;
		}catch (IOException e) {
			System.out.println("股票id错误!非法字符!");
		}
		return new ArrayList<String>();
	}
	

	
	
	
	
}
