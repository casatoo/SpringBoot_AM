package com.KMS.exam.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.vo.Member;

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
	 * return 값은 회원가입 메시지를 주기 위해 가입시키고 member 값을 받아온다.
	 * 인자값은 id,pw,이름,닉네임,전화번호,이메일
	 * vo에 member 만들어야 함
	 * Service에 doJoin 메서드 생성
	 */
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		Member member = memberService.getMember(id);
		
		return member.getName()+"님 가입을 축하합니다.";
	}
}
