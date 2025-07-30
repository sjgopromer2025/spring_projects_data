package com.example.boardProject.board.dto;

// 페이지네이션 정보를 담는 DTO 클래스 
// 현재 페이지, 전체 페이지 수, 시작/끝 페이지, 이전/다음 페이지 존재 여부를 관리
public class PaginationDTO {

	private int currentPage; // 현재 페이지 번호
	private int totalPages; // 전체 페이지 개수
	private int startPage; // 현재 페이지 블록의 시작 페이지 번호
	private int endPage; // 현재 페이지 블록의 마지막 페이지 번호
	private boolean hasPrev; // 이전 페이지 블록 존재 여부
	private boolean hasNext; // 다음 페이지 블록 존재 여부

	// 생성자
	// 생략..
	// getter & setter ~
	// 생략..
	// toString
	// 생략..

	// 페이지네이션 계산 로직
	// currentPage 현재 페이지 번호
	// totalCount 전체 데이터 개수
	// size 한 페이지당 보여줄 데이터 개수
	// blockLimit 한 번에 보여줄 페이지 번호 개수 (예: 1~10, 11~20)
	public PaginationDTO(int currentPage, int totalCount, int size, int blockLimit) {
		this.currentPage = currentPage;

		// 전체 페이지 수 계산 (소수점이 있으면 올림 처리)
		// 예: 데이터 25개, size 10 → 3페이지 필요
		this.totalPages = (int) Math.ceil((double) totalCount / size);

		// 현재 페이지 블록의 시작 페이지 계산
		// 예: currentPage=7, blockLimit=10 → startPage=1
		// currentPage=15, blockLimit=10 → startPage=11
		this.startPage = ((currentPage - 1) / blockLimit) * blockLimit + 1;

		// 현재 페이지 블록의 끝 페이지 계산
		// 블록의 마지막 페이지가 전체 페이지 수보다 클 수 없으므로 min 사용
		this.endPage = Math.min(startPage + blockLimit - 1, totalPages);

		// 이전 페이지 블록이 있는지 여부
		this.hasPrev = startPage > 1;

		// 다음 페이지 블록이 있는지 여부
		this.hasNext = endPage < totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}

	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	@Override
	public String toString() {
		return "PaginationDTO [currentPage=" + currentPage + ", totalPages=" + totalPages + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", hasPrev=" + hasPrev + ", hasNext=" + hasNext + "]";
	}

}
