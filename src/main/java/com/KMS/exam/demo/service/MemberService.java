package com.KMS.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KMS.exam.demo.repository.MemberRepository;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Member;
import com.KMS.exam.demo.vo.ResultData;

/**
 * 
 * @author Administrator
 * 서비스 어노테이션 추가
 */
@Service
public class MemberService {

/**
 * 리포지토리에 연결해야되니까 인스턴스 객체 만들기
 */
	@Autowired
	public MemberRepository memberRepository;
	
	/**
	 * dojoin 메서드 생성
	 * 리턴타입은 member 로 하고
	 * article과 같이 맴버생성하고 생성한 맴버 조회해서 값을 넘기는걸로 한다.
	 * 
	 */
	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		/**
		 * 맴버 생성
		 * 라스트생성 아이디 가져옴 반환함
		 */
		int matchLoginId = memberRepository.matchLoginId(loginId);
		
		if(matchLoginId == 1) {
			return ResultData.from("F-1",Ut.f("이미 사용중인 아이디 %s 입니다.",loginId));
		}
		int matchMember = memberRepository.matchMember(name, email);
		if(matchMember == 1) {
			return ResultData.from("F-2",Ut.f("이미 가입된 회원입니다. %s, %s",name, email));
		}
		memberRepository.Join(loginId, loginPw, name, nickname, cellphoneNum, email);
		int id = memberRepository.getLastInsertId();
		return ResultData.from("S-1","회원가입 성공","id",id);
	}
	
	public ResultData<Integer> doLogin(String loginId, String loginPw) {
		
		int matchLoginId = memberRepository.matchLoginId(loginId);
		if(matchLoginId == 0) {
			return ResultData.from("F-4",Ut.f("%s 는 존재하지 않는 아이디 입니다.",loginId));
		}
		String getLoginPw = memberRepository.getLoginPw(loginId);
		if(!getLoginPw.equals(loginPw)) {
			return ResultData.from("F-5",Ut.f("비밀번호가 틀렸습니다."));
		}
		return ResultData.from("S-1",Ut.f("로그인 성공"));
		
	}
	
	
	public Member getMember(int id) {
		return memberRepository.getMember(id);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public ResultData doModify(String name, String nickname, String cellphoneNum, String email, int memberId) {

		int matchMember = memberRepository.matchMember(name, email);
		if(matchMember == 1) {
			return ResultData.from("F-1",Ut.f("중복된 회원 정보입니다. %s, %s",name, email));
		}
		memberRepository.doModify(nickname, cellphoneNum, email, memberId);
		return ResultData.from("S-1","회원정보 수정 성공");
	}

	public ResultData doChangePassword(int memberId ,String loginPw) {
		
		memberRepository.doChangePassword(memberId,loginPw);
		
		return ResultData.from("S-1","비밀번호 수정 성공");
	}
	
}
