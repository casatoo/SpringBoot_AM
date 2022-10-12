package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Member;
import com.KMS.exam.demo.vo.ResultData;
import com.KMS.exam.demo.vo.Rq;

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
	private MemberService memberService;

	/**
	 * dojoin 맵핑하기 return 값은 회원가입 메시지를 주기 위해 가입시키고 member 값을 받아온다. 인자값은
	 * id,pw,이름,닉네임,전화번호,이메일 vo에 member 만들어야 함 Service에 doJoin 메서드 생성 가입절차 시작 전에 아이디
	 * 중복 여부 확인 -1 이면 아이디 중복 -2 이면 이름+이메일 중복
	 */
	@RequestMapping("/usr/member/dojoin")
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email, Model model) {
		
		ResultData resultRd;
		
		if(Ut.empty(loginId)) {
			resultRd = ResultData.from("F-1","아이디를 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		if(Ut.empty(loginPw)) {
			resultRd = ResultData.from("F-2","비밀번호를 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		if(Ut.empty(name)) {
			resultRd = ResultData.from("F-3","이름을 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		if(Ut.empty(nickname)) {
			resultRd = ResultData.from("F-4","닉네임을 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		if(Ut.empty(cellphoneNum)) {
			resultRd = ResultData.from("F-5","전화번호를 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		if(Ut.empty(email)) {
			resultRd = ResultData.from("F-6","이메일을 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		
		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if(doJoinRd.isFail()) {
			resultRd = doJoinRd;
			model.addAttribute("resultRd",resultRd);
			return "usr/member/join";
		}
		Member member = memberService.getMember((int) doJoinRd.getData1());
		resultRd = ResultData.newData(doJoinRd,"member",member);
		return "usr/home/main";
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
	 * 세션에 정보를 저장 리퀘스트를 받아서 세션을 생성
	 * loginedId 저장
	 */
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw,HttpServletRequest req, HttpSession httpSession, Model model) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(rq.isLogined() == true){
			return Ut.jsReplace(Ut.f("이미 로그인중입니다"),"../home/main");
		}
		if(Ut.empty(loginId)) {
			return Ut.jsHistoryBack(Ut.f("아이디를 입력해주세요"));
		}
		if(Ut.empty(loginPw)) {
			return Ut.jsHistoryBack(Ut.f("비밀번호를 입력해주세요"));
		}
		ResultData doLoginRd= memberService.doLogin(loginId,loginPw);
		if(doLoginRd.isFail()) {
			ResultData resultRd = ResultData.from(doLoginRd.getResultCode(),doLoginRd.getMsg());
			return Ut.jsReplace(resultRd.getMsg(), "../member/login");
		}
		Member member = memberService.getMemberByLoginId(loginId);
		httpSession.setAttribute("loginedMemberId", member.getId());
		return Ut.jsReplace(Ut.f("환영합니다."),"../home/main");
	}
	/**
	 * 로그아웃 기능
	 * 팹핑
	 * 로그인 하지 않으면 세션자체가 null 상태이기 때문에 오류가 발생한다.
	 * 조건식으로 세션이 null 이거나 loginedId 가 0 일경우 로그인 하지 않은걸로 한다.
	 * 
	 */
	@RequestMapping("usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req, Model model, HttpSession httpSession) {
		httpSession.setAttribute("loginedMemberId", null);
		return Ut.jsReplace(Ut.f("로그아웃 되었습니다."),"../home/main");
	}
	@RequestMapping("usr/member/login")
	public String loginForm(HttpServletRequest req, Model model) {
		Rq rq = (Rq) req.getAttribute("rq");
		model.addAttribute("rq",rq);
		return "usr/member/login";
	}
	@RequestMapping("usr/member/join")
	public String joinForm(HttpServletRequest req, Model model) {
		Rq rq = (Rq) req.getAttribute("rq");
		model.addAttribute("rq",rq);
		return "usr/member/join";
	}
}
