package com.ysj.practice.member;

import org.apache.ibatis.annotations.Mapper;

//MyBatis에서 이 인터페이스가 SQL Mapper라는 것을 스프링에게 알려주는 어노테이션
//즉, 이 인터페이스의 메서드가 XML에 정의된 SQL과 연결된다는 뜻
@Mapper
public interface MemberMapper {

	// userId 기준으로 회원 삭제
	int deleteMemberById(String userId);

	// 회원 정보 수정
	int updateMember(MemberDTO memberDTO);

	// 리턴타입 메서드명(파라매터)
	// 회원 정보 조회 후 MemberDTO에 저장하여 반화
	MemberDTO loginMember(MemberDTO memberDTO);

	// 회원 정보를 DB에 저장 (INSERT)
	int createMember(MemberDTO member);

	MemberDTO getMemberById(String userId);

}
