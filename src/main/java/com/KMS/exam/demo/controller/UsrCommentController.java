package com.KMS.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.CommentService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Comment;
import com.KMS.exam.demo.vo.ResultData;
import com.KMS.exam.demo.vo.Rq;


@Controller
public class UsrCommentController {

	// 인스턴스 변수
	@Autowired
	private Rq rq;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/usr/comment/doAdd")
	@ResponseBody
	public String doAdd(int id, String comment, String relTypeCode, String listUri) {
		
		if(Ut.empty(comment)) {
			return Ut.jsHistoryBack(Ut.f("댓글을 입력해주세요"));
		}
		String setComment = comment.replaceAll("\r\n", "<br>");
		
		ResultData commentRd = commentService.doWrite(id,rq.getLoginedMemberId(),setComment, relTypeCode);
		
		return Ut.jsReplace(Ut.f(""),  Ut.f("../article/detail?id=%d&listUri=%s", id,listUri));
	}
	
	@RequestMapping("/usr/comment/doDelete")
	@ResponseBody
	public String doDelete(int id, int relId , String listUri ) {
		
		ResultData commentRd = commentService.doDelete(id);
		ResultData<Integer> rd = ResultData.newData(commentRd, "Reaction", id);
		
		return Ut.jsReplace(Ut.f(""),  Ut.f("../article/detail?id=%d&listUri=%s", relId,Ut.getUriEncoded(listUri)));
	}
	
	@RequestMapping("/usr/comment/doModify")
	@ResponseBody
	public String doModify(int id, String comment, int relId , String listUri) {
		
		ResultData commentRd = commentService.doModify(id, comment);
		ResultData<Integer> rd = ResultData.newData(commentRd, "Reaction", id);
		
		return Ut.jsReplace(Ut.f(""),  Ut.f("../article/detail?id=%d&listUri=%s", relId,listUri));
	}
	
	
	@RequestMapping("/usr/comment/getComments")
	@ResponseBody
	public List doModify(int relId) {
		
		List<Comment> comments = commentService.getForPrintComments(relId);
		
		return comments;
	}
}