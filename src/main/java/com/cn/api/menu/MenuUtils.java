package com.cn.api.menu;

import com.alibaba.fastjson.JSONObject;
import com.cn.api.token.Token;
import com.cn.api.utils.HttpUitls;

/**
 * 菜单管理
 * @author Administrator
 *
 */
public class MenuUtils {

	public static String addMenu(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	
	public static String getMenu(){
		String url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doGet(url);
	}
	
	public static String removeMenu(){
		String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doGet(url);
	}
}
