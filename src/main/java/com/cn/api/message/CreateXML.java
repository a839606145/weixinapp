package com.cn.api.message;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreateXML {

	public static String createText(String toUser,String from,String content){
		String res="<xml>"+
					"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"+
				    "<FromUserName><![CDATA["+from+"]]></FromUserName>"+
					"<CreateTime>"+new Date().getTime()/1000+"</CreateTime>"+
				    "<MsgType><![CDATA[text]]></MsgType>"+
					"<Content><![CDATA["+content+"]]></Content>"+
				    "</xml>";
		return res;
	}
	public static String createImage(String toUser,String from,String mediaId){
		String res="<xml>"+
					"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"+
				    "<FromUserName><![CDATA["+from+"]]></FromUserName>"+
					"<CreateTime>"+new Date().getTime()/1000+"</CreateTime>"+
				    "<MsgType><![CDATA[image]]></MsgType>"+
					"<Image><MediaId><![CDATA["+mediaId+"]]></MediaId></Image>"+
				    "</xml>";
		return res;
	}
	
	public static String createVoice(String toUser,String from,String mediaId){
		String res="<xml>"+
					"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"+
				    "<FromUserName><![CDATA["+from+"]]></FromUserName>"+
					"<CreateTime>"+new Date().getTime()/1000+"</CreateTime>"+
				    "<MsgType><![CDATA[voice]]></MsgType>"+
					"<Voice><MediaId><![CDATA["+mediaId+"]]></MediaId></Voice>"+
				    "</xml>";
		return res;
	}
	
	public static String createVideo(String toUser,String from,String mediaId,String title,String description){
		String res="<xml>"+
					"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"+
				    "<FromUserName><![CDATA["+from+"]]></FromUserName>"+
					"<CreateTime>"+new Date().getTime()/1000+"</CreateTime>"+
				    "<MsgType><![CDATA[video]]></MsgType>"+
					"<Video><MediaId><![CDATA["+mediaId+"]]></MediaId></Voice>"+
					"<Title><![CDATA["+title+"]]></Title>"+
					"<Description><![CDATA["+description+"]]></Description>"+
					"</Video>"+
				    "</xml>";
		return res;
	}
	
	public static String createMusic(String toUser,String from,String mediaId,Map<String,String> dates){
		String res="<xml>"+
					"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"+
				    "<FromUserName><![CDATA["+from+"]]></FromUserName>"+
					"<CreateTime>"+new Date().getTime()/1000+"</CreateTime>"+
				    "<MsgType><![CDATA[video]]></MsgType>"+
				    "<Music>"+
				    "<Title><![CDATA["+dates.get("Title")+"]]></Title>"+
				    "<Description><![CDATA["+dates.get("Description")+"]]></Description>"+
				    "<MusicUrl><![CDATA["+dates.get("MusicUrl")+"]]></MusicUrl>"+
				    "<HQMusicUrl><![CDATA["+dates.get("HQMusicUrl")+"]]></HQMusicUrl>"+
				    "<ThumbMediaId><![CDATA["+mediaId+"]]></ThumbMediaId>"+
				    "</Music>"+
				    "</xml>";
		return res;
	}
	
	public static String createArticles(String toUser,String from,String mediaId,int count,List<Map<String,String>> dates){
		String res="<xml>"+
					"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"+
				    "<FromUserName><![CDATA["+from+"]]></FromUserName>"+
					"<CreateTime>"+new Date().getTime()/1000+"</CreateTime>"+
				    "<MsgType><![CDATA[video]]></MsgType>"+
				    "<ArticleCount>"+count+"</ArticleCount>"+
				    "<Articles>"+
				    getItems(dates)+
				      "</Articles>"+
				    "</xml>";
		return res;
	}
	private static String getItems(List<Map<String,String>> dates){
		StringBuffer sb=new StringBuffer();
		for(Map m:dates){
			sb.append("<item>"+
				      "<Title><![CDATA["+m.get("Title")+"]]></Title>"+
				      "<Description><![CDATA["+m.get("Description")+"]]></Description>"+
				      "<PicUrl><![CDATA["+m.get("PicUrl")+"]]></PicUrl>"+
				      "<Url><![CDATA["+m.get("Url")+"]]></Url>"+
				      "</item>");
		}
		return sb.toString();
	}
}
