
-- 회원가입용 데이터베이스
-- 데이터베이스 초기화
DROP DATABASE IF EXISTS practice;
CREATE DATABASE IF NOT EXISTS practice;

use practice;

drop table if exists member;

-- 사용자 테이블
CREATE TABLE IF NOT EXISTS practice.member (
    idx INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 ID',
    user_id VARCHAR(50) NOT NULL UNIQUE COMMENT '사용자 계정명',
    user_pw VARCHAR(255) NOT NULL COMMENT '비밀번호 (암호화 저장)',
    user_name VARCHAR(100) NOT NULL COMMENT '사용자 이름',
    user_email VARCHAR(100) NOT NULL COMMENT '이메일 주소',
    user_role INT NOT NULL DEFAULT 0 COMMENT '사용자 역할 (0: 일반, 1: 관리자)',
    created_at DATE DEFAULT (CURRENT_DATE) COMMENT '가입일자'
);
