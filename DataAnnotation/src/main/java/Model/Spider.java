package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class Spider {
	
	@LogAnnotation(className = "Model.Spider" , content = "Spider : load() trycatch")
	private static ArrayList<Data> load(String id) throws IOException{
		
		ArrayList<Data> dataArray = new ArrayList<Data>();
		ArrayList<String> urls = setUrl(id);
		for(String url:urls) {
			try {
			String unparseString = request(url);
			dataArray.addAll(parse(unparseString));
			}catch(IOException e1) {
				throw new IOException();
			}
		}		
		return dataArray;
	}
	
	/*根据不同的url爬取并清洗数据，保存到ArrayList中，最后返回.
	**
	**@param id 股票id
	*/
	private static ArrayList<String> setUrl(String id){
		ArrayList<String> urls = new ArrayList<String>();
		String url = "https://xueqiu.com/query/v1/symbol/search/status?u=471618903549175&uuid=1384416939497107456&count=10&comment=0&symbol=&hl=0&source=all&sort=time&page=&q=&type=11&last_id=177653139&session_token=null&access_token=520e7bca78673752ed71e19b8820b5eb854123af";
		url = url.replaceAll("symbol=.*?&", "symbol="+id+"&");
		for(int page=1;page<=20;page++) {
			url = url.replaceAll("page=.*?&", "page="+page+"&");
			urls.add(url);
		}
		return urls;
	}
	
	
	
	
	/*伪造成浏览器访问并返回网站数据
	**
	**@param url 爬取的网址
	*/
	@LogAnnotation(className = "Model.Spider" , content = "Spider : request() trycatch")
	private static String request(String url) throws IOException{
		String result = "";
		
		BufferedReader in = null;
		try {
			 HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			 connection.setRequestProperty("Charset", "unicode");
			 connection.setRequestProperty("accept","*/*");
			 connection.setRequestProperty("connection","Keep-Alive");
			 connection.setRequestProperty("user-agent",
					 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			 connection.connect();
			 
			 in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			 String line = null;
			 while((line = in.readLine())!=null) {
				 line = line;
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
		//result = toUTF8(result);
		return result;
	}
	
	
	
	
	/*清洗网站数据并返回评论。
	**
	**@param unparseString 原始的未经过清洗的网站数据
	*/
	private static ArrayList<Data> parse(String unparseString) throws ConnectTimeoutException{
		ArrayList<Data> dataArray = new ArrayList<Data>();
		//将String转换为json
		JSONObject jsonObject = JSONObject.parseObject(unparseString);
		//取出其中的List(String)并转换为JSONArray
		JSONArray lists = (JSONArray) jsonObject.get("list");
		
//		int status = (Integer) jsonObject.get("count");
//		if(status==0) {throw new ConnectTimeoutException();
//		}
		
		if(lists==null) {return dataArray;}
		//层层剥落直到取到评论值
		for (int i=0; i<lists.size(); i++) {
			Data data = new Data();
			JSONObject list = (JSONObject)lists.get(i);
			String comment = list.getString("text");
			JSONObject time = list.getJSONObject("trackJson");
			JSONObject user = list.getJSONObject("user");
			
			data.setComment(replace(comment));
			data.setName(user.getString("screen_name"));
			data.setTime(time.getString("created_at"));
			dataArray.add(data);
			
		}
		return dataArray;
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
	
	//已改
	@LogAnnotation(className = "Model.Spider" , content = "Spider : Load() trycatch")
	public static ArrayList<Data> Load(String id) {
		
		try {
			ArrayList<Data> data = Spider.load(id);
			return data;
		}catch (IOException e) {
			System.out.println("股票id错误!非法字符!");
		}
		return new ArrayList<Data>();
		
	}
	
	
//	public static void main(String[] args) {
//		ArrayList<Data> dataArrayList = Spider.Load("SZ002304");//SZ002304
//		for(Data data:dataArrayList) {
//			System.out.println(data.getName());
//			System.out.println(data.getTime());
//			System.out.println(data.getComment());	
//		}
//	}

	
	
	
	
	public static class Data{
		
		public Data() {
			
		}
		
		private String name;
		private String time;
		private String comment;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
			
		
	}
	
	
	/**
	 * 将字符串的编码格式转换为utf-8
	 * 
	 * @param str
	 * @return Name = new
	 * String(Name.getBytes("ISO-8859-1"), "utf-8");
	 */
	@LogAnnotation(className = "Model.Spider" , content = "Spider : toUTF8 trycatch")
	public static String toUTF8(String str) {
		if (isEmpty(str)) {
			return "";
		}
		try {
			if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
				str = new String(str.getBytes("GB2312"), "utf-8");
				return str;
			}
		} catch (Exception exception) {
		}
		try {
			if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
				str = new String(str.getBytes("ISO-8859-1"), "utf-8");
				return str;
			}
		} catch (Exception exception1) {
		}
		try {
			if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
				str = new String(str.getBytes("GBK"), "utf-8");
				return str;
			}
		} catch (Exception exception3) {
		}
		return str;
	}
	 

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		// 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
		if (str != null && !str.trim().isEmpty()) {
			return false;// 不为空
		}
		return true;// 为空
	}

}
