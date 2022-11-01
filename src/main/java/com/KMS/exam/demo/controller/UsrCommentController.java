package com.KMS.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.CommentService;
import com.KMS.exam.demo.util.Ut;
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
	public String doAdd(int id, String comment, String relTypeCode) {
		
		if(rq.getLoginedMemberId()==0) {
			return Ut.jsHistoryBack(Ut.f("로그인 후 이용 가능합니다."));
		}
		
		if(Ut.empty(comment)) {
			return Ut.jsHistoryBack(Ut.f("댓글을 입력해주세요"));
		}
		String setComment = comment.replaceAll("\r\n", "<br>");
		
		ResultData commentRd = commentService.doWrite(id,rq.getLoginedMemberId(),setComment, relTypeCode);
		
		return Ut.jsReplace(Ut.f(""),  Ut.f("../article/detail?id=%d", id));
	}
	
	@RequestMapping("/usr/comment/doDelete")
	@ResponseBody
	public String doDelete(int id, int relId) {
		
		if(rq.getLoginedMemberId()==0) {
			return Ut.jsHistoryBack(Ut.f("로그인 후 이용 가능합니다."));
		}
		ResultData commentRd = commentService.doDelete(id);
		ResultData<Integer> rd = ResultData.newData(commentRd, "Reaction", id);
		
		return Ut.jsReplace(Ut.f(""),  Ut.f("../article/detail?id=%d", relId));
	}
	
}