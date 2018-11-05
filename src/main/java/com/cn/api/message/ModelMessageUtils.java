package com.cn.api.message;

import com.alibaba.fastjson.JSONObject;
import com.cn.api.token.Token;
import com.cn.api.utils.HttpUitls;

public class ModelMessageUtils {

	/**
	 * 设置模板
	 * @param obj
	 * @return
	 */
	public static String setModel(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN"
				    .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	/**
	 * 得到设置的模板信息
	 * @return
	 */
	public static String getModel(){
		String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN"
				    .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doGet(url);
	}
	/**
	 * 得到模板列表
	 * @return
	 */
	public static String getModelList(){
		String url="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN"
				   .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doGet(url);
				
	}
	/**
	 * 发送模板信息
	 * @param obj
	 * @return
	 */
	public static String sendMessage(JSONObject obj){
		String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN"
				    .replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url, obj.toJSONString());
	}
	
}
