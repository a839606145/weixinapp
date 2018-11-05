package com.cn.api.utils;

import com.alibaba.fastjson.JSONObject;

public class FastJsonUtils {

	public static String getMediaId(String result){
		JSONObject o=JSONObject.parseObject(result);
		return o.getString("media_id");
	}
}
