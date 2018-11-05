package com.cn.api.mian;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.api.message.MateriaUtils;
import com.cn.api.message.MessagePo;
import com.cn.api.message.ParseXMl;
import com.cn.api.utils.FastJsonUtils;
import com.cn.api.utils.HttpUitls;
import com.cn.api.weixin.AesException;
import com.cn.api.weixin.SHA1;

@RestController
public class Main {
	private String token="a839606145";
	@RequestMapping("/verify_wx_token")
	public void getMessage(HttpServletRequest request,HttpServletResponse response){
		
		if(verifyWeixin(request)){//微信发过来的消息
        	//return request.getParameter("echostr");验证token
			responseImage(request,response);
			//HttpUitls.createResonse(response,"","");
        }
		
	}

	private void responseText(HttpServletRequest request, HttpServletResponse response) {
		MessagePo po=ParseXMl.getMessageByXML(HttpUitls.getString(request));
		String formUser=po.getFromUserName();
		String toUser=po.getToUserName();
		HttpUitls.createResonseText(response,formUser,toUser,"你好");
	}
	private void responseImage(HttpServletRequest request, HttpServletResponse response) {
		MessagePo po=ParseXMl.getMessageByXML(HttpUitls.getString(request));
		String formUser=po.getFromUserName();
		String toUser=po.getToUserName();
		String result=MateriaUtils.addTempMateria("image","E:\\workspace\\readApp\\src\\main\\resources\\1.jpg");
		String mediaId=FastJsonUtils.getMediaId(result);
		HttpUitls.createResonseImage(response,formUser,toUser,mediaId);
	}
	
	@RequestMapping("/postMessage")
	public String postMessage(HttpServletRequest request,HttpServletResponse response){
		return null;
	}
	/**
	 * 验证信息是微信发过来的
	 * @param request
	 * @return
	 */
	private boolean verifyWeixin(HttpServletRequest request){
		String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        return verifyToken(msgSignature,msgTimestamp,msgNonce);
       
	}
	/**
	 * SHA1加密验证
	 * @param msgSignature
	 * @param msgTimestamp
	 * @param msgNonce
	 * @return
	 */
	private boolean verifyToken(String msgSignature,String msgTimestamp,String msgNonce){
		String ver;
		try {
			ver = SHA1.getSHA1(token,msgTimestamp,msgNonce);
			if(ver.equals(msgSignature)){
				return true;
			}
		} catch (AesException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
}
