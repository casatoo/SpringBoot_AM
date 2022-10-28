package com.KMS.exam.demo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String comment;
	private int relId;
	private int goodReactionPoint;
	private int badReactionPoint;
	
	private String extra__writerName;
}
