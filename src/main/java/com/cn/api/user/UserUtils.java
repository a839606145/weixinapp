package com.cn.api.user;

import com.alibaba.fastjson.JSONObject;
import com.cn.api.token.Token;
import com.cn.api.utils.HttpUitls;

public class UserUtils {

	public static String createTag(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	
	public static String getTags(){
		String url="https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doGet(url);
	}
	
	public static String updateTag(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	
	public static String deleteTag(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	
	public static String getTagFans(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	
	public static String bantchTag(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	public static String releaseBatchTag(JSONObject array){
			String url="https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN"
			.replaceAll("ACCESS_TOKEN", Token.getToken());
	  return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	public static String getUserTags(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		  return HttpUitls.doPostWithJSON(url,obj.toJSONString());
	}
	public static String updateMark(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		  return HttpUitls.doPostWithJSON(url,obj.toJSONString());
	}
	public static String getUserDetail(String openId){
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"
				.replaceAll("ACCESS_TOKEN", Token.getToken())
				.replaceAll("OPENID", openId);
		return HttpUitls.doGet(url);
	}
	public static String getUsersDetail(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		  return HttpUitls.doPostWithJSON(url,obj.toJSONString());
	}
	public static String getUsersList(String nextOpenId){
		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		if(nextOpenId==null){
			url=url.replaceAll("&next_openid=NEXT_OPENID", "");
		}else{
			url=url.replaceAll("NEXT_OPENID", nextOpenId);
			
		}
		return HttpUitls.doGet(url);
	}
	public static String getBackUser(String openId){
		String url="https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		JSONObject o=null;
		if(openId==null){
			openId=new String("");
		}
		o=new JSONObject();
		o.put("begin_openid", openId);
		return HttpUitls.doPostWithJSON(url,o.toJSONString());
	}
	
	public static String addBackUser(JSONObject o){
		String url="https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,o.toJSONString());
	}
	
	public static String cancleBackUser(JSONObject o){
		String url="https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,o.toJSONString());
	}
	
}
