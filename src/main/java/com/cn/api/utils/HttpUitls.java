package com.cn.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;

import com.cn.api.message.CreateXML;


public class HttpUitls {

	/**
	 * 得到收到的信息
	 * @param request
	 * @return
	 */
	public static String getString(HttpServletRequest request){
		BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try{
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (null != reader){ reader.close();}
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
	}
	/**
	 * 创建返回信息
	 * @param response
	 * @param toUser
	 * @param from
	 * @param content
	 */
	public static void createResonseText(HttpServletResponse response,String toUser,String from,String content){
		response.setHeader("content-type", "application/xml;charset=UTF-8");
		PrintWriter outputStream=null;
		try {
			outputStream= response.getWriter();
			String date=CreateXML.createText(toUser,from,content);
			outputStream.print(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建返回的图片信息
	 * @param response
	 * @param toUser
	 * @param from
	 * @param meidaId
	 */
	public static void createResonseImage(HttpServletResponse response,String toUser,String from,String meidaId){
		PrintWriter outputStream=null;
		try {
			outputStream= response.getWriter();
			String date=CreateXML.createImage(toUser,from,meidaId);
			outputStream.print(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 访问微信平台
	 * @param url
	 */
	public static String doGet(String url){
		String result="";
		BufferedReader in=null;
		try{
			URL requrl=new URL(url);
			URLConnection conn=requrl.openConnection();
			
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			
			conn.connect();
			conn.setReadTimeout(2000);
			in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String doPost(String url,Map<String,Object> params){
		String result="";
		BufferedReader in=null;
		PrintWriter out=null;
		try{
			URL requrl=new URL(url);
			URLConnection conn=requrl.openConnection();
			
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out=new PrintWriter(conn.getOutputStream());
			out.print(params);
			out.flush();
			
			in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line=in.readLine())!=null){
				result+=line;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
						if(in!=null){
						 in.close();
						}
						if(out!=null){
							out.close();
						}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		return result;
	}
	/**
	 * post 提交文件以及文本
	 * @param url
	 * @param files
	 * @param dates
	 * @return
	 */
	public static String doPostWithFile(String url,Map<String, String> files,Map<String,String> dates){
		HttpClient httpClient=HttpClients.createDefault();
		String result="";
		try{
			HttpPost post=new HttpPost(url);
			MultipartEntityBuilder entityBuilder=MultipartEntityBuilder.create();
			if(files!=null){
				for(String key:files.keySet()){
					entityBuilder.addPart(key,new FileBody(new File(files.get(key))));
				}
			}
			if(dates!=null){
				for(String key:dates.keySet()){
					entityBuilder.addTextBody(key,dates.get(key));
				}
			}
			
			HttpEntity entity=entityBuilder.build();
			post.setEntity(entity);
			HttpResponse response=httpClient.execute(post);
			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode==200){
				HttpEntity httpentity=response.getEntity();
				BufferedReader in=new BufferedReader(new InputStreamReader(httpentity.getContent()));
				String line="";
				while((line=in.readLine())!=null){
					result+=line;
				}
				if(in!=null){
					in.close();
				}
			}
		}catch (Exception e) {
			
		}
		return result;
	}
	/**
	 * post 以json格式提交
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostWithJSON(String url,String json){
		HttpClient client=HttpClients.createDefault();
		String result="";
		try {
			HttpPost post=new HttpPost(url);
			StringEntity entity=new StringEntity(json);
			entity.setContentType("application/json");
			
			post.setEntity(entity);
			
			HttpResponse response=client.execute(post);
			int code=response.getStatusLine().getStatusCode();
			if(code==200){
				BufferedReader in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			    String line;
			    while((line=in.readLine())!=null){
			    	result+=line;
			    }
			    in.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
}
