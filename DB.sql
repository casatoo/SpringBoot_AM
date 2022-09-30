DROP DATABASE IF EXISTS SB_AM;
CREATE DATABASE SB_AM;
USE SB_AM;

DROP TABLE IF EXISTS article;

CREATE TABLE article(
id INT(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title VARCHAR(200) NOT NULL,
`body` TEXT NOT NULL,
loginedId INT(10) NOT NULL
);

SELECT * FROM article;

INSERT INTO article(regDate,updateDate,title,`body`,loginedId)VALUES
(NOW(),NOW(),'제목1','내용1',2),
(NOW(),NOW(),'제목2','내용2',3),
(NOW(),NOW(),'제목3','내용3',1),
(NOW(),NOW(),'제목4','내용4',1);

DELETE FROM article WHERE id = 5;

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

SELECT * FROM `member`;

		SELECT COUNT(*)
		FROM `member`
		WHERE loginId
		= 'id4';
		
		SELECT loginPw
		FROM `member`
		WHERE loginId
		= 'id4';
		
		SELECT COUNT(*)
		FROM `member`
		WHERE `name`
		= '사용자1' 
		AND email
		= 'asdasfasfg@gmail.com';
		 
