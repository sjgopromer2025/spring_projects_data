package com.example.boardProject.board.dto;

public class BoardMediaDTO {

	private int idx; // 미디어 고유 ID
	private int boardIdx; // 연결된 게시글 ID (board 테이블 참조)
	private String originalName; // 사용자가 업로드한 원본 파일명
	private String mediaPath; // 서버에 저장된 실제 파일 경로
	private String mediaType; // 미디어 유형 (예: image, video 등)

	// 기본 생성자
	public BoardMediaDTO() {
	}

	// 전체 필드 생성자
	public BoardMediaDTO(int idx, int boardIdx, String originalName, String mediaPath, String mediaType) {
		this.boardIdx = boardIdx;
		this.originalName = originalName;
		this.mediaPath = mediaPath;
		this.mediaType = mediaType;
	}

	// Getter & Setter
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return "BoardMediaDTO{" + "idx=" + idx + ", boardIdx=" + boardIdx + ", originalName='" + originalName + '\''
				+ ", mediaPath='" + mediaPath + '\'' + ", mediaType='" + mediaType + '\'' + '}';
	}
}
