package com.cn.api.user;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ParseXML {

	/**
	 * 解析用户的地理位置
	 * @param xml
	 * @return
	 */
	public UserLocationPo parseUserLocation(String xml){
		Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element root=doc.getRootElement();
			UserLocationPo ul=new UserLocationPo();
			ul.setFromUserName(root.element("FromUserName").getText());
			ul.setToUserName(root.element("toUserName").getText());
			ul.setCreateTime(root.element("CreateTime").getText());
			ul.setEvent(root.element("Event").getText());
			ul.setLatitude(root.element("Latitude").getText());
			ul.setLongitude(root.element("Longitude").getText());
			ul.setMsgType(root.element("MsgType").getText());
			ul.setPrecision(root.element("Precision").getText());
			return ul;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	    return null;
	}
}
