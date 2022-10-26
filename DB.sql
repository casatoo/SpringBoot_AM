DROP DATABASE IF EXISTS SB_AM;
CREATE DATABASE SB_AM;
USE SB_AM;

DROP TABLE IF EXISTS article;
CREATE TABLE article(
id INT(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
memberId INT(10) NOT NULL,
boardId INT(10) UNSIGNED NOT NULL,
title VARCHAR(200) NOT NULL,
`body` TEXT NOT NULL
);

INSERT INTO article(regDate,updateDate,memberId,boardId,title,`body`)VALUES
(NOW(),NOW(),1,2,'제목1','내용1'),
(NOW(),NOW(),2,2,'제목2','내용2'),
(NOW(),NOW(),3,2,'제목3','내용3'),
(NOW(),NOW(),1,2,'제목4','내용4');

SELECT * FROM article WHERE boardId = ;

SELECT article.id, article.regDate, article.updateDate, article.title, article.`body`, article.loginedId, `member`.name FROM article INNER JOIN `member` ON article.loginedId = `member`.id ORDER BY id DESC;

DELETE FROM article WHERE id = 5;

		SELECT *
		FROM article
		WHERE id
		= 2;

UPDATE article SET 
updateDate = NOW(),
title = '제목6',
`body` = '내용6'
WHERE id = 1;

SELECT LAST_INSERT_ID();

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`(
id INT(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
loginId VARCHAR(20) NOT NULL,
loginPw VARCHAR(20) NOT NULL,
`authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반,7=관리자)',
`name` VARCHAR(20) NOT NULL,
nickname VARCHAR(20) NOT NULL,
cellphoneNum VARCHAR(20) NOT NULL,
email VARCHAR(50) NOT NULL,
delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부 (0=탈퇴 전, 1= 탈퇴 후)',
delDate DATETIME COMMENT '탈퇴날짜'
);

INSERT INTO `member`(regDate,updateDate,loginId,loginPw,`authLevel`,`name`,nickname,cellphoneNum,email)VALUES
(NOW(),NOW(),'admin','admin',7,'관리자','관리자','01012341234','casato6666@gmail.com'),
(NOW(),NOW(),'id1','pw1',3,'사용자1','사용자1','01012345678','asdasfasfg@gmail.com'),
(NOW(),NOW(),'id2','pw2',3,'사용자2','사용자2','01056789012','a1223fg@gmail.com');

DROP TABLE IF EXISTS board;
CREATE TABLE board(
id INT(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
`code` VARCHAR(50) NOT NULL UNIQUE COMMENT 'notice(공지사항), free1(자유게시판1), free2(자유게시판2), ...',
`name` VARCHAR(50) NOT NULL UNIQUE COMMENT '게시판이름',
delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부 (0=삭제 전, 1= 삭제 후)',
delDate DATETIME COMMENT '삭제날짜'
);

INSERT INTO board(regDate,updateDate,`code`,`name`)VALUES
(NOW(),NOW(),'notice','공지사항'),
(NOW(),NOW(),'free','자유');

DROP TABLE IF EXISTS reactionPoint;
CREATE TABLE reactionPoint (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(50) NOT NULL COMMENT '관련 데이터 타입 코드',
	relId INT(10) NOT NULL COMMENT '관련 데이터  번호',
    `point` SMALLINT(2) NOT NULL,
    FOREIGN KEY (relId) REFERENCES article(id) ON DELETE CASCADE 
);

SELECT `point` FROM reactionPoint WHERE relId = 1 AND memberId = 2;

		DELETE FROM reactionPoint
		WHERE relId = 1
		AND memberId = 2;
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 1,
`point` = -1;

/*테스트데이터*/
INSERT INTO reactionPoint (regDate,updateDate,memberId,relTypeCode,relId,`point`)VALUES
(NOW(),NOW(),1,'article',1,1),
(NOW(),NOW(),2,'article',1,1),
(NOW(),NOW(),3,'article',1,-1);

SELECT * FROM reactionPoint;

SELECT A.*,
IFNULL(SUM(RP.point),0) AS extra__sumReactionPoint,
IFNULL(SUM(IF(RP.point &gt; 0, RP.point, 0)),0) AS extra__goodReactionPoint,
IFNULL(SUM(IF(RP.point &lt; 0, RP.point, 0)),0) AS extra__badReactionPoint
FROM (
SELECT A.*, M.nickname 
AS extra__writerName 
FROM article AS A
INNER JOIN `member` AS M
ON A.memberId = M.id
AND A.boardId = 2
AND ( A.title LIKE '%%' OR A.body LIKE '%%')
ORDER BY A.id DESC
LIMIT 0 , 10) AS A
LEFT JOIN reactionPoint AS RP
ON RP.relTypeCode = 'article'
AND A.id = RP.relId
GROUP BY A.id;

/*
리스트에 표현되어야 하는것.
글 번호
작성일시
제목
작성자
조회수
추천
추천수 테이블을 만든다면
id , 게시글번호, 맴버번호, 
*/
SELECT A.*,
			IFNULL(SUM(RP.point),0) AS extra__sumReactionPoint,
			IFNULL(SUM(IF(RP.point &gt; 0, RP.point, 0)),0) AS extra__goodReactionPoint,
			IFNULL(SUM(IF(RP.point &lt; 0, RP.point, 0)),0) AS extra__badReactionPoint
			FROM (
				SELECT A.*, M.nickname AS
				extra__writerName
				FROM article AS A
				LEFT JOIN `member` AS M
				ON A.memberId = M.id WHERE 1
				<IF test="boardId != 0">
					AND A.boardId = #{boardId}
				</IF>
				<IF test="searchKeyword != ''">
					<choose>
						<WHEN test="searchKeywordTypeCode == 'title'">
							AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
						</WHEN>
						<WHEN test="searchKeywordTypeCode == 'body'">
							AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
						</WHEN>
						<otherwise>
							AND (
							A.title LIKE CONCAT('%', #{searchKeyword}, '%')
							OR A.body LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</IF>
				ORDER BY A.id DESC
				<IF test="limitTake != -1">
					LIMIT #{limitStart}, #{limitTake}
				</IF>
						) AS A
			LEFT JOIN reactionPoint AS RP
			ON RP.relTypeCode = 'article'
			AND A.id = RP.relId
			GROUP BY A.id

