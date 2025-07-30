package com.example.boardProject.board;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.boardProject.board.dto.BoardDTO;

@Service
public class BoardService {
	private final BoardMapper boardMapper;

	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

//	List<BoardDTO> findAll();

	// 게시글 전체 조회
	public List<BoardDTO> findAll() {

		return boardMapper.findAll();
	}

	// 게시글 정보 저장
	public boolean createBoard(BoardDTO boardDTO) {
		try {
			// 데이터 처리를 하는 코드를 ~~~~
			int insertReuslt = boardMapper.createBoard(boardDTO);

			return insertReuslt > 0; // true 혹은 false
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	BoardDTO findBoardById(int idx);
	// 단일 게시글 조회
	public BoardDTO findBoardById(int idx) {
		try {
			BoardDTO board = boardMapper.findBoardById(idx);

			return board; // null 혹은 게시글 정보
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//	int updateBoard(BoardDTO boardDTO);
	// 게시글 수정
	public boolean updateBoard(BoardDTO boardDTO) {
		try {
			int updateResult = boardMapper.updateBoard(boardDTO);
			return updateResult > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	int deleteBoard(int idx);
	// 게시글 삭제
	public boolean deleteBoard(int idx) {
		try {
			int deleteResult = boardMapper.deleteBoard(idx);
			return deleteResult > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getTotalCount() {
		try {
			return boardMapper.getTotalCount();
		} catch (Exception e) {
			// 예외 발생 시 로그 출력하고 0 리턴
			System.err.println("[ERROR] 게시글 총 개수 조회 실패: " + e.getMessage());
			return 0;
		}
	}

	public List<BoardDTO> findPage(int page, int size) {
		// 기본값 처리
		if (size <= 0) {
			size = 10;
		}
		if (page <= 0) {
			page = 1;
		}

		// 전체 게시글 수 조회
		int totalCount = getTotalCount();
		int totalPages = (int) Math.ceil((double) totalCount / size);

		// 요청한 페이지가 최대 페이지보다 크면 마지막 페이지로 조정
		if (totalPages > 0 && page > totalPages) {
			page = totalPages;
		}

		int offset = (page - 1) * size;

		try {
			return boardMapper.findPage(size, offset);
		} catch (Exception e) {
			// 예외 발생 시 로그 출력하고 빈 리스트 반환
			System.err.println("[ERROR] 게시글 페이지 조회 실패: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
