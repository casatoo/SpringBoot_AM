package com.KMS.exam.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.vo.Member;

public class Ut {
	
	@Autowired
	public MemberService memberService;
	
	public static boolean empty(Object obj) {
		if(obj == null) {
			return true;
		}
		if(obj instanceof String == false) {
			return true;
		}
		String str = (String) obj;
		
		
		return str.trim().length() == 0;
	}
	
    public static String f(String format, Object... args){
        return String.format(format,args);
    }
    
    public static int getLoginedId(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("loginedId")==null) {
    		return 0;
    	}
    	int loginedId = (int) session.getAttribute("loginedId");
    	return loginedId;
    }
}
