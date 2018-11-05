package com.cn.api.message;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cn.api.utils.DateUtils;
/**
 * 解析公共号收到的消息
 * @author Administrator
 *
 */
public class ParseXMl implements MessageType {
	public static MessagePo getMessageByXML(String xml){
		if(xml!=null){
			MessagePo message=new MessagePo();
			try {
				Document doc=DocumentHelper.parseText(xml);
				Element rootE=doc.getRootElement();
				message.setToUserName(rootE.element("ToUserName").getText());
				message.setFromUserName(rootE.element("FromUserName").getTextTrim());
				message.setCreateTime(DateUtils.formateDateByString(rootE.element("CreateTime").getTextTrim()));
				String type=rootE.element("MsgType").getTextTrim();
				if(Message_EVENT.equals(type)){
					message.setMsgId(rootE.element("MsgID").getTextTrim());
					message.setEvent(rootE.element("Event").getTextTrim());
					message.setStatus(rootE.element("Status").getTextTrim());
				}else{
					message.setMsgId(rootE.element("MsgId").getTextTrim());
					if(Message_TEXT.equals(type)){
						message.setContent(rootE.element("Content").getTextTrim());
					}else if(Message_IMAGE.equals(type)){
						message.setMediaId(rootE.element("MediaId").getTextTrim());
						message.setPicUri(rootE.element("PicUrl").getTextTrim());
					}else if(Message_VOICE.equals(type)){
						message.setMediaId(rootE.element("MediaId").getTextTrim());
						message.setFormat(rootE.element("Format").getTextTrim());
						if(rootE.element("Recognition")!=null){
							message.setRecognition(rootE.element("Recognition").getTextTrim());
						}
					}else if(Message_VIDEO.equals(type)){
						message.setMediaId(rootE.element("MediaId").getTextTrim());
						message.setThumbMediaId(rootE.element("ThumbMediaId").getTextTrim());
					}else if(Message_SHORTVIDEO.equals(type)){
						message.setMediaId(rootE.element("MediaId").getTextTrim());
						message.setThumbMediaId(rootE.element("ThumbMediaId").getTextTrim());
					}else if(Message_LOCATION.equals(type)){
						message.setMediaId(rootE.element("MediaId").getTextTrim());
						message.setLocation_x(rootE.element("Location_X").getTextTrim());
						message.setLocation_y(rootE.element("Location_Y").getTextTrim());
						message.setScale(rootE.element("Scale").getTextTrim());
						message.setLabel(rootE.element("Label").getTextTrim());
					}else if(Message_LINK.equals(type)){
						message.setTitle(rootE.element("Title").getTextTrim());
						message.setUrl(rootE.element("Url").getTextTrim());
						message.setDescription(rootE.element("Description").getTextTrim());
					}
				}
				
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			return message;
		}
		return null;
	}
}
