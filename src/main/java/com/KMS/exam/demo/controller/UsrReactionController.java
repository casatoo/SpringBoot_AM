package com.KMS.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.service.ReactionService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.ResultData;
import com.KMS.exam.demo.vo.Rq;

@Controller
public class UsrReactionController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private Rq rq;
	@Autowired
	private ReactionService reactionService;
	
	
	@RequestMapping("/usr/reaction/doReaction")
	@ResponseBody
	public String reactionPoint(int relId, int memberId, int point) {
		ResultData<Integer> reactionPointRd = reactionService.doReaction(relId,memberId,point);
		
		reactionService.updateReaction();
		
		return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
	}
}
