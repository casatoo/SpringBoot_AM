package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Member;
import com.KMS.exam.demo.vo.ResultData;

/**
 * 
 * @author Administrator 컨트롤러 어노테이션 써주기
 */
@Controller
public class UsrMemberController {
	
	/**
	 * memberService 와 연결
	 */
	@Autowired
	public MemberService memberService;

	/**
	 * dojoin 맵핑하기 return 값은 회원가입 메시지를 주기 위해 가입시키고 member 값을 받아온다. 인자값은
	 * id,pw,이름,닉네임,전화번호,이메일 vo에 member 만들어야 함 Service에 doJoin 메서드 생성 가입절차 시작 전에 아이디
	 * 중복 여부 확인 -1 이면 아이디 중복 -2 이면 이름+이메일 중복
	 */
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1","아이디를 입력해주세요");
		}
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-2","비밀번호를 입력해주세요");
		}
		if(Ut.empty(name)) {
			return ResultData.from("F-3","이름을 입력해주세요");
		}
		if(Ut.empty(nickname)) {
			return ResultData.from("F-4","닉네임을 입력해주세요");
		}
		if(Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5","전화번호를 입력해주세요");
		}
		if(Ut.empty(email)) {
			return ResultData.from("F-6","이메일을 입력해주세요");
		}
		
		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if(doJoinRd.isFail()) {
			return doJoinRd;
		}
		Member member = memberService.getMember((int) doJoinRd.getData1());
		return ResultData.newData(doJoinRd,member);
	}
	/**
	 * 로그인 기능 구현
	 * 맵핑
	 * 받아야되는 파라미터 아이디 비밀번호
	 * 아이디 유효 여부 판단. 잘못된 아이디일 경우 메세지
	 * 비밀번호 유효 여부 판단. 잘못된 비밀번호일 경우 메세지
	 * 모두 일치 확인하면 로그인 성공 메세지
	 * 이것도 서비스에서 int 로 받아오자.
	 * -1 이면 아이디 잘못 -2 이면 비밀번호 잘못 1 이상은 참이니까
	 * 세션에 정보를 저장
	 * 
	 */
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, HttpServletRequest request) {
		if(Ut.empty(loginId)) {
			return "아이디를 입력해주세요";
		}
		if(Ut.empty(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		int doLogin= memberService.doLogin(loginId,loginPw);
		if(doLogin == -1) {
			return "존재하지 않는 아이디 입니다.";
		}
		if(doLogin == -2) {
			return "잘못된 비밀번호 입니다.";
		}
		Member member = memberService.getMemberByLoginId(loginId);
		HttpSession session = request.getSession();
		session.setAttribute("loginedId",member.getId());
		
		
		return member.getName()+"님 환영합니다.";
	}
}
