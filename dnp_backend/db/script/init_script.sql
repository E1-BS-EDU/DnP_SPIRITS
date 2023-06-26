-- 데이터베이스 생성
CREATE DATABASE `red_test`;

-- 사용자 DB 생성

CREATE TABLE tb_user (
  `user_no` int(11) NOT NULL COMMENT '사용자번호',
  `user_id` varchar(255) NOT NULL COMMENT 'E-mail',
  `user_pw` varchar(256) DEFAULT NULL COMMENT '비밀번호',
  `user_name` varchar(255) NOT NULL COMMENT '사용자명',
  `team_name` varchar(255) NOT NULL COMMENT '팀명',
  `user_auth` varchar(255) NOT NULL COMMENT '권한',
  `use_yn` char(1) DEFAULT 'Y' COMMENT '퇴사유무',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

-- 사용자 DB 생성

CREATE TABLE tb_user_detail (
  `user_no` int(11) NOT NULL COMMENT '사용자번호',
  `birth` varchar(8) NOT NULL COMMENT '생일',
  `phone` varchar(11) NULL COMMENT '핸드폰 번호',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

-- ROLE DB 생성

CREATE TABLE tb_role (
  `user_no` int(11) NOT NULL COMMENT '사용자번호',
  `role_id` varchar(255) NOT NULL COMMENT '권한 ID',
  `role_name` varchar(255) NOT NULL COMMENT '권한명',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  CONSTRAINT PRIMARY KEY (`user_no`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

-- 공지사항
CREATE TABLE tb_notice (
	`no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
	`title` VARCHAR(50) NOT NULL COMMENT '제목',
	`content` LONGTEXT NOT NULL COMMENT  '내용',
	`vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
	`vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
	`fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
	`fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
	`use_yn` VARCHAR(1) DEFAULT 'Y' COMMENT '사용유무',
	PRIMARY KEY (`no`)
); 

-- 주간업무
CREATE TABLE `tb_weekly_work` (
  `user_no` int(11) NOT NULL COMMENT '사용자번호',
  `week_no` varchar(256) NOT NULL COMMENT 'N주차',
  `site_no` int(11) DEFAULT NULL COMMENT  '프로젝트코드',
  `this_week_plan` TEXT DEFAULT NULL COMMENT '금주계획',
  `performance` TEXT DEFAULT NULL COMMENT '실적',
  `next_week_plan` TEXT DEFAULT NULL COMMENT '차주계획',
  `remarks` TEXT DEFAULT NULL COMMENT '비고',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  CONSTRAINT PRIMARY KEY (`user_no`,`week_no`)
);

-- working day

CREATE TABLE `tb_wkday` (
  `work_date` date NOT NULL COMMENT '근무일',
  `user_no` int(11) NOT NULL COMMENT '사용자번호',
  `id` int(11) NOT NULL COMMENT '달력id',
  `gubun_cd` int NOT NULL COMMENT '구분코드',
  `site_no` int(11) NOT NULL COMMENT '프로젝트코드',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  CONSTRAINT PRIMARY KEY (`work_date`,`user_no`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

------------------- 환경설정 -----------------

-- 공통코드 관리 마스터
CREATE TABLE tb_common_m (
  `com_cd` VARCHAR(30) NOT NULL COMMENT '코드',
  `com_nm` text(1000) NOT NULL COMMENT '코드명',
  `use_yn` VARCHAR(1) DEFAULT 'Y' COMMENT '사용유무',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  PRIMARY KEY (`com_cd`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 공통코드 관리 디테일
CREATE TABLE tb_common_d (
  `com_cd` VARCHAR(50) NOT NULL COMMENT '코드',
  `com_nm` text(1000) NOT NULL COMMENT '코드명',
  `etc` text(1000)  COMMENT '비고',
  `etc_desc` text(1000)  COMMENT '비고 설명',
  `parent_cd` VARCHAR(30)  COMMENT '부모 코드',
  `use_yn` VARCHAR(1) DEFAULT 'Y' COMMENT '사용유무',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  PRIMARY KEY (`com_cd`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- SITE 관리
CREATE TABLE tb_site (
  `site_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '프로젝트코드',
  `site` text(1000) NOT NULL COMMENT '프로젝트',
  `resion` text(1000) NOT NULL COMMENT '지역',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  PRIMARY KEY (`site_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

-- WORKING DAY 

CREATE TABLE tb_work (
  `work_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '등록번호',
  `gubun_cd` int NOT NULL COMMENT '구분코드',
  `gubun_a` text(1000) NOT NULL COMMENT '대구분',
  `gubun_b` text(1000) NOT NULL COMMENT '구분',
  `gubun_desc` text NOT NULL COMMENT '구분 설명',
  `vbg_cre_user_no` int(11) NOT NULL COMMENT '최초등록자',
  `vbg_cre_dtm` datetime DEFAULT NULL COMMENT '최초등록일자',
  `fin_cre_user_no` int(11) NOT NULL COMMENT '최종수정자',
  `fin_cre_dtm` datetime DEFAULT NULL COMMENT '최종수정일자',
  PRIMARY KEY (`work_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;



