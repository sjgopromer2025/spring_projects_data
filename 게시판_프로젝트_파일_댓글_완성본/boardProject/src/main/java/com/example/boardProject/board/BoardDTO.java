package com.example.boardProject.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {

	// 단일 파일
	private MultipartFile imageFile;

	// 다중 파일
	private List<MultipartFile> imageFiles;

	public List<MultipartFile> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<MultipartFile> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	private int idx;
	private String title;
	private String content;
	private String writer;
	private String createdAt;

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
		return "BoardDTO [imageFile=" + imageFile + ", title=" + title + ", content=" + content + "]";
	}

//	@Override
//	public String toString() {
//		return "BoardDTO [imageFile=" + imageFile + ", imageFiles=" + imageFiles + ", idx=" + idx + ", title="
//				+ title + ", content=" + content + ", writer=" + writer + ", createdAt=" + createdAt + "]";
//	}

//	private Timestamp createdAt;
//
//	public Timestamp getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Timestamp createdAt) {
//		this.createdAt = createdAt;
//	}
}
