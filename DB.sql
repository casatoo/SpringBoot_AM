DROP DATABASE IF EXISTS SB_AM;
CREATE DATABASE SB_AM;
USE SB_AM;

CREATE TABLE article(
id INT(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title VARCHAR(200) NOT NULL,
`body` TEXT NOT NULL);

SELECT * FROM article;

INSERT INTO article(regDate,updateDate,title,`body`)VALUES
(NOW(),NOW(),'제목1','내용1'),
(NOW(),NOW(),'제목2','내용2'),
(NOW(),NOW(),'제목3','내용3'),
(NOW(),NOW(),'제목4','내용4');

DELETE FROM article WHERE id = 5;

UPDATE article SET 
updateDate = NOW(),
title = '제목6',
`body` = '내용6'
WHERE id = 1;

SELECT LAST_INSERT_ID();