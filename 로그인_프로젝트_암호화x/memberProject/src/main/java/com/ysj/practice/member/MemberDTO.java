package com.ysj.practice.member;

public class MemberDTO {
	private int idx; // 사용자 고유 ID
	private String userId; // 사용자 계정명
	private String userPw; // 비밀번호 (암호화 저장)
	private String userName; // 사용자 이름
	private String userEmail; // 이메일 주소
	private int userRole; // 사용자 역할 일반 사용자 : 0 , 관리자 : 1 , 추가적인 것들은 사용자 정의
	private java.sql.Date createdAt; // 가입일자;

	// 기본 생성자
	public MemberDTO() {
	}

	@Override
	public String toString() {
		return "MemberDTO [idx=" + idx + ", userId=" + userId + ", userPw=" + userPw + ", userEmail=" + userEmail
				+ ", createdAt=" + createdAt + ", userName=" + userName + ", userRole=" + userRole + "]";
	}

	// getter & setter (롬복 사용 가능)

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public java.sql.Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.sql.Date createdAt) {
		this.createdAt = createdAt;
	}

}