package com.cn.api.message;
/**
 * 消息的类型
 * @author Administrator
 *
 */
public interface MessageType {
	public static final String Message_TEXT="text";
	public static final String Message_IMAGE="image";
	public static final String Message_VIDEO="video";
	public static final String Message_LINK="link";
	public static final String Message_VOICE="voice";
	public static final String Message_SHORTVIDEO="shortvideo";
	public static final String Message_LOCATION="location";
	public static final String Message_EVENT="event";
}