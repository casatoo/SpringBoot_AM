package com.KMS.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.KMS.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public void Join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	public Member getMember(int id);

	public int getLastInsertId();
	
	public int matchLoginId(String loginId);
	
	public String getLoginPw(String loginId);
	
	public Member getMemberByLoginId(String loginId);

}