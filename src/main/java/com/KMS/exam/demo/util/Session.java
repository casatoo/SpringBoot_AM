package com.KMS.exam.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.KMS.exam.demo.vo.Member;
import com.KMS.exam.demo.vo.ResultData;

public class Session {
    public static Member loginMember;
    
    public static void doLogin(HttpSession httpSession,Member member) {
        loginMember = member;
        httpSession.setAttribute("loginedId",member.getId());
        httpSession.setAttribute("loginedLevel",member.getAuthLevel());
    }
    
    public static boolean isLogined(HttpSession httpSession) {
        if(httpSession.getAttribute("loginedId")==null) {
            return true;
        }
        return false;
    }
    
    public static boolean authorization(HttpSession httpSession, int id) {
        if(loginMember.getId() != id &&  loginMember.getAuthLevel() != 7) {
            return true;
        }
        return false;
    }
    
    public static int getLoginedId(HttpSession httpSession) {
        
        if(httpSession.getAttribute("loginedId")==null) {
            return 0;
        }
        int loginedId = (int) httpSession.getAttribute("loginedId");
        return loginedId;
    }
    
    public static int getLoginedLevel(HttpSession httpSession) {
        if(httpSession.getAttribute("loginedLevel")==null) {
            return 0;
        }
        int loginedLevel = (int) httpSession.getAttribute("loginedLevel");
        return loginedLevel;
    }
    
    public static void doLogout(HttpSession httpSession) {
        loginMember = null;
        httpSession.setAttribute("loginedId",null);
        httpSession.setAttribute("loginedLevel",null);
    }
    
    
}
