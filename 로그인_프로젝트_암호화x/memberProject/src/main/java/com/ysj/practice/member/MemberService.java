package com.ysj.practice.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//비즈니스 로직을 처리하는 서비스 클래스임을 나타내는 어노테이션
//Spring이 이 클래스를 자동으로 Bean으로 등록하고 관리하게 함
@Service
public class MemberService {

	// 회원 관련 DB 작업을 처리할 Mapper 인터페이스
	// MyBatis의 SQL Mapper를 사용하기 위해 의존성 주입받음
	private final MemberMapper memberMapper;

	// 생성자 주입을 통해 MemberMapper를 전달받아 사용
	// 즉, MemberService는 내부적으로 MemberMapper를 호출해서 DB 작업을 수행함
	@Autowired
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	// 회원가입 메서드: 외부(Controller)에서 호출되며, 내부적으로 Mapper를 이용해 DB에 저장
	public boolean createMember(MemberDTO memberDTO) {
		try {
			// 삽입 결과를 받는 변수 생성
			int insertResult = memberMapper.createMember(memberDTO);
			return insertResult > 0; // 결과 반영이 되면 true 반영이 안돼면 false
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 로그인 처리
	public MemberDTO loginMember(MemberDTO memberDTO) {
		// Mapper에 DTO를 전달하여 DB 조회
		return memberMapper.loginMember(memberDTO);
	}

	// 회원정보 수정 메서드: Controller에서 호출되고, Mapper를 통해 DB를 업데이트함
	public boolean updateMember(MemberDTO memberDTO) {
		try {
			// 수정 결과를 받는 변수 생성
			int updateResult = memberMapper.updateMember(memberDTO);

			return updateResult > 0; // 수정된 행이 있으면 true, 없으면 false
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 콘솔에 출력
			return false;
		}
	}

	// 회원 삭제 메서드
	public boolean deleteMember(String userId) {
		try {
			int deleteResult = memberMapper.deleteMemberById(userId);
			return deleteResult > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
