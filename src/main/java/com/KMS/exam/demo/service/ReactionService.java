package com.KMS.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KMS.exam.demo.repository.ReactionRepository;
import com.KMS.exam.demo.vo.ResultData;

@Service
public class ReactionService {
	@Autowired
	private ReactionRepository reactionRepository;
	
	public ResultData<Integer> doReaction(int relId, int memberId, int point){
		int reactionPointRd = reactionRepository.doReaction(relId,memberId,point);
		if(point == -1) {
			return ResultData.from("S-1", "싫어요", "reactionPointRd", reactionPointRd);
		}
		return ResultData.from("S-2", "좋아요", "reactionPointRd", reactionPointRd);
	}
	
	public Integer getReactionResult(int relId, int memberId){
		return reactionRepository.getReactionResult(relId, memberId);
	}

	public void cancelReaction(int relId, int memberId, int point) {
		reactionRepository.cancelReaction(relId, memberId, point);
	}

	public void updateReaction(int relId) {
		reactionRepository.updateReaction(relId);
	}

}
