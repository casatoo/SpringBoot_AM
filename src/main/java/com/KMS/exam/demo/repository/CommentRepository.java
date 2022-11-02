package com.KMS.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KMS.exam.demo.vo.Comment;

@Mapper
public interface CommentRepository {

	@Select("""
			SELECT C.*, M.nickname AS
			extra__writerName
			FROM `comment` AS C
			INNER JOIN
			`member` AS M
			ON C.memberId
			= M.id
			WHERE relId = #{relId}
			ORDER BY C.id ASC;
						""")
	public List<Comment> getComments(int relId);

	@Insert("""
			INSERT INTO `comment`
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relId = #{relId},
			relTypeCode = #{relTypeCode},
			`comment` = #{comment}
				""")
	public int doWrite(int relId, int memberId, String comment, String relTypeCode);

	@Delete("""
			DELETE FROM `comment`
			WHERE id = #{id}
			""")
	public int doDelete(int id);


	public int doModify(int id, String comment);
}
