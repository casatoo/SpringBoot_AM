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
	public String reactionPoint(int relId, int point) {
		
		
		Integer reactionRd  = reactionService.getReactionResult(relId,rq.getLoginedMemberId());
		
		if(reactionRd != null && reactionRd == point) {
			reactionService.cancelReaction(relId, rq.getLoginedMemberId());
			reactionService.updateReaction();
			return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
		}
		
		if(reactionRd!=null) {
			reactionService.cancelReaction(relId, rq.getLoginedMemberId());
		}
		ResultData<Integer> reactionPointRd = reactionService.doReaction(relId,rq.getLoginedMemberId(),point);
		reactionService.updateReaction();
		
		return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
	}
}
