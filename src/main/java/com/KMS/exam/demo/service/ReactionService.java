package com.KMS.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KMS.exam.demo.repository.ReactionRepository;
import com.KMS.exam.demo.vo.ResultData;

@Service
public class ReactionService {
	@Autowired
	private ReactionRepository reactionRepository;
	
	public ResultData<Integer> reactionPoint(int relId, int memberId, int point){
		int reactionPointRd = reactionRepository.reactionPoint(relId,memberId,point);
		if (reactionPointRd == 0) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다", "reactionPointRd", reactionPointRd);
		}
		if(point == -1) {
			return ResultData.from("S-1", "싫어요", "reactionPointRd", reactionPointRd);
		}
		return ResultData.from("S-2", "좋아요", "reactionPointRd", reactionPointRd);
	}
	
	public Integer getReactionResult(int relId, int memberId){
		return reactionRepository.getReactionResult(relId, memberId);
	}

	public void cancelReaction(int relId, int memberId) {
		reactionRepository.cancelReaction(relId, memberId);
	}

}
