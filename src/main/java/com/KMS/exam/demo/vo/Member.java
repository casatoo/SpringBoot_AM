package com.KMS.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Administrator
 * 데이타 어노테이션 생성
 * 생성자 No, All 붙이기
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	int id;
	String loginId;
	String loginPw;
	int authLevel;
	String name;
	String nickname;
	String cellphoneNum;
	String email;
	int delStatus;
	String delDate;
}
