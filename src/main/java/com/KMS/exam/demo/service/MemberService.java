package com.KMS.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KMS.exam.demo.repository.MemberRepository;
import com.KMS.exam.demo.vo.Member;

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
	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		/**
		 * 맴버 생성
		 * 라스트생성 아이디 가져옴 반환함
		 */
		int matchLoginId = memberRepository.matchLoginId(loginId);
		
		if(matchLoginId == 1) {
			return -1;
		}
		int matchMember = memberRepository.matchMember(name, email);
		if(matchMember == 1) {
			return -2;
		}
		memberRepository.Join(loginId, loginPw, name, nickname, cellphoneNum, email);
		int id = memberRepository.getLastInsertId();
		return id;
	}
	
	public int doLogin(String loginId, String loginPw) {
		
		int matchLoginId = memberRepository.matchLoginId(loginId);
		if(matchLoginId == 0) {
			return -1;
		}
		String getLoginPw = memberRepository.getLoginPw(loginId);
		if(!getLoginPw.equals(loginPw)) {
			return -2;
		}
		return 1;
		
	}
	
	
	public Member getMember(int id) {
		return memberRepository.getMember(id);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
}
