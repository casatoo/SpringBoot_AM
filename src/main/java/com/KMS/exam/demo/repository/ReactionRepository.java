package com.KMS.exam.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface ReactionRepository {

	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relTypeCode = 'article',
			relId = #{relId},
			`point` = #{point}
				""")
	public int reactionPoint(int relId, int memberId, int point);

	@Select("""
			SELECT `point` FROM
			reactionPoint WHERE
			relId = #{relId}
			AND memberId = #{memberId};

							""")
	public Integer getReactionResult(int relId, int memberId);

	@Delete("""
			DELETE FROM
			reactionPoint
			WHERE relId=#{relId}
			AND memberId = #{memberId};
							""")
	public void cancelReaction(int relId, int memberId);
}
