package com.example.boardProject.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BoardDTO {
	private int idx;

	@NotBlank(message = "제목은 필수 입력입니다.")
	@Size(max = 100, message = "제목은 100자 이내여야 합니다.")
	private String title;

	@NotBlank(message = "내용은 필수 입력입니다.")
	private String content;

	@NotBlank(message = "작성자는 필수 입력입니다.")
	@Size(max = 30, message = "작성자 이름은 30자 이내여야 합니다.")
	private String writer;

	private String createdAt;
//	private Timestamp createdAt;
//
//	public Timestamp getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Timestamp createdAt) {
//		this.createdAt = createdAt;
//	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", createdAt=" + createdAt + "]";
	}

}
