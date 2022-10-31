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
	public ResultData<Integer> reactionPoint(int relId, int point) {
		
		ResultData<Integer> getReactionResult  = reactionService.getReactionResult(relId,rq.getLoginedMemberId());
		
		if(getReactionResult.getData1() == null){
			ResultData<Integer> doReactionRd = reactionService.doReaction(relId,rq.getLoginedMemberId(),point);
			reactionService.updateReaction(relId);
			ResultData<Integer> rd = ResultData.newData(doReactionRd, "Reaction", point);
			Article article = reactionService.getReactionPoint(relId);
			rd.setData2("goodReaction", article.getGoodReactionPoint());
			rd.setData3("badReaction", article.getBadReactionPoint());
			return rd;
		}
		if(getReactionResult.getData1() == 0) {
			ResultData<Integer> doReactionRd = reactionService.changeReaction(relId, rq.getLoginedMemberId(),point);
			reactionService.updateReaction(relId);
			ResultData<Integer> rd = ResultData.newData(doReactionRd, "Reaction", point);
			Article article = reactionService.getReactionPoint(relId);
			rd.setData2("goodReaction", article.getGoodReactionPoint());
			rd.setData3("badReaction", article.getBadReactionPoint());
			return rd;
		}
		
		if(getReactionResult.getData1() == 1) {
			ResultData<Integer> doReactionRd = new ResultData<Integer>();
			
			if(point == 1) {
				doReactionRd = reactionService.changeReaction(relId, rq.getLoginedMemberId(),0);
				reactionService.updateReaction(relId);
			}
			if(point == -1) {
				doReactionRd = reactionService.changeReaction(relId, rq.getLoginedMemberId(),point);
				reactionService.updateReaction(relId);
			}
			ResultData<Integer> rd = ResultData.newData(doReactionRd, "Reaction", point);
			Article article = reactionService.getReactionPoint(relId);
			rd.setData2("goodReaction", article.getGoodReactionPoint());
			rd.setData3("badReaction", article.getBadReactionPoint());
			return rd;
		}
		if(getReactionResult.getData1() == -1) {
			ResultData<Integer> doReactionRd = new ResultData<Integer>();
			if(point == 1) {
				doReactionRd = reactionService.changeReaction(relId, rq.getLoginedMemberId(),point);
				reactionService.updateReaction(relId);
			}
			if(point == -1) {
				doReactionRd = reactionService.changeReaction(relId, rq.getLoginedMemberId(),0);
				reactionService.updateReaction(relId);
			}
			ResultData<Integer> rd = ResultData.newData(doReactionRd, "Reaction", point);
			Article article = reactionService.getReactionPoint(relId);
			rd.setData2("goodReaction", article.getGoodReactionPoint());
			rd.setData3("badReaction", article.getBadReactionPoint());
			return rd;
			
		}
		
		return getReactionResult;
	}
}
