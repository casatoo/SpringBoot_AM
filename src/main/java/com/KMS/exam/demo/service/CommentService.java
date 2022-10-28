package com.KMS.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KMS.exam.demo.repository.CommentRepository;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Article;
import com.KMS.exam.demo.vo.Comment;
import com.KMS.exam.demo.vo.ResultData;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> getForPrintComments(int id) {
		List<Comment> comments = commentRepository.getComments(id);
		
		return comments;
	}

	public ResultData doWrite(int id, int memberId ,String comment) {
		
		commentRepository.doWrite(id,memberId,comment);
		
		return ResultData.from("S-1", Ut.f("댓글 작성 성공"));
	}
	
}
