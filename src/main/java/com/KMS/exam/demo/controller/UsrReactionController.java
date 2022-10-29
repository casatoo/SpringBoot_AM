package com.KMS.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.service.ReactionService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Article;
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
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), relId);
		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다", relId));
		}
		
		Integer reactionRd  = reactionService.getReactionResult(relId,rq.getLoginedMemberId());
		
		if(reactionRd == null){
			reactionService.doReaction(relId,rq.getLoginedMemberId(),point);
			reactionService.updateReaction(relId);
			return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
		}
		if(reactionRd == 0) {
			reactionService.cancelReaction(relId, rq.getLoginedMemberId(),point);
			reactionService.updateReaction(relId);
			return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
		}
		if(reactionRd == 1) {
			if(point == 1) {
				reactionService.cancelReaction(relId, rq.getLoginedMemberId(),0);
				reactionService.updateReaction(relId);
			}
			if(point == -1) {
				reactionService.cancelReaction(relId, rq.getLoginedMemberId(),point);
				reactionService.updateReaction(relId);
			}
			return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
		}
		if(reactionRd == -1) {
			if(point == 1) {
				reactionService.cancelReaction(relId, rq.getLoginedMemberId(),point);
				reactionService.updateReaction(relId);
			}
			if(point == -1) {
				reactionService.cancelReaction(relId, rq.getLoginedMemberId(),0);
				reactionService.updateReaction(relId);
			}
			return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
		}
		
		return Ut.jsReplace(Ut.f(""), Ut.f("../article/detail?id=%d", relId));
	}
}
