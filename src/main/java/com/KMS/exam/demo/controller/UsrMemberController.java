package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.AttrService;
import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Attr;
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
	@Autowired
	private Rq rq;
	@Autowired
	private AttrService attrService;
	/**
	 * dojoin 맵핑하기 return 값은 회원가입 메시지를 주기 위해 가입시키고 member 값을 받아온다. 인자값은
	 * id,pw,이름,닉네임,전화번호,이메일 vo에 member 만들어야 함 Service에 doJoin 메서드 생성 가입절차 시작 전에 아이디
	 * 중복 여부 확인 -1 이면 아이디 중복 -2 이면 이름+이메일 중복
	 */
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email, String afterLoginUri ,Model model) {
		
		ResultData resultRd;
		
		if(Ut.empty(loginId)) {
			return Ut.jsHistoryBack(Ut.f("아이디를 입력해주세요"));
		}
		if(Ut.empty(loginPw)) {
			return Ut.jsHistoryBack(Ut.f("비밀번호를 입력해주세요"));
		}
		if(Ut.empty(name)) {
			return Ut.jsHistoryBack(Ut.f("이름을 입력해주세요"));
		}
		if(Ut.empty(nickname)) {
			return Ut.jsHistoryBack(Ut.f("닉네임을 입력해주세요"));
		}
		if(Ut.empty(cellphoneNum)) {
			return Ut.jsHistoryBack(Ut.f("전화번호를 입력해주세요"));
		}
		if(Ut.empty(email)) {
			return Ut.jsHistoryBack(Ut.f("이메일을 입력해주세요"));
		}
		
		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if(doJoinRd.isFail()) {
			if(doJoinRd.getResultCode().equals("F-1")) {
				return Ut.jsHistoryBack(Ut.f("이미 사용중인 아이디 입니다."));
			}
			if(doJoinRd.getResultCode().equals("F-2")) {
				return Ut.jsHistoryBack(Ut.f("이미 가입된 회원입니다."));
			}
		}
		Member member = memberService.getMember((int) doJoinRd.getData1());
		resultRd = ResultData.newData(doJoinRd,"member",member);
		return Ut.jsReplace(Ut.f("회원가입 성공!"),Ut.f("../member/login?afterLoginUri=%s",Ut.getUriEncoded(afterLoginUri)));
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw,@RequestParam(defaultValue = "/")String afterLoginUri) {
		
		if(rq.isLogined()){
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
		rq.login(member);
		
		return Ut.jsReplace(Ut.f("%s 회원님 환영합니다.",member.getName()),afterLoginUri);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(@RequestParam(defaultValue = "/")String afterLogoutUri) {
		
		rq.logout();

		return Ut.jsReplace("로그아웃 되었습니다", afterLogoutUri);
	}
	@RequestMapping("/usr/member/login")
	public String loginForm(String afterLoginUri, HttpServletRequest req, Model model) {
		
		model.addAttribute("afterLoginUri",afterLoginUri);
		return "/usr/member/login";
	}
	@RequestMapping("/usr/member/join")
	public String joinForm(HttpServletRequest req, Model model, String afterLoginUri) {
		model.addAttribute("afterLoginUri",afterLoginUri);
		return "/usr/member/join";
	}
	
	@RequestMapping("usr/member/info")
	public String memberInfo(HttpServletRequest req, Model model) {
		Member member = memberService.getMember(rq.getLoginedMemberId());
		String level = "";
		if(member.getAuthLevel() == 3) {
			level = "일반";
		}else if(member.getAuthLevel() == 7) {
			level = "관리자";
		}
		
		model.addAttribute("member",member);
		model.addAttribute("level",level);
		return "/usr/member/info";
	}
	
	
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String name, String nickname, String cellphoneNum, String email,String memberModifyAuthKey, Model model) {
		
		if(memberModifyAuthKey == null) {
			return Ut.jsHistoryBack(Ut.f("인증코드가 생성되지 않았습니다."));
		}
		
		Attr getAttr = attrService.get("member", rq.getLoginedMemberId(), "extra", "memberModifyAuthKey");
		
		if(getAttr==null) {
			return Ut.jsHistoryBack(Ut.f("인증코드가 만료되었습니다. 다시 시도해주세요"));
		}
		if(!getAttr.getValue().equals(memberModifyAuthKey)) {
			return Ut.jsHistoryBack(Ut.f("인증코드가 일치하지 않습니다. 다시 시도해주세요"));
		}
		
		if(Ut.empty(nickname)) {
			return Ut.jsHistoryBack(Ut.f("닉네임을 입력해주세요"));
		}
		if(Ut.empty(cellphoneNum)) {
			return Ut.jsHistoryBack(Ut.f("전화번호를 입력해주세요"));
		}
		if(Ut.empty(email)) {
			return Ut.jsHistoryBack(Ut.f("이메일을 입력해주세요"));
		}
		
		ResultData doModifyRd = memberService.doModify(name, nickname, cellphoneNum, email, rq.getLoginedMemberId());

		if(doModifyRd.isFail()) {
			if(doModifyRd.getResultCode().equals("F-1")) {
				return Ut.jsHistoryBack(Ut.f("중복된 회원 정보입니다."));
			}
		}
		Member member = memberService.getMember((int) doModifyRd.getData1());
		ResultData resultRd = ResultData.newData(doModifyRd,"member",member);
		return Ut.jsReplace("개인정보 수정완료","../member/info");
	}
	@RequestMapping("/usr/member/doChangePassword")
	@ResponseBody
	public String doChangePassword(String loginPwCheck, String loginPw, String memberPasswordAuthKey) {
		
		if(memberPasswordAuthKey == null) {
			return Ut.jsHistoryBack(Ut.f("인증코드가 생성되지 않았습니다."));
		}
		
		Attr getAttr = attrService.get("member", rq.getLoginedMemberId(), "extra", "memberModifyAuthKey");
		
		if(getAttr==null) {
			return Ut.jsHistoryBack(Ut.f("인증코드가 만료되었습니다. 다시 시도해주세요"));
		}
		
		if(!getAttr.getValue().equals(memberPasswordAuthKey)) {
			return Ut.jsHistoryBack(Ut.f("인증코드가 일치하지 않습니다.다시 시도해주세요"));
		}
		
		Member member = memberService.getMember(rq.getLoginedMemberId());
		if(Ut.empty(loginPwCheck)) {
			return Ut.jsHistoryBack(Ut.f("기존비밀번호를 입력해주세요"));
		}
		if(Ut.empty(loginPw)) {
			return Ut.jsHistoryBack(Ut.f("새로운 비밀번호를 입력해주세요"));
		}
		if(!member.getLoginPw().equals(loginPwCheck)) {
			return Ut.jsHistoryBack(Ut.f("비밀번호가 틀렸습니다."));
		}
		if(loginPwCheck.equals(loginPw)) {
			return Ut.jsHistoryBack(Ut.f("기존비밀번호와 동일한 비밀번호 입니다."));
		}
		ResultData doChangePasswordRd = memberService.doChangePassword(member.getId() , loginPw);
		
		return Ut.jsReplace(Ut.f("비밀번호가 수정되었습니다!"), "../member/info");
	}
	@RequestMapping("/usr/member/createAuthKey")
	@ResponseBody
	public String createAuthKey() {
		
		String memberModifyAuthKey = memberService.genMemberModifyAuthKey(rq.getLoginedMemberId());
		
		return memberModifyAuthKey;
	}
	@RequestMapping("/usr/member/checkLoginId")
	@ResponseBody
	public ResultData checkLoginId(String loginId) {
		
		ResultData rd = memberService.doCheckLoginId(loginId);
		
		return rd;
	}
	
}
