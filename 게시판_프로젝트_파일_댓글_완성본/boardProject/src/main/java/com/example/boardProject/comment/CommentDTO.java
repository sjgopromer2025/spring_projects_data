package com.example.boardProject.comment;

public class CommentDTO {
	private int idx; // 댓글 고유 ID
	private int boardIdx; // 해당 댓글이 속한 게시글 ID
	private String userName; // 댓글 작성자 이름
	private String content; // 댓글 내용
	private String createdAt; // 작성일

	public int getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "CommentDto [idx=" + idx + ", boardIdx=" + boardIdx + ", userName=" + userName + ", content=" + content
				+ ", createdAt=" + createdAt + "]";
	}

}
