package com.KMS.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Administrator
 * 컨트롤러 어노테이션 써주기
 */
@Controller
public class UsrMemberController {
	/**
	 * memberService 와 연결
	 */
	@Autowired
	public MemberService memberService;
	
	/**
	 * dojoin 맵핑하기
	 * return 값은 회원가입 메시지를 주기 위해 성공 또는 실패로 String
	 * 인자값은 id,pw,이름,닉네임,전화번호,이메일
	 * vo에 member 만들어야 함
	 */
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNum, String email) {
		
		String msg = "";
		
		memberService.doJoin(loginId, loginPw, name, nickName, cellphoneNum, email);
		
		
		
		if name
		
		return msg;
	}
}
