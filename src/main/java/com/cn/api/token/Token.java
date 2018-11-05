package com.cn.api.token;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.aop.ThrowsAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Token {

	private final static String APPID="wxcce58ed3550d96bc";//"wx281a8ad08f7f9618";
	private final static String SECRET="5bdd279e2e33e2f946804e1beca1e325";//"8919bd8e286029252495217fca591873";
	private static String access_token=null;//获取到的token
	private static Date getDate;//获取到token的时间
	private static long MAXTIME=6000l;
	
	public static String getToken(){
		if(access_token==null||Math.abs(new Date().getTime()-getDate.getTime())>MAXTIME){
			String menuurl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;
			try {
				HttpClient client=HttpClients.createDefault();
				HttpGet request=new HttpGet(menuurl);
				HttpResponse response=client.execute(request);
				HttpEntity entity = response.getEntity();
				JSONObject json=JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
				if(json.containsKey("access_token")){
					access_token=json.getString("access_token");
					getDate=new Date();
				}else{
					throw new Exception(json.getString("errmsg"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return access_token;
	}
	
	
	
}
