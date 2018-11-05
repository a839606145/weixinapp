package com.cn.api.message;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cn.api.token.Token;
import com.cn.api.utils.HttpUitls;
/**
 * 素材管理类
 * @author Administrator
 *
 */
public class MateriaUtils {

	public static String addTempMateria(String type,String filePath){
		String url="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"
				.replaceAll("ACCESS_TOKEN", Token.getToken())
				.replaceAll("TYPE",type);
		
		Map<String, String> files = new HashMap<String, String>();
		files.put("media", filePath);
		return HttpUitls.doPostWithFile(url, files, null);
	}
	/**
	 * 获取临时素材 3天
	 * @param type
	 * @param mediaId
	 * @return
	 */
	public static String getTempMateria(String type,String mediaId){
		String url="";
		if(type.equals("vedio")){
			url="http://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
					.replaceAll("ACCESS_TOKEN",Token.getToken())
					.replaceAll("MEDIA_ID", mediaId);
		}else{
			url="http://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
					.replaceAll("ACCESS_TOKEN",Token.getToken())
					.replaceAll("MEDIA_ID", mediaId);
		}
		return HttpUitls.doGet(url);
	}
	
	/**
	 * 获取临时素材 3天【高清音频】
	 * @param type
	 * @param mediaId
	 * @return
	 */
	public static String getTempMateria(String mediaId){
		String url="https://api.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
					.replaceAll("ACCESS_TOKEN",Token.getToken())
					.replaceAll("MEDIA_ID", mediaId);
		return HttpUitls.doGet(url);
	}
	
	/**
	 * 图文信息中图的链接
	 * @param filePath
	 * @return
	 */
	public static String getPicTextURL(String filePath){
		String url="https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN",Token.getToken());
		Map<String,String> map=new HashMap<String, String>();
		map.put("media", filePath);
		return HttpUitls.doPostWithFile(url,map, null);
	}
	
	/**
	 * 非视频的其他永久素材
	 * @param type
	 * @param filePath
	 * @return
	 */
	public static String addTerminalria(String type,String filePath){
		String url="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE"
				.replaceAll("ACCESS_TOKEN", Token.getToken())
				.replaceAll("TYPE",type);
		
		Map<String, String> files = new HashMap<String, String>();
		files.put("media", filePath);
		return HttpUitls.doPostWithFile(url, files, null);
	}
	
	/**
	 * 视频的永久素材
	 * @param type
	 * @param filePath
	 * @return
	 */
	public static String addTerminalria(String type,String filePath,String title,String introduction){
		String url="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE"
				.replaceAll("ACCESS_TOKEN", Token.getToken())
				.replaceAll("TYPE",type);
		
		Map<String, String> files = new HashMap<String, String>();
		files.put("media", filePath);
		JSONObject j=new JSONObject();
		j.put("title", title);
		j.put("introduction", introduction);
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("description",j.toJSONString());
		return HttpUitls.doPostWithFile(url, files, datas);
	}
	
	/**
	 * 图片素材
	 * @param type
	 * @param filePath
	 * @return
	 */
	public static String addPicText(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	
	public static String addPicText2(JSONObject array){
		String url="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doPostWithJSON(url,array.toJSONString());
	}
	/**
	 * 得到永久素材
	 * @param mediaId
	 * @return
	 */
	public static String getTerminal(String mediaId){
		String url="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		JSONObject o=new JSONObject();
		o.put("media_id", mediaId);
		return HttpUitls.doPostWithJSON(url,o.toJSONString());
	}
	/**
	 * 删除永久素材
	 * @param mediaId
	 * @return
	 */
	public static String removeTerminal(String mediaId){
		String url="https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		JSONObject o=new JSONObject();
		o.put("media_id", mediaId);
		return HttpUitls.doPostWithJSON(url,o.toJSONString());
	}
	
	/**
	 * 获取永久素材的个数
	 * @return
	 */
	public static String getAllTerminalCount(){
		String url="https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		return HttpUitls.doGet(url);
	}
	/**
	 * 得到用户素材
	 * @param type
	 * @param count【1-20】
	 * @param offset【偏移量】
	 * @return
	 */
	public static String getAllTerminal(String type,int count,int offset){
		String url="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN"
				.replaceAll("ACCESS_TOKEN", Token.getToken());
		JSONObject o=new JSONObject();
		o.put("type", type);
		o.put("offset", offset);
		o.put("count", count);
		return HttpUitls.doPostWithJSON(url, o.toJSONString());
	}
	
}
