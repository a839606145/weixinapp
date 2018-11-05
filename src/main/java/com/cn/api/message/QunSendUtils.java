package com.cn.api.message;

import com.alibaba.fastjson.JSONObject;
import com.cn.api.token.Token;
import com.cn.api.utils.HttpUitls;

public class QunSendUtils {

	public static String qunSend(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN"
				 .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	
	public static String qunSendAddPicText(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN"
				 .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	
	public static String qunSendAddVedio(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN"
				 .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	public static String qunSendOpenIds(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN"
				 .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	public static String deleteSends(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN"
				 .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	public static String yulanSend(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN"
				 .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	
	
	
}
