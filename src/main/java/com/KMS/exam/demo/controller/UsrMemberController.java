package com.KMS.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.vo.Member;

/**
 * 
 * @author Administrator 컨트롤러 어노테이션 써주기
 */
@Controller
public class UsrMemberController {
	
	boolean checkStr(String str) {
		str.replaceAll(" ","");
		if(str == null || str.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * memberService 와 연결
	 */
	@Autowired
	public MemberService memberService;

	/**
	 * dojoin 맵핑하기 return 값은 회원가입 메시지를 주기 위해 가입시키고 member 값을 받아온다. 인자값은
	 * id,pw,이름,닉네임,전화번호,이메일 vo에 member 만들어야 함 Service에 doJoin 메서드 생성 가입절차 시작 전에 아이디
	 * 중복 여부 확인
	 */
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		if(checkStr(loginId)) {
			return "아이디를 입력해주세요";
		}
		if(checkStr(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		if(checkStr(name)) {
			return "이름을 입력해주세요";
		}
		if(checkStr(nickname)) {
			return "닉네임을 입력해주세요";
		}
		if(checkStr(cellphoneNum)) {
			return "전화번호를 입력해주세요";
		}
		if(checkStr(email)) {
			return "이메일을 입력해주세요";
		}
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if(id == -1) {
			return "이미 사용중인 ID 입니다.";
		}else {
			Member member = memberService.getMember(id);
			return member;
		}

	}
	/**
	 * 로그인 기능 구현
	 * 맵핑
	 * 받아야되는 파라미터 아이디 비밀번호
	 * 아이디 유효 여부 판단. 잘못된 아이디일 경우 메세지
	 * 비밀번호 유효 여부 판단. 잘못된 비밀번호일 경우 메세지
	 * 모두 일치 확인하면 로그인 성공 메세지
	 * 이것도 서비스에서 int 로 받아오자.
	 * -1 이면 아이디 잘못 0 이면 비밀번호 잘못 1 이상은 참이니까
	 * 세션에 정보를 저장
	 * 
	 */
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		if(checkStr(loginId)) {
			return "아이디를 입력해주세요";
		}
		if(checkStr(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		int doLogin= memberService.doLogin(loginId,loginPw);
		if(doLogin == -1) {
			return "잘못된 아이디 입니다.";
		}
		if(doLogin == 0) {
			return "잘못된 비밀번호 입니다.";
		}
		Member member = memberService.getMemberByLoginId(loginId);
		return member.getName()+"님 환영합니다.";
	}
}
